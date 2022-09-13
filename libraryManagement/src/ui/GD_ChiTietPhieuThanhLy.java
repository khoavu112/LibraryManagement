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

import dao.CTPhieuDatDAO;
import dao.CTPhieuThanhLyDAO;
import entities.ChiTietPhieuDat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.JButton;
import entities.ChitietPhieuThanhLy;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class GD_ChiTietPhieuThanhLy extends JFrame {
	private String maPTL = "";
	private JPanel contentPane;
	private JTable table;
	private JTextField txtMaSach;
	private JTextField txtDonGia;
	private CTPhieuThanhLyDAO dsCTPhieuTL = new CTPhieuThanhLyDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ChiTietPhieuThanhLy frame = new GD_ChiTietPhieuThanhLy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_ChiTietPhieuThanhLy() {
		initialize();

	}

	public GD_ChiTietPhieuThanhLy(String maPTL) {
		this.maPTL = maPTL;
		initialize();
		bangdulieuCTPTL();

	}

	public String getMaPTL() {
		return maPTL;
	}

	public void setTextMaSach(String maSach) {
		txtMaSach.setText(maSach);
	}

	public void bangdulieuCTPTL() {
		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    String donGia = null;
	    
		DefaultTableModel Df = (DefaultTableModel) table.getModel();

		ArrayList<ChitietPhieuThanhLy> list = dsCTPhieuTL.doctubangPhieuDat(getMaPTL());

		Df.setRowCount(0);

		for (ChitietPhieuThanhLy ptl : list) {
			donGia = currencyVN.format(Double.parseDouble(ptl.getDonGia()));
			String[] rowtable = { ptl.getMaCTPTL(), ptl.getTenSach(), donGia };
			Df.addRow(rowtable);

		}
		table.setModel(Df);

	}

	/**
	 * Create the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 436);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 140, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(338, 0, 368, 399);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();

				String mactptl = Df.getValueAt(selectedIndex, 0).toString();

				txtMaSach.setText(dsCTPhieuTL.getMaSach(mactptl));
				
				String s = Df.getValueAt(selectedIndex, 2).toString().replaceAll("\\D+","");
				
				txtDonGia.setText(s);

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 CTPTL", "T\u00EAn s\u00E1ch", "\u0110\u01A1n gi\u00E1" }) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(275);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Nhập mã sách:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 83, 94, 17);
		contentPane.add(lblNewLabel);

		txtMaSach = new JTextField();
		txtMaSach.setEditable(false);
		txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaSach.setBounds(127, 80, 126, 23);
		contentPane.add(txtMaSach);
		txtMaSach.setColumns(10);

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
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(127, 115, 126, 23);
		contentPane.add(txtDonGia);

		JLabel lblNhpnGi = new JLabel("Nhập đơn giá:");
		lblNhpnGi.setForeground(new Color(255, 255, 255));
		lblNhpnGi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhpnGi.setBounds(23, 118, 94, 17);
		contentPane.add(lblNhpnGi);

		JLabel LabelMaPTL = new JLabel("Mã phiếu:");
		LabelMaPTL.setForeground(new Color(255, 255, 255));
		LabelMaPTL.setFont(new Font("Tahoma", Font.BOLD, 18));
		LabelMaPTL.setBounds(23, 13, 161, 22);
		contentPane.add(LabelMaPTL);
		LabelMaPTL.setText("Mã Phiếu: " + maPTL);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(0, 147, 339, 252);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnThem = new JButton("Thêm");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(0, 0, 255));
		btnThem.setBounds(10, 109, 85, 35);
		panel.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maSach = txtMaSach.getText();
				String donGia = txtDonGia.getText();
				ArrayList<String> ck = dsCTPhieuTL.validationTonTaiThemPhieuThanhLy(maSach);
				if (maSach.length() > 0 && donGia.length() > 0) {
					if (dsCTPhieuTL.validationTrungThemPhieuThanhLy(maSach)) {
						if (ck.get(0).equalsIgnoreCase("kotrung")) {
							dsCTPhieuTL.themPhieuThanhLy(getMaPTL(), maSach, donGia);
							txtMaSach.setText("");
							txtDonGia.setText("");
							bangdulieuCTPTL();
						} else {
							JOptionPane.showMessageDialog(null, "Trùng mã sách ở phiếu " + ck.get(1) + "!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Mã sách không hợp lệ!");
					}
				} else if (maSach.length() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa Chọn Sách!");
				} else if (donGia.length() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa Nhập Đơn Giá!");
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setBackground(new Color(0, 0, 255));
		btnXoa.setBounds(116, 109, 85, 35);
		panel.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				if(selectedIndex != -1 ) {									
				String id = Df.getValueAt(selectedIndex, 0).toString();
				int dialog = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Warning!",
						JOptionPane.YES_NO_OPTION);
				if (dialog == JOptionPane.YES_OPTION) {
					CTPhieuThanhLyDAO ctptl = new CTPhieuThanhLyDAO();
					ctptl.xoaCTPTL(id);

					bangdulieuCTPTL();
				}
			}else {
				JOptionPane.showMessageDialog(null, "Chưa Chọn Sách!");
			}
				}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnSua = new JButton("Sửa");
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setBackground(new Color(0, 0, 255));
		btnSua.setBounds(231, 109, 85, 35);
		panel.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
				if(selectedIndex != -1) {
				String maCTPTLs = Df.getValueAt(selectedIndex, 0).toString();
				String maSachs = txtMaSach.getText();
				String donGias = txtDonGia.getText();
				if(maSachs.length() > 0 && donGias.length() > 0 ) {
				if (dsCTPhieuTL.getMaSach(maCTPTLs).equalsIgnoreCase(maSachs)) {
					dsCTPhieuTL.suaChiTietPhieuTL(maCTPTLs, maSachs, donGias);
					bangdulieuCTPTL();
					txtMaSach.setText("");
					txtDonGia.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Không được sửa mã sách!");
					txtMaSach.setText(dsCTPhieuTL.getMaSach(maCTPTLs));
				}
				}else if(maSachs.length() == 0){
					JOptionPane.showMessageDialog(null, "Chưa Chọn Sách!");
					
				}else if(donGias.length() == 0){
					JOptionPane.showMessageDialog(null, "Chưa Nhập Đơn Giá!");
					
				}
			}else {
				JOptionPane.showMessageDialog(null, "Chưa Chọn Sách!");
			}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = 800;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);

	}
}
