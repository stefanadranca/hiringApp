
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {

    // crearea unei instante de informatii despre angajat
    public Information returnInformation(JSONObject man) {

        // Information pentru Resume
        Information information = new Information();
        information.setName((String) man.get("name"));
        information.setEmail((String) man.get("email"));
        information.setPhone((String) man.get("phone"));
        information.setBirthday((String) man.get("date_of_birth"));
        information.setGenre((String) man.get("genre"));

        // hashmap-ul pentru limba-nivel
        HashMap<String, String> languages = new HashMap<>();
        JSONArray lang = (JSONArray) man.get("languages");
        ArrayList<String> langArr = new ArrayList<>();
        ArrayList<String> levelArr = new ArrayList<>();
        Iterator it1 = lang.iterator();
        while(it1.hasNext()) {
            langArr.add((String)it1.next());
        }
        JSONArray level = (JSONArray) man.get("languages_level");
        it1 = level.iterator();
        while(it1.hasNext()) {
            levelArr.add((String)it1.next());
        }
        for (int i = 0; i < lang.size(); i++) {
            information.addLanguage(langArr.get(i), levelArr.get(i));
        }
        return information;
    }

    // metoda care intoarce treeset-ul cu instante de education
    public TreeSet<Education> returnEducation(JSONObject man) throws InvalidDatesException {
        // treeset-ul de obiecte education
        TreeSet<Education> education = new TreeSet<>();
        JSONArray ed = (JSONArray) man.get("education");
        Iterator it1 = ed.iterator();
        while (it1.hasNext()) {
            Education ed1 = new Education();
            JSONObject ed2 = (JSONObject) it1.next();
            ed1.setNivel((String) ed2.get("level"));
            ed1.setInstitutie((String) ed2.get("name"));
            ed1.setDataInceput((String) ed2.get("start_date"));
            ed1.setDataSfarsit((String) ed2.get("end_date"));
            ed1.setMedie(((Number) ed2.get("grade")).doubleValue());
            education.add(ed1);
        }
        return education;
    }

    // metoda care adauga un angajat in departamentul corespunzator
    public void addEmployee(JSONObject man) throws InvalidDatesException, Resume.ResumeIncompleteException {

        Employee employee = new Employee();
        Information information = returnInformation(man);
        employee.setSalary((double) ((long) man.get("salary")));
        TreeSet<Education> education = returnEducation(man);

        // treeset-ul de obiecte experience
        TreeSet<Experience> experience = new TreeSet<>();
        JSONArray exp = (JSONArray) man.get("experience");
        Iterator it1 = exp.iterator();
        String companieString = null;
        String departmentString = null;
        Department finalDepartment = null;
        Company company = null;
        while (it1.hasNext()) {
            Experience exp1 = new Experience();
            JSONObject exp2 = (JSONObject) it1.next();
            companieString = (String) exp2.get("company");
            exp1.setCompanie(companieString);
            exp1.setPozitie((String) exp2.get("position"));
            departmentString = (String) exp2.get("department");
            exp1.setDataInceput((String) exp2.get("start_date"));
            exp1.setDataSfarsit((String) exp2.get("end_date"));
            experience.add(exp1);
            if (exp1.getDataSfarsit() == null) {
                company = Application.getApplication().getCompany(companieString);
                finalDepartment = company.getDepartment(departmentString);
            }
        }

        // crearea cv-ului
        Resume resume = new Resume.ResumeBuilder(information)
                .education(education).experience(experience).build();
        employee.setResume(resume);
        employee.setNameOfCompany(companieString);
        if (company != null) {
            // adaugarea angajatului in compania
            company.add(employee, finalDepartment);
        }
    }

    // adaugarea unui recruiter in companie, in departamentul de IT
    public void addRecruiter(JSONObject man) throws Resume.ResumeIncompleteException, InvalidDatesException {

        Recruiter recruiter = new Recruiter();
        Information information = returnInformation(man);
        recruiter.setSalary((double) ((long) man.get("salary")));
        TreeSet<Education> education = returnEducation(man);

        // treeset-ul de obiecte experience
        TreeSet<Experience> experience = new TreeSet<>();
        JSONArray exp = (JSONArray) man.get("experience");
        Iterator it1 = exp.iterator();
        String companieString = null;
        Company company = null;
        while (it1.hasNext()) {
            Experience exp1 = new Experience();
            JSONObject exp2 = (JSONObject) it1.next();
            companieString = (String) exp2.get("company");
            exp1.setCompanie(companieString);
            exp1.setPozitie((String) exp2.get("position"));
            exp1.setDataInceput((String) exp2.get("start_date"));
            exp1.setDataSfarsit((String) exp2.get("end_date"));
            experience.add(exp1);
            if (exp1.getDataSfarsit() == null) {
                company = Application.getApplication().getCompany(companieString);
            }
        }

        // crearea cv-ului
        Resume resume = new Resume.ResumeBuilder(information)
                .education(education).experience(experience).build();
        recruiter.setResume(resume);
        recruiter.setNameOfCompany(companieString);
        if (company != null) {
            // adaugarea angajatului in compania
            company.add(recruiter);
        }
    }

    // metoda care adauga userii in aplicatie
    public void addUser(JSONObject man) throws InvalidDatesException, Resume.ResumeIncompleteException {
        User user = new User();
        Information information = returnInformation(man);

        JSONArray interestedCompanies = (JSONArray) man.get("interested_companies");
        Iterator it1 = interestedCompanies.iterator();
        while(it1.hasNext()) {
            user.addCompany((String)it1.next());
        }
        TreeSet<Education> education = returnEducation(man);

        // treeset-ul de obiecte experience
        TreeSet<Experience> experience = new TreeSet<>();
        JSONArray exp = (JSONArray) man.get("experience");
        it1 = exp.iterator();
        String companieString = null;
        Company company = null;
        while (it1.hasNext()) {
            Experience exp1 = new Experience();
            JSONObject exp2 = (JSONObject) it1.next();
            companieString = (String) exp2.get("company");
            exp1.setCompanie(companieString);
            exp1.setPozitie((String) exp2.get("position"));
            exp1.setDataInceput((String) exp2.get("start_date"));
            exp1.setDataSfarsit((String) exp2.get("end_date"));
            experience.add(exp1);
        }

        // crearea cv-ului
        Resume resume = new Resume.ResumeBuilder(information)
                .education(education).experience(experience).build();
        user.setResume(resume);
        Application.getApplication().add(user);
    }

    // adaugarea unui manager
    public void addManager(JSONObject man) throws InvalidDatesException, Resume.ResumeIncompleteException {
        Manager manager = new Manager();
        Information information = returnInformation(man);

        manager.setSalary((double) ((long) man.get("salary")));
        TreeSet<Education> education = returnEducation(man);

        // treeset-ul de obiecte experience
        TreeSet<Experience> experience = new TreeSet<>();
        JSONArray exp = (JSONArray) man.get("experience");
        Iterator it1 = exp.iterator();
        String companieString = null;
        Company company = null;
        while (it1.hasNext()) {
            Experience exp1 = new Experience();
            JSONObject exp2 = (JSONObject) it1.next();
            companieString = (String) exp2.get("company");
            exp1.setCompanie(companieString);
            exp1.setPozitie((String) exp2.get("position"));
            exp1.setDataInceput((String) exp2.get("start_date"));
            exp1.setDataSfarsit((String) exp2.get("end_date"));
            experience.add(exp1);
            if (exp1.getDataSfarsit() == null) {
                company = Application.getApplication().getCompany(companieString);
            }
        }

        // crearea cv-ului
        Resume resume = new Resume.ResumeBuilder(information)
                .education(education).experience(experience).build();
        manager.setResume(resume);
        manager.setNameOfCompany(companieString);
        company.setManager(manager);
    }

    public void readInput() throws IOException, ParseException, InvalidDatesException, Resume.ResumeIncompleteException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("consumers.json"));
        JSONObject jobj = (JSONObject) obj;

        // adaugarea angajatiolor in departamentele corespunzatoare
        JSONArray employees = (JSONArray) jobj.get("employees");
        Iterator it = employees.iterator();
        while(it.hasNext()) {
            JSONObject man = (JSONObject) it.next();
            addEmployee(man);
        }

        // adaugara recruiter-ilor in departamentele de IT ale companiilor
        JSONArray recruiters = (JSONArray) jobj.get("recruiters");
        it = recruiters.iterator();
        while(it.hasNext()) {
            JSONObject man = (JSONObject) it.next();
            addRecruiter(man);
        }

        // adaugarea userilor in aplicatie
        JSONArray users = (JSONArray) jobj.get("users");
        it = users.iterator();
        while(it.hasNext()) {
            JSONObject man = (JSONObject) it.next();
            addUser(man);
        }

        // configurarea detaliilor despre manageri
        JSONArray managers = (JSONArray) jobj.get("managers");
        it = managers.iterator();
        while(it.hasNext()) {
            JSONObject man = (JSONObject) it.next();
            addManager(man);
        }

    }
    public static void main(String[] args) {
        Test test = new Test();
        Application application = Application.getApplication();
        Manager manager1 = new Manager();
        Company company1 = new Company("Google", manager1);
        Manager manager2 = new Manager();
        Company company2 = new Company("Amazon", manager2);
        application.add(company1);
        application.add(company2);

        // Google
        Department dep1 = DepartmentFactory.factory("IT");
        Department dep2 = DepartmentFactory.factory("Management");
        Department dep3 = DepartmentFactory.factory("Marketing");
        Department dep4 = DepartmentFactory.factory("Finance");
        company1.add(dep1);
        company1.add(dep2);
        company1.add(dep3);
        company1.add(dep4);

        // Amazon
        company2.add(DepartmentFactory.factory("IT"));
        company2.add(DepartmentFactory.factory("Management"));
        company2.add(DepartmentFactory.factory("Marketing"));
        company2.add(DepartmentFactory.factory("Finance"));

        try {
            test.readInput();
            ArrayList<Employee> employees = company1.getDepartment("IT").getEmployees();
            for (Employee empl:employees) {
                System.out.println("IT " + company1.name + " " + empl.getResume().getInformation().getName());
            }
            employees = company1.getDepartment("Management").getEmployees();
            for (Employee empl:employees) {
                System.out.println("Management "  + company1.name + " "  + empl.getResume().getInformation().getName());
            }
            employees = company1.getDepartment("Finance").getEmployees();
            for (Employee empl:employees) {
                System.out.println("Finance "  + company1.name + " " + empl.getResume().getInformation().getName());
            }
            employees = company1.getDepartment("Marketing").getEmployees();
            for (Employee empl:employees) {
                System.out.println("Marketing " + company1.name + " " +empl.getResume().getInformation().getName());
            }
            employees = company2.getDepartment("IT").getEmployees();
            for (Employee empl:employees) {
                System.out.println("IT " + company2.name + " " + empl.getResume().getInformation().getName());
            }
            employees = company2.getDepartment("Management").getEmployees();
            for (Employee empl:employees) {
                System.out.println("Management " +  company2.name + " " + empl.getResume().getInformation().getName());
            }
            employees = company2.getDepartment("Finance").getEmployees();
            for (Employee empl:employees) {
                System.out.println("Finance "  + company2.name + " " + empl.getResume().getInformation().getName());
            }
            employees = company2.getDepartment("Marketing").getEmployees();
            for (Employee empl:employees) {
                System.out.println("Marketing " + company2.name + " " + empl.getResume().getInformation().getName());
            }

            System.out.println("Managerul companiei " + company1.name + " este " + company1.manager.getResume().getInformation().getName());
            System.out.println("Managerul companiei " + company2.name + " este "+ company2.manager.getResume().getInformation().getName());
            System.out.println("\n");

            // crearea si adaugarea joburilor
            Job job1 = new Job("Software Developer Engineer", company1, 1, 10000d);
            Constraint gradConst1 = new Constraint(2002, 2020);
            Constraint expConst1 = new Constraint(8, Integer.MAX_VALUE);
            Constraint averageConst1 = new Constraint(8, Integer.MAX_VALUE);
            job1.setGraduationConstraint(gradConst1);
            job1.setExperienceConstraint(expConst1);
            job1.setGPAConstraint(averageConst1);
            company1.getDepartment("IT").add(job1);

            Job job2 = new Job("Software Developer Engineer Intern", company1, 1, 5000d);
            Constraint gradConst2 = new Constraint(Double.MIN_VALUE, Integer.MAX_VALUE);
            Constraint expConst2 = new Constraint(0, 2);
            Constraint averageConst2 = new Constraint(9, Integer.MAX_VALUE);
            job1.setGraduationConstraint(gradConst2);
            job1.setExperienceConstraint(expConst2);
            job1.setGPAConstraint(averageConst2);
            company1.getDepartment("IT").add(job2);

            Job job3 = new Job("Software Developer Engineer", company2, 1, 12000d);
            Constraint gradConst3 = new Constraint(2014, 2020);
            Constraint expConst3 = new Constraint(1, Integer.MAX_VALUE);
            Constraint averageConst3 = new Constraint(9, Integer.MAX_VALUE);
            job3.setGraduationConstraint(gradConst3);
            job3.setExperienceConstraint(expConst3);
            job3.setGPAConstraint(averageConst3);
            company2.getDepartment("IT").add(job3);

            Job job4 = new Job("Software Developer Engineer Intern", company2, 1, 6000d);
            Constraint gradConst4 = new Constraint(Double.MIN_VALUE, Integer.MAX_VALUE);
            Constraint expConst4 = new Constraint(0, 2);
            Constraint averageConst4 = new Constraint(9.35, Integer.MAX_VALUE);
            job4.setGraduationConstraint(gradConst4);
            job4.setExperienceConstraint(expConst4);
            job4.setGPAConstraint(averageConst4);
            company2.getDepartment("IT").add(job4);

            User u1 = Application.getApplication().users.get(0);
            User u2 = Application.getApplication().users.get(1);
            User u3 = Application.getApplication().users.get(2);
            User u4 = Application.getApplication().users.get(3);

            Employee e2 = company2.getDepartment("Management").employees.get(0);
            Employee e3 = company2.getDepartment("Marketing").employees.get(0);
            Employee e6 = company1.getDepartment("IT").employees.get(0);
            Employee e10 = company1.getDepartment("Marketing").employees.get(1);
            Employee e7 = company1.getDepartment("Management").employees.get(0);

            Recruiter r1 = company1.getRecruiters().get(0);
            Recruiter r2 = company1.getRecruiters().get(1);
            Recruiter r3 = company2.getRecruiters().get(0);
            Recruiter r4 = company2.getRecruiters().get(1);

            // adaugari in listele de cunoscuti
            u1.add(u2);
            u1.add(e3);
            u2.add(u1);
            u2.add(r1);
            u2.add(e7);
            u3.add(e3);
            u3.add(u4);
            u4.add(u3);
            u4.add(e10);

            e2.add(e10);
            e2.add(r3);
            e3.add(u1);
            e3.add(r2);
            e3.add(u3);
            e3.add(e6);
            e6.add(e3);
            e6.add(r4);
            e10.add(e2);
            e10.add(u4);
            e7.add(u2);

            r1.add(u2);
            r2.add(e3);
            r3.add(e2);
            r4.add(e6);

            // utilizatorii aplica la toate joburile
            for (User u : Application.getApplication().users) {
                job1.apply(u);
                job2.apply(u);
                job3.apply(u);
                job4.apply(u);
            }

            company1.getManager().process(job1);
            company1.getManager().process(job2);
            company2.getManager().process(job3);
            company2.getManager().process(job4);

            // alti utilizatori generati random
            Random r = new Random();
            int ed1 = 2005;
            int ed2 = 2010;
            int ed3 = 2020;
            int exp1 = 2014;
            int exp2 = 2017;
            int exp3 = 2020;
            String s1;
            String s2;
            for (int i = 0; i < 30; i++) {
                User user = new User();
                Information inf = new Information();
                inf.setName("Utilizator"+String.valueOf(i));
                inf.setEmail("user"+String.valueOf(i)+"@gmail.com");
                inf.addLanguage("English", "advanced");
                TreeSet<Education> education = new TreeSet<Education>();
                s1 = "01.01."+ String.valueOf(r.nextInt(ed2 - ed1) + ed1);
                s2 = "25.03." + String.valueOf(r.nextInt(ed3 - ed2) + ed2);
                education.add(new Education("University"+String.valueOf(i), "college", 9.80, s1, s2));
                TreeSet<Experience> experiences = new TreeSet<Experience>();
                s1 = "02.03." + String.valueOf(r.nextInt(exp2 - exp1) + exp1);
                s2 = "12.04." + String.valueOf(r.nextInt(exp3 - exp2) + exp2);
                experiences.add(new Experience("Companie" + String.valueOf(i), "Software Developer", s1, s2));
                Resume res1 = new Resume.ResumeBuilder(inf).education(education).experience(experiences).build();
                user.setResume(res1);
                application.add(user);
            }

//            AdminPage adminPage = new AdminPage();
//            ProfilePage profilePage = new ProfilePage();
//            ManagerPage manpage = new ManagerPage(company1.getManager());

            Login loginPage = new Login();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidDatesException e) {
            e.printStackTrace();
        } catch (Resume.ResumeIncompleteException e) {
            e.printStackTrace();
        }
    }
}
