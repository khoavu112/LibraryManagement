package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CTPhieuDatDAO;
import dao.SachDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GD_SuaSach extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenSach;
	private JTextField txtTheLoai;
	private JTextField txtTinhTrang;
	private JComboBox comboBoxNXB;
	private JComboBox comboBoxNamXB;
	private CTPhieuDatDAO ctpd = new CTPhieuDatDAO();
	private String tenNXB;
	private String namXB;
	/**
	 * Launch the application.
	 */
	private String maSachFromMain;
	private String tenSachFromMain;
	private String theLoaiFromMain;
	private String namXBFromMain;
	private String tenNXBFromMain;
	private String tinhTrangFromMain;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ThemSach frame = new GD_ThemSach();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_SuaSach() {
		initialize();
	}
	public void setComBoBox() {
		txtTenSach.setText(tenSachFromMain);
		txtTheLoai.setText(theLoaiFromMain);
		txtTinhTrang.setText(tinhTrangFromMain);
		int nams = Integer.parseInt(namXBFromMain);
		for (int i=0; i<comboBoxNamXB.getItemCount(); i++) {
			
			String namfor = comboBoxNamXB.getItemAt(i)+"";
		      if (namfor.equalsIgnoreCase(nams+"")) {
		    	  comboBoxNamXB.setSelectedIndex(i);
		        break;
		      }
		    }
		
		for (int i=0; i<comboBoxNXB.getItemCount(); i++) {
		      if (comboBoxNXB.getItemAt(i).equals(this.tenNXBFromMain)) {
		    	  comboBoxNXB.setSelectedIndex(i);
		        break;
		      }
		    }
	}
	public GD_SuaSach(String maSach,String tenSach,String theLoai,String namXB,String tenNXB,String tinhTrang) {
		this.maSachFromMain = maSach;
		this.tenSachFromMain = tenSach;
		this.theLoaiFromMain = theLoai;
		this.namXBFromMain = namXB;
		this.tenNXBFromMain = tenNXB;
		this.tinhTrangFromMain = tinhTrang;		
		initialize();
		setComBoBox();
	}
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 468);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sửa Sách",SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 140, 0));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(0, 0, 698, 100);
		contentPane.add(lblNewLabel);
		
		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenSach.setBounds(173, 168, 126, 23);
		contentPane.add(txtTenSach);
		txtTenSach.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Sách:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(68, 168, 63, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Thể loại:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(68, 212, 55, 17);
		contentPane.add(lblNewLabel_1_1);
		
		txtTheLoai = new JTextField();
		txtTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTheLoai.setColumns(10);
		txtTheLoai.setBounds(173, 212, 126, 23);
		contentPane.add(txtTheLoai);
		
		JLabel lblNewLabel_1_2 = new JLabel("Năm Xuất Bản:");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(384, 233, 95, 17);
		contentPane.add(lblNewLabel_1_2);
		
		comboBoxNXB = new JComboBox();
		comboBoxNXB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenNXB =  (String) comboBoxNXB.getItemAt(comboBoxNXB.getSelectedIndex());
			}
		});
		comboBoxNXB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxNXB.setBounds(529, 165, 126, 25);
		contentPane.add(comboBoxNXB);
		
		ArrayList<String> dsnv = ctpd.JComBoBoxNXB();
		for (int i = 0; i < dsnv.size(); i++) {
			comboBoxNXB.addItem(dsnv.get(i));
		}
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Chọn Nhà Xuất Bản:");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(384, 168, 129, 17);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Tình trạng:");
		lblNewLabel_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2.setBounds(68, 262, 95, 17);
		contentPane.add(lblNewLabel_1_2_2);
		
		txtTinhTrang = new JTextField();
		txtTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTinhTrang.setColumns(10);
		txtTinhTrang.setBounds(173, 262, 126, 23);
		contentPane.add(txtTinhTrang);
		
		JButton btnNewButton = new JButton("Sửa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenSach = txtTenSach.getText();
				String theLoai = txtTheLoai.getText();
				
				String tinhTrang  = txtTinhTrang.getText();
				if(tenSach.length() > 0 && theLoai.length() > 0 && tinhTrang.length() > 0) {
				new SachDAO().suaSach(maSachFromMain,tenSach, theLoai, namXB, tenNXB, tinhTrang);
				GD_MainPage mainframe = new GD_MainPage().getInstanceOfMainPage();
				mainframe.dulieubangSach();
				}else {
					JOptionPane.showMessageDialog(null, "Chưa điền đẩy đủ thông tin!");
				}
			}
		});
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(307, 348, 86, 38);
		contentPane.add(btnNewButton);
		
		comboBoxNamXB = new JComboBox();
		comboBoxNamXB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namXB =  (String) comboBoxNamXB.getItemAt(comboBoxNamXB.getSelectedIndex());
			}
		});
		comboBoxNamXB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxNamXB.setBounds(529, 229, 126, 25);
		contentPane.add(comboBoxNamXB);
		for (int i = 1919; i <= 2021; i++) {
			comboBoxNamXB.addItem(i+"");
		}
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
	}
}
