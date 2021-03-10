
import java.util.ArrayList;

public abstract class Department {
    ArrayList<Employee> employees; // angajatii
    ArrayList<Job> jobs; // joburile disponibile din departament

    Department() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }
    Department(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        this.employees = employees;
        this.jobs = jobs;
    }

    public abstract double getTotalSalaryBudget();
    public ArrayList<Job> getJobs() {
        return jobs;
    }
    public void add(Employee employee) {
        employees.add(employee);
    }
    public void remove(Employee employee) {
        employees.remove(employee);
    }
    public void add(Job job) {
        jobs.add(job);
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}

class DepartmentFactory {
    public static Department factory (String type) {
        switch (type) {
            case "IT":
                return new IT();
            case "Finance":
                return new Finance();
            case "Management":
                return new Management();
            case "Marketing":
                return new Marketing();
        } throw new IllegalArgumentException("Departamentul " + type + "nu poate exista");
    }
    public static Department factory (String type, ArrayList<Employee> employees, ArrayList<Job> jobs) {
        switch (type) {
            case "IT":
                return new IT(employees, jobs);
            case "Finance":
                return new Finance(employees, jobs);
            case "Management":
                return new Management(employees, jobs);
            case "Marketing":
                return new Marketing(employees, jobs);
        } throw new IllegalArgumentException("Departamentul " + type + "nu poate exista");
    }
}