
import java.util.ArrayList;

public class Job {

    public String jobName;
    public Company company;
    public Boolean flag;
    Constraint graduationConstraint;
    Constraint experienceConstraint;
    Constraint GPAConstraint;
    private ArrayList<User> candidates;
    private int numberNeeded;
    private Double salary;

    Job(String jobName, Company company, int number, Double salary) {
        this.jobName = jobName;
        this.company = company;
        this.flag = true;
        this.numberNeeded = number;
        this.salary = salary;
        candidates = new ArrayList<>();
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setNumberNeeded(int nr) {
        numberNeeded = nr;
    }

    public Double getSalary() {
        return salary;
    }

    public void setGraduationConstraint(Constraint graduationConstraint) {
        this.graduationConstraint = graduationConstraint;
    }

    public void setExperienceConstraint(Constraint experienceConstraint) {
        this.experienceConstraint = experienceConstraint;
    }

    public int getNumberNeeded() {
        return numberNeeded;
    }

    public void setGPAConstraint(Constraint GPAConstraint) {
        this.GPAConstraint = GPAConstraint;
    }

    public void apply(User user) {
        if (this.getFlag()) {
            Recruiter recruiter = this.company.getRecruiter(user);
            double score = recruiter.evaluate(this, user);
            candidates.add(user);
        }
    }

    public boolean meetsRequirments(User user){
        if (graduationConstraint != null) {
            if (user.getGraduationYear() != null) {
                if (user.getGraduationYear() < graduationConstraint.inferiorLimit || user.getGraduationYear() > graduationConstraint.superiorLimit)
                    return false;
            }
        }
        if (experienceConstraint != null) {
            if (user.getResume().getYearsOfExperience() < experienceConstraint.inferiorLimit || user.getResume().getYearsOfExperience() > experienceConstraint.superiorLimit)
                return false;
        }
        if (GPAConstraint != null) {
            if (user.meanGPA() < GPAConstraint.inferiorLimit || user.meanGPA() > GPAConstraint.superiorLimit)
                return false;
        }
        return true;
    }
}
