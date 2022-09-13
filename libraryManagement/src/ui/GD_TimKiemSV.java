package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DataBase;
import dao.DocGiaDAO;
import entities.DocGia;

public class GD_TimKiemSV extends JFrame implements ActionListener, MouseListener{
	
	private JFrame frame;
	private JTextField txtTim;
	private JTable table;
	private DefaultTableModel tableModel;
	private DocGiaDAO dsDocGia = new DocGiaDAO();
	private JButton btnTimKiem;
	private JButton btnThemPM;
	private String maSV;
	
	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_TimKiemSV window = new GD_TimKiemSV();
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
	public GD_TimKiemSV() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 586, 463);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã SV");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(113, 76, 78, 48);
		panel.add(lblNewLabel);
		
		txtTim = new JTextField();
		txtTim.setBounds(242, 95, 96, 19);
		panel.add(txtTim);
		txtTim.setColumns(10);
		
		btnTimKiem = new JButton("New button");
		btnTimKiem.setBounds(404, 94, 85, 21);
		panel.add(btnTimKiem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 157, 495, 222);
		panel.add(scrollPane);
		
		table = new JTable();
		String[] headers = "Mã Độc Giả; Tên Độc Giả".split(";");
		tableModel = new DefaultTableModel(headers,0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		btnThemPM = new JButton("ThemPM");
		btnThemPM.setBounds(225, 400, 85, 21);
		panel.add(btnThemPM);
		
		btnTimKiem.addActionListener(this);
		btnThemPM.addActionListener(this);
		table.addMouseListener(this);
		DataBase.getInstance().connect();
		dulieubang();
	}
	public void dulieubang() {
		ArrayList<DocGia> list = dsDocGia.doctubang();
		tableModel.setRowCount(0);
		for (DocGia docGia : list) {
			String [] rowdata = {docGia.getMaDG(),docGia.getTenDG()};
			tableModel.addRow(rowdata);
		}
		table.setModel(tableModel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTimKiem)) {
			String ten = txtTim.getText().toString();
			if(ten.length()<=0) {
				JOptionPane.showMessageDialog(this, "Chua nhap ten");
				dulieubang();
			}else {
				ArrayList<DocGia> list = dsDocGia.TimDocGiaBangMa(ten);
				if(list.size()>0) {
					tableModel.setRowCount(0);
					for (DocGia dg : list) {
						String[] rowtable = {dg.getMaDG(),dg.getTenDG()};
						tableModel.addRow(rowtable);
					}					
					table.setModel(tableModel);
				}else {
					JOptionPane.showMessageDialog(null,"Khong tim thay");
					dulieubang();
				}
			}
		}
		if(o.equals(btnThemPM)) {
			int row = table.getSelectedRow();
			if(row>=0) {
				maSV = table.getValueAt(row, 0).toString();
				//System.out.println(maSV);
				
			}else {
				JOptionPane.showMessageDialog(this, "Ban chua chon doc gia");
			}
		}
		
	}
}
