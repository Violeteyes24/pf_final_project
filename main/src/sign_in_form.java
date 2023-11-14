import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum Sex {
    MALE, FEMALE, OTHERS
}

public class sign_in_form extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<Sex> sexComboBox;
    private JTextField locationField;
    private JTextField nameField;
    private JTextField ageField;

    public SignInForm() {
        // Set up the frame
        setTitle("Sign In Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel sexLabel = new JLabel("Sex:");
        JLabel locationLabel = new JLabel("Location:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        sexComboBox = new JComboBox<>(Sex.values());
        locationField = new JTextField(20);
        nameField = new JTextField(20);
        ageField = new JTextField(20);

        JButton signInButton = new JButton("Sign In");

        // Set up layout
        setLayout(new GridLayout(7, 2));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(sexLabel);
        add(sexComboBox);
        add(locationLabel);
        add(locationField);
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(new JLabel()); // Placeholder
        add(signInButton);

        // Add action listener to the sign-in button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sign-in logic here (e.g., validate inputs, store in a database, etc.)
                // For this example, we will just display the entered information in the console
                displayUserInfo();
            }
        });
    }

    private void displayUserInfo() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        Sex sex = (Sex) sexComboBox.getSelectedItem();
        String location = locationField.getText();
        String name = nameField.getText();
        String age = ageField.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + new String(password));
        System.out.println("Sex: " + sex);
        System.out.println("Location: " + location);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SignInForm().setVisible(true);
            }
        });
    }
}

// public class SignInForm extends JFrame {
//     private JTextField usernameField;
//     private JPasswordField passwordField;
//     private JComboBox<Sex> sexComboBox;
//     private JTextField locationField;
//     private JTextField nameField;
//     private JTextField ageField;

//     // JDBC database URL, username, and password
//     private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/exampledb";
//     private static final String DATABASE_USER = "your_username";
//     private static final String DATABASE_PASSWORD = "your_password";

//     public SignInForm() {
//         // ... (previous code remains the same)

//         // Add action listener to the sign-in button
//         signInButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Handle sign-in logic here (e.g., validate inputs, store in a database, etc.)
//                 insertUserInfo();
//             }
//         });
//     }

//     private void insertUserInfo() {
//         try {
//             // Load the JDBC driver
//             Class.forName("com.mysql.cj.jdbc.Driver");

//             // Establish a connection
//             try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
//                 // Create a prepared statement
//                 String sql = "INSERT INTO users (username, password, sex, location, name, age) VALUES (?, ?, ?, ?, ?, ?)";
//                 try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                     // Set values for the prepared statement
//                     preparedStatement.setString(1, usernameField.getText());
//                     preparedStatement.setString(2, new String(passwordField.getPassword()));
//                     preparedStatement.setString(3, sexComboBox.getSelectedItem().toString());
//                     preparedStatement.setString(4, locationField.getText());
//                     preparedStatement.setString(5, nameField.getText());
//                     preparedStatement.setString(6, ageField.getText());

//                     // Execute the statement
//                     preparedStatement.executeUpdate();

//                     JOptionPane.showMessageDialog(this, "User information inserted successfully!");
//                 }
//             }
//         } catch (ClassNotFoundException | SQLException ex) {
//             ex.printStackTrace();
//             JOptionPane.showMessageDialog(this, "Error inserting user information.");
//         }

