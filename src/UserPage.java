
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage extends JFrame {

    User user;
    JLabel notifL;
    JList notifications;
    JButton profile;

    UserPage(User user) {
        this.user = user;
        setTitle("User " + user.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(1,2));
        setPreferredSize(new Dimension(700, 500));

        // panelul cu notificari
        JPanel notif = new JPanel();
        JLabel notifL = new JLabel("Notifications");
        notifL.setForeground(Color.decode("#B8B8FF"));
        notifications = new JList(user.notifications.toArray());
        JScrollPane showNotif = new JScrollPane(notifications);
        showNotif.setBorder(new EmptyBorder(10, 40, 10, 40));
        notif.add(notifL);
        notif.add(showNotif);
        notif.setBorder(BorderFactory.createLineBorder(Color.decode("#B8B8FF"), 10));

        // panel cu butoane spre alte pagini
        JPanel redirect = new JPanel(new BorderLayout());
        profile = new JButton("Profile Page");
        profile.setBackground(Color.decode("#B8B8FF"));
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfilePage profilePage = new ProfilePage();
                profilePage.setVisible(true);
            }
        });
        redirect.add(profile, BorderLayout.CENTER);
        getContentPane().add(notif);
        getContentPane().add(redirect);

        pack();
        setVisible(true);
    }
}
