package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.CTPhieuThanhLyDAO;
import entities.PhieuMuon;
import entities.SachHienCo;
import dao.PhieuMuonDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class GD_ChiTietPhieuMuon extends JFrame {

	private JPanel contentPane;
	private JTable tableSachHC;
	private JTable tableSachMuon;
	private JLabel lblTenDG;
	private String maPM;
	private String tenDG;
	private CTPhieuThanhLyDAO dsshc = new CTPhieuThanhLyDAO();
	private PhieuMuonDAO pmd = new PhieuMuonDAO();
	private JTextField textFieldTenSach;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ChiTietPhieuMuon frame = new GD_ChiTietPhieuMuon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void bangdulieuSachHienCo() {
		DefaultTableModel Df = (DefaultTableModel) tableSachHC.getModel();

		ArrayList<SachHienCo> list = dsshc.doctubangSachHienCo();
		if (list.size() > 0) {
			Df.setRowCount(0);
			for (SachHienCo shc : list) {
				String[] rowtable = { shc.getMaSach(), shc.getTenSach() };
				Df.addRow(rowtable);
			}
			tableSachHC.setModel(Df);
		}

	}

	public void bangdulieuSachDangMuon() {
		DefaultTableModel Df = (DefaultTableModel) tableSachMuon.getModel();

		ArrayList<SachHienCo> list = pmd.doctubangSachDangMuon(getMaPM());

		Df.setRowCount(0);
		for (SachHienCo shc : list) {
			String[] rowtable = { shc.getMaSach(), shc.getTenSach() };
			Df.addRow(rowtable);
		}
		tableSachMuon.setModel(Df);

	}

	public String getMaPM() {
		return this.maPM;
	}

	public GD_ChiTietPhieuMuon() {
		initialize();
	}

	public GD_ChiTietPhieuMuon(String maPM, String tenDG) {
		this.maPM = maPM;
		this.tenDG = tenDG;
		initialize();
		lblTenDG.setText(this.tenDG);
		bangdulieuSachHienCo();
		bangdulieuSachDangMuon();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 601);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 183, 296, 381);
		contentPane.add(scrollPane);

		tableSachHC = new JTable();
		tableSachHC.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 S\u00E1ch ", "T\u00EAn S\u00E1ch" }) {
					Class[] columnTypes = new Class[] { String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tableSachHC.getColumnModel().getColumn(0).setResizable(true);
		tableSachHC.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableSachHC.getColumnModel().getColumn(1).setResizable(true);
		scrollPane.setViewportView(tableSachHC);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(506, 183, 296, 381);
		contentPane.add(scrollPane_1);

		tableSachMuon = new JTable();
		tableSachMuon.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch" }) {
					Class[] columnTypes = new Class[] { String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tableSachMuon.getColumnModel().getColumn(0).setResizable(true);
		tableSachMuon.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableSachMuon.getColumnModel().getColumn(1).setResizable(true);
		scrollPane_1.setViewportView(tableSachMuon);

		JButton btnThm = new JButton("Thêm");
		btnThm.setForeground(new Color(255, 255, 255));
		btnThm.setBackground(new Color(0, 0, 255));
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableSachHC.getModel();
				int selectedIndex = tableSachHC.getSelectedRow();
				if (selectedIndex != -1) {
					String maSach = Df.getValueAt(selectedIndex, 0).toString();
					if (pmd.validationTonTaiSach(maSach)) {
						pmd.themPhieuChiTietMuon(getMaPM(), maSach);
						bangdulieuSachDangMuon();
					} else {
						JOptionPane.showMessageDialog(null, "Sách đã được mượn!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Sách!");
				}
			}
		});
		btnThm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThm.setBounds(354, 285, 85, 36);
		contentPane.add(btnThm);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(0, 0, 255));
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableSachMuon.getModel();
				int selectedIndex = tableSachMuon.getSelectedRow();
				if (selectedIndex != -1) {
					int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
							JOptionPane.YES_NO_OPTION);
					if (dialog == JOptionPane.YES_OPTION) {
					String maSach = Df.getValueAt(selectedIndex, 0).toString();
					pmd.xoaChiTietPhieuMuon(maSach);
					bangdulieuSachDangMuon();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Sách!");
				}
			}

		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(354, 382, 85, 36);
		contentPane.add(btnXoa);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 802, 181);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bảng Sách Đang Có:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(51, 103, 131, 17);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblTenDG = new JLabel("New label", SwingConstants.CENTER);
		lblTenDG.setForeground(new Color(255, 255, 255));
		lblTenDG.setBounds(270, 70, 272, 21);
		panel.add(lblTenDG);
		lblTenDG.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblChiTitPhiu = new JLabel("Chi Tiết Phiếu Mượn Của Độc Giả:");
		lblChiTitPhiu.setForeground(new Color(255, 255, 255));
		lblChiTitPhiu.setBounds(270, 27, 286, 20);
		panel.add(lblChiTitPhiu);
		lblChiTitPhiu.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblBngSchc = new JLabel("Bảng Sách Độc Đang Giả Mượn:");
		lblBngSchc.setForeground(new Color(255, 255, 255));
		lblBngSchc.setBounds(548, 103, 205, 17);
		panel.add(lblBngSchc);
		lblBngSchc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldTenSach = new JTextField();
		textFieldTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTenSach.setBounds(51, 150, 126, 23);
		panel.add(textFieldTenSach);
		textFieldTenSach.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) tableSachHC.getModel();
				String tenSach = textFieldTenSach.getText();
				if (tenSach.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Tên Sách!");
					bangdulieuSachHienCo();
				} else {
					ArrayList<SachHienCo> list = dsshc.timBangSachHienCo(tenSach);
					if (list.size() > 0) {
						Df.setRowCount(0);
						for (SachHienCo shc : list) {
							String[] rowtable = { shc.getMaSach(), shc.getTenSach() };
							Df.addRow(rowtable);
						}
						tableSachHC.setModel(Df);
					} else {
						JOptionPane.showMessageDialog(null, "Không Tìm Thấy Sách!");
						bangdulieuSachHienCo();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(197, 151, 96, 20);
		panel.add(btnNewButton);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
	}
}
