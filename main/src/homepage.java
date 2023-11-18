import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        setLayout(new GridBagLayout()); // Use GridBagLayout
        setLocationRelativeTo(null);

        // Add a JLabel for the image
        ImageIcon icon = new ImageIcon("D:\\Documents\\2023\\pf_final_project\\main\\images\\Zach.jpg");
        Image scaledImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(icon);

        // GridBagConstraints for image label
        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.gridx = 0;
        gbcImage.gridy = 0;
        add(imageLabel, gbcImage);

        // Add your components or content here if needed
        JLabel label = new JLabel("Dmid is a dating application for people who are desperate");

        // GridBagConstraints for label
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 1;
        gbcLabel.insets = new Insets(10, 0, 10, 0); // Add some space above and below the label
        add(label, gbcLabel);

        JButton homeStartButton = new JButton("Start");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton logoutButton = new JButton("Log out");

        // Set preferred size for the buttons to make them smaller and pink background
        Dimension buttonSize = new Dimension(100, 30);
        homeStartButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        // Add spaces between buttons
        int space = 10;
        Insets buttonInsets = new Insets(0, space, 0, space);
        homeStartButton.setMargin(buttonInsets);
        updateButton.setMargin(buttonInsets);
        deleteButton.setMargin(buttonInsets);
        logoutButton.setMargin(buttonInsets);

        // Use BoxLayout for button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(homeStartButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        // GridBagConstraints for button panel
        GridBagConstraints gbcButtonPanel = new GridBagConstraints();
        gbcButtonPanel.gridx = 0;
        gbcButtonPanel.gridy = 2;
        gbcButtonPanel.anchor = GridBagConstraints.EAST; // Align to the right
        add(buttonPanel, gbcButtonPanel); // Add the button panel instead of individual buttons

        getContentPane().setBackground(Color.PINK);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming you have a method to get the user's email in your DatingProfile class
                String userEmail = getUserEmail(); // Implement this method to get the email
                System.out.print(userEmail);
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
                        JOptionPane.showMessageDialog(delframe, "Account Deleted");
                        del_user.deleteUser(userEmail);
                        delframe.dispose(); // Close the confirmation frame
    
                        // Use the email to create the DatingProfile instance
                        DatingProfile datingProfile = new DatingProfile(userEmail);
                        //datingProfile.setVisible(true);
                        // You may also close or hide the homepage frame if needed
                    }
                });
            }
        });
    
        // Implement this method to get the user's email from your DatingProfile class
    }
    
    private String getUserEmail() {
        // Return the user's email; replace this with your actual implementation
        String retrieveUserSQL = "SELECT name, age, location FROM sign_up WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement(retrieveUserSQL)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

        return "user@example.com";
    }

    public void show_h(){
        setVisible(true);
    }

    
}
