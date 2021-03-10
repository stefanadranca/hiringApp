
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame {
    JTextField email;
    JPasswordField password;
    JRadioButton userB;
    JRadioButton managerB;
    JRadioButton adminB;
    JLabel text;
    JLabel emailL;
    JLabel passL;
    JButton loginB;
    JButton signB;

    Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(2,1));
        setPreferredSize(new Dimension(700, 500));
        setBackground(Color.decode("#B8B8FF"));

        JPanel welcome = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String s = "<html>" + "<h1 style=\"font-family:verdana;font-size:230%\"> Login </h1>" + "</html>";
        text = new JLabel(s);
        text.setForeground(Color.decode("#A53860"));
        text.setBorder(new EmptyBorder(30, 0, 0, 0));
        welcome.add(text);

        // panelul cu detaliile de logare
        JPanel log = new JPanel(new GridLayout(3, 1));

        // panelul pentru email si parola
        JPanel emailPass = new JPanel(new GridLayout(2,2));
        emailL = new JLabel("email");
        emailL.setForeground(Color.decode("#A53860"));
        emailL.setFont(new Font("Verdana", Font.BOLD, 18));
        email = new JTextField(15);
        email.setFont(new Font("Verdana", Font.PLAIN, 16));
        emailL.setBorder(new EmptyBorder(10, 160, 10, 0));
        emailPass.add(emailL);
        emailPass.add(email);
        passL = new JLabel("password");
        passL.setForeground(Color.decode("#A53860"));
        passL.setFont(new Font("Verdana", Font.BOLD, 18));
        password = new JPasswordField(20);
        passL.setBorder(new EmptyBorder(10, 160, 10, 0));
        emailPass.add(passL);
        emailPass.add(password);
        emailPass.setBorder(new EmptyBorder(10, 0, 10, 50));
        log.add(emailPass);

        // butoanele de alegere al tipului de logare
        userB = new JRadioButton("user");
        adminB = new JRadioButton("admin");
        managerB = new JRadioButton("manager");
        ButtonGroup tip = new ButtonGroup();
        tip.add(userB);
        tip.add(adminB);
        tip.add(managerB);
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(userB);
        buttons.add(adminB);
        buttons.add(managerB);
        log.add(buttons);

        // Panelul cu butoanele de Login / SignUp
        JPanel logSign = new JPanel(new FlowLayout());
        loginB = new JButton("Login");
        signB = new JButton("Sign Up");
        loginB.setBackground(Color.decode("#A53860"));
        loginB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = email.getText();
                String par = password.getText();
                if (userB.isSelected()) {
                    User user = checkUser(mail, par);
                    if (user != null) {
                        JOptionPane.showMessageDialog(emailPass, "You have been successfully logged in");
                        UserPage userPage = new UserPage(user);
                        userPage.setVisible(true);
                        JFrame f = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
                        f.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(emailPass, "Wrong email or password");
                    }
                }
                if (adminB.isSelected()) {
                    if (mail.equals("admin") && par.equals("admin")) {
                        JOptionPane.showMessageDialog(emailPass, "You have been successfully logged in");
                        AdminPage adminPage = new AdminPage();
                        adminPage.setVisible(true);
                        JFrame f = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
                        f.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(emailPass, "Wrong email or password");
                    }
                }
                if (managerB.isSelected()) {
                    Manager manager = checkManager(mail, par);
                    if (manager != null) {
                        JOptionPane.showMessageDialog(emailPass, "You have been successfully logged in");
                        ManagerPage manPage = new ManagerPage(manager);
                        manPage.setVisible(true);
                        JFrame f = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
                        f.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(emailPass, "Wrong email or password");
                    }
                }
            }
        });
        signB.setBackground(Color.decode("#A53860"));
        logSign.add(loginB);
        logSign.add(signB);
        log.add(logSign);
        getContentPane().add(welcome);
        getContentPane().add(log);
        pack();
        setVisible(true);
    }

    // se verifica daca exista managerul cu credentialele furnizate
    private Manager checkManager(String mail, String par) {
        ArrayList<Company> companies = Application.getApplication().companies;
        for (Company c : companies) {
            if ((c.getManager().getEmail()).equals(mail)) {
                return c.getManager();
            }
        }
        return null;
    }

    // se verifica daca exista userul cu credentialele furnizate
    private User checkUser(String mail, String par) {
        Application app = Application.getApplication();
        for (User u : app.users) {
            if (u.getEmail().equals(mail)) {
                return u;
            }
        }
        return null;
    }
}
