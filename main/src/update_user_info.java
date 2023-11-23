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

    public update_user_info(String userEmail) {
        setTitle("Update User Information");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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

        // Set up layout
        setLayout(new GridLayout(6, 2, 10, 10));
        getContentPane().setBackground(Color.PINK);
        add(nameLabel);
        add(newNameField);
        add(ageLabel);
        add(newAgeField);
        add(sexLabel);
        add(newSexField);
        add(locationLabel);
        add(newLocationField);
        add(passwordLabel);
        add(newPasswordField);
        add(new JLabel()); // Placeholder
        add(updateButton);

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
    }
}
