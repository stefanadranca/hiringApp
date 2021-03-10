

import java.util.ArrayList;
import java.util.List;

public class Application {

    // Singleton pattern
    private static Application application = null;
    public ArrayList<Company> companies;
    public ArrayList<User> users;

    // private - Singleton
    private Application() {
        companies = new ArrayList<>();
        users = new ArrayList<>();
    }

    // instantierea intarziata
    public static Application getApplication() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompany(String name) {
        for (Company comp : companies) {
            if (comp.name.equals(name))
                return comp;
        }
        return null;
    }

    public void add(Company company) {
        companies.add(company);
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean remove(Company company) {
        if (!companies.contains(company))
            return false;
        companies.remove(company);
        return true;
    }

    public boolean remove(User user) {
        if (!users.contains(user))
            return false;
        users.remove(user);
        return true;
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> availableJobs = new ArrayList<>();
        Company company;
        for (String companyName : companies) {
            company = getCompany(companyName);
            availableJobs.addAll(company.getJobs());
        }
        return availableJobs;
    }

    public ArrayList<String> getUsersNames() {
        ArrayList<String> names = new ArrayList<>();
        for (User user : users) {
            names.add(user.getName());
        }
        return names;
    }

    public ArrayList<String> getCompaniesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Company c : companies)
            names.add(c.name);
        return names;
    }

}
