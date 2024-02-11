package employee;

public class EmployeeStorage {

    Employee[] employees = new Employee[10];

    private int size = 0;

    public void add(Employee employee) {
        if (size == employees.length) {
            extend();
        }
        employees[size++] = employee;
    }


    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }

    }

    public Employee getById(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employeeId.equals(employees[i].getEmployeeID())) {
              return employees[i];
            }
        }
         return null;
    }

    public void searchByCompany(String keyword) {
        for (int i = 0; i < size; i++) {
            if (keyword.contains(employees[i].getCompany())) {
                System.out.println(employees[i].getName() + " " + employees[i].getSurname() + " " + employees[i].getEmployeeID() + " " + employees[i].getCompany()
                        + " " + employees[i].getPosition() + " " + employees[i].getSalary());
            }
        }
    }

    public void deleteByIndex(String employeeId) {
        int indexByID = getIndexById(employeeId);
        if (indexByID == -1){
            System.out.println("Employee doesn't exist");
            return;
        }
        for (int i = indexByID + 1; i < size; i++) {
            employees[i - 1] = employees[i];
        }
        size--;
    }

    private int getIndexById(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeID().equals(employeeId)) {
                return i;
            }
        }
        return -1;
    }

    private void extend() {
        if (size == employees.length) {
            Employee[] tmp = new Employee[employees.length + 10];
            System.arraycopy(employees, 0, tmp, 0, employees.length);
            employees = tmp;
        }
    }
}
