
import java.util.ArrayList;

public class IT extends Department {

    IT() {
        super();
    }
    IT(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }
    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        for (Employee employee : this.getEmployees()) {
           total += employee.getSalary();
        }
        return total;
    }
}
