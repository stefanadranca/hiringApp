
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ManagerPage extends JFrame {
    Manager manager;
    JList requests;
    Company company;
    String[] columnNames;
    Object[][] data;
    ArrayList<Request> requests1;

    ManagerPage(Manager manager) {
        this.manager = manager;
        company = Application.getApplication().getCompany(manager.getNameOfCompany());
        requests1 = manager.getRequests();
        columnNames = new String[]{"Job", "Name", "Recruiter", "Score"};
        data = new Object[requests1.size()][4];
        for (int i = 0; i < requests1.size(); i++) {
            data[i][0] = ((Job) requests1.get(i).getKey()).jobName;
            data[i][1] = ((User)requests1.get(i).getValue1()).getName();
            data[i][2] = ((Recruiter)requests1.get(i).getValue2()).getName();
            data[i][3] = (double) requests1.get(i).getScore();
        }

        setTitle("Manager of " + company.name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(1,1));
        setPreferredSize(new Dimension(700, 500));

        JTable table = new JTable(data, columnNames);

        JPanel tabelP = new JPanel(new BorderLayout());
        tabelP.add(table.getTableHeader(), BorderLayout.PAGE_START);
        tabelP.add(table, BorderLayout.CENTER);
        tabelP.setBorder(new EmptyBorder(30, 30, 30, 30));

        getContentPane().add(tabelP);

        pack();
        setVisible(true);
    }
}
