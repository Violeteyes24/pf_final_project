import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DatingProfile extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dmid";
    static final String USER = "root";
    static final String PASS = "";

    public DatingProfile(String email) {
        setTitle("Dating Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Retrieve user information from the database based on the email
        String retrieveUserSQL = "SELECT name, age, location FROM sign_up WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(retrieveUserSQL)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String location = resultSet.getString("location");

                    JPanel profilePanel = new JPanel();
                    profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
                    profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    getContentPane().setBackground(Color.PINK);

                    JLabel nameLabel = new JLabel("Name: " + name);
                    JLabel ageLabel = new JLabel("Age: " + age);
                    JLabel locationLabel = new JLabel("Location: " + location);

                    // Center text
                    nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                    profilePanel.add(nameLabel);
                    profilePanel.add(ageLabel);
                    profilePanel.add(locationLabel);

                    // Add buttons
                    JButton startMatchingButton = new JButton("Start Matching");
                    JButton homepageButton = new JButton("Homepage");

                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    buttonPanel.add(startMatchingButton);
                    buttonPanel.add(homepageButton);

                    // Set layout for the frame
                    setLayout(new BorderLayout());

                    // Use GridBagLayout for centering the profilePanel
                    JPanel centerPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    centerPanel.add(profilePanel, gbc);

                    add(centerPanel, BorderLayout.CENTER);
                    add(buttonPanel, BorderLayout.SOUTH);

                    // Set the size of the frame
                    setSize(400, 400);

                    // Center the frame on the screen
                    setLocationRelativeTo(null);

                    // Make the frame visible
                    setVisible(true);

                    startMatchingButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Match matchWindow = new Match();
                            
                            setVisible(false); // Close the signup window
                            dispose();  // Release system resources
            
                            // Open the login window
                            matchWindow.setVisible(true);
                            
                        }
                    });

                    homepageButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            log_in test_gologin = new log_in("jdbc:mysql://localhost:3306/dmid");
                            
                            setVisible(false); // Close the signup window
                            dispose();  // Release system resources
            
                            // Open the login window
                            test_gologin.setVisible(true);
                            
                        }
                    });

                    
                } 
                
                // else {
                //     System.out.println("DEBUG: User not found in the database."); 
                //     JOptionPane.showMessageDialog(this, "User not found in the database.");
                //     return;
                // } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving user information.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // log_in get_email = new log_in(DB_URL);
            String userEmail = log_in.getLogged_in_email(); // Use the getter method

            new DatingProfile(userEmail);
        });
    }
}
