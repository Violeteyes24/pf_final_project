import javax.swing.*;
import java.awt.*;

public class DatingProfile extends JFrame {

    public DatingProfile(String name, int age, String location, String interests, String bio) {
        setTitle("Dating Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name: " + name);
        JLabel ageLabel = new JLabel("Age: " + age);
        JLabel locationLabel = new JLabel("Location: " + location);
        JLabel interestsLabel = new JLabel("Interests: " + interests);
        JLabel bioLabel = new JLabel("Biography: " + bio);

        // Center text
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        interestsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePanel.add(nameLabel);
        profilePanel.add(ageLabel);
        profilePanel.add(locationLabel);
        profilePanel.add(interestsLabel);
        profilePanel.add(bioLabel);

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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatingProfile(
                "Zach",
                60,
                "Sa Puso Mo",
                "Coding, Genshin Impact",
                "I have many ladies"
        ));
    }
}
