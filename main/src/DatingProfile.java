import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatingProfile extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "";

    public DatingProfile(String email) {
        setTitle("Dating Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Retrieve user information from the database based on the email
        String retrieveUserSQL = "SELECT name, age, location FROM sign_up WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(retrieveUserSQL)) {
            Statement stmnt = conn.createStatement();

            String useDBSQL = "USE dmid";
            stmnt.executeUpdate(useDBSQL);
            System.out.println("Using database 'dmid'...");

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String location = resultSet.getString("location");

                    JPanel profilePanel = new JPanel();
                    profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
                    profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
                } else {
                    JOptionPane.showMessageDialog(this, "User not found in the database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving user information.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Assume the user is already signed up and has an email
            String userEmail = "rj@udtohan";

            // Uncomment the line below if you want to insert user information first
            // insertUserInfo(userEmail);

            new DatingProfile(userEmail);
        });
    }

    private static void insertUserInfo(String email) {
        // Insert user information into the database based on the email
        // This method is similar to the insertUserInfo method in your sign_in_form class
        // You can reuse the code for inserting user information here
        // ...
    }
}
