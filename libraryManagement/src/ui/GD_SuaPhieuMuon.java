package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.PhieuMuonDAO;
import dao.DocGiaDAO;
import dao.PhieuDatDAO;
import entities.DocGia;
import entities.DocGia_PM;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GD_SuaPhieuMuon extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField txtMaDG;
	private String tenNV;
	private String dateLap;
	private String maPM;
	private String maDGFromeMain;
	private String dateAfter1Week;
	private String ngayMuon;
	private PhieuMuonDAO pm = new PhieuMuonDAO();
	private JLabel lblSuaPhieuMuon;
	private JComboBox comboBoxNV;
	private DocGiaDAO docGiaDAO = new DocGiaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ThemPhieuMuon2 frame = new GD_ThemPhieuMuon2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getmaPM() {
		return this.maPM;
	}

	public String getmaDG() {
		return this.maDGFromeMain;
	}

	public void bangdulieuDocGiaHienCo() {
		DefaultTableModel Df = (DefaultTableModel) table.getModel();

		ArrayList<DocGia_PM> list = pm.doctubangThemPhieuMuon();
		if (list.size() > 0) {
			Df.setRowCount(0);
			for (DocGia_PM shc : list) {
				String[] rowtable = { shc.getMaDG(), shc.getTenDG(), shc.getCmnd() };
				Df.addRow(rowtable);
			}
			table.setModel(Df);
		}

	}

	/**
	 * Create the frame.
	 */
	public GD_SuaPhieuMuon(String maPM, String maDG, String tenNVFromMain,String ngayMuonFromMain) {
		initialize();
		this.maPM = maPM;
		this.maDGFromeMain = maDG;
		
		this.ngayMuon = ngayMuonFromMain;
		lblSuaPhieuMuon.setText("S???a Phi???u M?????n: " + maPM);
		for (int i = 0; i < comboBoxNV.getItemCount(); i++) {
			if (comboBoxNV.getItemAt(i).equals(tenNVFromMain)) {
				comboBoxNV.setSelectedIndex(i);
				break;
			}
		}
		txtMaDG.setText(maDG);
	}

	public GD_SuaPhieuMuon() {
		initialize();
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 66, 398, 253);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				txtMaDG.setText(Df.getValueAt(selectedIndex, 0).toString());
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 \u0110\u1ED9c Gi\u1EA3", "T\u00EAn \u0110\u1ED9c Gi\u1EA3", "S\u1ED1 CMND" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		JLabel lblMcGi = new JLabel("M?? ?????c Gi???: ");
		lblMcGi.setForeground(new Color(255, 255, 255));
		lblMcGi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMcGi.setBounds(443, 110, 82, 17);
		contentPane.add(lblMcGi);

		txtMaDG = new JTextField();
		txtMaDG.setEditable(false);
		txtMaDG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaDG.setBounds(535, 107, 126, 23);
		contentPane.add(txtMaDG);
		txtMaDG.setColumns(10);

		JButton btnLu = new JButton("L??u");
		btnLu.setBackground(new Color(0, 0, 255));
		btnLu.setForeground(new Color(255, 255, 255));
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDG = txtMaDG.getText();
				if (!maDG.equalsIgnoreCase(getmaDG())) {
					if (pm.validationTrungThemPhieuMuon(maDG)) {
						
						new PhieuMuonDAO().suaPhieuMuon(getmaPM(), maDG, tenNV);
						txtMaDG.setText("");
						GD_MainPage mainframe = new GD_MainPage().getInstanceOfMainPage();
						mainframe.dulieubangPhieuMuon();
						mainframe.dulieubangTraSach();
					} else {
						JOptionPane.showMessageDialog(null, "?????c Gi??? ch??a Tr??? Phi???u M?????n!");
					}
				} else {
					
					new PhieuMuonDAO().suaPhieuMuon(getmaPM(), maDG, tenNV);
					txtMaDG.setText("");
					GD_MainPage mainframe = new GD_MainPage().getInstanceOfMainPage();
					mainframe.dulieubangPhieuMuon();
					mainframe.dulieubangTraSach();
				}
			}
		});
		btnLu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLu.setBounds(308, 395, 74, 42);
		contentPane.add(btnLu);

		JLabel lblChnNhnVin = new JLabel("Ch???n Nh??n Vi??n:");
		lblChnNhnVin.setForeground(new Color(255, 255, 255));
		lblChnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChnNhnVin.setBounds(418, 153, 107, 17);
		contentPane.add(lblChnNhnVin);

		comboBoxNV = new JComboBox();
		comboBoxNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenNV = (String) comboBoxNV.getItemAt(comboBoxNV.getSelectedIndex());
			}
		});
		comboBoxNV.setBounds(535, 153, 133, 21);
		contentPane.add(comboBoxNV);
		ArrayList<String> dsnv = new PhieuDatDAO().JComBoBoxNV();
		for (int i = 0; i < dsnv.size(); i++) {
			comboBoxNV.addItem(dsnv.get(i));
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 725, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
				JLabel lblTmcGi = new JLabel("T??m ?????c Gi???:");
				lblTmcGi.setForeground(new Color(255, 255, 255));
				lblTmcGi.setBounds(28, 35, 83, 17);
				panel.add(lblTmcGi);
				lblTmcGi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
						textField = new JTextField();
						textField.setBounds(121, 32, 126, 23);
						panel.add(textField);
						textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
						textField.setColumns(10);
						
								JButton btnTm = new JButton("T??m");
								btnTm.setBounds(277, 31, 68, 25);
								panel.add(btnTm);
								btnTm.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										String ten = textField.getText();
										if (ten.length() <= 0) {
											JOptionPane.showMessageDialog(null, "B???n Ch??a Nh???p ?????c gi???!");
											bangdulieuDocGiaHienCo();
										} else {
											ArrayList<DocGia> list = docGiaDAO.TimDocGiaBangMa(ten);
											if (list.size() > 0) {
												DefaultTableModel Df = (DefaultTableModel) table.getModel();

												Df.setRowCount(0);
												for (DocGia shc : list) {
													String[] rowtable = { shc.getMaDG(), shc.getTenDG(), shc.getCmnd() };
													Df.addRow(rowtable);
												}
												table.setModel(Df);

											}else {
												JOptionPane.showMessageDialog(null, "Kh??ng Th???y ?????c Gi??? C???n T??m!");
												bangdulieuDocGiaHienCo();
											}
										}
									}
								});
								btnTm.setFont(new Font("Tahoma", Font.PLAIN, 14));
								lblSuaPhieuMuon = new JLabel("S???a Phi???u M?????n");
								lblSuaPhieuMuon.setForeground(new Color(255, 255, 255));
								lblSuaPhieuMuon.setBounds(443, 30, 272, 23);
								panel.add(lblSuaPhieuMuon);
								lblSuaPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 19));
		bangdulieuDocGiaHienCo();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
	}
}
