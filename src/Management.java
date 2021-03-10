import java.util.ArrayList;

public class Management extends Department {

    Management() {
        super();
    }
    Management(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (Employee employee : this.getEmployees()) {
            total += employee.getSalary() + 0.16 * employee.getSalary();
        }
        return total;
    }
}
