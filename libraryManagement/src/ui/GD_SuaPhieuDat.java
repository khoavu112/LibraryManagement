package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.PhieuDatDAO;
import java.awt.Color;

public class GD_SuaPhieuDat extends JFrame {

	
	private JPanel contentPane;
	private JComboBox comboBoxTenNV;
	private PhieuDatDAO phieuDatDAO = new PhieuDatDAO();
	private String tenNV;
	private String ngayNhap;
	private String thangNhap;
	private String namNhap;
	private JComboBox comboBoxNam;
	private JComboBox comboBoxThang;
	private JComboBox comboBoxNgay;
	
	private String mapdFromMain;
	private String tenNVFromMain;
	private String ngayNhapFromMain;
	private String thangNhapFromMain;
	private String namNhapFromMain;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_SuaPhieuDat frame = new GD_SuaPhieuDat();
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
	public GD_SuaPhieuDat() {
		initialize();
		
	}
	public void setComBoBox() {
		int ngays = Integer.parseInt(ngayNhapFromMain);
		for (int i=0; i<comboBoxNgay.getItemCount(); i++) {
		      if (comboBoxNgay.getItemAt(i).equals(ngays+"")) {
		    	  comboBoxNgay.setSelectedIndex(i);
		        break;
		      }
		    }
		
		
		int thangs = Integer.parseInt(thangNhapFromMain);
		for (int i=0; i<comboBoxThang.getItemCount(); i++) {
			 String thangfor = comboBoxThang.getItemAt(i)+"";
		      if (thangfor.equalsIgnoreCase(thangs+"")) {
		    	  comboBoxThang.setSelectedIndex(i);
		        break;
		      }
		    }
		
		int nams = Integer.parseInt(namNhapFromMain);
		for (int i=0; i<comboBoxNam.getItemCount(); i++) {
			
			String namfor = comboBoxNam.getItemAt(i)+"";
		      if (namfor.equalsIgnoreCase(nams+"")) {
		    	  comboBoxNam.setSelectedIndex(i);
		        break;
		      }
		    }
		
		for (int i=0; i<comboBoxTenNV.getItemCount(); i++) {
		      if (comboBoxTenNV.getItemAt(i).equals(this.tenNVFromMain)) {
		    	  comboBoxTenNV.setSelectedIndex(i);
		        break;
		      }
		    }
		
	}
	public GD_SuaPhieuDat(String mapdFromMain,String tenNVFromMain,String ngayNhapFromMain,String thangNhapFromMain,String namNhapFromMain) {
		this.mapdFromMain = mapdFromMain;
		this.tenNVFromMain = tenNVFromMain;
		this.ngayNhapFromMain = ngayNhapFromMain;
		this.thangNhapFromMain = thangNhapFromMain;
		this.namNhapFromMain = namNhapFromMain;
	
		initialize();
		
	}
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 459);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblThmPhiut = new JLabel("Sửa Phiếu Đặt",SwingConstants.CENTER);
		lblThmPhiut.setOpaque(true);
		lblThmPhiut.setBackground(new Color(255, 140, 0));
		lblThmPhiut.setForeground(new Color(255, 255, 255));
		lblThmPhiut.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblThmPhiut.setBounds(0, 0, 609, 89);
		contentPane.add(lblThmPhiut);
		/* Event nut luu*/
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setBackground(new Color(0, 0, 255));

		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeDat = namNhap + "-" + thangNhap + "-" + ngayNhap;
				new PhieuDatDAO().suaPhieuDat(tenNV, timeDat,mapdFromMain);
				GD_MainPage mainframe = new GD_MainPage().getInstanceOfMainPage();
				mainframe.dulieubangPhieuDat();
			}
		});

		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLuu.setBounds(240, 368, 110, 29);
		contentPane.add(btnLuu);

		JLabel lblChnNhnVin = new JLabel("Chọn Nhân Viên:");
		lblChnNhnVin.setForeground(new Color(255, 255, 255));
		lblChnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChnNhnVin.setBounds(90, 143, 107, 17);
		contentPane.add(lblChnNhnVin);

		JLabel lblNgy = new JLabel("Ngày đặt:");
		lblNgy.setForeground(new Color(255, 255, 255));
		lblNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgy.setBounds(90, 194, 62, 17);
		contentPane.add(lblNgy);

		JLabel lblThngt = new JLabel("Tháng đặt:");
		lblThngt.setForeground(new Color(255, 255, 255));
		lblThngt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThngt.setBounds(231, 194, 70, 17);
		contentPane.add(lblThngt);

		JLabel lblNmt = new JLabel("Năm đặt:");
		lblNmt.setForeground(new Color(255, 255, 255));
		lblNmt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmt.setBounds(385, 194, 58, 17);
		contentPane.add(lblNmt);

		comboBoxNgay = new JComboBox();
		comboBoxNgay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ngayNhap =  comboBoxNgay.getItemAt(comboBoxNgay.getSelectedIndex()).toString();
			}
		});
		comboBoxNgay.setBounds(162, 194, 50, 21);
		for (int i = 1; i <= 31; i++) {
			comboBoxNgay.addItem(i+"");
		}
		contentPane.add(comboBoxNgay);
		
		for (int i=0; i<comboBoxNgay.getItemCount(); i++) {
		      if (comboBoxNgay.getItemAt(i).equals(this.ngayNhapFromMain)) {
		    	  comboBoxNgay.setSelectedIndex(i);
		        break;
		      }
		    }
		comboBoxThang = new JComboBox();
		comboBoxThang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thangNhap =  comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex()).toString();
			}
		});
		comboBoxThang.setBounds(323, 194, 50, 21);
		contentPane.add(comboBoxThang);
		
		for (int i = 1; i <= 12; i++) {
			comboBoxThang.addItem(i);
		}
		comboBoxNam = new JComboBox();
		comboBoxNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namNhap =  comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex()).toString();
			}
		});
		comboBoxNam.setBounds(489, 194, 70, 21);
		contentPane.add(comboBoxNam);
		
		
		comboBoxTenNV = new JComboBox();
		comboBoxTenNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenNV =  (String) comboBoxTenNV.getItemAt(comboBoxTenNV.getSelectedIndex());

			}
		});
		comboBoxTenNV.setBounds(215, 143, 135, 21);
		contentPane.add(comboBoxTenNV);
		
		
		
		ArrayList<String> dsnv = phieuDatDAO.JComBoBoxNV();
		for (int i = 0; i < dsnv.size(); i++) {
			comboBoxTenNV.addItem(dsnv.get(i));
		}
		for (int i = 2019; i <= 2024; i++) {
			comboBoxNam.addItem(i);
		}
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		setComBoBox();
	}	
}
