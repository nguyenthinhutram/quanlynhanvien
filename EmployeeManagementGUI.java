package DACS1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class EmployeeManagementUI extends JFrame {

	private Connection connect;
	private JMenu nhansu, thongke, dangnhap;
	private JMenuItem danhsach, thongtin, chamcong;
	private JMenuBar control;
	private JTable table;
    private DefaultTableModel model;
    private JTextField searchTextField, idField, nameField, genderField, phoneField, emailField, addressField;
	private BorderLayout borderlayout;
	private JPanel trangchu, baocao, nhanvien, buttonPanel, itemsGroup, titlePanel, buttonJPanel, formPanel;
	private JButton themButton, suaButton, xoaButton, timButton, saveButton, cancelButton;
	private JTabbedPane tabs;
	private ImageIcon icon;
	private JFrame addFrame;
	private JLabel titleLabel, idLabel, nameLabel, dobLabel, genderLabel, phoneLabel, emailLabel, addressLabel, dayLabel, monthLabel, yearLabel;
	private ButtonGroup genderGroup;
	private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
	private JComboBox<String> day, month, year, position, department, status, education;

	public EmployeeManagementUI() {
		setTitle("Quản lí thông tin nhân viên");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
		UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 17));
		UIManager.put("Button.background", new Color(0, 191, 255));
		UIManager.put("JTextField.font", new Font("Arial", Font.PLAIN, 18));
		UIManager.put("JTextField.background", Color.WHITE);

        icon = new ImageIcon("C:\\HOC_PHAN_HK2\\DO AN CO SO (1)\\DOANCOSO1\\src\\DACS1\\Aha-Soft-Free-Large-Boss-Global-Manager.256.png");

        if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
            System.err.println("⚠ Ảnh icon không tìm thấy!");
        } else {
            setIconImage(icon.getImage());
        }

		tabs = new JTabbedPane();
        tabs.add("Trang chủ", createTrangChuTab());
        tabs.add("Nhân viên", createNhanVienTab());
        tabs.add("Báo cáo", createBaoCaoTab());

		add(tabs);

		loadTable();
        connectToDatabase();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void connectToDatabase() {
	        String url = "jdbc:mysql://localhost:3306/Employee_Management";
	        String user = "root";
	        String password = "";  

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); 
	            connect= DriverManager.getConnection(url, user, password);
	            System.out.println("Database connection successful!");
	            setVisible(true);
	        } catch (ClassNotFoundException e) {
	            JOptionPane.showMessageDialog(this, "MySQL Driver not found! Make sure the driver is in your classpath.", "Error", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Database connection failed! Please check your settings.", "Error", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	        }
	}

	private void loadTable() {
        try {
            model.setRowCount(0);
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM nhanvien");
            while (rs.next()) {
                model.addRow(new Object[]{
					rs.getInt("manhanvien"),
					rs.getString("hovaten"),
					rs.getDate("ngaysinh"),
					rs.getString("gioitinh"),
					rs.getString("sodienthoai"),
					rs.getString("email"),
					rs.getString("diachi"),
					rs.getString("chucvu"),
					rs.getString("phongban"),
					rs.getBigDecimal("luongthang"),
					rs.getDate("ngaylam"),
					rs.getString("trangthai"),
					rs.getString("cccd"),
					rs.getString("trinhdohocvan"),
					rs.getString("ghichu")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private JPanel createTrangChuTab() {
		trangchu = new JPanel();
		Color cl1 = new Color(135,206,250);
		trangchu.setBackground(cl1);
		return trangchu;
	}

	private JPanel createNhanVienTab() {
        nhanvien = new JPanel();
		nhanvien.setLayout(new BorderLayout());

		itemsGroup = new JPanel();

        model = new DefaultTableModel(new Object[]{"Mã nhân viên", 
												   "Họ và tên", 
												   "Ngày sinh", 
												   "Giới tính", 
												   "Số điện thoại", 
												   "Email", 
												   "Địa chỉ", 
												   "Chức vụ", 
												   "Phòng ban", 
												   "Lương tháng", 
												   "Ngày làm", 
												   "Trạng thái", 
												   "CCCD", 
												   "Trình độ học vấn", 
												   "Ghi chú"}, 
												   50);
        table = new JTable(model);

		Color cl1 = new Color(135,206,250);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		buttonPanel.setBackground(cl1);

        themButton = new JButton("Thêm");
		themButton.setPreferredSize(new Dimension(80, 30)); 
		themButton.setBorder(new LineBorder(Color.BLACK, 2, true));
		themButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        suaButton  = new JButton("Sửa");
		suaButton.setPreferredSize(new Dimension(80, 30)); 
		suaButton.setBorder(new LineBorder(Color.BLACK, 2, true));
		suaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
		xoaButton  = new JButton("Xóa");
		xoaButton.setPreferredSize(new Dimension(80, 30)); 
		xoaButton.setBorder(new LineBorder(Color.BLACK, 2, true));
		xoaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        timButton  = new JButton("Tìm");
		timButton.setPreferredSize(new Dimension(80, 30)); 
		timButton.setBorder(new LineBorder(Color.BLACK, 2, true));
		timButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		searchTextField = new JTextField(20);
		searchTextField.setPreferredSize(new Dimension(200, 30));
		

        buttonPanel.add(themButton); 
		buttonPanel.add(suaButton); 
		buttonPanel.add(xoaButton);
        buttonPanel.add(searchTextField); 
		buttonPanel.add(timButton);

		nhanvien.add(new JScrollPane(table), BorderLayout.CENTER);
		nhanvien.add(buttonPanel, BorderLayout.NORTH);
		nhanvien.setBackground(cl1);

        themButton.addActionListener(e -> themNhanVienFrame());
        suaButton.addActionListener(e -> suaNhanVien());
        xoaButton.addActionListener(e -> xoaNhanVien());
        timButton.addActionListener(e -> timNhanVien());


        return nhanvien;
    }

	private JPanel createBaoCaoTab() {

		return baocao;
	}

	private void themNhanVienFrame() {

        /*int Manhanvien = JOptionPane.showInputDialog(this, "Mã nhân viên:");
        String Hovaten = JOptionPane.showInputDialog(this, "Họ và tên:");
        Date Ngaysinh = JOptionPane.showInputDialog(this, "Ngày sinh:");
		String Gioitinh = JOptionPane.showInputDialog(this, "Giới tính:");
		String Sodienthoai = JOptionPane.showInputDialog(this, "Số điện thoại:");
		String Email = JOptionPane.showInputDialog(this, "Email:");
		String Diachi = JOptionPane.showInputDialog(this, "Địa chỉ:");
		String Chucvu = JOptionPane.showInputDialog(this, "Chức vụ:");
		String Phongban = JOptionPane.showInputDialog(this, "Phòng ban:");
		String Luongthang = JOptionPane.showInputDialog(this, "Lương tháng:");
		String Ngaylam = JOptionPane.showInputDialog(this, "Ngày làm:");
		String Trangthai = JOptionPane.showInputDialog(this, "Trạng thái làm việc:");
		String Cccd = JOptionPane.showInputDialog(this, "Căn cước công dân:");
		String Trinhdohocvan = JOptionPane.showInputDialog(this, "Trình độ học vấn:");
		String Ghichu = JOptionPane.showInputDialog(this, "Ghi chú:");
        try {
            PreparedStatement ps = connect.prepareStatement("INSERT INTO nhanvien(ten, chucvu, phongban) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, MM); 
			ps.setString(2, chucvu); 
			ps.setString(3, phongban);
            ps.executeUpdate();
            loadTable();
        } catch (Exception e) { e.printStackTrace(); }*/

		addFrame = new JFrame("Thêm thông tin nhân viên");
		addFrame.setSize(800, 800);
		addFrame.setLocationRelativeTo(null);
		addFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		addFrame.setLayout(new BorderLayout());
		addFrame.setBackground(Color.GRAY);

		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		titlePanel.setBackground(new Color(135, 206, 250));
		titleLabel = new JLabel("THÊM THÔNG TIN NHÂN VIÊN");

		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setForeground(Color.BLACK);

		titlePanel.add(titleLabel);
		
		// Add your form fields here (e.g., JTextField, JComboBox, etc.)
		// Example:
		// JTextField nameField = new JTextField(20);	
		// formPanel.add(new JLabel("Họ và tên:"));
		// formPanel.add(nameField);
		// Add more fields as needed
		// ...
		// Add the form panel to the center of the addFrame
		formPanel = new JPanel(null);
		formPanel.setBackground(new Color(135, 206, 250));

		idLabel = new JLabel("1. Mã nhân viên (*)");
		idLabel.setForeground(Color.BLACK);
		idLabel.setBounds(50, 20, 700, 20);
		idField = new JTextField(20);
		idField.setBounds(50, 45, 700, 20);

		nameLabel = new JLabel("2. Họ và tên");
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setBounds(50, 70, 700, 20);
		nameField = new JTextField(20);
		nameField.setBounds(50, 95, 700, 20);

		dobLabel = new JLabel("3. Ngày sinh");
		dobLabel.setBounds(50, 120, 700, 20);

		dayLabel = new JLabel("Day"); 
        dayLabel.setBounds(50,145,30,20);  
		day = new JComboBox(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
										  "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
										  "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		day.setBounds(85, 145, 60, 20);

        monthLabel = new JLabel("Month");
        monthLabel.setBounds(150,145,30,20); 
		month = new JComboBox(new String[] {"01", "02", "03", "04", "05", "06",
										    "07", "08", "09", "10", "11", "12"});
		month.setBounds(185, 145, 60, 20);

        yearLabel = new JLabel("Year");
        yearLabel.setBounds(250,145,30,20); 
		year = new JComboBox(new String[] {"1990", "1991", "1992", "1993", "1994", "1995",
										   "1996", "1997", "1998", "1999", "2000", "2001",
										   "2002", "2003", "2004", "2005", "2006", "2007", 
										   "2008", "2009", "2010", "2011", "2012", "2013",
										   "2014", "2015", "2016", "2017", "2018", "2019", 
										   "2020", "2021", "2022", "2023", "2024", "2025"});
		year.setBounds(285, 145, 70, 20);

		genderLabel = new JLabel("4. Giới tính");
		genderLabel.setBounds(50, 170, 700, 20);
		genderGroup = new ButtonGroup();
		maleRadioButton = new JRadioButton("Nam");
		maleRadioButton.setBounds(50, 195, 100, 20);
		maleRadioButton.setBackground(new Color(135, 206, 250));			
		femaleRadioButton = new JRadioButton("Nữ");
		femaleRadioButton.setBounds(150, 195, 100, 20);
		femaleRadioButton.setBackground(new Color(135, 206, 250));
		otherRadioButton = new JRadioButton("Khác");
		otherRadioButton.setBounds(250, 195, 100, 20);
		otherRadioButton.setBackground(new Color(135, 206, 250));
		genderGroup.add(maleRadioButton);
		genderGroup.add(femaleRadioButton);
		genderGroup.add(otherRadioButton);

		formPanel.add(idLabel);
		formPanel.add(idField);
		formPanel.add(nameLabel);
		formPanel.add(nameField);
		formPanel.add(dobLabel);
		formPanel.add(dayLabel);
		formPanel.add(day);
		formPanel.add(monthLabel);
		formPanel.add(month);
		formPanel.add(yearLabel);
		formPanel.add(year);
		formPanel.add(genderLabel);
		formPanel.add(maleRadioButton);
		formPanel.add(femaleRadioButton);
		formPanel.add(otherRadioButton);

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		buttonPanel.setBackground(new Color(135, 206, 250));

		saveButton = new JButton("Lưu");
		cancelButton = new JButton("Hủy");	
		saveButton.setSize(50, 30);
		cancelButton.setSize(50, 30);
		buttonPanel.add(saveButton);	
		buttonPanel.add(cancelButton);

		addFrame.add(titlePanel, BorderLayout.NORTH);
		addFrame.add(formPanel, BorderLayout.CENTER);
		addFrame.add(buttonPanel, BorderLayout.SOUTH);	


		saveButton.addActionListener(e -> {
			// Handle save action here
			// You can retrieve the values from the form fields and save them to the database
			// Example:
			// String name = nameField.getText();
			// Save to database logic here
			JOptionPane.showMessageDialog(addFrame, "Thông tin nhân viên đã được lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			addFrame.dispose(); // Close the add frame after saving
		});

		cancelButton.addActionListener(e -> {
			addFrame.dispose(); 
		});

		addFrame.setResizable(false);	
		addFrame.setVisible(true);
    }

	private void suaNhanVien() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Chức năng này chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			int manhanvien = (int) model.getValueAt(selectedRow, 0);
			String hovaten = (String) model.getValueAt(selectedRow, 1);
			// Các trường khác...
			// Hiển thị hộp thoại để sửa thông tin nhân viên
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void xoaNhanVien() {
		

		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Chức năng này chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			int manhanvien = (int) model.getValueAt(selectedRow, 0);
			// Xóa nhân viên khỏi cơ sở dữ liệu
			// Cập nhật lại bảng
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		// TODO Auto-generated method stub	


	}

	private void timNhanVien() {
		String searchText = searchTextField.getText().trim();
			if (searchText.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					model.setRowCount(0);
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM nhanvien WHERE hovaten LIKE '%" + searchText + "%'");
					while (rs.next()) {
						model.addRow(new Object[]{
							rs.getInt("manhanvien"),
							rs.getString("hovaten"),
							rs.getDate("ngaysinh"),
							rs.getString("gioitinh"),
							rs.getString("sodienthoai"),
							rs.getString("email"),
							rs.getString("diachi"),
							rs.getString("chucvu"),
							rs.getString("phongban"),
							rs.getBigDecimal("luongthang"),
							rs.getDate("ngaylam"),
							rs.getString("trangthai"),
							rs.getString("cccd"),
							rs.getString("trinhdohocvan"),
							rs.getString("ghichu")
						});
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}                                                                                                                                                                                           
}

