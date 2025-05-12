package DACS1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public  class nhanvien  extends JPanel{
    private JPanel nhanvien, buttonPanel, itemsGroup;
    private DefaultTableModel model;
    private JTable table;
    private JButton themButton, suaButton, xoaButton, timButton;
    private JTextField searchTextField;
    
    

    public nhanvien() {
        setLayout(new BorderLayout());

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
		searchTextField.setBorder(new LineBorder(Color.BLACK, 1, true));
		searchTextField.setPreferredSize(new Dimension(200, 30));
		
        buttonPanel.add(themButton); 
		buttonPanel.add(suaButton); 
		buttonPanel.add(xoaButton);
        buttonPanel.add(searchTextField); 
		buttonPanel.add(timButton);

		nhanvien.add(new JScrollPane(table), BorderLayout.CENTER);
		nhanvien.add(buttonPanel, BorderLayout.NORTH);
		nhanvien.setBackground(cl1);

        themButton.addActionListener(e -> {new addEmployee().setVisible(true);});
        suaButton.addActionListener(e -> suaNhanVien());
        xoaButton.addActionListener(e -> xoaNhanVien());
        timButton.addActionListener(e -> timNhanVien());
    }
}
