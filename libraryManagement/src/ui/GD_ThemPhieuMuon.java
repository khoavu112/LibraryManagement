package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class GD_ThemPhieuMuon {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ThemPhieuMuon window = new GD_ThemPhieuMuon();
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
	public GD_ThemPhieuMuon() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNgyMn = new JLabel("Ngày Mượn");
		lblNgyMn.setOpaque(true);
		lblNgyMn.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNgyMn.setBackground(new Color(50, 205, 50));
		lblNgyMn.setBounds(459, 89, 94, 30);
		panel.add(lblNgyMn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 232, 726, 116);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "S\u1ED1 Ng\u00E0y Qu\u00E1 H\u1EA1n", "Ti\u1EC1n Ph\u1EA1t", "T\u00ECnh Tr\u1EA1ng S\u00E1ch"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Double.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.setFont(new Font("Verdana", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Mã Độc Giả");
		lblNewLabel.setBackground(new Color(152, 251, 152));
		lblNewLabel.setForeground(Color.black);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setToolTipText("RRRRRR");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel.setBounds(44, 89, 94, 30);
		panel.add(lblNewLabel);
		
		JLabel lblTncGi = new JLabel("Tên Độc Giả");
		lblTncGi.setBackground(new Color(152, 251, 152));
		lblTncGi.setOpaque(true);
		lblTncGi.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblTncGi.setBounds(44, 129, 94, 30);
		panel.add(lblTncGi);
		
		JLabel lblSLngSch = new JLabel("Số Lượng Sách");
		lblSLngSch.setOpaque(true);
		lblSLngSch.setBackground(new Color(152, 251, 152));
		lblSLngSch.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSLngSch.setBounds(44, 169, 125, 30);
		panel.add(lblSLngSch);
		
		JLabel lblNgyTr = new JLabel("Ngày Trả");
		lblNgyTr.setOpaque(true);
		lblNgyTr.setBackground(new Color(50, 205, 50));
		lblNgyTr.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNgyTr.setBounds(459, 162, 94, 30);
		panel.add(lblNgyTr);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField.setBounds(193, 89, 207, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(193, 129, 207, 30);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(193, 169, 207, 30);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(563, 89, 207, 30);
		panel.add(textField_3);
		
		Canvas canvas = new Canvas();
		canvas.setEnabled(false);
		canvas.setBackground(new Color(152, 251, 152));
		canvas.setBounds(10, 58, 416, 191);
		panel.add(canvas);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(563, 162, 207, 30);
		panel.add(textField_4);
		
		Canvas canvas_1 = new Canvas();
		canvas_1.setBackground(new Color(50, 205, 50));
		canvas_1.setBounds(388, 72, 408, 203);
		panel.add(canvas_1);
		
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnNewButton.setBounds(279, 358, 100, 40);
		panel.add(btnNewButton);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnThot.setBounds(453, 358, 100, 40);
		panel.add(btnThot);
		
		JLabel lblNewLabel_1 = new JLabel("Thêm Phiếu Mượn");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(279, 21, 300, 40);
		panel.add(lblNewLabel_1);
	}
}
