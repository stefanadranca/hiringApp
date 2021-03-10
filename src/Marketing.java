import java.util.ArrayList;

public class Marketing extends Department {

    Marketing() {
        super();
    }
    Marketing(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (Employee employee : this.getEmployees()) {
            if (employee.getSalary() > 5000) {
                total += 1.1 * employee.getSalary();
            } else if (employee.getSalary() < 3000) {
                total += employee.getSalary();
            } else {
                total += 1.16 * employee.getSalary();
            }
        }
        return total;
    }
}
