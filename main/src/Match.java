import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Match extends JFrame {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dmid";
    static final String USER = "root";
    static final String PASS = "";
    
    private static final Map<String, Set<String>> USER_INTERESTS = new HashMap<>();
    private static final String[] IMAGE_PATHS = {
            "images/Alice.jpg",
            "images/Diana.jpg",
            "images/Bea.jpg",
            "images/Lize.jpg",
            "images/Nayah.jpg",
            "images/Luwan.jpg",
            "images/Zach.jpg",
            "images/Pierre.jpg",
            "images/Charlie.jpg",
            "images/Bob.jpg",
    };

    private static final String[] NAMES = {
            "Alice",
            "Diana",
            "Lize",
            "Nayah",
            "Bea",
            "Luwan",
            "Pierre",
            "Zach",
            "Charlie",
            "Bob",
    };

    static {
        USER_INTERESTS.put("Alice", new HashSet<>(Arrays.asList("anime", "music")));
        USER_INTERESTS.put("Diana", new HashSet<>(Arrays.asList("anime", "sports")));
        USER_INTERESTS.put("Lize", new HashSet<>(Arrays.asList("sports", "poetry")));
        USER_INTERESTS.put("Nayah", new HashSet<>(Arrays.asList("music", "games")));
        USER_INTERESTS.put("Bea", new HashSet<>(Arrays.asList("poetry", "anime")));
        USER_INTERESTS.put("Luwan", new HashSet<>(Arrays.asList("sports", "music")));
        USER_INTERESTS.put("Pierre", new HashSet<>(Arrays.asList("anime", "sports")));
        USER_INTERESTS.put("Zach", new HashSet<>(Arrays.asList("poetry", "music")));
        USER_INTERESTS.put("Charlie", new HashSet<>(Arrays.asList("anime", "music")));
        USER_INTERESTS.put("Bob", new HashSet<>(Arrays.asList("music", "games")));
    }
    private JFrame frame;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel interestsLabel;
    private JButton chatButton;
    private JButton skipButton;
    
    private String currentMatchedUser;
    
    public Match() {
        System.out.println("Im in Match page");
        frame = new JFrame("Match Found!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());
        
        imageLabel = new JLabel();
        frame.add(imageLabel, BorderLayout.CENTER);
        
        nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(nameLabel, BorderLayout.NORTH);
        
        interestsLabel = new JLabel();
        interestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(interestsLabel, BorderLayout.SOUTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        chatButton = new JButton("Chat with Them");
        chatButton.addActionListener(e -> startChat());
        skipButton = new JButton("Skip");
        skipButton.addActionListener(e -> displayRandomMatch());
        
        buttonPanel.add(chatButton);
        buttonPanel.add(skipButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void displayRandomMatch() {
        Random random = new Random();
        int randomIndex = random.nextInt(IMAGE_PATHS.length);
        String imagePath = IMAGE_PATHS[randomIndex];
        String name = NAMES[randomIndex];
        Set<String> interests = USER_INTERESTS.get(name);
        
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        
        imageLabel.setIcon(icon);
        nameLabel.setText("Matched with: " + name);
        
        if (interests != null) {
            interestsLabel.setText("Interests: " + interests.toString());
        } else {
            interestsLabel.setText("No interests listed.");
        }
        
        currentMatchedUser = name;
        chatButton.setEnabled(true); 

    }
    
    private void startChat() {
        if (currentMatchedUser != null) {
            JOptionPane.showMessageDialog(frame, "Starting chat with " + currentMatchedUser + "!");
        }
    }
    
    public void showApp() {
        frame.setVisible(true);
    }
}

