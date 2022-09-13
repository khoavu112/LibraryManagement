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

import entities.SachHienCo;
import entities.ChitietPhieuThanhLy;
import dao.SachDAO;
import dao.CTPhieuThanhLyDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GD_DanhSachHienCo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private CTPhieuThanhLyDAO dsshc = new CTPhieuThanhLyDAO();
	private SachDAO sachDao = new SachDAO();
	private GD_ChiTietPhieuThanhLy ctPTL;
	private JTextField textFieldTenSach;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_DanhSachHienCo frame = new GD_DanhSachHienCo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_ChiTietPhieuThanhLy getOject() {
		return ctPTL;

	}

	public void bangdulieuSachHienCo() {
		DefaultTableModel Df = (DefaultTableModel) table.getModel();

		ArrayList<SachHienCo> list = dsshc.doctubangSachHienCo();
		if (list.size() > 0) {
			Df.setRowCount(0);
			for (SachHienCo shc : list) {
				String[] rowtable = { shc.getMaSach(), shc.getTenSach(), shc.getTheLoai(), shc.getNamXB(),
						shc.getTenNXB(), shc.getTinhTrangSach() };
				Df.addRow(rowtable);
			}
			table.setModel(Df);
		}

	}

	/**
	 * Create the frame.
	 */
	public GD_DanhSachHienCo(GD_ChiTietPhieuThanhLy ctPTL) {
		this.ctPTL = ctPTL;
		khoiTao();
	}

	public GD_DanhSachHienCo() {
		khoiTao();
	}

	public void khoiTao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 436);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 140, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 150, 706, 249);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();

				String maSach = Df.getValueAt(selectedIndex, 0).toString();
				ctPTL.setTextMaSach(maSach);
			}
		});
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch",
						"Th\u1EC3 lo\u1EA1i", "N\u0103m XB", "T\u00EAn NXB", "T\u00ECnh tr\u1EA1ng s\u00E1ch" }) {
					Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class,
							String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(205);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		scrollPane.setViewportView(table);

		JLabel lblDanhSchSch = new JLabel("Danh sách sách hiện có");
		lblDanhSchSch.setForeground(new Color(255, 255, 255));
		lblDanhSchSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDanhSchSch.setBounds(242, 21, 253, 25);
		contentPane.add(lblDanhSchSch);

		textFieldTenSach = new JTextField();
		textFieldTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTenSach.setForeground(new Color(0, 0, 0));
		textFieldTenSach.setBounds(120, 107, 126, 23);
		contentPane.add(textFieldTenSach);
		textFieldTenSach.setColumns(10);

		JLabel lblTenSach = new JLabel("Nhập tên sách:");
		lblTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenSach.setForeground(new Color(255, 255, 255));
		lblTenSach.setBounds(15, 110, 95, 17);
		contentPane.add(lblTenSach);

		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenSach = textFieldTenSach.getText();
				if (tenSach.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Ten Sach");
					bangdulieuSachHienCo();
				} else {
					ArrayList<SachHienCo> list = sachDao.timSachHienCo(tenSach);
					if (list.size() > 0) {
						DefaultTableModel Df = (DefaultTableModel) table.getModel();

						Df.setRowCount(0);
						for (SachHienCo shc : list) {
							String[] rowtable = { shc.getMaSach(), shc.getTenSach(), shc.getTheLoai(), shc.getNamXB(),
									shc.getTenNXB(), shc.getTinhTrangSach() };
							Df.addRow(rowtable);
						}
						table.setModel(Df);

					}else {
						JOptionPane.showMessageDialog(null, "Không thấy sách cần tìm");
						bangdulieuSachHienCo();
					}
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(270, 108, 76, 21);
		contentPane.add(btnNewButton);
		bangdulieuSachHienCo();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = 90;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);

	}
}
