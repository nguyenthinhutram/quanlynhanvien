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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class registerform extends JFrame{
	
	BackgroundPanel registerPanel;
	JLabel title2, nameLabel, phone2Label, emailLabel, pass2Label;
	JTextField nameField, phone2Field, emailField, pass2Field;
	JButton saveButton, cancelButton;
	Connection connect;
	Border lineBorder;
	
	public registerform() {
		setTitle("Đăng ký tài khoản");
        setSize(490,420);
        setLocationRelativeTo(null);
        
        connectToData();
        ImageIcon icon = new ImageIcon(loginform.class.getResource("Aha-Soft-Free-Large-Boss-Global-Manager.256.png"));
		setIconImage(icon.getImage());
        
        UIManager.put("Label.font", new Font("Time New Roman", Font.BOLD + Font.ITALIC, 16));
        UIManager.put("Button.font", new Font("Time New Roman", Font.BOLD, 15));
        UIManager.put("TextField.font", new Font("Time New Roman", Font.BOLD, 15));
        lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        
        //-------------------------------Thành phần của form đăng kí------------------------------------------
        title2 = new JLabel("ĐĂNG KÝ");
        title2.setFont(new Font("Time New Roman", Font.BOLD, 20));
        title2.setForeground(Color.decode("#006600"));
        title2.setBounds(185, 10, 200, 40);
        /*----------------------------------------------*/
        nameLabel = new JLabel("Họ và tên");
        nameLabel.setBounds(60,50,150,40);
        nameField = new JTextField();
        nameField.setBounds(220,50,175,40);
        /*----------------------------------------------*/
        phone2Label = new JLabel("Số điện thoại");
        phone2Label.setBounds(60,110,150,40);
        phone2Field = new JTextField();
        phone2Field.setBounds(220,110,175,40);
        /*----------------------------------------------*/
        emailLabel = new JLabel("Email");
        emailLabel.setBounds(60,170,150,40);
        emailField = new JTextField();
        emailField.setBounds(220,170,175,40);
        /*----------------------------------------------*/
        pass2Label = new JLabel("Mật khẩu");
        pass2Label.setBounds(60,230,150,40);
        pass2Field = new JTextField();
        pass2Field.setBounds(220,230,175,40);
        /*--------------------------------------------- */
        saveButton = new JButton("Lưu lại");
        saveButton.setBounds(60,290,150,30);
        saveButton.setBorder(lineBorder);
        saveButton.setBackground(Color.decode("#EEEEEE"));
        saveButton.addActionListener(e -> addAccount());
        /*--------------------------------------------- */
        cancelButton = new JButton("Hủy bỏ");
        cancelButton.setBounds(245,290,150,30);
        cancelButton.setBorder(lineBorder);
        cancelButton.setBackground(Color.decode("#EEEEEE"));
        cancelButton.addActionListener(e -> {
            resetRegister();
            SwingUtilities.invokeLater(() -> {
                new loginform().setVisible(true);
            });
            dispose();
        });
        //---------------------------Tạo panel chứa form đăng kí và thêm thành phần---------------------------
        BackgroundPanel registerPanel = new BackgroundPanel();
        registerPanel.setLayout(null);
        /*----------------------------------------------*/
        registerPanel.add(title2);
        registerPanel.add(nameLabel);
        registerPanel.add(nameField);
        registerPanel.add(phone2Label);
        registerPanel.add(phone2Field);
        registerPanel.add(emailLabel);
        registerPanel.add(emailField);
        registerPanel.add(pass2Label);
        registerPanel.add(pass2Field);
        registerPanel.add(saveButton);
        registerPanel.add(cancelButton);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        makeTextFieldTransparent(nameField);
        makeTextFieldTransparent(phone2Field);
        makeTextFieldTransparent(emailField);
        makeTextFieldTransparent(pass2Field);
        
        add(registerPanel);
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

	
	void addAccount() {
    	String name  = nameField.  getText();
        String phone = phone2Field.getText();
        String email = emailField. getText();
        String pass  = pass2Field. getText();
        
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi!!!", JOptionPane.ERROR_MESSAGE);
        } else {
        	try {               
                // Insert into database
                String sql = "INSERT INTO employee_management.login(full_name, phone_number, email, password) VALUES (?,?,?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, name) ;
                preparedStatement.setString(2, phone);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, pass) ;  

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows != 0) {
                    JOptionPane.showMessageDialog(this, "Bạn đã đăng kí tài khoản thành công!", "Chúc mừng!", JOptionPane.INFORMATION_MESSAGE);
                    resetRegister();
                    SwingUtilities.invokeLater(() -> {
                        new loginform().setVisible(true);
                    });
                    dispose();
                } else if (affectedRows == 0) {
                	JOptionPane.showMessageDialog(this, "Đăng kí tài khoản thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                preparedStatement.close();
            } catch (SQLException e) {
            	JOptionPane.showMessageDialog(this, "Đăng kí tài khoản thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
	}

    private void resetRegister() {
        nameField.  setText("");
        phone2Field.setText("");
        emailField. setText("");
        pass2Field. setText("");
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
            // Vẽ ảnh nền trước
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Tạo lớp mờ phủ lên ảnh nền
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 0, 100));  // Màu đen, độ mờ 100 (trong khoảng 0-255)
        g2d.fillRect(0, 0, getWidth(), getHeight());
        }

    }

    private void makeTextFieldTransparent(JTextField textField) {
        textField.setOpaque(false);
        textField.setBackground(new Color(0,0,0,0));
        textField.setForeground(Color.WHITE); // Màu chữ trắng cho dễ nhìn
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE)); // Viền dưới trắng (kiểu modern)
    }   
}

