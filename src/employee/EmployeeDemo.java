package employee;

import java.util.Scanner;

public class EmployeeDemo {
    public static void main(String[] args) {
        EmployeeStorage employeeStorage = new EmployeeStorage();
        Scanner scanner = new Scanner(System.in);
        String[] scArr = scanner.delimiter().split(" ");
        boolean isRun = true;
        while (isRun) {
            System.out.println("Please input 0 for EXIT");
            System.out.println("Please input 1 for ADD EMPLOYEE");
            System.out.println("Please input 2 for PRINT ALL EMPLOYEE");
            System.out.println("Please input 3 for SEARCH BY EMPLOYEE ID");
            System.out.println("Please input 4 for SEARCH BY COMPANY");
            String command = scanner.nextLine();
            switch (command){
                case "0":
                    isRun = false;
                    break;
                case "1":
                    System.out.println("Please input employee information!");
                    System.out.println("Name, Surname, employeeId, salary,company,position");
                    String name = scanner.nextLine();
                    String surname = scanner.nextLine();
                    String employeeId = scanner.nextLine();
                    double salary = Double.parseDouble(scanner.nextLine());
                    String company = scanner.nextLine();
                    String position = scanner.nextLine();
                    Employee employee = new Employee(name,surname,employeeId,salary,company,position);
                    employeeStorage.add(employee);
                    System.out.println("Employee added");
                    break;
                case "2":
                    System.out.println("--");
                    employeeStorage.print();
                    System.out.println("----------");
                    break;
                case "3":
                   String keyword =  scanner.nextLine();
                   employeeStorage.searchById(keyword);
                   break;
                case "4":
                    String companySearch = scanner.nextLine();
                    employeeStorage.searchByCompany(companySearch);
                    break;
                default:
                    System.out.println("Invalid command. Please try again!");




            }
        }


    }
}
