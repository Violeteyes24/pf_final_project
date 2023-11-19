import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class delete {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dmid";
    static final String USER = "root";
    static final String PASS = "";

    public static void deleteUser(String userEmail) {
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

                String deleteSQL = "DELETE FROM sign_up WHERE email = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
                    // Set values for the prepared statement
                    preparedStatement.setString(1, userEmail);
                    System.out.println("delete function attempt to get email " + userEmail);
                    // Execute the statement
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "User deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found or deletion failed.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting user.");
        }
    }
}
