
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class AdminPage extends JFrame {

    JList users;
    JList companies;
    JButton calculate;
    JTextField salaryC;
    JList departments = new JList();
    JList employees = new JList();
    JList jobs = new JList();

    AdminPage() {

        setTitle("Admin page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(1,1));
        setPreferredSize(new Dimension(700, 500));

        // pentru a prelua datele din aplicatie
        Application app = Application.getApplication();

        UIManager.put("TabbedPane.selected", Color.decode("#B8B8FF"));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        // tabul cu panel-ul cu lista de utilizatori
        JPanel usersPanel = new JPanel();
        users = new JList(app.getUsersNames().toArray());
        JScrollPane scrollUsers = new JScrollPane(users);
        usersPanel.add(scrollUsers);
        usersPanel.setLayout(new GridLayout(1,1));
        usersPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#B8B8FF"), 10));
        tabbedPane.addTab("Users", usersPanel);

        // tabul cu panel-ul cu lista de companii
        JPanel companiesPanel = new JPanel(new GridLayout(1, 4));
        // lista de companii
        companies = new JList(app.getCompaniesNames().toArray());
        companies.addListSelectionListener(new listener());
        JScrollPane scrollCompanies = new JScrollPane(companies);
        companiesPanel.add(scrollCompanies);

        // Panelul cu departamentele din compania selectata
        JPanel departmentsPanel = new JPanel(new BorderLayout());
        JPanel depShow = new JPanel(new GridLayout(1, 1));
        departments.addListSelectionListener(new listener());
        JScrollPane scrollDepartments = new JScrollPane(departments);
        depShow.add(scrollDepartments);
        departmentsPanel.add(depShow, BorderLayout.CENTER);
        // Panelul pentru calcularea salariului
        JPanel salary = new JPanel(new GridLayout(2,1));
        salaryC = new JTextField(10);
        calculate = new JButton("Afiseaza salariu");
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (departments != null) {
                    if (!departments.isSelectionEmpty()) {
                        Company comp = Application.getApplication().getCompany((String) companies.getSelectedValue());
                        Department dep = comp.getDepartment((String) departments.getSelectedValue());
                        salaryC.setText(String.valueOf(dep.getTotalSalaryBudget()));
                    }
                }
            }
        });
        calculate.setPreferredSize(new Dimension(90, 20));
        salary.add(calculate, BorderLayout.EAST);
        salary.add(salaryC, BorderLayout.WEST);
        departmentsPanel.add(salary, BorderLayout.SOUTH);
        departmentsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        companiesPanel.add(departmentsPanel);

        // Panelul cu angajatii
        JPanel employeesPanel = new JPanel(new GridLayout(1, 1));
        JScrollPane scrollEmployees = new JScrollPane(employees);
        employeesPanel.add(scrollEmployees);
        employeesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        companiesPanel.add(employeesPanel);

        // Panelul cu joburile din departamentul selectat
        JPanel jobsPanel = new JPanel(new GridLayout(1, 1));
        JScrollPane scrollJobs = new JScrollPane(jobs);
        jobsPanel.add(scrollJobs);
        jobsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        companiesPanel.add(jobsPanel);

        companiesPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#B8B8FF"), 10));
        tabbedPane.addTab("Companies", companiesPanel);

        getContentPane().add(tabbedPane);

        pack();
        setVisible(true);
    }

    private class listener implements javax.swing.event.ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // a fost selectat o companie si se afiseaza departamentele
            if (e.getSource() == companies) {
                Company comp = Application.getApplication().getCompany((String) companies.getSelectedValue());
                ArrayList<Department> dep = comp.getDepartments();
                Vector<String> depComp = new Vector<>();
                for (Department d : dep) {
                    if (d instanceof IT)
                        depComp.addElement("IT");
                    if (d instanceof Management)
                        depComp.addElement("Management");
                    if (d instanceof Marketing)
                        depComp.addElement("Marketing");
                    if (d instanceof Finance)
                        depComp.addElement("Finance");
                }
                departments.setListData(depComp);
            }
            if (e.getSource() == departments) {
                // a fost selectat un departament si se afiseaza angajatii si joburile disponibile
                Company comp = Application.getApplication().getCompany((String) companies.getSelectedValue());
                Department dep = comp.getDepartment((String) departments.getSelectedValue());
                Vector<String> emplComp = new Vector<>();
                for (Employee emp : dep.getEmployees()) {
                    emplComp.addElement(emp.getName());
                }
                employees.setListData(emplComp);
                Vector<String> jobComp = new Vector<>();
                for (Job j : dep.getJobs()) {
                    jobComp.addElement(j.jobName);
                }
                jobs.setListData(jobComp);
            }

        }
    }
}
