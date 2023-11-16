import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*; // for gui, usually Jlabel, Jtexfield, access to its built in methods
import java.awt.event.*; // Action Listener
import java.awt.*; // font, maint font and color

public class create_db {
    static final String DB_URL = "jdbc:mysql://localhost:3306/dmid";
    static final String USER = "root";
    static final String PASS = "";
    public static void main(String[] args){

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmnt = conn.createStatement();)
        
        {
            String createdb_sql = "CREATE DATABASE IF NOT EXISTS dmid";
            stmnt.executeUpdate(createdb_sql);
            System.out.println("Database created succesfully...");

            String useDBSQL = "USE dmid";
            stmnt.executeUpdate(useDBSQL);
            System.out.println("Using database 'dmid'...");

            String signUpTableSQL = "CREATE TABLE IF NOT EXISTS sign_up("
                + "name VARCHAR(50)NOT NULL, "
                + "age INT(11) NOT NULL, "
                + "sex ENUM('male', 'female', 'others') NOT NULL, "
                + "location VARCHAR(100) NOT NULL, "
                + "email VARCHAR(50) NOT NULL, "
                + "passwordField VARCHAR(50) NOT NULL, "
                + "PRIMARY KEY (email))";
            stmnt.executeUpdate(signUpTableSQL);
            System.out.println("'sign_up' table created successfully...");

            // Create 'interests' table
            String interestsTableSQL = "CREATE TABLE IF NOT EXISTS interests ("
                + "interest_1 ENUM('anime', 'sports', 'music', 'games', 'poetry') NOT NULL, "
                + "interest_2 ENUM('anime', 'sports', 'music', 'games', 'poetry') NOT NULL, "
                + "interest_3 ENUM('anime', 'sports', 'music', 'games', 'poetry') NOT NULL, "
                + "email VARCHAR(50) NOT NULL, "
                + "CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES sign_up(email) ON DELETE CASCADE ON UPDATE CASCADE)";

            stmnt.executeUpdate(interestsTableSQL);
            System.out.println("'interests' table created successfully...");
                } catch(SQLException e){
                    e.printStackTrace();
                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new sign_in_form().setVisible(true);
                    }
                });

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new log_in().setVisible(true);
                    }
                });
    }
}
