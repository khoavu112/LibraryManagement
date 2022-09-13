package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.PhieuDat;
import entities.PhieuThanhLy;

public class PhieuThanhLyDAO {
	public PhieuThanhLyDAO() {

	}

	public ArrayList<PhieuThanhLy> doctubangPhieuThanhLy() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<PhieuThanhLy> dsPTL = new ArrayList<PhieuThanhLy>();
		String sql = "Select PTL.MaPTL,nv.TenNV, PTL.NgayThanhLy from PhieuThanhLy PTL, NHANVIEN NV  WHERE PTL.MANV = NV.MANV";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maPTL = rs.getString(1);
				String tenNV = rs.getString(2);
				String ngayTL = rs.getString(3);
				PhieuThanhLy ptl = new PhieuThanhLy(maPTL, tenNV, ngayTL);
				dsPTL.add(ptl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPTL;
	}

	public ArrayList<PhieuThanhLy> TimPhieuThanhLyBangMa(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<PhieuThanhLy> list = new ArrayList<>();
		String sql = "select PTL.MaPTL, NV.TenNV, PTL.NgayThanhLy, Count(CTPTL.MaSach) as \"Số Lượng Sách\" , SUM(CTPTL.DonGia) as \"Tổng Tiền\"\r\n"
				+ "from PhieuThanhLy PTL, NhanVien NV, ChiTietPhieuThanhLy CTPTL\r\n"
				+ "where PTL.MaPTL = ? AND PTL.MaPTL = CTPTL.MaPTL AND NV.MaNV = PTL.MaNV\r\n"
				+ "group by PTL.MaPTL, NV.TenNV, PTL.NgayThanhLy";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maPTL = rs.getString(1);
				String tenNV = rs.getString(2);
				String ngayTL = rs.getString(3);

				PhieuThanhLy ptl = new PhieuThanhLy(maPTL, tenNV, ngayTL);
				list.add(ptl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<String> JComBoBoxNV() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> listNV = new ArrayList<String>();
		String sql = "SELECT * FROM NHANVIEN";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String tenNV = rs.getString("tennv");
				listNV.add(tenNV);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV;
	}

	public void themThanhLy(String tenNV, String timeDat) {
		String maPD = getLastMaPD();
		String manv = getMaNV(tenNV);
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Insert into PhieuThanhLy values('" + maPD + "','" + manv + "','" + timeDat + "');";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã lưu");

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Gặp Lỗi! Kiểm tra lại thông tin vừa chọn!");
			e.printStackTrace();
		}

	}
	public void suaPhieuThanhLy(String tenNV, String timeDat,String mapd) {
		
		String manv = getMaNV(tenNV);
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Update PhieuThanhLy set manv = '"+manv+"', ngaythanhly = '"+timeDat+"' where maptl = '"+mapd+"'";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã sửa!");
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Gặp Lỗi! Kiểm tra lại thông tin vừa chọn!");
			e.printStackTrace();
		}

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

	public String getLastMaPD() {
		String tienTo = "PTL_";
		String toanMa = "";
		String maPD = "";
		int max = 0;
		int hauTo;
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select maPTL from PhieuThanhLy";

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
}
