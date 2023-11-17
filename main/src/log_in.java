import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class log_in extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "";

    protected JTextField email;
    protected JPasswordField passwordField;

    public log_in(String dbUrl) {
        // Set up the frame
        setTitle("Login Form");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");

        email = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        // Set up layout
        setLayout(new GridLayout(3, 2));
        setBackground(Color.pink);
        add(emailLabel);
        add(email);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Placeholder
        add(loginButton);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            DatingProfile dateobj = new DatingProfile(DB_URL);
                if (checkLogin()) {
                    JOptionPane.showMessageDialog(log_in.this, "Login successful!");
                    dateobj.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(log_in.this, "Invalid email or password.");
                }
            }

            private boolean checkLogin() {
                try {
                    // Load the JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Establish a connection
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                         Statement stmnt = conn.createStatement()) {

                        // Create a prepared statement
                        String useDBSQL = "USE dmid";
                        stmnt.executeUpdate(useDBSQL);

                        String sql = "SELECT * FROM sign_up WHERE email=? AND passwordField=?";
                        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                            // Set values for the prepared statement
                            preparedStatement.setString(1, email.getText());
                            preparedStatement.setString(2, new String(passwordField.getPassword()));

                            // Execute the query
                            ResultSet resultSet = preparedStatement.executeQuery();

                            // Check if the result set has any rows
                            return resultSet.next();
                        }
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(log_in.this, "Error checking login credentials.");
                    return false;
                }
            }
        });
    }
}
