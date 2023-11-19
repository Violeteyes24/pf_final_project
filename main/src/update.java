import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class update {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dmid";
    static final String USER = "root";
    static final String PASS = "";

    public static void updateUserInfo(String userEmail, String newName, int newAge, String newSex, String newLocation, String newPassword) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                // Create a prepared statement
                String useDBSQL = "USE dmid";
                try (PreparedStatement useDBStatement = conn.prepareStatement(useDBSQL)) {
                    useDBStatement.executeUpdate();
                }

                // Assuming you have a table named 'sign_up' to store user information
                String updateSQL = "UPDATE sign_up SET name = ?, age = ?, sex = ?, location = ?, passwordField = ? WHERE email = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(updateSQL)) {
                    // Set values for the prepared statement
                    preparedStatement.setString(1, newName);
                    preparedStatement.setInt(2, newAge);
                    preparedStatement.setString(3, newSex);
                    preparedStatement.setString(4, newLocation);
                    preparedStatement.setString(5, newPassword);
                    preparedStatement.setString(6, userEmail);

                    // Execute the statement
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "User information updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found or update failed.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating user information.");
        }
    }
}
