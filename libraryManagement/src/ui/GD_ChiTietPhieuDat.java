package ui;

import dao.DataBase;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import dao.CTPhieuDatDAO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CTPhieuDatDAO;
import entities.ChiTietPhieuDat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class GD_ChiTietPhieuDat extends JFrame {
	private String maPD = "";
	private JPanel contentPane;
	private JTextField txtTenSach;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;
	private JLabel lblmaPD;
	private CTPhieuDatDAO dsCTPhieuDat = new CTPhieuDatDAO();
	private JTable table;
	private String tenNXB;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ChiTietPhieuDat frame = new GD_ChiTietPhieuDat();
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
	public GD_ChiTietPhieuDat() {
		initialize();

	}

	public GD_ChiTietPhieuDat(String maPD) {
		this.maPD = maPD;
		initialize();
		bangdulieuCTPD();

	}

	public String getMaPD() {
		return maPD;
	}

	public void bangdulieuCTPD() {
		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    String donGia = null;
		DefaultTableModel Df = (DefaultTableModel) table.getModel();
		ChiTietPhieuDat ctpd = new ChiTietPhieuDat();
		ArrayList<ChiTietPhieuDat> list = dsCTPhieuDat.doctubangPhieuDat(getMaPD());

		Df.setRowCount(0);

		for (ChiTietPhieuDat pd : list) {
			donGia = currencyVN.format(Double.parseDouble(pd.getDonGia()));
			String[] rowtable = { pd.getMaCTPD(), pd.getMaNXB(), pd.getTenSach(), pd.getSoLuong(), donGia };
			Df.addRow(rowtable);

		}
		table.setModel(Df);

	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 523);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 140, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(379, 10, 545, 339);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				String valueCombo = Df.getValueAt(selectedIndex, 1).toString();
				for (int i = 0; i < comboBox.getItemCount(); i++) {
					if (comboBox.getItemAt(i).equals(valueCombo)) {
						comboBox.setSelectedIndex(i);
						break;
					}
				}
				txtTenSach.setText(Df.getValueAt(selectedIndex, 2).toString());
				txtSoLuong.setText(Df.getValueAt(selectedIndex, 3).toString());
				
				String s = Df.getValueAt(selectedIndex, 4).toString().replaceAll("\\D+","");
				
				txtDonGia.setText(s);
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã CTPD", "Nh\u00E0 xu\u1EA5t b\u1EA3n",
				"T\u00EAn s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(205);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);
		scrollPane.setViewportView(table);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenNXB = (String) comboBox.getItemAt(comboBox.getSelectedIndex());

			}
		});
		comboBox.setBounds(132, 107, 115, 21);
		contentPane.add(comboBox);
		ArrayList<String> dsNXB = dsCTPhieuDat.JComBoBoxNXB();
		for (int i = 0; i < dsNXB.size(); i++) {
			comboBox.addItem(dsNXB.get(i));
		}

		JLabel lblNhXutBn = new JLabel("Nhà xuất bản:");
		lblNhXutBn.setForeground(new Color(255, 255, 255));
		lblNhXutBn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhXutBn.setBounds(10, 110, 112, 17);
		contentPane.add(lblNhXutBn);

		JLabel lblNewLabel = new JLabel("Tên sách:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 150, 62, 17);
		contentPane.add(lblNewLabel);

		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenSach.setBounds(132, 146, 221, 19);
		contentPane.add(txtTenSach);
		txtTenSach.setColumns(10);

		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setForeground(new Color(255, 255, 255));
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSLng.setBounds(10, 190, 62, 17);
		contentPane.add(lblSLng);

		txtSoLuong = new JTextField();
		txtSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = txtSoLuong.getText();

				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					txtSoLuong.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					txtSoLuong.setText("");
				}
			}
		});
		txtSoLuong.setBounds(133, 188, 96, 19);
		contentPane.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		JLabel lblnGi = new JLabel("Đơn giá:");
		lblnGi.setForeground(new Color(255, 255, 255));
		lblnGi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnGi.setBounds(10, 228, 62, 17);
		contentPane.add(lblnGi);

		txtDonGia = new JTextField();
		txtDonGia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = txtDonGia.getText();

				if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN 
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					txtDonGia.setEditable(true);

				} else {
					String mess = "Chỉ được nhập số!";
					JOptionPane.showMessageDialog(null, mess);
					txtDonGia.setText("");
				}
			}
		});
		txtDonGia.setBounds(133, 226, 96, 19);
		contentPane.add(txtDonGia);
		txtDonGia.setColumns(10);

		lblmaPD = new JLabel("Phiếu đặt:");
		lblmaPD.setForeground(new Color(255, 255, 255));
		lblmaPD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblmaPD.setBounds(10, 21, 151, 22);
		contentPane.add(lblmaPD);
		lblmaPD.setText("Phiếu đặt: " + this.maPD);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(0, 351, 924, 135);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(0, 0, 255));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBounds(145, 58, 103, 31);
		panel.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTenSach.getText().length() > 0 && txtSoLuong.getText().length() > 0
						&& txtDonGia.getText().length() > 0) {
					String tenSach = txtTenSach.getText();
					String soLuong = txtSoLuong.getText();
					String donGia = txtDonGia.getText();
					new CTPhieuDatDAO().themPhieuDat(maPD, tenNXB, tenSach, soLuong, donGia);
					bangdulieuCTPD();
					//txtTenSach.setText("");
					//txtSoLuong.setText("");
					//txtDonGia.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin!");
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton btnSua = new JButton("Sửa");
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setBackground(new Color(0, 0, 255));
		btnSua.setBounds(424, 58, 103, 31);
		panel.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				if (selectedIndex != -1) {
					if (txtTenSach.getText().length() > 0 && txtSoLuong.getText().length() > 0
							&& txtDonGia.getText().length() > 0) {
						String maCTPD = Df.getValueAt(selectedIndex, 0).toString();
						String tenSach = txtTenSach.getText();
						String soLuong = txtSoLuong.getText();
						String donGia = txtDonGia.getText();
						new CTPhieuDatDAO().suaPhieuDat(tenNXB, tenSach, soLuong, donGia, maCTPD);
						bangdulieuCTPD();
						txtTenSach.setText("");
						txtSoLuong.setText("");
						txtDonGia.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn sách!");
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setBackground(new Color(0, 0, 255));
		btnXoa.setBounds(692, 58, 103, 31);
		panel.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				if (selectedIndex != -1) {
					String idTenSach = Df.getValueAt(selectedIndex, 0).toString();
					int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
							JOptionPane.YES_NO_OPTION);
					if (dialog == JOptionPane.YES_OPTION) {
						CTPhieuDatDAO ctpd = new CTPhieuDatDAO();
						ctpd.xoaCTPD(idTenSach);

						bangdulieuCTPD();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn sách!");
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);

	}
}
