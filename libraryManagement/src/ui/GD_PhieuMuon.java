package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class GD_PhieuMuon {

	private JFrame frame;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_PhieuMuon window = new GD_PhieuMuon();
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
	public GD_PhieuMuon() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setText("Search");
		txtSearch.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtSearch.setBounds(310, 97, 200, 30);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnNewButton = new JButton("Thêm Phiếu Mượn");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton.setBounds(532, 97, 200, 30);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Phiếu Mượn");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblNewLabel.setBounds(85, 28, 187, 43);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 159, 647, 119);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 Phi\u1EBFu M\u01B0\u1EE3n", "M\u00E3 \u0110\u1ED9c Gi\u1EA3", "T\u00EAn \u0110\u1ED9c Gi\u1EA3", "Ng\u00E0y M\u01B0\u1EE3n", "Ng\u00E0y Tr\u1EA3", "Ph\u00ED M\u01B0\u1EE3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setFont(new Font("Verdana", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnNewButton_1.setBounds(258, 331, 85, 40);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Xóa");
		btnNewButton_1_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnNewButton_1_1.setBounds(425, 331, 85, 40);
		panel.add(btnNewButton_1_1);
	}
}
