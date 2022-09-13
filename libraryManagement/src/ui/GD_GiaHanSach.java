package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;

public class GD_GiaHanSach {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_GiaHanSach window = new GD_GiaHanSach();
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
	public GD_GiaHanSach() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trả Sách");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblNewLabel.setBounds(48, 24, 145, 49);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnNewButton.setBounds(542, 98, 126, 40);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Phiếu Mượn:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(152, 98, 168, 40);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField.setBounds(336, 103, 196, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 178, 691, 139);
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
				"M\u00E3 Phi\u1EBFu M\u01B0\u1EE3n", "M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "S\u1ED1 Ng\u00E0y Qu\u00E1 H\u1EA1n", "Ti\u1EC1n Ph\u1EA1t"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(89);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.setFont(new Font("Verdana", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		JButton btnLu = new JButton("Lưu");
		btnLu.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnLu.setBounds(165, 355, 126, 40);
		panel.add(btnLu);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSa.setBounds(324, 355, 126, 40);
		panel.add(btnSa);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXa.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnXa.setBounds(487, 355, 126, 40);
		panel.add(btnXa);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(new Color(65, 105, 225));
		canvas.setBounds(325, 117, 352, 40);
		panel.add(canvas);
		
		Canvas canvas_1 = new Canvas();
		canvas_1.setBackground(new Color(65, 105, 225));
		canvas_1.setBounds(48, 341, 691, 40);
		panel.add(canvas_1);
	}
}
