import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class update_user_info extends JFrame {

    private JTextField newNameField;
    private JTextField newAgeField;
    private JComboBox<String> newSexField;
    private JTextField newLocationField;
    private JPasswordField newPasswordField;
    private JButton backButton;

    public update_user_info(String userEmail) {
        setTitle("Update User Information");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 10, 1));
        mainPanel.setBackground(Color.PINK);

        // Create components
        JLabel nameLabel = new JLabel("Enter new name:");
        JLabel ageLabel = new JLabel("Enter new age:");
        JLabel sexLabel = new JLabel("Select new sex:");
        JLabel locationLabel = new JLabel("Enter new location:");
        JLabel passwordLabel = new JLabel("Enter new password:");

        newNameField = new JTextField(20);
        newAgeField = new JTextField(20);
        String[] sexOptions = {"male", "female", "others"};
        newSexField = new JComboBox<>(sexOptions);
        newLocationField = new JTextField(20);
        newPasswordField = new JPasswordField(20);

        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("<<");
        backButton.setBounds(100,100,100,80);

        // Set up layout
        setLayout(new GridLayout(6, 2, 10, 10));
        getContentPane().setBackground(Color.PINK);
        mainPanel.add(nameLabel);
        mainPanel.add(newNameField);
        mainPanel.add(ageLabel);
        mainPanel.add(newAgeField);
        mainPanel.add(sexLabel);
        mainPanel.add(newSexField);
        mainPanel.add(locationLabel);
        mainPanel.add(newLocationField);
        mainPanel.add(passwordLabel);
        mainPanel.add(newPasswordField);
        mainPanel.add(new JLabel()); // Placeholder
        mainPanel.add(updateButton);
        mainPanel.add(backButton);

        setContentPane(mainPanel);

        int padding = 40;
        mainPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        // Add action listener to the update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = newNameField.getText();
                int newAge = Integer.parseInt(newAgeField.getText());
                String newSex = (String) newSexField.getSelectedItem();
                String newLocation = newLocationField.getText();
                String newPassword = new String(newPasswordField.getPassword());

                // Call the update method from the update class
                update.updateUserInfo(userEmail, newName, newAge, newSex, newLocation, newPassword);

                // Close the update window
                setVisible(false);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homepage h = new homepage();
                
                setVisible(false); // Close the signup window
                dispose();  // Release system resources
                
                // Open the login window
                System.out.println("I am on homepage from udpate_user");
                h.setVisible(true);
            }
        });
    }
}
