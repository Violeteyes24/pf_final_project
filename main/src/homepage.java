import java.awt.*;
import javax.swing.*;

public class homepage extends JFrame {

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
        buttonSize.colors.pink;
        homeStartButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        // Set background color for buttons
        homeStartButton.setBackground(Color.PINK);
        updateButton.setBackground(Color.PINK);
        deleteButton.setBackground(Color.PINK);
        logoutButton.setBackground(Color.PINK);

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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            homepage h = new homepage();
            h.setVisible(true);
        });
    }
}
