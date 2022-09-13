package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.DocGia;
import entities.PhieuMuon;
import entities.SachHienCo;
import entities.DocGia_PM;

public class PhieuMuonDAO {
	
	public PhieuMuonDAO() {

	}

	public ArrayList<PhieuMuon> doctubangPhieuMuon() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<PhieuMuon> dsPM = new ArrayList<PhieuMuon>();
		String sql = "select pm.MaPM ,dg.HoTen, nv.TenNV , pm.Ngaymuon, pm.Ngaytra from DocGia dg, PhieuMuon pm, NhanVien nv where dg.MaSV = pm.Madocgia and nv.MaNV = pm.MaNV";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maPM = rs.getString(1);
				String tenDG = rs.getString(2);
				String tenNV = rs.getString(3);
				String ngayMuon = rs.getString(4);
				String ngayTra = rs.getString(5);

				PhieuMuon pm = new PhieuMuon(maPM, tenDG, tenNV, ngayMuon, ngayTra);
				dsPM.add(pm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPM;
	}

	public ArrayList<PhieuMuon> TimPhieuMuonBangMa(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<PhieuMuon> list = new ArrayList<>();
		String sql = "select pm.MaPM ,dg.HoTen, nv.TenNV , pm.Ngaymuon, pm.Ngaytra from DocGia dg, PhieuMuon pm, NhanVien nv where pm.MaPM LIKE N'%"+ma+"%' and dg.MaSV = pm.Madocgia and nv.MaNV = pm.MaNV";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maPM = rs.getString(1);
				String tenDG = rs.getString(2);
				String tenNV = rs.getString(3);
				String ngayMuon = rs.getString(4);
				String ngayTra = rs.getString(5);

				PhieuMuon pm = new PhieuMuon(maPM, tenDG, tenNV, ngayMuon, ngayTra);
				list.add(pm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<PhieuMuon> TimDocGiaTraSach(String tenDGs) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<PhieuMuon> list = new ArrayList<>();
		String sql = "select pm.MaPM ,dg.HoTen, nv.TenNV , pm.Ngaymuon, pm.Ngaytra from DocGia dg, PhieuMuon pm, NhanVien nv where dg.HoTen LIKE N'%"+tenDGs+"%' AND dg.MaSV = pm.Madocgia and nv.MaNV = pm.MaNV";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maPM = rs.getString(1);
				String tenDG = rs.getString(2);
				String tenNV = rs.getString(3);
				String ngayMuon = rs.getString(4);
				String ngayTra = rs.getString(5);

				PhieuMuon pm = new PhieuMuon(maPM, tenDG, tenNV, ngayMuon, ngayTra);
				list.add(pm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<DocGia_PM> doctubangThemPhieuMuon() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<DocGia_PM> dsDG = new ArrayList<DocGia_PM>();
		String sql = "select MaSV,HoTen,CMND from DocGia ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDG = rs.getString(1);
				String tenDG = rs.getString(2);
				String cmnd = rs.getString(3);

				DocGia_PM dg = new DocGia_PM(maDG, tenDG, cmnd);
				dsDG.add(dg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDG;
	}

	public void themPhieuMuon(String maDG, String ngayMuon, String ngayTra, String tenNV) {
		String maPM = getLastMaPM();
		String manv = getMaNV(tenNV);
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Insert into PhieuMuon (maPM,madocgia,ngaymuon,ngaytra,manv) values('" + maPM + "','" + maDG + "','" + ngayMuon + "','" + ngayTra
					+ "','" + manv + "'" + ");";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã thêm!");

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Gặp Lỗi! Kiểm tra lại thông tin vừa chọn!");
			e.printStackTrace();
		}

	}
	public ArrayList<SachHienCo> doctubangSachDangMuon(String maPM){
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SachHienCo> dsshc = new ArrayList<SachHienCo>();
		String sql = "Select ctpm.MaSach, s.TenSach from ChiTietPhieuMuon ctpm, Sach s where ctpm.MaPM = '"+maPM+"' and s.MaSach = ctpm.MaSach;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maSach = rs.getString(1);
				String tenSach = rs.getString(2);
				
				SachHienCo ds = new SachHienCo(maSach, tenSach);
				dsshc.add(ds);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsshc;
	}
	public boolean kiemTraSachDangMuon(String maPM){
		boolean ck = true;
		int soLuong = 0;
		Connection con = DataBase.getInstance().getConnection();
		
		String sql = "Select ctpm.MaSach, s.TenSach from ChiTietPhieuMuon ctpm, Sach s where ctpm.MaPM = '"+maPM+"' and s.MaSach = ctpm.MaSach;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				soLuong++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(soLuong < 5) {
			ck = true;
		}else {
			ck = false;
		}
		return ck;
	}
	public void themPhieuChiTietMuon(String maPM, String maSach) {
		try {
			if(kiemTraSachDangMuon(maPM)) {
			
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Insert into ChiTietPhieuMuon values('" + maPM + "','" + maSach + "');";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã thêm!");

			}else {
				JOptionPane.showMessageDialog(null, "Không Được Mượn Quá 5 Cuốn!");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public void suaPhieuMuon(String maPM, String maDG, String tenNV) {
		String manv = getMaNV(tenNV);

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Update PhieuMuon set madocgia = '" + maDG + "',manv = '" + manv + "' where maPM = '" + maPM + "'";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã sửa!");

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dữ liệu nhập không hợp lệ!");
			e.printStackTrace();
		}

	}
	public void giaHanPM(String maPM,String ngayTraMoi) {
	

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Update PhieuMuon set ngaytra = '"+ngayTraMoi+"',giahan = 'daGH' where maPM = '"+maPM+"'";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã gia hạn!");

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dữ liệu nhập không hợp lệ!");
			e.printStackTrace();
		}

	}

	public String getLastMaPM() {
		String tienTo = "PM_";
		String toanMa = "";
		String maPD = "";
		int max = 1;
		int hauTo;
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select maPM from PhieuMuon";

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

		maPD = tienTo + max;

		return maPD;
	}

	public String getMaNV(String tenNV) {

		String maNV = "";
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select manv from nhanvien where tenNV = N'" + tenNV + "'";

			PreparedStatement ps = con.prepareStatement(querry);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				maNV = rs.getString(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maNV;
	}

	public String getMaDG(String tenDG) {

		String maDG = "";
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select maSV from DocGia where HoTen = N'" + tenDG + "';";

			PreparedStatement ps = con.prepareStatement(querry);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				maDG = rs.getString(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maDG;
	}

	public void xoaPM(String maPM) {

		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry2 = "delete from PhieuMuon where maPM = '" + maPM + "'";
			String querry1 = "delete from ChiTietPhieuMuon where maPM = '" + maPM + "'";
			PreparedStatement ps = con.prepareStatement(querry1);

			ps.executeUpdate();
			ps = con.prepareStatement(querry2);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã xóa!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void xoaChiTietPhieuMuon(String maSach) {

		try {
			Connection con = DataBase.getInstance().getConnection();
			
			String querry1 = "delete from ChiTietPhieuMuon where MaSach = '" + maSach + "'";
			PreparedStatement ps = con.prepareStatement(querry1);

			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Đã xóa!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean validationDaGiaHan(String maPM) {
		boolean ck = true;
		Connection con = DataBase.getInstance().getConnection();
		String sql = "select GiaHan from PhieuMuon where mapm ='"+maPM+"'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String fromDB = rs.getString(1);
				if (fromDB != null) {
					ck = false;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ck;
	}

	public boolean validationTrungThemPhieuMuon(String maDG) {
		boolean ck = true;
		Connection con = DataBase.getInstance().getConnection();
		String sql = "select madocgia from PhieuMuon";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maDG1 = rs.getString(1);
				if (maDG.equalsIgnoreCase(maDG1)) {
					ck = false;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ck;
	}
	public boolean validationTonTaiSach (String maSach){
		boolean ck = true;
		Connection con = DataBase.getInstance().getConnection();
		String sql = "select masach from chitietphieumuon";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maSachFromDB = rs.getString(1);
				if(maSach.equalsIgnoreCase(maSachFromDB)) {
					ck = false;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ck;
	}

}
