package onlineShop.storage;

import onlineShop.models.Order;
import onlineShop.models.Product;
import onlineShop.models.User;
import onlineShop.models.enums.OrderStatus;
import onlineShop.util.StorageSerializeUtil;

import java.util.LinkedList;
import java.util.List;

public class OrderStorage {
    private List<Order> orders = new LinkedList<>();

    public void add(Order order) {
        orders.add(order);
        StorageSerializeUtil.serializeOrderStorage(this);
    }

    public void printOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public Order getById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public void printByUser(User currentUser) {
        for (Order order : orders) {
            if (order.getUser().equals(currentUser)) {
                System.out.println(order);
            }
        }
    }
}
