package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.DocGia;

public class DocGiaDAO {
	// ArrayList<DocGia> dsMHP = new ArrayList<DocGia>();
	public DocGiaDAO() {

	}

	public ArrayList<DocGia> doctubang() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<DocGia> dsDG = new ArrayList<DocGia>();
		String sql = "select * from DocGia";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDG = rs.getString(1);
				String tenDG = rs.getString(2);
				String namSinh = rs.getString(3);
				String cmnd = rs.getString(4);
				String sdt = rs.getString(5);
				DocGia dg = new DocGia(maDG, tenDG, namSinh, cmnd, sdt);
				dsDG.add(dg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDG;
	}

	public ArrayList<DocGia> TimDocGiaBangMa(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<DocGia> list = new ArrayList<>();
		String sql = "select * from DocGia where HoTen LIKE N'%"+ma+"%';";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDG = rs.getString(1);
				String tenDG = rs.getString(2);
				String namSinh = rs.getString(3);
				String cmnd = rs.getString(4);
				String sdt = rs.getString(5);
				DocGia dg = new DocGia(maDG, tenDG, namSinh, cmnd, sdt);
				list.add(dg);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public void themDG(String hoTen, String namSinh, String soCMND, String soDT) {

		String maDG = getLastMaDG();

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Insert into DocGia values('" + maDG + "',N'" + hoTen + "','" + namSinh + "','" + soCMND + "','"+ soDT + "'"+");";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã thêm!");

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Gặp Lỗi! Kiểm tra lại thông tin vừa nhập!");
			e.printStackTrace();
		}

	}

	public String getLastMaDG() {
		String tienTo = "maDG_";
		String toanMa = "";
		String maDG = "";
		int max = 1;
		int hauTo;
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select maSV from DocGia";

			PreparedStatement ps = con.prepareStatement(querry);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				toanMa = rs.getString(1);
				String[] part = toanMa.split("_");
				hauTo = Integer.parseInt(part[1].trim());
				if (max < hauTo) {
					max = hauTo;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		max++;

		maDG = tienTo + max;

		return maDG;
	}

	public void xoaDG(String maDG) {

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "delete from DocGia where maSV = '" + maDG + "'";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã xóa!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Không thể xóa độc giả khi độc giả còn đang mượn sách!");
			e.printStackTrace();
		}

	}
public void suaDocGia(String masv, String hoTen, String namSinh, String CMND, String soDT) {
		
		
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Update DocGia set hoten = N'"+hoTen+"',namSinh = '"+namSinh+"',CMND = '" +CMND+"',soDienThoai = '"+soDT+"' where masv = '"+ masv+"'";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã Sửa!");
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Gặp Lỗi! Kiểm tra lại thông tin vừa nhập!");
			e.printStackTrace();
		}

	}
}
