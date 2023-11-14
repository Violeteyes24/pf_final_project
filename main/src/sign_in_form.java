import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum Sex {
    male, female, others
}

public class sign_in_form extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "";
    
    protected JTextField email;
    protected JPasswordField passwordField;
    protected JComboBox<Sex> sex;
    protected JTextField locationField;
    protected JTextField nameField;
    protected JTextField ageField;

    public sign_in_form() {
        // Set up the frame
        setTitle("Sign In Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel sexLabel = new JLabel("Sex:");
        JLabel locationLabel = new JLabel("Location:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");

        email = new JTextField(20);
        passwordField = new JPasswordField(20);
        sex = new JComboBox<>(Sex.values());
        locationField = new JTextField(20);
        nameField = new JTextField(20);
        ageField = new JTextField(20);

        JButton signInButton = new JButton("Sign In");

        // Set up layout
        setLayout(new GridLayout(7, 2));
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(sexLabel);
        add(sex);
        add(locationLabel);
        add(locationField);
        add(emailLabel);
        add(email);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Placeholder
        add(signInButton);

        // Add action listener to the sign-in button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame email_validator;
                email_validator = new JFrame();
                final String email_var = (String)email.getText();
                    if (!email_var.equals(email_var) && email_var.equals("@")){
                    JOptionPane.showMessageDialog(email_validator,"Please enter a valid email");
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }                // Handle sign-in logic here (e.g., validate inputs, store in a database, etc.)
                // For this example, we will just display the entered information in the console
                displayUserInfo();
            }
        });
    }

    private void displayUserInfo() {
        String email_display = email.getText();
        char[] password = passwordField.getPassword();
        Sex sexobj = (Sex) sex.getSelectedItem();
        String location = locationField.getText();
        String name = nameField.getText();
        String age = ageField.getText();

        System.out.println("Email: " + email_display);
        System.out.println("Password: " + new String(password));
        System.out.println("Sex: " + sexobj);
        System.out.println("Location: " + location);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    
    private void insertUserInfo() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmnt = conn.createStatement();)
                // Create a prepared statement
                String useDBSQL = "USE dmid";
                stmnt.executeUpdate(useDBSQL);

                String sql = "INSERT INTO sign_up (email, passwordField, sex, location, name, age) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    // Set values for the prepared statement
                    preparedStatement.setString(1, email.getText());
                    preparedStatement.setString(2, new String(passwordField.getPassword()));
                    preparedStatement.setString(3, sex.getSelectedItem().toString());
                    preparedStatement.setString(4, locationField.getText());
                    preparedStatement.setString(5, nameField.getText());
                    preparedStatement.setString(6, ageField.getText());
                    
                    // Execute the statement
                    preparedStatement.executeUpdate();
                    
                    JOptionPane.showMessageDialog(this, "User information inserted successfully!");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting user information.");
        }
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new sign_in_form().setVisible(true);
        }
    });
    sign_in_form obj = new sign_in_form();
    obj.insertUserInfo();
}
}
