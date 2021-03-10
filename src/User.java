
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User extends Consumer implements Observer {
    ArrayList<String> companies;
    ArrayList<String> notifications;
    User() {
        companies = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    User(Resume resume) {
        this.resume = resume;
        this.acquaintances = new ArrayList<>();
    }

    public void addCompany(String company) {
        companies.add(company);
    }

    public Employee convert() {
        Employee empl = new Employee();
        empl.setAcquaintances(this.getAcquaintances());
        empl.setResume(this.getResume());
        return empl;
    }

    public Double getTotalScore() {
        LocalDate endOfExperience;
        LocalDate startOfExperience;
        Period difference;
        int yearsOfExperience, months;

        if (this.resume.getExperience().isEmpty()) {
            yearsOfExperience = 0;
        } else {
            startOfExperience = this.resume.getExperience().last().getDataInceput();
            //daca in momentul actual userul nu este angajat, data de final
            //va fi considerata data de final a ultimuilui job avut
            if (this.resume.getExperience().first().getDataSfarsit() != null) {
                endOfExperience = this.resume.getExperience().first().getDataSfarsit();
            } else {
                //daca este aangajat in prezent, se calculeaza experienta pana in prezent
                endOfExperience = LocalDate.now();
            }
            difference = Period.between(startOfExperience, endOfExperience);
            yearsOfExperience = difference.getYears();
            months = difference.getMonths();
            if (months >= 1)
                yearsOfExperience += 1;
        }
    return yearsOfExperience * 1.5 + this.meanGPA();
    }

    @Override
    public void update(String notification) {
        notifications.add(notification);
    }
}
