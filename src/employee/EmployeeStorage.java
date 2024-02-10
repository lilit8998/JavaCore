package employee;

public class EmployeeStorage {

    Employee[] employees = new Employee[10];

    private int size = 0;

    public void add(Employee employee){
        if (size == employees.length){
            extend();
        }
        employees[size++] = employee;
    }

    public void print(){
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i].getName() + " " + employees[i].getSurname() + " " + employees[i].getEmployeeID() + " " + employees[i].getCompany()
                    + " " + employees[i].getPosition() + " " + employees[i].getSalary());
        }
    }

    public void searchById(String keyword){
        for (int i = 0; i < size; i++) {
            if (keyword.contains(employees[i].getEmployeeID())){
                System.out.println(employees[i].getName()  + " " + employees[i].getSurname()  + " " + employees[i].getEmployeeID()  + " " + employees[i].getCompany()
                        + " " + employees[i].getPosition()  + " " + employees[i].getSalary());
            }
        }
    }
    public void searchByCompany(String keyword){
        for (int i = 0; i < size; i++) {
            if (keyword.contains(employees[i].getCompany())){
                System.out.println(employees[i].getName()  + " " + employees[i].getSurname()  + " " + employees[i].getEmployeeID()  + " " + employees[i].getCompany()
                        + " " + employees[i].getPosition()  + " " + employees[i].getSalary());
            }
        }
    }

    private void extend() {
        if (size == employees.length){
            Employee[] tmp = new Employee[employees.length + 10];
            System.arraycopy(employees,0,tmp,0,employees.length);
            employees = tmp;
        }
    }
}
