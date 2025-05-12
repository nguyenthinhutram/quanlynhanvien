package DACS1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class loginform extends JFrame{
	
	BackgroundPanel loginPanel;
	JLabel title1, phone1Label, pass1Label;
	JTextField phone1Field;
	JPasswordField pass1Field;
	JButton loginButton, registerButton;
	Connection connect;
	Border lineBorder;
	
	public loginform() {
		setTitle("Đăng nhập vào hệ thống");
        setSize(490,310);
        setLocationRelativeTo(null);
        
        connectToData();
        ImageIcon icon =new ImageIcon(loginform.class.getResource("Aha-Soft-Free-Large-Boss-Global-Manager.256.png"));
		setIconImage(icon.getImage());
        
        UIManager.put("Label.font", new Font("Time New Roman", Font.BOLD + Font.ITALIC, 16));
        UIManager.put("Button.font", new Font("Time New Roman", Font.BOLD, 15));
        UIManager.put("TextField.font", new Font("Time New Roman", Font.BOLD, 15));
        
        lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        
        //-------------------------------Thành phần của form đăng nhập----------------------------------------
        title1 = new JLabel("ĐĂNG NHẬP");
        title1.setFont(new Font("Time New Roman", Font.BOLD, 20));
        title1.setForeground(Color.decode("#006600"));
        title1.setBounds(170, 10, 150, 40);
        /*----------------------------------------------*/
        phone1Label = new JLabel("Số điện thoại");
        phone1Label.setBounds(60,50,150,40);
        phone1Field = new JTextField();
        phone1Field.setBounds(220,50,190,40);
        /*----------------------------------------------*/
        pass1Label = new JLabel("Mật khẩu");
        pass1Label.setBounds(60,110,150,40);
        pass1Field = new JPasswordField();
        pass1Field.setBounds(220,110,190,40);
        /*----------------------------------------------*/
        loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(160,170,150,30);
        loginButton.setBorder(lineBorder);
        loginButton.setBackground(Color.decode("#EEEEEE"));
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(e -> testLogin());
        /*--------------------------------------------- */
        registerButton = new JButton("<HTML><U>Đăng kí</U></HTML>");
        registerButton.setForeground(Color.BLUE);           
        registerButton.setBorderPainted(false);              
        registerButton.setContentAreaFilled(false);        
        registerButton.setFocusPainted(false);  
        registerButton.setBounds(335,170,100,30);
        registerButton.addActionListener(e -> {
            new registerform().setVisible(true);
            dispose();
        });
        //---------------------------Tạo panel chứa form đăng nhập và thêm thành phần-------------------------
        loginPanel = new BackgroundPanel();
        loginPanel.setLayout(null);
        /*----------------------------------------------*/
        loginPanel.add(title1);
        loginPanel.add(phone1Label);
        loginPanel.add(phone1Field);
        loginPanel.add(pass1Label);
        loginPanel.add(pass1Field);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
    
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
        makeTextFieldTransparent(phone1Field);
        makeTextFieldTransparent(pass1Field);

        add(loginPanel);
	}

	private void connectToData() {
        String url = "jdbc:mysql://localhost:3306/employee_management"; // Change this to your database URL
        String user = "root"; // Default XAMPP MySQL user
        String password = "";  // Default XAMPP password is empty

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the JDBC Driver
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection successful!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL Driver not found! Make sure the driver is in your classpath.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed! Please check your settings.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
	
	
	private void testLogin() { 
		// Thông báo nêu Username trồng 
		if ((phone1Field.getText().equals("")) && (pass1Field.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "Vui lòng điển đầy đủ các thông tin bắt buộc!"); 		
		}
		else if (phone1Field.getText().equals("")) { 
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!"); 
		} 
		else if (pass1Field.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu!"); 
		} 
		else { 
			try { 
				String sql = "SELECT * FROM employee_management.login WHERE phone_number = ? AND password = ?"; 
				PreparedStatement ps = connect.prepareStatement (sql); 
				ps.setString(1, phone1Field.getText()); 
				ps.setString(2, pass1Field.getText()); 
				ResultSet rs = ps.executeQuery(); 
				if (rs.next()) { 
					JOptionPane.showMessageDialog(this, "Đăng nhập thành công!"); 
					resetLogin();
					new EmployeeManagementUI().setVisible(true); 
					setVisible(false);
				} else { 
					JOptionPane.showMessageDialog(this, "Bạn đã nhập sai số điện thoại hoặc mật khẩu, nếu bạn chưa có tài khoản hãy bấm nút đăng kí.", "Lỗi!!!", JOptionPane.ERROR_MESSAGE); 
				}
			} catch (Exception e) { 
				System.out.println(e); 
			}
		}
	}
	
    private void resetLogin() {
        phone1Field.setText("");
        pass1Field. setText("");
    }
    
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        public BackgroundPanel() {
            backgroundImage = new ImageIcon("background_login.png").getImage();
            setLayout(new BorderLayout());
        }

        /*@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }*/
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 0, 50));  
        g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }


    private void makeTextFieldTransparent(JTextField textField) {
        textField.setOpaque(false);
        textField.setBackground(new Color(0,0,0,0));
        textField.setForeground(Color.WHITE); 
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
    }   
}

