
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage extends JFrame {
    JTextField name;
    JButton search;
    JTextArea profile;

    ProfilePage() {
        setTitle("Profile page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(1,2));
        setPreferredSize(new Dimension(700, 500));

        // Panelul cu textfieldul si butonul pentru cautare
        JPanel searching = new JPanel();
        name = new JTextField(25);
        search = new JButton("Search user");
        profile = new JTextArea();
        profile.setMargin(new Insets(30, 30, 10, 10));
        profile.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Application app = Application.getApplication();
        searching.add(name);

        // cand butonul este apasat se cauta userul cu numele din textfield
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = name.getText();
                Boolean found = false;
                for (User u : app.users) {
                    if (u.getName().equals(userName)) {
                        Resume resume = u.getResume();
                        profile.setEditable(true);
                        profile.setText(resume.toString());
                        profile.setEditable(false);
                        found = true;
                        break;
                    }
                }
                // verificam daca nu a fost gasit utilizatorul cautat
                if (!found) {
                    profile.setEditable(true);
                    profile.setText("\nUser not found");
                    profile.setEditable(false);
                }
            }
        });
        searching.add(search);
        searching.setBorder(BorderFactory.createLineBorder(Color.decode("#B8B8FF"), 4));
        JPanel information = new JPanel(new BorderLayout());
        JScrollPane scrollInfo = new JScrollPane(profile, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        information.add(scrollInfo, BorderLayout.CENTER);
        information.setBorder(BorderFactory.createLineBorder(Color.decode("#B8B8FF"), 4));

        getContentPane().add(searching);
        getContentPane().add(information);

        pack();
        setVisible(true);
    }
}
