package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.ChiTietPhieuDat;

public class CTPhieuDatDAO {
	
	public ArrayList<ChiTietPhieuDat> doctubangPhieuDat(String mapPD) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ChiTietPhieuDat> dsCTPD = new ArrayList<ChiTietPhieuDat>();
		String sql = "select ctpd.MaPD,nxb.TenNXB , ctpd.TenSach , ctpd.Soluong,ctpd.DonGia,ctpd.maCTPD from ChiTietPhieuDat ctpd , NhaXuatBan nxb where mapd = '"+mapPD+"' and ctpd.MaNXB = nxb.MaNXB";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maPD = rs.getString(1);
				String maNXB = rs.getString(2);
				String tenSach = rs.getString(3);
				String soLuong = rs.getString(4);
				String donGia = rs.getString(5);
				String maCTPD = rs.getString(6);
				ChiTietPhieuDat ctPD = new ChiTietPhieuDat(maPD,maCTPD ,maNXB, tenSach, soLuong, donGia);
				dsCTPD.add(ctPD);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTPD;
	}
	
	public ArrayList<String> JComBoBoxNXB() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> listNXB = new ArrayList<String>();
		String sql = "SELECT * FROM NhaXuatBan";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String tenNV = rs.getString("tennxb");
				listNXB.add(tenNV);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNXB;
	}
	public void themPhieuDat(String maPD,String tenNXB, String tenSach, String soLuong, String donGia) {
		
		String maCTPD = getLastMaCTPD();
		
		String maNXB = getMaNXB(tenNXB);
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Insert into ChiTietPhieuDat values('"+maPD+"','"
					+maNXB+"',N'"+tenSach+"',"+soLuong+","+donGia+",'"+maCTPD+"');";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã thêm!");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void suaPhieuDat(String tenNXB, String tenSach, String soLuong, String donGia, String maCTPD) {
		
		String maNXB = getMaNXB(tenNXB);
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Update ChiTietPhieuDat set MaNXB = '"+maNXB+"',tensach = N'"+tenSach+"',soluong = " +soLuong+",dongia = "+donGia+" where maCTPD = '"+ maCTPD+"'";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã sửa!");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
public String getMaNXB(String tenNXB) {
		
		String maNXB = "";
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select manxb from NhaXuatBan where tenNXB = N'"+tenNXB+"'";
			
			PreparedStatement ps = con.prepareStatement(querry);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				maNXB = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maNXB;
	}
public String getLastMaCTPD() {
	String tienTo = "maCTPD_";
	String toanMa = "";
	String maCTPD = "";
	int max = 1;
	int hauTo;
	try {
		Connection con = DataBase.getInstance().getConnection();
		String querry = "Select maCTPD from ChitietPhieuDat";
		
		PreparedStatement ps = con.prepareStatement(querry);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			toanMa = rs.getString(1);
			String[] part = toanMa.split("_");
			hauTo = Integer.parseInt(part[1].trim());
			if(max < hauTo) {
				max = hauTo;
			}
		}
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	max++;
	
	maCTPD = tienTo + max;
	
	return maCTPD;
}
public void xoaCTPD(String maCTPD) {
	
	
	try {
		Connection con = DataBase.getInstance().getConnection();
		String querry = "delete from ChiTietPhieuDat where MACTPD = '"+maCTPD+"'";
		
		PreparedStatement ps = con.prepareStatement(querry);
		
		ps.executeUpdate();
		JOptionPane.showMessageDialog(null, "Đã xóa!");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
	

