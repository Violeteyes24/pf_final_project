import javax.swing.JOptionPane;

public class AgeValidator {

    public static boolean isValidAge(String ageText) {
        try {
            int age = Integer.parseInt(ageText);
            return age > 0;
        } catch (NumberFormatException e) {
            return false; // If the input is not a valid integer
        }
    }

    public static void showInvalidAgeMessage() {
        JOptionPane.showMessageDialog(null, "Age must be a positive integer.", "Invalid Age", JOptionPane.ERROR_MESSAGE);
    }
}
