package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CTPhieuThanhLyDAO;
import dao.PhieuMuonDAO;
import entities.SachHienCo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GD_ChiTietPhieuTra extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String maPMFromMain;
	private String tenDGFromMain;
	private CTPhieuThanhLyDAO dsshc = new CTPhieuThanhLyDAO();
	private PhieuMuonDAO pmd = new PhieuMuonDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ChiTietPhieuTra frame = new GD_ChiTietPhieuTra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getMaPM() {
		return this.maPMFromMain;
	}
	
	public void bangdulieuSachDangMuon() {
		DefaultTableModel Df = (DefaultTableModel) table.getModel();

		ArrayList<SachHienCo> list = pmd.doctubangSachDangMuon(getMaPM());

		Df.setRowCount(0);
		for (SachHienCo shc : list) {
			String[] rowtable = { shc.getMaSach(), shc.getTenSach() };
			Df.addRow(rowtable);
		}
		table.setModel(Df);

	}

	/**
	 * Create the frame.
	 */
	public GD_ChiTietPhieuTra() {
		initialize();
	}
	
	public GD_ChiTietPhieuTra(String maPM, String tenDG) {
		this.maPMFromMain = maPM;
		this.tenDGFromMain = tenDG;
		
		initialize();
		bangdulieuSachDangMuon();
	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 579);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 167, 574, 194);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Sách Độc Giả Đang Mượn:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(34, 116, 168, 17);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Xác Nhận Trả");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel) table.getModel();
				int selectedIndex = table.getSelectedRow();
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(212, 427, 145, 47);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 574, 106);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Tên Độc Giả:");
		lblTitle.setBounds(31, 54, 140, 42);
		panel.add(lblTitle);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTenDG = new JLabel("");
		lblTenDG.setBounds(128, 54, 231, 42);
		panel.add(lblTenDG);
		lblTenDG.setForeground(new Color(255, 255, 255));
		lblTenDG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenDG.setText(tenDGFromMain);
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		int x = (screenSize.width - getWidth()) / 2;  
		int y = (screenSize.height - getHeight()) / 2;  
		setLocation(x, y);  
	}
}
