package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dao.CTPhieuDatDAO;
import dao.DataBase;
import dao.DocGiaDAO;
import dao.PhieuDatDAO;
import dao.PhieuMuonDAO;
import dao.PhieuThanhLyDAO;
import dao.SachDAO;
import entities.DocGia;
import entities.PhieuDat;
import entities.PhieuMuon;
import entities.PhieuThanhLy;
import entities.Sach;
import net.miginfocom.swing.MigLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GD_MainPage extends javax.swing.JFrame {
	private static GD_MainPage window;
	private JFrame frame;
	private JTextField txtNhapMaPM;
	private JTable tableMuonSach, tableDocGia, tableQuanLySach, tableQuanLyNhapSach, tableQuanLyThanhLySach;
	private JTable tableDocGia_1;
	private JTable tableQuanLyThanhLySach_1;
	private DefaultTableModel tableModelMuonSach, tableModelDocGia, tableModelQuanLySach, tableModelQuanLyNhapSach,
			tableModelQuanLyThanhLySach , tableModelTraSach;
	private PhieuMuonDAO dsPhieuMuon = new PhieuMuonDAO();
	private DocGiaDAO dsDocGia = new DocGiaDAO();
	private SachDAO dsSach = new SachDAO();
	private PhieuDatDAO dsPhieuDat = new PhieuDatDAO();
	private PhieuThanhLyDAO dsPhieuThanhLy = new PhieuThanhLyDAO();
	private JTextField txtTimDocGia;
	private JTextField txtNhapMaSach;
	private JTextField txtTimPhieuDat;
	private JTextField txtTimPTL;
	// DatTheTV_DKTV
	private JTextField hoTenDG;
	private JTextField soCMNDDG;
	private JTextField soDTDG;
	private JComboBox comboBoxDKTVNgay;
	private JComboBox comboBoxDKTVThang;
	private JComboBox comboBoxDKTVNam;
	private String ngaySinhDG_DKTV;
	private String thangSinhDG_DKTV;
	private String namSinhDG_DKTV;
	private final String URL_IMAGE = "/images/libraryandgirl.png";
	private final String URL_IMAGELOGO = "/images/ImageBook.png";
	// QLDG
	private JTextField txtTenDG_QLDG;
	private JTextField txtSoCMND_QLDG;
	private JTextField txtSoDT_QLDG;
	private JTextField txtNgaySinh_QLDG;
	private JTextField txtThangSinh_QLDG;
	private JTextField txtNamSinhQLDG;
	private JTable tableTraSach;
	private JTextField txtTenDG_TraSach;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GD_MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public GD_MainPage() {
		initialize();
	}

	public GD_MainPage getInstanceOfMainPage() {
		return window;
	}

	/**
	 * Initialize the contents of the frame.
	 */

	void setColor(JPanel panel) {
		panel.setBackground(new Color(51, 51, 51));
	}

	void resetColor(JPanel panel) {
		panel.setBackground(new Color(153, 153, 153));
	}

	private void initialize() {

		CardLayout cardLayout;
		JPanel pnlDashBoard = new JPanel();
		JPanel pnlQuanLySach = new JPanel();
		JPanel pnlDangKyTheTV = new JPanel();
		JPanel pnlNhapSach = new JPanel();
		JPanel pnlThanhLySach = new JPanel();
		JPanel pnlQuanLyDocGia = new JPanel();
		JPanel pnlMuonSach = new JPanel();
		JPanel pnlTraSach = new JPanel();
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1560, 882);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		frame.setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1540, 835);
		panel.add(desktopPane);

		JPanel pnlSideBar = new JPanel();
		pnlSideBar.setBackground(new Color(102, 102, 102));
		pnlSideBar.setBounds(0, 0, 281, 835);
		desktopPane.add(pnlSideBar);
		pnlSideBar.setLayout(null);

		JPanel pnlCard = new JPanel();
		pnlCard.setBackground(new Color(102, 255, 204));
		pnlCard.setBounds(285, 0, 1255, 835);
		desktopPane.add(pnlCard);
		pnlCard.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) (pnlCard.getLayout());

		JPanel pnlCardDashBoard = new JPanel();
		pnlCardDashBoard.setBackground(UIManager.getColor("ScrollBar.foreground"));
		pnlCard.add(pnlCardDashBoard, "pnlCardDashBoard");
		pnlCardDashBoard.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel labelImage = new JLabel("");
		pnlCardDashBoard.add(labelImage);
		
		labelImage.setIcon(new ImageIcon(new ImageIcon(GD_DangNhap.class.getResource(URL_IMAGE)).getImage().getScaledInstance(1400, 850, Image.SCALE_DEFAULT)));
		JPanel pnlCardQuanLySach = new JPanel();
		pnlCardQuanLySach.setBackground(UIManager.getColor("activeCaptionBorder"));
		pnlCard.add(pnlCardQuanLySach, "pnlCardQuanLySach");
		pnlCardQuanLySach.setLayout(null);

		JPanel pnlCardMuonSach = new JPanel();
		pnlCardMuonSach.setBackground(Color.DARK_GRAY);
		pnlCard.add(pnlCardMuonSach, "pnlCardMuonSach");
		pnlCardMuonSach.setLayout(null);
		
		JPanel pnlCardTraSach = new JPanel();
		pnlCard.add(pnlCardTraSach, "pnlCardTraSach");
		pnlCardTraSach.setLayout(null);
		pnlCardTraSach.setBackground(new Color(51, 102, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(219, 333, 874, 221);
		pnlCardTraSach.add(scrollPane);
		
		tableTraSach = new JTable();
		String[] headerTraSach = " Mã Phiếu Mượn; Tên Độc Giả; Tên Nhân Viên; Ngày Mượn; Ngày Trả".split(";");
		tableModelTraSach = new DefaultTableModel(headerTraSach, 0){

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableTraSach = new JTable(tableModelTraSach);
		
		tableTraSach.getColumnModel().getColumn(0).setResizable(false);
		tableTraSach.getColumnModel().getColumn(0).setPreferredWidth(95);
		tableTraSach.getColumnModel().getColumn(1).setResizable(false);
		tableTraSach.getColumnModel().getColumn(1).setPreferredWidth(105);
		tableTraSach.getColumnModel().getColumn(2).setResizable(false);
		tableTraSach.getColumnModel().getColumn(2).setPreferredWidth(105);
		tableTraSach.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(tableTraSach);
		
		JButton btnXem_1 = new JButton("Xem");
		btnXem_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableTraSach.getModel();
				int selectedIndex = tableTraSach.getSelectedRow();
				if (selectedIndex != -1) {
					String maPM = Df.getValueAt(selectedIndex, 0).toString();
					String tenDG = Df.getValueAt(selectedIndex, 1).toString();
					GD_ChiTietPhieuTra ctpt = new GD_ChiTietPhieuTra(maPM,tenDG);
					ctpt.setVisible(true);
					ctpt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				} else {
					JOptionPane.showMessageDialog(null, "Chưa Chọn Phiếu Mượn Để Xác Nhận Trả!");
				}
			}
		});
		btnXem_1.setBounds(397, 577, 85, 40);
		pnlCardTraSach.add(btnXem_1);
		
		JButton btnXa = new JButton("Trả");
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableTraSach.getModel();
				int selectedIndex = tableTraSach.getSelectedRow();
				if (selectedIndex != -1) {
					String idPM = Df.getValueAt(selectedIndex, 0).toString();
					int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
							JOptionPane.YES_NO_OPTION);
					if (dialog == JOptionPane.YES_OPTION) {
						PhieuMuonDAO pm = new PhieuMuonDAO();
						pm.xoaPM(idPM);
						dulieubangPhieuMuon();
						dulieubangTraSach();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu!");
				}
			}
		});
		btnXa.setBounds(794, 577, 85, 40);
		pnlCardTraSach.add(btnXa);
		
		JLabel lblTrSch_1 = new JLabel("Trả Sách");
		lblTrSch_1.setFont(new Font("SansSerif", Font.BOLD, 50));
		lblTrSch_1.setForeground(Color.WHITE);
		lblTrSch_1.setBounds(219, 97, 336, 64);
		pnlCardTraSach.add(lblTrSch_1);
		
		txtTenDG_TraSach = new JTextField();
		txtTenDG_TraSach.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtTenDG_TraSach.setBounds(806, 279, 146, 26);
		pnlCardTraSach.add(txtTenDG_TraSach);
		txtTenDG_TraSach.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten = txtTenDG_TraSach.getText();
				if (ten.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Tên Độc Giả");
					dulieubangTraSach();
				} else {
					ArrayList<PhieuMuon> list = dsPhieuMuon.TimDocGiaTraSach(ten);
					if (list.size() > 0) {
						tableModelTraSach.setRowCount(0);
						for (PhieuMuon pm : list) {
							String[] rowtable = { pm.getMaPM(), pm.getTenDG(), pm.getTenNV(), pm.getNgayMuon(),
									pm.getNgayTra() };
							tableModelTraSach.addRow(rowtable);
						}
						tableTraSach.setModel(tableModelTraSach);
					} else {
						JOptionPane.showMessageDialog(null, "Không Tìm Thấy Độc Giả");
						dulieubangTraSach();
					}
				}
			}
		});
		btnNewButton.setBounds(980, 279, 90, 26);
		pnlCardTraSach.add(btnNewButton);
		
		JLabel lblNewLabel_8 = new JLabel("Tìm Độc Giả:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(709, 283, 87, 19);
		pnlCardTraSach.add(lblNewLabel_8);
		
		JPanel pnlCardQuanLyDocGia = new JPanel();
		pnlCardQuanLyDocGia.setBackground(new Color(51, 153, 204));
		pnlCard.add(pnlCardQuanLyDocGia, "pnlCardQuanLyDocGia");
		pnlCardQuanLyDocGia.setLayout(null);

		JPanel pnlCardDangKyTheTV = new JPanel();
		pnlCardDangKyTheTV.setBackground(new Color(147, 112, 219));
		pnlCard.add(pnlCardDangKyTheTV, "pnlCardDangKyTheTV");
		pnlCardDangKyTheTV.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Họ Tên:");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(361, 177, 65, 22);
		pnlCardDangKyTheTV.add(lblNewLabel_7);

		JLabel lblNewLabel_7_1 = new JLabel("Số CMND:");
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7_1.setBounds(361, 213, 82, 22);
		pnlCardDangKyTheTV.add(lblNewLabel_7_1);

		JLabel lblNewLabel_7_2 = new JLabel("Số Điện Thoại: ");
		lblNewLabel_7_2.setForeground(Color.WHITE);
		lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7_2.setBounds(361, 255, 124, 22);
		pnlCardDangKyTheTV.add(lblNewLabel_7_2);

		JLabel lblNewLabel_7_2_1 = new JLabel("Ngày sinh:");
		lblNewLabel_7_2_1.setForeground(Color.WHITE);
		lblNewLabel_7_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7_2_1.setBounds(314, 322, 86, 22);
		pnlCardDangKyTheTV.add(lblNewLabel_7_2_1);

		JLabel lblNewLabel_7_2_1_1 = new JLabel("Tháng Sinh:");
		lblNewLabel_7_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_7_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7_2_1_1.setBounds(511, 325, 106, 17);
		pnlCardDangKyTheTV.add(lblNewLabel_7_2_1_1);

		JLabel lblNewLabel_7_2_1_2 = new JLabel("Năm Sinh:");
		lblNewLabel_7_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_7_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7_2_1_2.setBounds(737, 325, 106, 17);
		pnlCardDangKyTheTV.add(lblNewLabel_7_2_1_2);

		hoTenDG = new JTextField();
		hoTenDG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		hoTenDG.setBounds(687, 177, 156, 28);
		pnlCardDangKyTheTV.add(hoTenDG);
		hoTenDG.setColumns(10);

		soCMNDDG = new JTextField();
		soCMNDDG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					soCMNDDG.setEditable(true);
				}else {
					JOptionPane.showMessageDialog(null, "Chỉ được nhập số!");
					soCMNDDG.setText("");
				}
				
				
			}
		});
		soCMNDDG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		soCMNDDG.setColumns(10);
		soCMNDDG.setBounds(687, 213, 156, 28);
		pnlCardDangKyTheTV.add(soCMNDDG);

		soDTDG = new JTextField();
		soDTDG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					soDTDG.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					soDTDG.setText("");
				}
			}
		});
		
		soDTDG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		soDTDG.setColumns(10);
		soDTDG.setBounds(687, 255, 156, 28);
		pnlCardDangKyTheTV.add(soDTDG);

		comboBoxDKTVNgay = new JComboBox();
		comboBoxDKTVNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxDKTVNgay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ngaySinhDG_DKTV = comboBoxDKTVNgay.getItemAt(comboBoxDKTVNgay.getSelectedIndex()).toString();
			}
		});
		comboBoxDKTVNgay.setBounds(421, 318, 53, 30);
		pnlCardDangKyTheTV.add(comboBoxDKTVNgay);

		for (int i = 1; i <= 31; i++) {
			comboBoxDKTVNgay.addItem(i);
		}

		comboBoxDKTVThang = new JComboBox();
		comboBoxDKTVThang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxDKTVThang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thangSinhDG_DKTV = comboBoxDKTVThang.getItemAt(comboBoxDKTVThang.getSelectedIndex()).toString();
			}
		});
		comboBoxDKTVThang.setBounds(627, 318, 53, 30);
		pnlCardDangKyTheTV.add(comboBoxDKTVThang);

		for (int i = 1; i <= 12; i++) {
			comboBoxDKTVThang.addItem(i);
		}

		comboBoxDKTVNam = new JComboBox();
		comboBoxDKTVNam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxDKTVNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namSinhDG_DKTV = comboBoxDKTVNam.getItemAt(comboBoxDKTVNam.getSelectedIndex()).toString();
			}
		});
		comboBoxDKTVNam.setBounds(841, 318, 82, 30);
		pnlCardDangKyTheTV.add(comboBoxDKTVNam);

		for (int i = 1980; i <= 2021; i++) {
			comboBoxDKTVNam.addItem(i);
		}

		JButton btnLuuDKTV = new JButton("Lưu");
		btnLuuDKTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hoTenDG.getText().length() > 0 && soCMNDDG.getText().length() > 0 && soDTDG.getText().length() > 0 ) {
				
				String hoTen = hoTenDG.getText();
				String soCMND = soCMNDDG.getText();
				String soDT = soDTDG.getText();
				String birthday = namSinhDG_DKTV + "-" + thangSinhDG_DKTV + "-" + ngaySinhDG_DKTV;
				DocGiaDAO dg = new DocGiaDAO();
				dg.themDG(hoTen, birthday, soCMND, soDT);
				
				dulieubangDocGia();
				
			}else {
				JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin!");
			}
				
			}
		});
		btnLuuDKTV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLuuDKTV.setBounds(408, 514, 124, 51);
		pnlCardDangKyTheTV.add(btnLuuDKTV);

		JButton btnDatLaiDKTV = new JButton("Đặt Lại");
		btnDatLaiDKTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hoTenDG.setText("");
				soCMNDDG.setText("");
				soDTDG.setText("");
			}
		});
		btnDatLaiDKTV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDatLaiDKTV.setBounds(686, 514, 132, 51);
		pnlCardDangKyTheTV.add(btnDatLaiDKTV);

		JLabel lblngKTh = new JLabel("Đăng Ký Thẻ Thư Viện");
		lblngKTh.setForeground(Color.WHITE);
		lblngKTh.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblngKTh.setBounds(450, 49, 336, 37);
		pnlCardDangKyTheTV.add(lblngKTh);

		JPanel pnlCardNhapSach = new JPanel();
		pnlCardNhapSach.setBackground(new Color(51, 153, 153));
		pnlCard.add(pnlCardNhapSach, "pnlCardNhapSach");
		pnlCardNhapSach.setLayout(null);

		JPanel pnlCardThanhLySach = new JPanel();
		pnlCardThanhLySach.setBackground(SystemColor.inactiveCaption);
		pnlCard.add(pnlCardThanhLySach, "pnlCardThanhLySach");
		pnlCardThanhLySach.setLayout(null);

		pnlDashBoard.setBackground(new Color(51, 51, 51));
		pnlDashBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardDashBoard");
				setColor(pnlDashBoard);
				resetColor(pnlQuanLySach);
				resetColor(pnlMuonSach);
				resetColor(pnlQuanLyDocGia);
				resetColor(pnlDangKyTheTV);
				resetColor(pnlNhapSach);
				resetColor(pnlThanhLySach);
				resetColor(pnlTraSach);
			}
		});
		pnlDashBoard.setBounds(0, 182, 281, 67);
		pnlSideBar.add(pnlDashBoard);
		pnlDashBoard.setLayout(null);

		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setForeground(new Color(255, 255, 255));
		lblDashboard.setFont(new Font("Verdana", Font.BOLD, 15));
		lblDashboard.setBounds(77, 10, 102, 48);
		pnlDashBoard.add(lblDashboard);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\KiemThuPM\\src\\images\\icons8_home_30px_1.png"));
		lblNewLabel.setBounds(28, 10, 39, 48);
		pnlDashBoard.add(lblNewLabel);

		pnlQuanLySach.setBackground(new Color(153, 153, 153));
		pnlQuanLySach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardQuanLySach");
				setColor(pnlQuanLySach);
				resetColor(pnlDashBoard);
				resetColor(pnlMuonSach);
				resetColor(pnlQuanLyDocGia);
				resetColor(pnlDangKyTheTV);
				resetColor(pnlNhapSach);
				resetColor(pnlThanhLySach);
				resetColor(pnlTraSach);
			}
		});
		pnlQuanLySach.setLayout(null);
		pnlQuanLySach.setBounds(0, 327, 281, 67);
		pnlSideBar.add(pnlQuanLySach);

		JLabel lblQuanLySach = new JLabel("Quản Lý Sách");
		lblQuanLySach.setForeground(new Color(255, 255, 255));
		lblQuanLySach.setFont(new Font("Verdana", Font.BOLD, 15));
		lblQuanLySach.setBounds(77, 10, 126, 48);
		pnlQuanLySach.add(lblQuanLySach);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\KiemThuPM\\src\\images\\icons8_saddle_stitched_booklet_30px.png"));
		lblNewLabel_2.setBounds(28, 10, 39, 48);
		pnlQuanLySach.add(lblNewLabel_2);

		pnlDangKyTheTV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardDangKyTheTV");
				setColor(pnlDangKyTheTV);
				resetColor(pnlDashBoard);
				resetColor(pnlQuanLySach);
				resetColor(pnlQuanLyDocGia);
				resetColor(pnlMuonSach);
				resetColor(pnlNhapSach);
				resetColor(pnlThanhLySach);
				resetColor(pnlTraSach);
			}
		});
		pnlDangKyTheTV.setLayout(null);
		pnlDangKyTheTV.setBackground(new Color(153, 153, 153));
		pnlDangKyTheTV.setBounds(0, 473, 281, 67);
		pnlSideBar.add(pnlDangKyTheTV);

		JLabel lblDangKyTheTV = new JLabel("Đăng Ký Thẻ TV");
		lblDangKyTheTV.setForeground(Color.WHITE);
		lblDangKyTheTV.setFont(new Font("Verdana", Font.BOLD, 15));
		lblDangKyTheTV.setBounds(77, 10, 147, 48);
		pnlDangKyTheTV.add(lblDangKyTheTV);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\KiemThuPM\\src\\images\\icons8_card_security_30px.png"));
		lblNewLabel_4.setBounds(28, 10, 39, 48);
		pnlDangKyTheTV.add(lblNewLabel_4);

		pnlNhapSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardNhapSach");
				setColor(pnlNhapSach);
				resetColor(pnlDashBoard);
				resetColor(pnlQuanLySach);
				resetColor(pnlQuanLyDocGia);
				resetColor(pnlDangKyTheTV);
				resetColor(pnlMuonSach);
				resetColor(pnlThanhLySach);
				resetColor(pnlTraSach);
			}
		});
		pnlNhapSach.setLayout(null);
		pnlNhapSach.setBackground(new Color(153, 153, 153));
		pnlNhapSach.setBounds(0, 546, 281, 67);
		pnlSideBar.add(pnlNhapSach);

		JLabel lblNhapSach = new JLabel("Nhập Sách");
		lblNhapSach.setForeground(Color.WHITE);
		lblNhapSach.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNhapSach.setBounds(77, 10, 111, 48);
		pnlNhapSach.add(lblNhapSach);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("D:\\KiemThuPM\\src\\images\\icons8_add_book_30px_1.png"));
		lblNewLabel_5.setBounds(28, 10, 39, 48);
		pnlNhapSach.add(lblNewLabel_5);

		pnlThanhLySach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardThanhLySach");
				setColor(pnlThanhLySach);
				resetColor(pnlDashBoard);
				resetColor(pnlQuanLySach);
				resetColor(pnlQuanLyDocGia);
				resetColor(pnlDangKyTheTV);
				resetColor(pnlNhapSach);
				resetColor(pnlMuonSach);
				resetColor(pnlTraSach);
			}
		});
		pnlThanhLySach.setLayout(null);
		pnlThanhLySach.setBackground(new Color(153, 153, 153));
		pnlThanhLySach.setBounds(0, 619, 281, 67);
		pnlSideBar.add(pnlThanhLySach);

		JLabel lblThanhLySach = new JLabel("Thanh Lý Sách");
		lblThanhLySach.setForeground(Color.WHITE);
		lblThanhLySach.setFont(new Font("Verdana", Font.BOLD, 15));
		lblThanhLySach.setBounds(77, 10, 141, 48);
		pnlThanhLySach.add(lblThanhLySach);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("D:\\KiemThuPM\\src\\images\\icons8_remove_book_30px.png"));
		lblNewLabel_6.setBounds(28, 10, 39, 48);
		pnlThanhLySach.add(lblNewLabel_6);
///////////////////////////////// PanelCard Quản Lý Nhập Sách

		JLabel lblTieuDeDatSach = new JLabel("Quản Lý Đặt Sách");
		lblTieuDeDatSach.setFont(new Font("Verdana", Font.BOLD, 45));
		lblTieuDeDatSach.setForeground(Color.WHITE);
		lblTieuDeDatSach.setBounds(110, 91, 507, 64);
		pnlCardNhapSach.add(lblTieuDeDatSach);

		JButton btnThemPD = new JButton("Thêm Phiếu Đặt");
		btnThemPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GD_ThemPhieuDat themPhieuDat = new GD_ThemPhieuDat();
				themPhieuDat.setVisible(true);
				themPhieuDat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnThemPD.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnThemPD.setBounds(110, 290, 200, 30);
		pnlCardNhapSach.add(btnThemPD);

		JLabel lblNhapMaPD = new JLabel("Nhập Mã Phiếu Đặt:");
		lblNhapMaPD.setForeground(Color.WHITE);
		lblNhapMaPD.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNhapMaPD.setBounds(470, 283, 234, 40);
		pnlCardNhapSach.add(lblNhapMaPD);

		txtTimPhieuDat = new JTextField();
		txtTimPhieuDat.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtTimPhieuDat.setColumns(10);
		txtTimPhieuDat.setBounds(716, 288, 200, 35);
		pnlCardNhapSach.add(txtTimPhieuDat);

		JButton btnTimPD = new JButton("Tìm");
		btnTimPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnTimPD)) {
					String ten = txtTimPhieuDat.getText().toString();
					if (ten.length() <= 0) {
						JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Mã Phiếu Đặt");
						dulieubangPhieuDat();
					} else {
						ArrayList<PhieuDat> list = dsPhieuDat.TimPhieuDatBangMa(ten);
						if (list.size() > 0) {
							tableModelQuanLyNhapSach.setRowCount(0);
							for (PhieuDat pd : list) {
								String[] rowtable = { pd.getMaPD(), pd.getTenNV(), pd.getNgayDat() };
								tableModelQuanLyNhapSach.addRow(rowtable);
							}
							tableQuanLyNhapSach.setModel(tableModelQuanLyNhapSach);
						} else {
							JOptionPane.showMessageDialog(null, "Không Tìm Thấy Phiếu Đặt");
							dulieubangPhieuDat();
						}
					}
				}
			}
		});
		btnTimPD.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnTimPD.setBounds(951, 288, 114, 35);
		pnlCardNhapSach.add(btnTimPD);

		JButton btnXoaPD = new JButton("Xóa");
		btnXoaPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLyNhapSach.getModel();
				int selectedIndex = tableQuanLyNhapSach.getSelectedRow();
				if (selectedIndex != -1) {
					try {
						String id = Df.getValueAt(selectedIndex, 0).toString();

						int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
								JOptionPane.YES_NO_OPTION);

						String querry1 = "delete from ChiTietPhieuDat where MAPD = ?";
						String querry2 = "delete from phieudat where MAPD = ?";
						if (dialog == JOptionPane.YES_OPTION) {
							Connection con = DataBase.getInstance().getConnection();
							PreparedStatement ps = con.prepareStatement(querry1);

							ps.setString(1, id);

							ps.executeUpdate();

							ps = con.prepareStatement(querry2);

							ps.setString(1, id);

							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Đã xóa!");
							dulieubangPhieuDat();

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu đặt!");
				}
			}
		});
		btnXoaPD.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXoaPD.setBounds(760, 580, 85, 40);
		pnlCardNhapSach.add(btnXoaPD);

		JButton btnSuaPD = new JButton("Sửa");
		btnSuaPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLyNhapSach.getModel();
				int selectedIndex = tableQuanLyNhapSach.getSelectedRow();
				if (selectedIndex != -1) {
					String mapds = Df.getValueAt(selectedIndex, 0).toString();
					String tennvs = Df.getValueAt(selectedIndex, 1).toString();
					String dates = Df.getValueAt(selectedIndex, 2).toString();
					String[] part = dates.split("-");
					String ngays = part[2];
					String thangs = part[1];
					String nams = part[0];

					GD_SuaPhieuDat spd = new GD_SuaPhieuDat(mapds, tennvs, ngays, thangs, nams);
					spd.setVisible(true);
					spd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu đặt!");
				}
			}
		});
		btnSuaPD.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSuaPD.setBounds(502, 580, 85, 40);
		pnlCardNhapSach.add(btnSuaPD);

		JScrollPane scrollPaneQuanLyDatSach = new JScrollPane();
		scrollPaneQuanLyDatSach.setBounds(110, 362, 956, 190);
		pnlCardNhapSach.add(scrollPaneQuanLyDatSach);

		tableQuanLyNhapSach = new JTable();
		tableQuanLyNhapSach.setEnabled(false);
		String[] headers3 = "Mã Phiếu Đặt; Tên Nhân Viên; Ngày Đặt".split(";");
		tableModelQuanLyNhapSach = new DefaultTableModel(headers3, 0){

			boolean[] columnEditables = new boolean[] { false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableQuanLyNhapSach = new JTable(tableModelQuanLyNhapSach);
		scrollPaneQuanLyDatSach.setViewportView(tableQuanLyNhapSach);

		JButton btnXem = new JButton("Xem");
		btnXem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLyNhapSach.getModel();
				int selectedIndex = tableQuanLyNhapSach.getSelectedRow();
				if (selectedIndex != -1) {
					String maPD = Df.getValueAt(selectedIndex, 0).toString();

					GD_ChiTietPhieuDat ctPD = new GD_ChiTietPhieuDat(maPD);
					ctPD.setVisible(true);
					ctPD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu đặt!");
				}
			}
		});
		btnXem.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXem.setBounds(260, 580, 85, 40);
		pnlCardNhapSach.add(btnXem);
///////////////////////panelCard Quản Lý Thanh Lý

		JButton btnThemPTL = new JButton("Thêm Phiếu Thanh Lý");
		btnThemPTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GD_ThemPhieuThanhLy themPhieuTL = new GD_ThemPhieuThanhLy();
				themPhieuTL.setVisible(true);
				themPhieuTL.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnThemPTL.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnThemPTL.setBounds(206, 312, 200, 30);
		pnlCardThanhLySach.add(btnThemPTL);

		JLabel lblTitleQuanLyTLS = new JLabel("Quản Lý Thanh Lý Sách");
		lblTitleQuanLyTLS.setForeground(new Color(0, 0, 0));
		lblTitleQuanLyTLS.setFont(new Font("Verdana", Font.BOLD, 45));
		lblTitleQuanLyTLS.setBounds(206, 113, 594, 64);
		pnlCardThanhLySach.add(lblTitleQuanLyTLS);

		JLabel lblNhapMaPTL = new JLabel("Nhập Mã Phiếu Thanh Lý:");
		lblNhapMaPTL.setForeground(new Color(0, 0, 0));
		lblNhapMaPTL.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNhapMaPTL.setBounds(507, 305, 293, 40);
		pnlCardThanhLySach.add(lblNhapMaPTL);

		txtTimPTL = new JTextField();
		txtTimPTL.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtTimPTL.setColumns(10);
		txtTimPTL.setBounds(812, 310, 200, 35);
		pnlCardThanhLySach.add(txtTimPTL);

		JButton btnTimPTL = new JButton("Tìm");
		btnTimPTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnTimPTL)) {
					String ten = txtTimPTL.getText().toString();
					if (ten.length() <= 0) {
						JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Mã Phiếu Thanh Lý");
						dulieubangPhieuThanhLy();
					} else {
						ArrayList<PhieuThanhLy> list = dsPhieuThanhLy.TimPhieuThanhLyBangMa(ten);
						if (list.size() > 0) {
							tableModelQuanLyThanhLySach.setRowCount(0);
							for (PhieuThanhLy ptl : list) {
								String[] rowtable = { ptl.getMaPTL(), ptl.getTenNV(), ptl.getNgayTL(), };
								tableModelQuanLyThanhLySach.addRow(rowtable);
							}
							tableQuanLyThanhLySach.setModel(tableModelQuanLyThanhLySach);
						} else {
							JOptionPane.showMessageDialog(null, "Không Tìm Thấy Phiếu Thanh Lý");
							dulieubangPhieuThanhLy();
						}
					}
				}
			}
		});
		btnTimPTL.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnTimPTL.setBounds(1047, 310, 114, 35);
		pnlCardThanhLySach.add(btnTimPTL);

		JButton btnSuaPTL = new JButton("Sửa");
		btnSuaPTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLyThanhLySach_1.getModel();
				int selectedIndex = tableQuanLyThanhLySach_1.getSelectedRow();
				if (selectedIndex != -1) {
					String maptls = Df.getValueAt(selectedIndex, 0).toString();
					String tennvs = Df.getValueAt(selectedIndex, 1).toString();
					String dates = Df.getValueAt(selectedIndex, 2).toString();
					String[] part = dates.split("-");
					String ngays = part[2];
					String thangs = part[1];
					String nams = part[0];

					GD_SuaPhieuThanhLy sptl = new GD_SuaPhieuThanhLy(maptls, tennvs, ngays, thangs, nams);
					sptl.setVisible(true);
					sptl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu thanh lý!");
				}
			}
		});
		btnSuaPTL.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSuaPTL.setBounds(659, 602, 85, 40);
		pnlCardThanhLySach.add(btnSuaPTL);

		JButton btnXoaPTL = new JButton("Xóa");
		btnXoaPTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLyThanhLySach_1.getModel();
				int selectedIndex = tableQuanLyThanhLySach_1.getSelectedRow();
				if (selectedIndex != -1) {
					try {
						String id = Df.getValueAt(selectedIndex, 0).toString();

						int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
								JOptionPane.YES_NO_OPTION);

						String querry1 = "delete from ChiTietPhieuThanhLy where MAPTL = ?";
						String querry2 = "delete from PhieuThanhLy where MAPTL = ?";
						if (dialog == JOptionPane.YES_OPTION) {
							Connection con = DataBase.getInstance().getConnection();
							PreparedStatement ps = con.prepareStatement(querry1);

							ps.setString(1, id);

							ps.executeUpdate();

							ps = con.prepareStatement(querry2);

							ps.setString(1, id);

							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Đã xóa!");
							dulieubangPhieuThanhLy();

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu thanh lý!");
				}
			}
		});
		btnXoaPTL.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXoaPTL.setBounds(951, 602, 85, 40);
		pnlCardThanhLySach.add(btnXoaPTL);

		JScrollPane scrollPaneQuanLyPTL = new JScrollPane();
		scrollPaneQuanLyPTL.setBounds(206, 373, 955, 190);
		pnlCardThanhLySach.add(scrollPaneQuanLyPTL);

		tableQuanLyThanhLySach = new JTable();
		String[] headers4 = "Mã Phiếu Thanh Lý; Tên Nhân Viên; Ngày Thanh Lý".split(";");
		tableModelQuanLyThanhLySach = new DefaultTableModel(headers4, 0){

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableQuanLyThanhLySach_1 = new JTable(tableModelQuanLyThanhLySach);

		scrollPaneQuanLyPTL.setViewportView(tableQuanLyThanhLySach_1);

		JButton btnXemPTL = new JButton("Xem");
		btnXemPTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLyThanhLySach_1.getModel();
				int selectedIndex = tableQuanLyThanhLySach_1.getSelectedRow();
				if (selectedIndex != -1) {
					String maPTL = Df.getValueAt(selectedIndex, 0).toString();

					GD_ChiTietPhieuThanhLy ctPTL = new GD_ChiTietPhieuThanhLy(maPTL);
					ctPTL.setVisible(true);
					ctPTL.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					GD_DanhSachHienCo dshc = new GD_DanhSachHienCo(ctPTL);

					dshc.setVisible(true);
					dshc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu thanh lý!");
				}
			}
		});
		btnXemPTL.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXemPTL.setBounds(345, 602, 85, 40);
		pnlCardThanhLySach.add(btnXemPTL);

//////////////////////PanelCard Quản Lý Sách
		JButton btnThemSach = new JButton("Thêm Sách");
		btnThemSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GD_ThemSach themSach = new GD_ThemSach();
				themSach.setVisible(true);
				themSach.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnThemSach.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnThemSach.setBounds(189, 361, 200, 30);
		pnlCardQuanLySach.add(btnThemSach);

		JLabel lblNhapMaSach = new JLabel("Nhập Tên Sách:");
		lblNhapMaSach.setForeground(Color.DARK_GRAY);
		lblNhapMaSach.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNhapMaSach.setBounds(594, 354, 172, 40);
		pnlCardQuanLySach.add(lblNhapMaSach);

		txtNhapMaSach = new JTextField();
		txtNhapMaSach.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtNhapMaSach.setColumns(10);
		txtNhapMaSach.setBounds(795, 359, 200, 35);
		pnlCardQuanLySach.add(txtNhapMaSach);

		JButton btnTimSach = new JButton("Tìm");
		btnTimSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnTimSach)) {
					String ten = txtNhapMaSach.getText().toString();
					if (ten.length() <= 0) {
						JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Tên Sách");
						dulieubangSach();
					} else {
						ArrayList<Sach> list = dsSach.TimSachBangMa(ten);
						if (list.size() > 0) {
							tableModelQuanLySach.setRowCount(0);
							for (Sach s : list) {
								String[] rowtable = { s.getMaSach(), s.getTenSach(), s.getTheLoai(), s.getNamXB(),
										s.getTenNXB(), s.getTinhTrangSach() };
								tableModelQuanLySach.addRow(rowtable);
							}
							tableQuanLySach.setModel(tableModelQuanLySach);
						} else {
							JOptionPane.showMessageDialog(null, "Không Tìm Thấy Sách");
							dulieubangSach();
						}
					}
				}
			}
		});
		btnTimSach.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnTimSach.setBounds(1030, 359, 114, 35);
		pnlCardQuanLySach.add(btnTimSach);

		JButton btnSuaSach = new JButton("Sửa");
		btnSuaSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLySach.getModel();
				int selectedIndex = tableQuanLySach.getSelectedRow();
				if (selectedIndex != -1) {
					String maSach = Df.getValueAt(selectedIndex, 0).toString();
					String tenSach = Df.getValueAt(selectedIndex, 1).toString();
					String theLoai = Df.getValueAt(selectedIndex, 2).toString();
					String namXB = Df.getValueAt(selectedIndex, 3).toString();
					String tenNXB = Df.getValueAt(selectedIndex, 4).toString();
					String trinhTrang = Df.getValueAt(selectedIndex, 5).toString();

					GD_SuaSach spd = new GD_SuaSach(maSach, tenSach, theLoai, namXB, tenNXB, trinhTrang);
					spd.setVisible(true);
					spd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn sách!");
				}

			}
		});
		btnSuaSach.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSuaSach.setBounds(490, 651, 85, 40);
		pnlCardQuanLySach.add(btnSuaSach);

		JButton btnXoaSach = new JButton("Xóa");
		btnXoaSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableQuanLySach.getModel();
				int selectedIndex = tableQuanLySach.getSelectedRow();
				if (selectedIndex != -1) {
					String maSach = Df.getValueAt(selectedIndex, 0).toString();
					int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
							JOptionPane.YES_NO_OPTION);
					if (dialog == JOptionPane.YES_OPTION) {
						SachDAO s = new SachDAO();
						s.xoaSach(maSach);

						dulieubangSach();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn sách!");
				}
			}
		});
		btnXoaSach.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXoaSach.setBounds(714, 651, 85, 40);
		pnlCardQuanLySach.add(btnXoaSach);

		JLabel lblTitleQuanLySach = new JLabel("Quản Lý Sách");
		lblTitleQuanLySach.setFont(new Font("Verdana", Font.BOLD, 50));
		lblTitleQuanLySach.setForeground(Color.DARK_GRAY);
		lblTitleQuanLySach.setBounds(189, 155, 428, 64);
		pnlCardQuanLySach.add(lblTitleQuanLySach);

		JScrollPane scrollPaneQuanLySach = new JScrollPane();
		scrollPaneQuanLySach.setBounds(189, 430, 956, 186);
		pnlCardQuanLySach.add(scrollPaneQuanLySach);

		tableQuanLySach = new JTable();
		String[] headers2 = "Mã Sách; Tên Sách; Thể Loại; Năm Xuất Bản; Tên Nhà Xuất Bản; Tình Trạng Sách".split(";");
		tableModelQuanLySach = new DefaultTableModel(headers2, 0){

			boolean[] columnEditables = new boolean[] { false, false, false, false, false,false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableQuanLySach = new JTable(tableModelQuanLySach);
		scrollPaneQuanLySach.setViewportView(tableQuanLySach);

/////////////////////////////////////
//////////////////////PanelCard Quản Lý Độc Giả		
		pnlQuanLyDocGia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardQuanLyDocGia");
				setColor(pnlQuanLyDocGia);
				resetColor(pnlDashBoard);
				resetColor(pnlQuanLySach);
				resetColor(pnlMuonSach);
				resetColor(pnlDangKyTheTV);
				resetColor(pnlNhapSach);
				resetColor(pnlThanhLySach);
				resetColor(pnlTraSach);
			}
		});
		pnlQuanLyDocGia.setLayout(null);
		pnlQuanLyDocGia.setBackground(new Color(153, 153, 153));
		pnlQuanLyDocGia.setBounds(0, 254, 281, 67);
		pnlSideBar.add(pnlQuanLyDocGia);

		JLabel lblQuanLyDocGia = new JLabel("Quản Lý Độc Giả");
		lblQuanLyDocGia.setForeground(Color.WHITE);
		lblQuanLyDocGia.setFont(new Font("Verdana", Font.BOLD, 15));
		lblQuanLyDocGia.setBounds(77, 10, 142, 48);
		pnlQuanLyDocGia.add(lblQuanLyDocGia);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\KiemThuPM\\src\\images\\icons8_person_30px.png"));
		lblNewLabel_1.setBounds(28, 10, 39, 48);
		pnlQuanLyDocGia.add(lblNewLabel_1);

		JLabel lblTitleQuanLyDocGia = new JLabel("Quản Lý Độc Giả");
		lblTitleQuanLyDocGia.setForeground(Color.WHITE);
		lblTitleQuanLyDocGia.setFont(new Font("Verdana", Font.PLAIN, 45));
		lblTitleQuanLyDocGia.setBounds(343, 73, 410, 56);
		pnlCardQuanLyDocGia.add(lblTitleQuanLyDocGia);

		JLabel lblNhapMaDocGia = new JLabel("Nhập Tên Độc Giả:");
		lblNhapMaDocGia.setForeground(Color.WHITE);
		lblNhapMaDocGia.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNhapMaDocGia.setBounds(582, 242, 234, 40);
		pnlCardQuanLyDocGia.add(lblNhapMaDocGia);

		txtTimDocGia = new JTextField();
		txtTimDocGia.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtTimDocGia.setColumns(10);
		txtTimDocGia.setBounds(826, 247, 200, 35);
		pnlCardQuanLyDocGia.add(txtTimDocGia);

		JButton btnTimDocGia = new JButton("Tìm");
		btnTimDocGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnTimDocGia)) {
					String ten = txtTimDocGia.getText().toString();
					if (ten.length() <= 0) {
						JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Tên Độc Giả");
						dulieubangDocGia();
					} else {
						ArrayList<DocGia> list = dsDocGia.TimDocGiaBangMa(ten);

						if (list.size() > 0) {
							tableModelDocGia.setRowCount(0);
							for (DocGia dg : list) {
								String[] rowtable = { dg.getMaDG(), dg.getTenDG(), dg.getNgaySinh(), dg.getCmnd(),
										dg.getSdt() };
								tableModelDocGia.addRow(rowtable);
							}
							tableDocGia.setModel(tableModelDocGia);
						} else {
							JOptionPane.showMessageDialog(null, "Không Tìm Thấy Độc Giả");
							dulieubangDocGia();
						}
					}
				}
			}
		});
		btnTimDocGia.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnTimDocGia.setBounds(1065, 245, 114, 35);
		pnlCardQuanLyDocGia.add(btnTimDocGia);

		JButton btnXoaDocGia = new JButton("Xóa");
		btnXoaDocGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableDocGia_1.getModel();
				int selectedIndex = tableDocGia_1.getSelectedRow();
				if (selectedIndex != -1) {
					String idDG = Df.getValueAt(selectedIndex, 0).toString();
					int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc chứ?", "Warning!",
							JOptionPane.YES_NO_OPTION);
					if (dialog == JOptionPane.YES_OPTION) {
						DocGiaDAO dg = new DocGiaDAO();
						dg.xoaDG(idDG);
						dulieubangPhieuMuon();
						dulieubangTraSach();
						dulieubangDocGia();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn độc giả!");
				}
			}
		});
		btnXoaDocGia.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXoaDocGia.setBounds(908, 539, 85, 40);
		pnlCardQuanLyDocGia.add(btnXoaDocGia);

		JButton btnSuaDocGia = new JButton("Sửa");
		btnSuaDocGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

					DefaultTableModel Df = (DefaultTableModel) tableDocGia_1.getModel();
					int selectedIndex = tableDocGia_1.getSelectedRow();
					if (selectedIndex != -1) {
						if (txtTenDG_QLDG.getText().length() > 0 && txtSoCMND_QLDG.getText().length() > 0
								&& txtSoDT_QLDG.getText().length() > 0 && txtNgaySinh_QLDG.getText().length() > 0
								&& txtThangSinh_QLDG.getText().length() > 0 && txtNamSinhQLDG.getText().length() > 0) {
						String maDG = Df.getValueAt(selectedIndex, 0).toString();
						String hoTen = txtTenDG_QLDG.getText();
						String soCMND = txtSoCMND_QLDG.getText();
						String soDT = txtSoDT_QLDG.getText();
						String birthday = txtNamSinhQLDG.getText() + "-" + txtThangSinh_QLDG.getText() + "-"
								+ txtNgaySinh_QLDG.getText();
						DocGiaDAO dg = new DocGiaDAO();
						dg.suaDocGia(maDG, hoTen, birthday, soCMND, soDT);
						dulieubangDocGia();
						dulieubangPhieuMuon();
						dulieubangTraSach();
						}else {
							JOptionPane.showMessageDialog(null, "Chưa Nhập Đủ Thông Tin Độc giả!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Chưa Chọn Độc Giả!");
					}
				} 
			
		});
		btnSuaDocGia.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSuaDocGia.setBounds(700, 539, 85, 40);
		pnlCardQuanLyDocGia.add(btnSuaDocGia);

		JScrollPane scrollPaneQuanLyDocGia = new JScrollPane();
		scrollPaneQuanLyDocGia.setBounds(516, 311, 677, 197);
		pnlCardQuanLyDocGia.add(scrollPaneQuanLyDocGia);

		tableDocGia = new JTable();
		String[] headers1 = "Mã Độc Giả; Tên Độc Giả; Năm Sinh; CMND ; Số Điện Thoại".split(";");
		tableModelDocGia = new DefaultTableModel(headers1, 0) {

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableDocGia_1 = new JTable(tableModelDocGia);
		tableDocGia_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableDocGia_1.getModel();
				int selectedIndex = tableDocGia_1.getSelectedRow();
				String birthDay = Df.getValueAt(selectedIndex, 2).toString();
				String[] part = birthDay.split("-");
				txtNgaySinh_QLDG.setText(part[2]);
				txtThangSinh_QLDG.setText(part[1]);
				txtNamSinhQLDG.setText(part[0]);
				txtTenDG_QLDG.setText(Df.getValueAt(selectedIndex, 1).toString());
				txtSoDT_QLDG.setText(Df.getValueAt(selectedIndex, 4).toString());
				txtSoCMND_QLDG.setText(Df.getValueAt(selectedIndex, 3).toString());

			}
		});
		scrollPaneQuanLyDocGia.setViewportView(tableDocGia_1);

		JLabel lblTncGi = new JLabel("Tên Độc Giả:");
		lblTncGi.setForeground(Color.WHITE);
		lblTncGi.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTncGi.setBounds(78, 318, 94, 18);
		pnlCardQuanLyDocGia.add(lblTncGi);

		JLabel lblSCmnd = new JLabel("Số CMND: ");
		lblSCmnd.setForeground(Color.WHITE);
		lblSCmnd.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSCmnd.setBounds(78, 357, 83, 17);
		pnlCardQuanLyDocGia.add(lblSCmnd);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại: ");
		lblSinThoi.setForeground(Color.WHITE);
		lblSinThoi.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSinThoi.setBounds(78, 395, 111, 18);
		pnlCardQuanLyDocGia.add(lblSinThoi);

		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setForeground(Color.WHITE);
		lblNgySinh.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNgySinh.setBounds(78, 443, 83, 17);
		pnlCardQuanLyDocGia.add(lblNgySinh);

		JLabel lblThngSinh = new JLabel("Tháng Sinh");
		lblThngSinh.setForeground(Color.WHITE);
		lblThngSinh.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblThngSinh.setBounds(78, 480, 83, 17);
		pnlCardQuanLyDocGia.add(lblThngSinh);

		JLabel lblNmSinh = new JLabel("Năm Sinh: ");
		lblNmSinh.setForeground(Color.WHITE);
		lblNmSinh.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNmSinh.setBounds(78, 520, 83, 17);
		pnlCardQuanLyDocGia.add(lblNmSinh);

		txtTenDG_QLDG = new JTextField();
		txtTenDG_QLDG.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenDG_QLDG.setBounds(196, 319, 146, 24);
		pnlCardQuanLyDocGia.add(txtTenDG_QLDG);
		txtTenDG_QLDG.setColumns(10);

		txtSoCMND_QLDG = new JTextField();
		txtSoCMND_QLDG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					txtSoCMND_QLDG.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					txtSoCMND_QLDG.setText("");
				}
			}
		});
		txtSoCMND_QLDG.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoCMND_QLDG.setColumns(10);
		txtSoCMND_QLDG.setBounds(196, 353, 146, 24);
		pnlCardQuanLyDocGia.add(txtSoCMND_QLDG);

		txtSoDT_QLDG = new JTextField();
		txtSoDT_QLDG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					txtSoDT_QLDG.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					txtSoDT_QLDG.setText("");
				}
			}
		});
		txtSoDT_QLDG.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoDT_QLDG.setColumns(10);
		txtSoDT_QLDG.setBounds(196, 389, 146, 24);
		pnlCardQuanLyDocGia.add(txtSoDT_QLDG);

		txtNgaySinh_QLDG = new JTextField();
		txtNgaySinh_QLDG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					txtNgaySinh_QLDG.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					txtNgaySinh_QLDG.setText("");
				}
			}
		});
		txtNgaySinh_QLDG.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNgaySinh_QLDG.setColumns(10);
		txtNgaySinh_QLDG.setBounds(196, 436, 42, 24);
		pnlCardQuanLyDocGia.add(txtNgaySinh_QLDG);

		txtThangSinh_QLDG = new JTextField();
		txtThangSinh_QLDG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					txtThangSinh_QLDG.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					txtThangSinh_QLDG.setText("");
				}
			}
		});
		txtThangSinh_QLDG.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtThangSinh_QLDG.setColumns(10);
		txtThangSinh_QLDG.setBounds(196, 481, 42, 24);
		pnlCardQuanLyDocGia.add(txtThangSinh_QLDG);

		txtNamSinhQLDG = new JTextField();
		txtNamSinhQLDG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					txtNamSinhQLDG.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					txtNamSinhQLDG.setText("");
				}
			}
		});
		txtNamSinhQLDG.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNamSinhQLDG.setColumns(10);
		txtNamSinhQLDG.setBounds(196, 521, 42, 24);
		pnlCardQuanLyDocGia.add(txtNamSinhQLDG);

		pnlMuonSach.setBounds(0, 400, 281, 67);
		pnlSideBar.add(pnlMuonSach);

		pnlMuonSach.setBackground(new Color(153, 153, 153));
		pnlMuonSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardMuonSach");
				setColor(pnlMuonSach);
				resetColor(pnlDashBoard);
				resetColor(pnlQuanLySach);
				resetColor(pnlQuanLyDocGia);
				resetColor(pnlDangKyTheTV);
				resetColor(pnlNhapSach);
				resetColor(pnlThanhLySach);
				resetColor(pnlTraSach);
			}
		});
		pnlMuonSach.setLayout(null);

		JLabel lblMuonSach = new JLabel("Mượn Sách");
		lblMuonSach.setForeground(new Color(255, 255, 255));
		lblMuonSach.setFont(new Font("Verdana", Font.BOLD, 15));
		lblMuonSach.setBounds(77, 10, 126, 48);
		pnlMuonSach.add(lblMuonSach);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("D:\\KiemThuPM\\src\\images\\icons8_course_assign_30px.png"));
		lblNewLabel_3.setBounds(28, 10, 39, 48);
		pnlMuonSach.add(lblNewLabel_3);
		
		
		pnlTraSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(pnlCard, "pnlCardTraSach");
				setColor(pnlTraSach);
				resetColor(pnlDashBoard);
				resetColor(pnlQuanLySach);
				resetColor(pnlQuanLyDocGia);
				resetColor(pnlDangKyTheTV);
				resetColor(pnlNhapSach);
				resetColor(pnlMuonSach);
				resetColor(pnlThanhLySach);
			}
		});
		pnlTraSach.setLayout(null);
		pnlTraSach.setBackground(new Color(153, 153, 153));
		pnlTraSach.setBounds(0, 692, 281, 67);
		pnlSideBar.add(pnlTraSach);
		
		JLabel lblTrSch = new JLabel("Trả Sách");
		lblTrSch.setForeground(Color.WHITE);
		lblTrSch.setFont(new Font("Verdana", Font.BOLD, 15));
		lblTrSch.setBounds(77, 10, 141, 48);
		pnlTraSach.add(lblTrSch);
		
		JLabel lblNewLabel_6_1 = new JLabel("");
		lblNewLabel_6_1.setBounds(28, 10, 39, 48);
		pnlTraSach.add(lblNewLabel_6_1);
		
		JLabel labelLoGo = new JLabel("");
		labelLoGo.setBounds(0, 0, 281, 183);
		pnlSideBar.add(labelLoGo);
		
		labelLoGo.setIcon(new ImageIcon(new ImageIcon(GD_DangNhap.class.getResource(URL_IMAGELOGO)).getImage().getScaledInstance(280, 180, Image.SCALE_DEFAULT)));
		JLabel lblTieuDePhieuMuon = new JLabel("Phiếu Mượn");
		lblTieuDePhieuMuon.setForeground(Color.WHITE);
		lblTieuDePhieuMuon.setFont(new Font("SansSerif", lblTieuDePhieuMuon.getFont().getStyle(), 50));
		lblTieuDePhieuMuon.setBounds(202, 88, 336, 64);
		pnlCardMuonSach.add(lblTieuDePhieuMuon);

		txtNhapMaPM = new JTextField();
		txtNhapMaPM.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtNhapMaPM.setColumns(10);
		txtNhapMaPM.setBounds(808, 285, 200, 35);
		pnlCardMuonSach.add(txtNhapMaPM);

		JButton btnThemPM = new JButton("Thêm Phiếu Mượn");
		btnThemPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GD_ThemPhieuMuon2 tpm = new GD_ThemPhieuMuon2();
				tpm.setVisible(true);
				tpm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnThemPM.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnThemPM.setBounds(202, 287, 200, 30);
		pnlCardMuonSach.add(btnThemPM);

		JScrollPane scrollPanePhieuMuon = new JScrollPane();
		scrollPanePhieuMuon.setBounds(202, 362, 955, 155);
		pnlCardMuonSach.add(scrollPanePhieuMuon);

		tableMuonSach = new JTable();
		String[] headers = " Mã Phiếu Mượn; Tên Độc Giả; Tên Nhân Viên; Ngày Mượn; Ngày Trả".split(";");
		tableModelMuonSach = new DefaultTableModel(headers, 0){

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableMuonSach = new JTable(tableModelMuonSach);
		scrollPanePhieuMuon.setViewportView(tableMuonSach);

		JButton btnSuaPM = new JButton("Sửa");
		btnSuaPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableMuonSach.getModel();
				int selectedIndex = tableMuonSach.getSelectedRow();
				if (selectedIndex != -1) {
					String mapm = Df.getValueAt(selectedIndex, 0).toString();
					String maDG = new PhieuMuonDAO().getMaDG(Df.getValueAt(selectedIndex, 1).toString());
					String tenNV = Df.getValueAt(selectedIndex, 2).toString();
					String ngayMuon = Df.getValueAt(selectedIndex, 3).toString();
					GD_SuaPhieuMuon tpm = new GD_SuaPhieuMuon(mapm, maDG, tenNV,ngayMuon);
					tpm.setVisible(true);
					tpm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu mượn!");
				}
			}
		});
		btnSuaPM.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSuaPM.setBounds(652, 587, 85, 40);
		pnlCardMuonSach.add(btnSuaPM);

		JButton btnXoaPM = new JButton("Xóa");
		btnXoaPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableMuonSach.getModel();
				int selectedIndex = tableMuonSach.getSelectedRow();
				if (selectedIndex != -1) {
					String idPM = Df.getValueAt(selectedIndex, 0).toString();
					int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
							JOptionPane.YES_NO_OPTION);
					if (dialog == JOptionPane.YES_OPTION) {
						PhieuMuonDAO pm = new PhieuMuonDAO();
						pm.xoaPM(idPM);

						dulieubangPhieuMuon();
						dulieubangTraSach();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu mượn!");
				}
			}
		});
		btnXoaPM.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXoaPM.setBounds(876, 587, 85, 40);
		pnlCardMuonSach.add(btnXoaPM);

		JLabel lblNhapMaPM = new JLabel("Nhập Mã Phiếu Mượn:");
		lblNhapMaPM.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNhapMaPM.setForeground(Color.WHITE);
		lblNhapMaPM.setBounds(562, 280, 234, 40);
		pnlCardMuonSach.add(lblNhapMaPM);

		JButton btnTimPM = new JButton("Tìm");
		btnTimPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnTimPM)) {
					String ten = txtNhapMaPM.getText().toString();
					if (ten.length() <= 0) {
						JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Phiếu Mượn!");
						dulieubangPhieuMuon();
					} else {
						ArrayList<PhieuMuon> list = dsPhieuMuon.TimPhieuMuonBangMa(ten);
						if (list.size() > 0) {
							tableModelMuonSach.setRowCount(0);
							for (PhieuMuon pm : list) {
								String[] rowtable = { pm.getMaPM(), pm.getTenDG(), pm.getTenNV(), pm.getNgayMuon(),
										pm.getNgayTra() };
								tableModelMuonSach.addRow(rowtable);
							}
							tableMuonSach.setModel(tableModelMuonSach);
						} else {
							JOptionPane.showMessageDialog(null, "Không Tìm Thấy Phiếu Mượn");
							dulieubangPhieuMuon();
						}
					}
				}
			}
		});
		btnTimPM.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnTimPM.setBounds(1043, 285, 114, 35);
		pnlCardMuonSach.add(btnTimPM);

		JButton btnXemPM = new JButton("Xem");
		btnXemPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableMuonSach.getModel();
				int selectedIndex = tableMuonSach.getSelectedRow();
				if (selectedIndex != -1) {
					String maPM = Df.getValueAt(selectedIndex, 0).toString();
					String tenDG = Df.getValueAt(selectedIndex, 1).toString();
					GD_ChiTietPhieuMuon ctpm = new GD_ChiTietPhieuMuon(maPM, tenDG);
					ctpm.setVisible(true);
					ctpm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu mượn!");
				}
			}
		});
		btnXemPM.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXemPM.setBounds(415, 587, 85, 40);
		pnlCardMuonSach.add(btnXemPM);

		JButton btnGiaHanPhieu = new JButton("Gia Hạn Phiếu Mượn");
		btnGiaHanPhieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuMuonDAO pmd = new PhieuMuonDAO();
				DefaultTableModel Df = (DefaultTableModel) tableMuonSach.getModel();
				int selectedIndex = tableMuonSach.getSelectedRow();
				if (selectedIndex != -1) {
					String maPM = Df.getValueAt(selectedIndex, 0).toString();
					String ngayTraCu = Df.getValueAt(selectedIndex, 4).toString();
					if (pmd.validationDaGiaHan(maPM)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();

						try {
							c.setTime(sdf.parse(ngayTraCu));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						c.add(Calendar.DAY_OF_MONTH, 3);

						String ngayTraMoi = sdf.format(c.getTime());
						pmd.giaHanPM(maPM, ngayTraMoi);
						dulieubangPhieuMuon();
						dulieubangTraSach();
					} else {
						JOptionPane.showMessageDialog(null, "Mỗi phiếu mượn chỉ được gia hạn 1 lần!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn phiếu mượn!");
				}
			}
		});
		btnGiaHanPhieu.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnGiaHanPhieu.setBounds(202, 239, 200, 30);
		pnlCardMuonSach.add(btnGiaHanPhieu);
		

///////////////////////////////////////////
		DataBase.getInstance().connect();
		dulieubangPhieuThanhLy();
		dulieubangPhieuDat();
		dulieubangSach();
		dulieubangDocGia();
		dulieubangPhieuMuon();
		dulieubangTraSach();
///////////////////////////////////		
		//color table
		TableColorCellRender colorTable = new TableColorCellRender();
		
		tableTraSach.setDefaultRenderer(Object.class, colorTable);
	}

	public void dulieubangPhieuMuon() {
		ArrayList<PhieuMuon> list = dsPhieuMuon.doctubangPhieuMuon();
		tableModelMuonSach.setRowCount(0);
		for (PhieuMuon phieumuon : list) {
			String[] rowdata1 = { phieumuon.getMaPM(), phieumuon.getTenDG(), phieumuon.getTenNV(),
					phieumuon.getNgayMuon(), phieumuon.getNgayTra() };
			tableModelMuonSach.addRow(rowdata1);
		}
		tableMuonSach.setModel(tableModelMuonSach);
	}
	
	public void dulieubangTraSach() {
		ArrayList<PhieuMuon> list = dsPhieuMuon.doctubangPhieuMuon();
		tableModelTraSach.setRowCount(0);
		for (PhieuMuon phieumuon : list) {
			String[] rowdata1 = { phieumuon.getMaPM(), phieumuon.getTenDG(), phieumuon.getTenNV(),
					phieumuon.getNgayMuon(), phieumuon.getNgayTra() };
			tableModelTraSach.addRow(rowdata1);
		}
		tableTraSach.setModel(tableModelTraSach);
	}

	public void dulieubangPhieuThanhLy() {
		ArrayList<PhieuThanhLy> list = dsPhieuThanhLy.doctubangPhieuThanhLy();
		tableModelQuanLyThanhLySach.setRowCount(0);
		for (PhieuThanhLy ptl : list) {
			String[] rowdata1 = { ptl.getMaPTL(), ptl.getTenNV(), ptl.getNgayTL() };

			tableModelQuanLyThanhLySach.addRow(rowdata1);
		}
		tableQuanLyThanhLySach_1.setModel(tableModelQuanLyThanhLySach);
	}

	public void dulieubangPhieuDat() {
		ArrayList<PhieuDat> list = dsPhieuDat.doctubangPhieuDat();
		tableModelQuanLyNhapSach.setRowCount(0);
		for (PhieuDat pd : list) {
			String[] rowdata1 = { pd.getMaPD(), pd.getTenNV(), pd.getNgayDat() };
			tableModelQuanLyNhapSach.addRow(rowdata1);
		}
		tableQuanLyNhapSach.setModel(tableModelQuanLyNhapSach);
	}

	public void dulieubangDocGia() {
		ArrayList<DocGia> list = dsDocGia.doctubang();
		tableModelDocGia.setRowCount(0);
		for (DocGia docGia : list) {
			String[] rowdata = { docGia.getMaDG(), docGia.getTenDG(), docGia.getNgaySinh(), docGia.getCmnd(),
					docGia.getSdt() };
			tableModelDocGia.addRow(rowdata);
		}
		tableDocGia_1.setModel(tableModelDocGia);
	}

	public void dulieubangSach() {
		ArrayList<Sach> list = dsSach.doctubangSach();
		tableModelQuanLySach.setRowCount(0);
		for (Sach sach : list) {
			String[] rowdata = { sach.getMaSach(), sach.getTenSach(), sach.getTheLoai(), sach.getNamXB(),
					sach.getTenNXB(), sach.getTinhTrangSach() };
			tableModelQuanLySach.addRow(rowdata);
		}
		tableQuanLySach.setModel(tableModelQuanLySach);
	}
}
