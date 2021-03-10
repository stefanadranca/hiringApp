
import java.util.ArrayList;
import java.util.Iterator;

public class Company implements Subject{
    public String name;
    public Manager manager;
    private ArrayList<Department> departments;
    private ArrayList<Recruiter> recruiters;
    private ArrayList<User> observers;

    Company(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
        departments = new ArrayList<>();
        recruiters = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void add(Department department) {
        departments.add(department);
    }

    public void add(Recruiter recruiter) {
        recruiters.add(recruiter);
        Iterator<Department> it = departments.iterator();
        while(it.hasNext()) {
            Department dep = it.next();
            if (dep instanceof IT) {
                add(recruiter, dep);
                break;
            }
        }
    }

    public void add(Employee employee, Department department) {
        if (departments.contains(department)) {
            for (Department dep : departments) {
                if (dep.equals(department)) {
                    dep.add(employee);
                    break;
                }
            }
        }
    }

    public void remove(Employee employee) {
        for (Department dep : this.departments) {
            if (dep.getEmployees().contains(employee))
                dep.getEmployees().remove(employee);
        }
    }

    public void remove(Department department) {

        if (department instanceof IT)
            recruiters = null;
        if (department.getEmployees().contains(this.manager))
            manager = null;
        if (departments.contains(department))
            departments.remove(department);
    }

    public void remove(Recruiter recruiter) {
        if (recruiters.contains(recruiter))
            recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination) {
        for (Employee empl : source.getEmployees()) {
            destination.add(empl);
        }
        remove(source);
    }

    public void move(Employee employee, Department newDepartment) {
        for (Department dep : departments) {
            if (dep.getEmployees().contains(employee)) {
                dep.remove(employee);
                newDepartment.add(employee);
            }
        }
    }

    public boolean contains(Department department) {
        return departments.contains(department);
    }

    public boolean contains(Employee employee) {
        for (Department dep : departments) {
            if (dep.getEmployees().contains(employee)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }

    // returneaza recruiterul din companie potrivit pentru user
    public Recruiter getRecruiter(User user) {
        int degree = 0;
        double score = 5;
        Recruiter chosen = null;
        for (Recruiter recruiter : recruiters) {
            if (user.getDegreeInFriendship(recruiter) == degree) {
                if (recruiter.getRating() >= score) {
                    score = recruiter.getRating();
                    chosen = recruiter;
                }
            }
            if (user.getDegreeInFriendship(recruiter) > degree) {
                degree = user.getDegreeInFriendship(recruiter);
                score = recruiter.getRating();
                chosen = recruiter;
            }
        }
        return chosen;
    }

    public ArrayList<Job> getJobs() {
         ArrayList<Job> availableJobs = new ArrayList<>();
         for (Department dep : departments) {
             for (Job job : dep.getJobs()) {
                 if (job.flag)
                     availableJobs.add(job);
             }
         }
         return availableJobs;
    }

    public ArrayList<Recruiter> getRecruiters() {
        return recruiters;
    }

    @Override
    public void addObserver(User user) {
        if (!observers.contains(user))
            observers.add(user);
    }

    @Override
    public void removeObserver(User c) {
        if (observers.contains(c))
            observers.remove(c);
    }

    @Override
    public void notifyAllObservers(String notification) {
        for (User user : observers) {
            user.update(notification);
        }
    }

    // returneaza departamentul in care se afla oferta de job
    public Department getDepartment(Job job) {
        Iterator<Department> it = departments.iterator();
        Department dep = null;
        while (it.hasNext()) {
            dep = it.next();
            if(dep.getJobs().contains(job))
                return dep;
        }
        return dep;
    }

    // returneaza departamentul din companie dupe numele acestuia
    public Department getDepartment(String name) {
        for (Department dep : departments) {
            switch (name) {
                case "IT":
                    if (dep instanceof IT) {
                        return dep;
                    } else {
                        break;
                    }
                case "Finance":
                    if (dep instanceof Finance) {
                        return dep;
                    } else {
                        break;
                    }
                case "Management":
                    if (dep instanceof Management) {
                        return dep;
                    } else {
                        break;
                    }
                case "Marketing":
                    if (dep instanceof Marketing) {
                        return dep;
                    } else {
                        break;
                    }
            }
        }
        return null;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }
}
