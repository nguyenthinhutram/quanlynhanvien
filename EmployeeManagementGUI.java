package PROJECT_FINAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EmployeeManagementGUI extends JFrame {
    private Connection connect;

    public EmployeeManagementGUI() {
        setTitle("Employee Management Software");
        setSize(800, 600); // Tăng kích thước mặc định
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Mở rộng toàn màn hình
        
        // Thiết lập icon
        ImageIcon icon = new ImageIcon(getClass().getResource("Aha-Soft-Free-Large-Boss-Global-Manager.256.png"));
        if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
            System.err.println("⚠ Ảnh icon không tìm thấy!");
        } else {
            setIconImage(icon.getImage());
        }

        // Kết nối CSDL
        //connectToDatabase();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/Employee_Management";
        String user = "root"; // Default XAMPP MySQL user
        String password = "duykhai006";  // Default XAMPP password is empty

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the JDBC Driver
            connect= DriverManager.getConnection(url, user, password);
            System.out.println("Database connection successful!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found! Make sure the driver is in your classpath.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed! Please check your settings.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new EmployeeManagementGUI();
    }
}
