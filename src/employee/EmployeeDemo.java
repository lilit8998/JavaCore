package employee;

import java.util.Scanner;

public class EmployeeDemo {
    static private EmployeeStorage employeeStorage = new EmployeeStorage();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addBook();
                    break;
                case "2":
                    printAllEmployee();
                    break;
                case "3":
                    searchEmployeeById();
                    break;
                case "4":
                    printEmployeeByCompany();
                    break;
                case "5":
                    deleteEmployee();
                    break;
                case "6":
                    changeEmployee();
                    break;
                default:
                    System.out.println("Invalid command. Please try again!");
            }
        }
    }

    private static void changeEmployee() {
        System.out.println("Please input employee id");
        String employeeId = scanner.nextLine();
        Employee employeeStorageFr = employeeStorage.getById(employeeId);
        if (employeeStorageFr == null){
            System.out.println("employee with " + employeeId + " doesn't exist");
            return;
        }
        System.out.println("Please input new data");
        String name = scanner.nextLine();
        String surname = scanner.nextLine();
        double salary = Double.parseDouble(scanner.nextLine());
        String company = scanner.nextLine();
        String position = scanner.nextLine();

        employeeStorageFr.setName(name);
        employeeStorageFr.setSurname(surname);
        employeeStorageFr.setSalary(salary);
        employeeStorageFr.setCompany(company);
        employeeStorageFr.setPosition(position);
        System.out.println("employee updated");
    }


    private static void deleteEmployee() {
        System.out.println("Input index employee");
        String index = scanner.nextLine();
        employeeStorage.deleteByIndex(index);
    }

    private static void printAllEmployee() {
        System.out.println("--");
        employeeStorage.print();
        System.out.println("----------");
    }

    private static void printEmployeeByCompany() {
        String companySearch = scanner.nextLine();
        employeeStorage.searchByCompany(companySearch);
    }

    private static void searchEmployeeById() {
        String keyword = scanner.nextLine();
        Employee byId = employeeStorage.getById(keyword);
        System.out.println(byId);
    }

    private static void addBook() {
        System.out.println("Please input employee Name, Surname, employeeId, salary,company,position");
        String name = scanner.nextLine();
        String surname = scanner.nextLine();
        String employeeId = scanner.nextLine();
        double salary = Double.parseDouble(scanner.nextLine());
        String company = scanner.nextLine();
        String position = scanner.nextLine();
        Employee employee = new Employee(name, surname, employeeId, salary, company, position);
        employeeStorage.add(employee);
        System.out.println("Employee added");
    }

    private static void printCommands() {
        System.out.println("Please input 0 for EXIT");
        System.out.println("Please input 1 for ADD EMPLOYEE");
        System.out.println("Please input 2 for PRINT ALL EMPLOYEE");
        System.out.println("Please input 3 for SEARCH BY EMPLOYEE ID");
        System.out.println("Please input 4 for SEARCH BY COMPANY");
        System.out.println("Please input 5 for DELETE EMPLOYEE");
        System.out.println("Please input 6 for CHANGE EMPLOYEE");
    }
}
