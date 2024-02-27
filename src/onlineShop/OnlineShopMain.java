package onlineShop;

import onlineShop.commands.Command;
import onlineShop.models.Order;
import onlineShop.models.Product;
import onlineShop.models.User;
import onlineShop.models.enums.OrderStatus;
import onlineShop.models.enums.PaymentMethod;
import onlineShop.models.enums.ProductType;
import onlineShop.models.enums.UserType;
import onlineShop.storage.OrderStorage;
import onlineShop.storage.ProductStorage;
import onlineShop.storage.UserStorage;
import onlineShop.util.IdGenerator;
import onlineShop.util.StorageSerializeUtil;

import java.util.Date;
import java.util.Scanner;

public class OnlineShopMain implements Command {
    private static OrderStorage orderStorage = StorageSerializeUtil.deserializeOrderStorage();
    private static ProductStorage productStorage = StorageSerializeUtil.deserializeProductStorage();
    private static UserStorage userStorage = StorageSerializeUtil.deserializeUserStorage();
    private static Scanner scanner = new Scanner(System.in);
    public static User currentUser = null;

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Command.printCommandsMain();
            String command = scanner.nextLine();
            switch (command) {

                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    loginUser();
                    break;
                case REGISTER:
                    registerUser();
                    break;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }


    private static void buyProduct() {
        productStorage.printProducts();
        System.out.println("Please input product id, qty,paymentMethod(CARD,CASH,PAYPAL)");
        String orderDataStr = scanner.nextLine();
        String[] orderData = orderDataStr.split(",");
        Product product = productStorage.getById(orderData[0]);
        PaymentMethod paymentMethod = PaymentMethod.valueOf(orderData[2]);
        if (product == null) {
            System.out.println("Wrong product Id");
            return;
        }
        int qty = Integer.parseInt(orderData[1]);
        if (product.getStockQty() < qty) {
            System.out.println("Wrong qty");
            return;
        }
        double price = qty * product.getPrice();
        System.out.println("You want to buy" + product.getName() + " qty " + qty + " " + " price "+ price + "paymentMethod " + paymentMethod + "\n Are you sure(YES/NO)");
        String answer = scanner.nextLine();
        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println("Order canceled");
            return;
        }
        Order order = new Order(IdGenerator.generateId(), currentUser, product, new Date(), product.getPrice(), price, OrderStatus.NEW);
        orderStorage.add(order);

    }

    private static void loginUser() {
        System.out.println("Please input email,password");
        String loginDataStr = scanner.nextLine();
        String[] loginData = loginDataStr.split(",");
        User user = userStorage.getByEmail(loginData[0]);
        if (user == null || user.getPassword().equals(loginData[1])) {
            System.out.println("Email or password is incorrect!");
            return;
        }
        currentUser = user;
        if (user.getUserType() == UserType.ADMIN) {
            adminCommands();
        } else if (user.getUserType() == UserType.USER) {
            userCommands();
        }
    }

    private static void userCommands() {
        boolean isRun = true;
        while (isRun) {
            Command.printCommandsAdmin();
            String command = scanner.nextLine();
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    currentUser = null;
                    break;
                case PRINT_ALL_PRODUCTS:
                    productStorage.printProducts();
                    break;
                case BUY_PRODUCT:
                    buyProduct();
                    break;
                case PRINT_MY_ORDERS:
                    orderStorage.printByUser(currentUser);
                    break;
                case CANCEL_ORDER_BY_ID:
                    cancelOrderById();
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    private static void cancelOrderById() {
        orderStorage.printByUser(currentUser);
        System.out.println("Please input order Id");
        String orderId = scanner.nextLine();
        Order order = orderStorage.getById(orderId);
        if (order == null || !order.getUser().equals(currentUser)) {
            System.out.println("Wrong order id");
            return;
        }
        if (order.getOrderStatus() != OrderStatus.NEW) {
            System.out.println("Order can not be canceled");
            return;
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        System.out.println("Order canceled");
        StorageSerializeUtil.serializeOrderStorage(orderStorage);
    }

    private static void adminCommands() {
        boolean isRun = true;
        while (isRun) {
            Command.printCommandsAdmin();
            String command = scanner.nextLine();
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    currentUser = null;
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case REMOVE_PRODUCT_BY_ID:
                    removeProductById();
                    break;
                case PRINT_USERS:
                    userStorage.printUserByType(UserType.USER);
                    break;
                case PRINT_ORDERS:
                    orderStorage.printOrders();
                    break;
                case CHANGE_ORDER_STATUS_BY_ID:
                    changeOrderStatusById();
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Please input name,description, stockQty,price,productType('ELECTRONICS,CLOTHING,BOOK')");
        String productDataStr = scanner.nextLine();
        String[] productData = productDataStr.split(",");
        try {
            Product product = new Product();
            product.setId(IdGenerator.generateId());
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setStockQty(Integer.parseInt(productData[2]));
            product.setPrice(Double.parseDouble(productData[3]));
            product.setType(ProductType.valueOf(productData[4]));
            productStorage.addProduct(product);
            System.out.println("Product added");
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data" + e.getMessage());
        }

    }

    private static void changeOrderStatusById() {
        orderStorage.printOrders();
        System.out.println("Please input order id,new status(NEW,DELIVERED,CANCELED)");
        String orderData = scanner.nextLine();
        String[] orderDataArr = orderData.split(",");
        Order order = orderStorage.getById(orderDataArr[0]);
        if (order == null) {
            System.out.println("Order doesn't exist");
            return;
        }
        OrderStatus newStatus = OrderStatus.valueOf(orderDataArr[1]);
        if (order.getOrderStatus() == OrderStatus.NEW && newStatus == OrderStatus.DELIVERED) {
            if (order.getProduct().getStockQty() < order.getQty()) {
                System.out.println("You do not have enough product qty");
                return;
            }
            order.getProduct().setStockQty(order.getProduct().getStockQty() - order.getQty());
            order.setOrderStatus(newStatus);
            System.out.println("Order status has changed");
            StorageSerializeUtil.serializeOrderStorage(orderStorage);
        }

    }

    private static void removeProductById() {
        productStorage.printProducts();
        System.out.println("Please input product id");
        String productId = scanner.nextLine();
        Product product = productStorage.getById(productId);
        if (product == null) {
            System.out.println("Wrong product id");
            return;
        }
        productStorage.delete(productId);
        StorageSerializeUtil.serializeProduct(productStorage);

    }


    private static void registerUser() {
        System.out.println("Please input user data: name,email, password, userType ");
        String userRegister = scanner.nextLine();
        String[] userRegisterSplit = userRegister.split(",");
        User user = userStorage.getByEmail(userRegisterSplit[1]);
        if (user != null) {
            System.out.println("User already exist");
            return;
        }
        try {
            user = new User(IdGenerator.generateId(), userRegisterSplit[0], userRegisterSplit[1], userRegisterSplit[2], UserType.valueOf(userRegisterSplit[3].toUpperCase()));
            userStorage.registerUser(user);
            System.out.println("User registered");
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid user type");
        }
    }

}
