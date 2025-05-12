package DACS1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class addEmployee extends JFrame{
    private JPanel titlePanel, formPanel, buttonPanel, imagePanel;
    private JLabel titleLabel, idLabel, nameLabel, dobLabel, genderLabel, phoneLabel, emailLabel, addressLabel, positionLabel, departmentLabel, statusLabel, educationLabel;    
    private JTextField idField, nameField, phoneField, emailField, addressField;
    private JComboBox<String> day, month, year, position, department, status, education;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private ButtonGroup genderGroup;
    private JLabel imageLabel, dayLabel, monthLabel, yearLabel;
    private JButton chooseImageButton, saveButton, cancelButton;
    private ImageIcon imageIcon;
    private String imagePath = null; // Path to the selected image
    
    public addEmployee() {
        setTitle("Thêm nhân viên");
        setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.GRAY);

		/*-------------TIÊU ĐỀ------------*/
		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		titlePanel.setBackground(new Color(135, 206, 250));
		titleLabel = new JLabel("THÊM THÔNG TIN NHÂN VIÊN");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setForeground(Color.BLACK);
		titlePanel.add(titleLabel);
		/*-------------FORM ĐIỀN THÔNG TIN----------*/
		formPanel = new JPanel(null);
		formPanel.setBackground(new Color(135, 206, 250));
        /*---------------MÃ NHÂN VIÊN----------------- */
		idLabel = new JLabel("1. Mã nhân viên (*)");
		idLabel.setForeground(Color.BLACK);
		idLabel.setBounds(50, 20, 400, 20);

		idField = new JTextField(20);
		idField.setBounds(50, 45, 400, 20);
		idField.setBorder(new LineBorder(Color.BLACK, 1, true));
		idField.setToolTipText("Nhập mã nhân viên của bạn");
		idField.setPreferredSize(new Dimension(400, 20)); 
        /*---------------HỌ VÀ TÊN---------------- */
		nameLabel = new JLabel("2. Họ và tên");
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setBounds(50, 70, 400, 20);

		nameField = new JTextField(20);
		nameField.setBounds(50, 95, 400, 20);
		nameField.setToolTipText("Nhập họ và tên của bạn");
		nameField.setPreferredSize(new Dimension(400, 20));
		nameField.setBackground(Color.WHITE);
		nameField.setBorder(new LineBorder(Color.BLACK, 1, true));
		nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        /*-----------------NGÀY SINH------------------- */
		dobLabel = new JLabel("3. Ngày sinh");
		dobLabel.setBounds(50, 120, 400, 20);
		dobLabel.setForeground(Color.BLACK);
		dobLabel.setPreferredSize(new Dimension(400, 20));
		dobLabel.setBackground(new Color(135, 206, 250));

		dayLabel = new JLabel("Day"); 
        dayLabel.setBounds(50,145,30,20);  

		day = new JComboBox(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
										  "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
										  "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		day.setBounds(80, 145, 40, 20);

        monthLabel = new JLabel("Month");
        monthLabel.setBounds(140,145,40,20); 

		month = new JComboBox(new String[] {"01", "02", "03", "04", "05", "06",
										    "07", "08", "09", "10", "11", "12"});
		month.setBounds(185, 145, 40, 20);

        yearLabel = new JLabel("Year");
        yearLabel.setBounds(245,145,30,20); 

		year = new JComboBox(new String[] {"1990", "1991", "1992", "1993", "1994", "1995",
										   "1996", "1997", "1998", "1999", "2000", "2001",
										   "2002", "2003", "2004", "2005", "2006", "2007", 
										   "2008", "2009", "2010", "2011", "2012", "2013",
										   "2014", "2015", "2016", "2017", "2018", "2019", 
										   "2020", "2021", "2022", "2023", "2024", "2025"});
		year.setBounds(285, 145, 60, 20);
        /*-------------------GIỚI TÍNH------------------- */
		genderLabel = new JLabel("4. Giới tính");
		genderLabel.setBounds(50, 170, 400, 20);
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

		maleRadioButton.setSelected(true); 
		maleRadioButton.setActionCommand("Nam");
		femaleRadioButton.setActionCommand("Nữ");
		otherRadioButton.setActionCommand("Khác");
        /*--------------------SỐ ĐIỆN THOẠI--------------------- */
		phoneLabel = new JLabel("5. Số điện thoại");
		phoneLabel.setForeground(Color.BLACK);
		phoneLabel.setBounds(50, 220, 400, 20);

		phoneField = new JTextField(20);
		phoneField.setBounds(50, 245, 400, 20);
		phoneField.setToolTipText("Nhập số điện thoại của bạn");
		phoneField.setBorder(new LineBorder(Color.BLACK, 1, true));
		/*---------------------------ẢNH THẺ-------------------- */
		imagePanel = new JPanel();
		imagePanel.setBounds(470, 20, 300, 300);
		imagePanel.setBackground(new Color(135, 206, 250));
		imagePanel.setBorder(BorderFactory.createTitledBorder("Ảnh thẻ"));

		imageLabel = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(500, 300));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		imageLabel.setBackground(Color.WHITE);
		imageLabel.setOpaque(true);
		imageLabel.setPreferredSize(new Dimension(200, 200));

		chooseImageButton = new JButton("Chọn ảnh");
		chooseImageButton.addActionListener(e -> chooseImage());

		imagePanel.add(imageLabel, BorderLayout.CENTER);
		imagePanel.add(chooseImageButton, BorderLayout.SOUTH);
        /*------------------------EMAIL-------------------------- */
		emailLabel = new JLabel("6. Email");
		emailLabel.setForeground(Color.BLACK);
		emailLabel.setBounds(50, 270, 700, 20);

		emailField = new JTextField(20);
		emailField.setBounds(50, 295, 700, 20);
		emailField.setToolTipText("Nhập địa chỉ email của bạn");
		emailField.setBorder(new LineBorder(Color.BLACK, 1, true));
        /*------------------------ĐỊA CHỈ------------------- */
		addressLabel = new JLabel("7. Địa chỉ");
		addressLabel.setForeground(Color.BLACK);
		addressLabel.setBounds(50, 320, 700, 20);

		addressField = new JTextField(20);
		addressField.setBounds(50, 345, 700, 20);
		addressField.setToolTipText("Nhập địa chỉ của bạn");
		addressField.setBorder(new LineBorder(Color.BLACK, 1, true));
        /*--------------------------CHỨC VỤ------------------------- */
		positionLabel = new JLabel("8. Chức vụ");
		positionLabel.setForeground(Color.BLACK);
		positionLabel.setBounds(50, 370, 700, 20);

		position = new JComboBox<>(new String[]{"Giám đốc điều hành", "Giám đốc tài chính", "Giám đốc Marketing", 
												"Giám đốc pháp lý", "Giám đốc thương mại", "Giám đốc vận hành", 
												"Giám đốc chi nhánh", "Trợ lí giám đốc", "Trưởng phòng", 
												"Trưởng nhóm", "Nhân viên", "Lễ tân", "Bảo vệ", "Tài xế"});
		position.setBounds(50, 395, 700, 20);
		position.setToolTipText("Chọn chức vụ của bạn");
		position.setPreferredSize(new Dimension(700, 20));
		position.setBackground(Color.WHITE);
		position.setBorder(new LineBorder(Color.BLACK, 1, true));
		position.setFont(new Font("Arial", Font.PLAIN, 16));
		position.setFocusable(false);
		position.setSelectedIndex(-1); 
		position.setEditable(false); 
		position.setFocusable(false);
        /*--------------------------PHÒNG BAN------------------------- */
		departmentLabel = new JLabel("9. Phòng ban");
		departmentLabel.setForeground(Color.BLACK);
		departmentLabel.setBounds(50, 420, 700, 20);

		department = new JComboBox<>(new String[]{"Phòng kế toán", "Phòng hành chính", "Phòng kiểm toán",
												  "Phòng chăm sóc khách hàng", "Phòng nhân sự", 
												  "Phòng Công nghệ thông tin", "Phòng quan hệ quốc tế", 
												  "Phòng Marketing", "Phòng Nghiên cứu và phát triển Sản phẩm", 
												  "Phòng kinh doanh", "Phòng thu mua"});
		department.setBounds(50, 445, 700, 20);
		department.setToolTipText("Chọn phòng ban của bạn");
		department.setPreferredSize(new Dimension(700, 20));
		department.setBackground(Color.WHITE);
		department.setBorder(new LineBorder(Color.BLACK, 1, true));
		department.setFont(new Font("Arial", Font.PLAIN, 16));
		department.setFocusable(false);
		department.setCursor(new Cursor(Cursor.HAND_CURSOR));
		department.setSelectedIndex(-1);
		department.setEditable(false); 
		department.setPreferredSize(new Dimension(700, 20)); 
		department.setFocusable(false); 
        /*--------------------------TRẠNG THÁI LÀM VIỆC------------------------- */
		statusLabel = new JLabel("10. Trạng thái làm việc");
		statusLabel.setForeground(Color.BLACK);
		statusLabel.setBounds(50, 470, 700, 20);

		status = new JComboBox<>(new String[]{"Đang làm việc", "Đã nghỉ việc", "Thử việc", "Thực tập sinh"});
		status.setBounds(50, 495, 700, 20);
		status.setToolTipText("Chọn trạng thái làm việc của bạn");
		status.setPreferredSize(new Dimension(700, 20));
		status.setBackground(Color.WHITE);
		status.setBorder(new LineBorder(Color.BLACK, 1, true));
		status.setFont(new Font("Arial", Font.PLAIN, 16));
		status.setFocusable(false);
		status.setCursor(new Cursor(Cursor.HAND_CURSOR));
		status.setSelectedIndex(-1); 
		status.setEditable(false);
		/*--------------------------TRÌNH ĐỘ HỌC VẤN------------------------- */
		educationLabel = new JLabel("11. Trình độ học vấn");
		educationLabel.setForeground(Color.BLACK);
		educationLabel.setBounds(50, 520, 700, 20);
		
		education = new JComboBox<>(new String[]{"Giáo sư", "Tiến sĩ", "Thạc sĩ", "Đại học (Kỹ sư)", 
												 "Đại học (Cử nhân)", "Cao đẳng", "Trung học phổ thông"});
		education.setBounds(50, 545, 700, 20);
		education.setToolTipText("Chọn trình độ học vấn của bạn");
		education.setPreferredSize(new Dimension(700, 20));
		education.setBackground(Color.WHITE);
		education.setBorder(new LineBorder(Color.BLACK, 1, true));
		education.setFont(new Font("Arial", Font.PLAIN, 16));
		education.setFocusable(false);
		education.setCursor(new Cursor(Cursor.HAND_CURSOR));
		education.setSelectedIndex(-1); 
		education.setEditable(false); 
		/*-------------THÊM CÁC THÀNH PHẦN VÀO FRAME----------*/
		formPanel.setPreferredSize(new Dimension(800, 600));
		formPanel.add(idLabel);        		formPanel.add(idField);
		formPanel.add(nameLabel);      		formPanel.add(nameField);
		formPanel.add(dobLabel);
		formPanel.add(dayLabel); 			formPanel.add(day);    
		formPanel.add(monthLabel); 			formPanel.add(month);    
		formPanel.add(yearLabel); 			formPanel.add(year);
		formPanel.add(genderLabel);
		formPanel.add(maleRadioButton);		
		formPanel.add(femaleRadioButton);     
		formPanel.add(otherRadioButton);
		formPanel.add(phoneLabel);     		formPanel.add(phoneField);
		formPanel.add(imageLabel);			formPanel.add(imagePanel);
		formPanel.add(emailLabel);     		formPanel.add(emailField);
		formPanel.add(addressLabel);   		formPanel.add(addressField);
		formPanel.add(positionLabel);  		formPanel.add(position);
		formPanel.add(departmentLabel);		formPanel.add(department);
		formPanel.add(statusLabel);    		formPanel.add(status);
		formPanel.add(educationLabel); 		formPanel.add(education);
		/*----------XÁC NHẬN LƯU LẠI HOẶC HỦY BỎ TÁC VỤ--------- */
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		buttonPanel.setBackground(new Color(135, 206, 250));

		saveButton = new JButton("Lưu lại");
		saveButton.setSize(100, 30);
		saveButton.setBackground(Color.CYAN);
		
		cancelButton = new JButton("Hủy bỏ");
		cancelButton.setSize(100, 30);
		cancelButton.setBackground(Color.RED);

		buttonPanel.add(saveButton);	
		buttonPanel.add(cancelButton);

		/*--------THÊM CÁC PANEL VÀO FRAME CHÍNH------------ */
		add(titlePanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);	

		/*---------THÊM HÀNH ĐỘNG CHO CÁC BUTTON------------ */
		saveButton.addActionListener(e -> {
			// Handle save action here
			// You can retrieve the values from the form fields and save them to the database
			// Example:
			// String name = nameField.getText();
			// Save to database logic here
			JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			dispose(); // Close the add frame after saving
		});

		cancelButton.addActionListener(e -> {
			dispose(); 
		});

		setResizable(false);	
		setVisible(true);
    }
    
    private void showImage(File file) {
        try {
            BufferedImage img = ImageIO.read(file);

            // Resize ảnh cho vừa label
            Image scaledImage = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);

            imageLabel.setIcon(icon);
            imageLabel.setText(""); // Xóa chữ "Chưa có ảnh"
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể tải ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

	private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh");

        // Chỉ cho chọn file ảnh
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Image files", ImageIO.getReaderFileSuffixes()));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            showImage(selectedFile);
        }
    }
}
