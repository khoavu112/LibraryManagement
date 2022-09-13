package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.PhieuDatDAO;
import dao.PhieuThanhLyDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class GD_ThemPhieuThanhLy extends JFrame {
	private PhieuThanhLyDAO phieuThanhLyDAO = new PhieuThanhLyDAO();
	private JPanel contentPane;
	private String tenNV;
	private String ngayNhap;
	private String thangNhap;
	private String namNhap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ThemPhieuThanhLy frame = new GD_ThemPhieuThanhLy();
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
	public GD_ThemPhieuThanhLy() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 361);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboTenNV = new JComboBox();
		comboTenNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenNV =  (String) comboTenNV.getItemAt(comboTenNV.getSelectedIndex());
			}
		});
		comboTenNV.setBounds(289, 110, 116, 21);
		contentPane.add(comboTenNV);
		ArrayList<String> dsNV = phieuThanhLyDAO.JComBoBoxNV();
		for(int i = 0 ; i < dsNV.size(); i++) {
			comboTenNV.addItem(dsNV.get(i));
		}
		JComboBox comboNgay = new JComboBox();
		comboNgay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ngayNhap =  comboNgay.getItemAt(comboNgay.getSelectedIndex()).toString();
			}
		});
		comboNgay.setBounds(140, 174, 50, 21);
		contentPane.add(comboNgay);
		for(int i = 1 ; i <= 31 ; i++) {
			comboNgay.addItem(i);
		}
		
		JComboBox comboThang = new JComboBox();
		comboThang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thangNhap =  comboThang.getItemAt(comboThang.getSelectedIndex()).toString();
			}
		});
		comboThang.setBounds(315, 174, 50, 21);
		contentPane.add(comboThang);
		for(int i = 1 ; i <= 12 ; i++) {
			comboThang.addItem(i);
		}
		JComboBox comboNam = new JComboBox();
		comboNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namNhap =  comboNam.getItemAt(comboNam.getSelectedIndex()).toString();
			}
		});
		comboNam.setBounds(485, 174, 76, 21);
		contentPane.add(comboNam);
		for(int i = 2019 ; i <= 2024 ; i++) {
			comboNam.addItem(i);
		}
		JButton btnLu = new JButton("Lưu");
		btnLu.setForeground(new Color(255, 255, 255));
		btnLu.setBackground(new Color(0, 0, 255));
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeDat = namNhap + "-" + thangNhap + "-" + ngayNhap;
				
			    new PhieuThanhLyDAO().themThanhLy(tenNV, timeDat);
				GD_MainPage mainframe = new GD_MainPage().getInstanceOfMainPage();
				mainframe.dulieubangPhieuThanhLy();
			}
		});
		btnLu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLu.setBounds(243, 269, 97, 31);
		contentPane.add(btnLu);
		
		JLabel lblThmPhiuThanh = new JLabel("Thêm Phiếu Thanh Lý",SwingConstants.CENTER);
		lblThmPhiuThanh.setForeground(new Color(255, 255, 255));
		lblThmPhiuThanh.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblThmPhiuThanh.setBackground(new Color(255, 140, 0));
		lblThmPhiuThanh.setOpaque(true);
		lblThmPhiuThanh.setBounds(0, 0, 600, 90);
		contentPane.add(lblThmPhiuThanh);
		
		JLabel lblChnNhnVin = new JLabel("Chọn nhân viên:");
		lblChnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChnNhnVin.setForeground(new Color(255, 255, 255));
		lblChnNhnVin.setBounds(175, 110, 104, 17);
		contentPane.add(lblChnNhnVin);
		
		JLabel lblChnNgy = new JLabel("Chọn ngày:");
		lblChnNgy.setForeground(Color.WHITE);
		lblChnNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChnNgy.setBounds(57, 174, 73, 17);
		contentPane.add(lblChnNgy);
		
		JLabel lblChnThng = new JLabel("Chọn tháng:");
		lblChnThng.setForeground(Color.WHITE);
		lblChnThng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChnThng.setBounds(227, 174, 78, 17);
		contentPane.add(lblChnThng);
		
		JLabel lblChnNm = new JLabel("Chọn năm:");
		lblChnNm.setForeground(Color.WHITE);
		lblChnNm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChnNm.setBounds(405, 174, 70, 17);
		contentPane.add(lblChnNm);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
	}
}
