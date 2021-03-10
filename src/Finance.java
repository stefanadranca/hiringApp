import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Finance extends Department {

    Finance() {
        super();
    }
    Finance(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget() {
        double total = 0;
        Period experience;
        for (Employee employee : this.getEmployees()) {
            experience = Period.between(employee.getResume().getExperience().first().getDataInceput(), LocalDate.now());
            if(experience.getYears() == 0) {
                total += 1.1 * employee.getSalary();
            } else {
                total += 1.6 * employee.getSalary();
            }
        }
        return total;
    }
}
