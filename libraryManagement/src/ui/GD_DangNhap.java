package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GD_DangNhap extends JFrame {
	static GD_DangNhap frame;
	private JPanel contentPane;
	private JTextField taiKhoanField;
	private JPasswordField passwordField;
	private final String URL_LOGO = "/images/LoGoBook.png";
	private final String URL_LOGO2 = "/images/TrippleK.png";
	private final String TAIKHOAN = "taikhoanthuvien";
	private final String MATKHAU = "123123";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GD_DangNhap();
					frame.setUndecorated(true);
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
	public GD_DangNhap() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 861, 517);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 425, 537);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel labelLoGo = new JLabel("");
		labelLoGo.setBounds(0, 0, 425, 263);
		panel.add(labelLoGo);
		
		
		
		
		labelLoGo.setIcon(new ImageIcon(new ImageIcon(GD_DangNhap.class.getResource(URL_LOGO)).getImage().getScaledInstance(425, 263, Image.SCALE_DEFAULT)));
		
		JLabel loGoTripleK = new JLabel("");
		loGoTripleK.setBounds(0, 262, 425, 265);
		panel.add(loGoTripleK);
		
		loGoTripleK.setIcon(new ImageIcon(new ImageIcon(GD_DangNhap.class.getResource(URL_LOGO2)).getImage().getScaledInstance(425, 263, Image.SCALE_DEFAULT)));
		
		JButton btnNewButton = new JButton("\u0110\u0103ng nh\u1EADp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tk = taiKhoanField.getText();
				String pw = new String(passwordField.getPassword());			
				if(tk.length() == 0 || pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin!");
				}else {
					if(tk.equals(TAIKHOAN) && pw.equals(MATKHAU)) {
						GD_MainPage mp = new GD_MainPage();
						mp.main(null);
						mp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						
						
					}else if(tk.equals(TAIKHOAN) && !pw.equals(MATKHAU)){
						JOptionPane.showMessageDialog(null, "Nhập sai mật khẩu!");
					}else if(!tk.equals(TAIKHOAN) && pw.equals(MATKHAU)){
						JOptionPane.showMessageDialog(null, "Nhập sai tài khoản!");
					}else {
						JOptionPane.showMessageDialog(null, "Nhập sai tài khoản và mật khẩu!");
					}
				}
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(570, 395, 156, 36);
		contentPane.add(btnNewButton);
		
		taiKhoanField = new JTextField();
		taiKhoanField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		taiKhoanField.setBounds(571, 185, 156, 28);
		contentPane.add(taiKhoanField);
		taiKhoanField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("T\u00E0i Kho\u1EA3n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(570, 138, 117, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt Kh\u1EA9u");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMtKhu.setBounds(570, 233, 117, 22);
		contentPane.add(lblMtKhu);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(571, 288, 156, 28);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(836, 0, 30, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel labelDangNhap = new JLabel("\u0110\u0103ng nh\u1EADp");
		labelDangNhap.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		labelDangNhap.setBounds(593, 22, 133, 29);
		contentPane.add(labelDangNhap);
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		int x = (screenSize.width - getWidth()) / 2;  
		int y = (screenSize.height - getHeight()) / 2;  
		setLocation(x, y);  
	}
}
