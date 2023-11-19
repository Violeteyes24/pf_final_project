import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homepage extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dmid";
    static final String USER = "root";
    static final String PASS = "";

    public homepage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 450);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("D:\\Documents\\2023\\pf_final_project\\main\\images\\Zach.jpg");
        Image scaledImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(icon);

        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.gridx = 0;
        gbcImage.gridy = 0;
        add(imageLabel, gbcImage);

        JLabel label = new JLabel("Dmid is a dating application for people who are desperate");

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 1;
        gbcLabel.insets = new Insets(10, 0, 10, 0);
        add(label, gbcLabel);

        JButton homeStartButton = new JButton("Start");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton logoutButton = new JButton("Log out");

        Dimension buttonSize = new Dimension(100, 30);
        homeStartButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        int space = 10;
        Insets buttonInsets = new Insets(0, space, 0, space);
        homeStartButton.setMargin(buttonInsets);
        updateButton.setMargin(buttonInsets);
        deleteButton.setMargin(buttonInsets);
        logoutButton.setMargin(buttonInsets);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(homeStartButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);
        buttonPanel.setBackground(Color.PINK);

        GridBagConstraints gbcButtonPanel = new GridBagConstraints();
        gbcButtonPanel.gridx = 0;
        gbcButtonPanel.gridy = 2;
        gbcButtonPanel.anchor = GridBagConstraints.EAST;
        add(buttonPanel, gbcButtonPanel);

        getContentPane().setBackground(Color.PINK);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = getUserEmail(log_in.getLogged_in_email()); // Get logged-in email
                System.out.println("homepage attempt to get email: " + userEmail);
                delete del_user = new delete();

                JFrame delframe = new JFrame();
                delframe.setTitle("Deleting User");
                delframe.setSize(300, 300);
                delframe.setLocation(400, 400);

                JLabel delLabel = new JLabel("Are you sure you want to delete this account?");
                JButton delButton = new JButton("Delete");

                delframe.setLayout(new FlowLayout());
                delframe.add(delLabel);
                delframe.add(delButton);

                delframe.setVisible(true);

                delButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        del_user.deleteUser(userEmail);
                        delframe.dispose();

                        // Use the email to create the DatingProfile instance
                        
                    }
                });
            }
        });

        homeStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = getUserEmail(log_in.getLogged_in_email());
                DatingProfile datingProfile = new DatingProfile(userEmail);
                setVisible(false); // Close the signup window
                dispose();  // Release system resources

                // Open the login window
                System.out.println("I am here on Dating Profile");
                datingProfile.setVisible(true);
                
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = getUserEmail(log_in.getLogged_in_email()); // Get logged-in email
                System.out.println("homepage attempt to update: " + userEmail);
                // Implement actions for the "Update" button, if needed
                update_user_info updateForm = new update_user_info(userEmail);
                updateForm.setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log_in test_gologin = new log_in("jdbc:mysql://localhost:3306/dmid");

                setVisible(false); // Close the signup window
                dispose();  // Release system resources

                // Open the login window
                System.out.println("I am on log in");
                test_gologin.setVisible(true);
                // Implement actions for the "Log out" button, if needed
            }
        });
    }

    private String getUserEmail(String email) {
        String retrieveUserSQL = "SELECT email FROM sign_up WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(retrieveUserSQL)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getString("email");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void show_h() {
        setVisible(true);
    }
}
