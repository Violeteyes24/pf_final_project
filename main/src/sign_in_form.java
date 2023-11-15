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
        setBackground(Color.pink);
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
                    // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                displayUserInfo();
                insertUserInfo();
                // caller of 2 functions after pressing sign in button
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
        });
    }
}

// Frame frame1 = new Frame("Hello World");
        // frame1.setSize(500, 300);
        // frame1.setLocation(500, 120);
        
        // // Add a WindowListener to handle the close button click
        // frame1.addWindowListener(new WindowAdapter() {
        //     public void windowClosing(WindowEvent close) {
        //         frame1.dispose(); // Close the frame
        //     }
        // });

        // frame1.setVisible(true);
        
        // // added label I think it automatically labels the first panel
        // Label alabel = new Label("Would you like to go out with me? ");
        // alabel.setAlignment(Label.LEFT);

        // // added panel inside frame
        // Panel apanel = new Panel();
        // Button okButton = new Button("Yes");
        // Button cancelButton = new Button("yes please");
        
        // // added list
        // List alist = new List();
        // alist.add("String 1");
        // alist.add("List of strings?");  
        // alist.add("Where");  
        // alist.add("Do ");  
        // alist.add("You wanna eat?");  

        // //checkbox
        // Checkbox creamCheckbox = new Checkbox("Cream");
        // Checkbox cheeseCheckbox = new Checkbox("Cheese");

        // // choice
        // Choice aChoice = new Choice();
        // aChoice.add("gamedev");
        // aChoice.add("webdev");
        // aChoice.add("appdev");
        // aChoice.add("AI/ML");
        // aChoice.add("Cloud Engineering");
        // aChoice.add("Manager");

        // TextField emailTextField = new TextField();
        // TextField passwordTextField = new TextField();
        // passwordTextField.setEchoChar('*');

        // String userEmail = emailTextField.getText();
        // String userpassword = passwordTextField.getText();

        // TextArea txtarea = new TextArea(5, 50);
        // String txt = txtarea.getText();

        //  // nvm, here is the chronological order for everything
        // apanel.add(alabel);
        // apanel.add(okButton);
        // apanel.add(cancelButton);
        // apanel.add(alist);
        // apanel.add(creamCheckbox);
        // apanel.add(cheeseCheckbox);
        // apanel.add(aChoice);
        // apanel.add(emailTextField);
        // apanel.add(passwordTextField);
        // apanel.add(txtarea);
        // // called the panel from frame
        // frame1.add(apanel);

