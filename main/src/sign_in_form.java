import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

    // idk if this boolean will work
    // private boolean executed = false;

    // public void execute() {
    //     executed = true;
    // }

    // public boolean isExecuted() {
    //     return executed;
    // }
    public sign_in_form() {
        // Set up the frame
        setTitle("Sign In Form");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 10, 2));
        mainPanel.setBackground(Color.PINK);
        

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton signInButton = new JButton("Sign In");
        JButton loginButton = new JButton("Login"); 
        
        
        buttonPanel.setLayout(new GridLayout(1,2));
        
        // Set up layout
        // setLayout(new GridLayout(8, 2, 10, 1));
        // getContentPane().setBackground(Color.PINK);
        // setBackground(Color.pink);
        buttonPanel.add(signInButton);
        buttonPanel.add(loginButton);
        
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(ageLabel);
        mainPanel.add(ageField);
        mainPanel.add(sexLabel);
        mainPanel.add(sex);
        mainPanel.add(locationLabel);
        mainPanel.add(locationField);
        mainPanel.add(emailLabel);
        mainPanel.add(email);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel()); // Placeholder
        // buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        // EmptyBorder pinkEmptyBorder = new EmptyBorder(0, 0, 0, 0);
        // buttonPanel.setBorder(BorderFactory.createCompoundBorder(buttonPanel.getBorder(), pinkEmptyBorder));
        mainPanel.add(buttonPanel);
        // mainPanel.add(signInButton);
        // mainPanel.add(loginButton);

        // Set up layout with the new content pane
        setContentPane(mainPanel);
        // Add padding around the entire layout
        int padding = 40;
        mainPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        
        // Add action listener to the sign-in button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailText = email.getText();
                String ageText = ageField.getText();
                if (!EmailValidator.isValidEmail(emailText)) {
                    JOptionPane.showMessageDialog(sign_in_form.this, "Invalid email address!");
                    return;
                }

                if (!AgeValidator.isValidAge(ageText)) {
                    AgeValidator.showInvalidAgeMessage();
                    return;
                }
                log_in loginobj = new log_in(DB_URL);

                displayUserInfo();
                insertUserInfo();
                
                setVisible(false); // Close the signup window
                dispose();  // Release system resources

                // Open the login window
                loginobj.setVisible(true);
                
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log_in loginobj = new log_in(DB_URL);
                
                setVisible(false); // Close the signup window
                dispose();  // Release system resources

                // Open the login window
                loginobj.setVisible(true);
                System.out.println("I am on log_in page");  
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
                    Statement stmnt = conn.createStatement();){
                        // Create a prepared statement
                        String useDBSQL = "USE dmid";
                        stmnt.executeUpdate(useDBSQL);
        
                        String sql = "INSERT INTO sign_up (name, age, sex, location, email, passwordField) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                            // Set values for the prepared statement
                            preparedStatement.setString(1, nameField.getText());
                            preparedStatement.setInt(2, Integer.parseInt(ageField.getText())); // age is int
                            preparedStatement.setString(3, sex.getSelectedItem().toString());
                            preparedStatement.setString(4, locationField.getText());
                            preparedStatement.setString(5, email.getText());
                            preparedStatement.setString(6, new String(passwordField.getPassword()));
                            
                            // Execute the statement
                            preparedStatement.executeUpdate();
                            
                            JOptionPane.showMessageDialog(sign_in_form.this, "User information inserted successfully!");
                            // "this" argument was for action listener, putting "sign_in_form.this was to indicate that it refers to the outer class"
                        }
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(sign_in_form.this, "Error inserting user information.");
                }
        }
    }
