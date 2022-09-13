package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.ChitietPhieuThanhLy;
import entities.SachHienCo;
public class CTPhieuThanhLyDAO {
	public ArrayList<ChitietPhieuThanhLy> doctubangPhieuDat(String mapPTL) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ChitietPhieuThanhLy> dsCTPTL = new ArrayList<ChitietPhieuThanhLy>();
		String sql = "select ctptl.maPTL , s.TenSach , ctptl.dongia , ctptl.maCTPTL  from ChiTietPhieuThanhLy ctptl, sach s where MaPTL = '"
				+ mapPTL + "' and ctptl.MaSach = s.MaSach";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maPTL = rs.getString(1);
				String tenSach = rs.getString(2);
				String donGia = rs.getString(3);
				String maCTPTL = rs.getString(4);
				ChitietPhieuThanhLy ctptl = new ChitietPhieuThanhLy(maPTL, tenSach, donGia, maCTPTL);
				dsCTPTL.add(ctptl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTPTL;
	}

	public String getLastMaCTPTL() {
		String tienTo = "maCTPTL_";
		String toanMa = "";
		String maCTPTL = "";
		int max = 1;
		int hauTo;
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select maCTPTL from ChitietPhieuThanhLy";

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

		maCTPTL = tienTo + max;

		return maCTPTL;
	}

	public void themPhieuThanhLy(String maPTL, String maSach, String donGia) {

		String maCTPTL = getLastMaCTPTL();

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "insert into ChiTietPhieuThanhLy values ('" + maPTL + "','" + maSach + "'," + donGia + ",'"
					+ maCTPTL + "');";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã thêm!");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void suaChiTietPhieuTL(String maCTPTL, String maSach, String donGia) {

		

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Update ChiTietPhieuThanhLy set masach = '"+maSach+"',donGia = "+donGia+" where maCTPTL = '"+ maCTPTL+"'";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã sửa!");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void xoaCTPTL(String maCTPTL) {

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "delete from ChiTietPhieuThanhLy where MACTPTL = '" + maCTPTL + "'";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã xóa!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String getMaSach(String mactptl) {		
		String maSach = "";
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select maSach from  ChiTietPhieuThanhLy ctptl where mactptl = '"+mactptl+"'";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				maSach = rs.getString(1);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maSach;
	}
	public ArrayList<SachHienCo> doctubangSachHienCo(){
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SachHienCo> dsshc = new ArrayList<SachHienCo>();
		String sql = "select s.MaSach , s.TenSach, s.TheLoai, s.NamXB, nxb.TenNXB, s.Tinhtrangsach from Sach s, NhaXuatBan nxb where s.MaNXB = nxb.MaNXB";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maSach = rs.getString(1);
				String tenSach = rs.getString(2);
				String theLoai = rs.getString(3);
				String namXB = rs.getString(4);
				String tenNXB = rs.getString(5);
				String tinhTrangSach = rs.getString(6);
				SachHienCo ds = new SachHienCo(maSach, tenSach, theLoai, namXB,tenNXB,tinhTrangSach);
				dsshc.add(ds);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsshc;
	}
	public ArrayList<SachHienCo> timBangSachHienCo(String tenSachs){
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SachHienCo> dsshc = new ArrayList<SachHienCo>();
		String sql = "select s.MaSach , s.TenSach, s.TheLoai, s.NamXB, nxb.TenNXB, s.Tinhtrangsach from Sach s, NhaXuatBan nxb where s.MaNXB = nxb.MaNXB and s.tensach Like '%"+tenSachs+"%'";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maSach = rs.getString(1);
				String tenSach = rs.getString(2);
				String theLoai = rs.getString(3);
				String namXB = rs.getString(4);
				String tenNXB = rs.getString(5);
				String tinhTrangSach = rs.getString(6);
				SachHienCo ds = new SachHienCo(maSach, tenSach, theLoai, namXB,tenNXB,tinhTrangSach);
				dsshc.add(ds);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsshc;
	}
	public boolean validationTrungThemPhieuThanhLy (String maSach){
		boolean ck = false;
		Connection con = DataBase.getInstance().getConnection();		
		String sql = "select maSach from sach";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maSach1 = rs.getString(1);
				if(maSach.equalsIgnoreCase(maSach1)) {
					ck = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ck;
	}
	public ArrayList<String> validationTonTaiThemPhieuThanhLy (String maSach){
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("kotrung");
		ar.add("kocomaphieu");
		Connection con = DataBase.getInstance().getConnection();
		String sql = "select ctptl.maSach, ptl.MaPTL from ChiTietPhieuThanhLy ctptl, PhieuThanhLy ptl where ptl.MaPTL = ctptl.MaPTL";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maSach1 = rs.getString(1);
				String maphieu = rs.getString(2);
				if(maSach.equalsIgnoreCase(maSach1)) {
					ar.set(0, "cotrung");
					ar.set(1, maphieu);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ar;
	}

}
