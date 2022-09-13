package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.Sach;
import entities.SachHienCo;

public class SachDAO {
	public SachDAO() {

	}

	public ArrayList<Sach> doctubangSach() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<Sach> dsSach = new ArrayList<Sach>();
		String sql = "SELECT [MaSach]\r\n" + "      ,[TenSach]\r\n" + "      ,[TheLoai]\r\n" + "      ,[NamXB]\r\n"
				+ "      ,[TenNXB]\r\n" + "      ,[Tinhtrangsach]\r\n" + "  FROM Sach, NhaXuatBan\r\n"
				+ "  WHERE Sach.MaNXB = NhaXuatBan.MaNXB";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maSach = rs.getString(1);
				String tenSach = rs.getString(2);
				String theLoai = rs.getString(3);
				String namXB = rs.getString(4);
				String tenNXB = rs.getString(5);
				String tinhTrangSach = rs.getString(6);
				Sach s = new Sach(maSach, tenSach, theLoai, namXB, tenNXB, tinhTrangSach);
				dsSach.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSach;
	}

	public ArrayList<Sach> TimSachBangMa(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<Sach> list = new ArrayList<>();
		String sql = "select s.MaSach,s.TenSach,s.TheLoai,s.NamXB, nxb.TenNXB ,s.Tinhtrangsach from sach s, NhaXuatBan nxb where s.MaNXB = nxb.MaNXB and s.TenSach LIKE N'%"+ma+"%';";
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
				Sach s = new Sach(maSach, tenSach, theLoai, namXB, tenNXB, tinhTrangSach);
				list.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void xoaSach(String maSach) {

		try {
			Connection con = DataBase.getInstance().getConnection();

			String querry1 = "delete from Sach where MaSach = '" + maSach + "'";
			PreparedStatement ps = con.prepareStatement(querry1);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã xóa!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể xóa sách khi độc giả còn đang mượn sách!");
			e.printStackTrace();
		}

	}
public void suaSach(String maSach, String tenSach, String theLoai, String namXB, String tenNXB,String tinhTrang) {
		
		String maNXB = getMaNXB(tenNXB);
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Update Sach set tensach = N'"+tenSach+"',theLoai = N'" +theLoai+"',namXB = '"+namXB+"', maNXB = '"+ maNXB+"',tinhTrangsach = N'"+tinhTrang +"' where maSach = '"+ maSach+"'";
			
			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã sửa!");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void themSach(String tenSach, String theLoai, String namXB, String tenNXB,
			String tinhTrang) {
		String maSach = getLastSach();
		String maNXB = getMaNXB(tenNXB);
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Insert into Sach values('"+maSach+"',N'"
					+tenSach+"',N'"+theLoai+"','"+namXB+"','"+maNXB+"',N'"+tinhTrang+"');";

			PreparedStatement ps = con.prepareStatement(querry);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Đã thêm!");

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public ArrayList<SachHienCo> timSachHienCo(String tenSachs){
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SachHienCo> dsshc = new ArrayList<SachHienCo>();
		String sql = "select s.MaSach , s.TenSach, s.TheLoai, s.NamXB, nxb.TenNXB, s.Tinhtrangsach from Sach s, NhaXuatBan nxb where s.MaNXB = nxb.MaNXB and s.tenSach LIKE '%"+tenSachs+"%'";
		
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

	public String getMaNXB(String tenNXB) {
		
		String maNXB = "";
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select manxb from NhaXuatBan where tenNXB = N'" + tenNXB + "'";

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

	public String getLastSach() {
		String tienTo = "SS_";
		String toanMa = "";
		String maSach = "";
		int max = 0;
		int hauTo;
		try {
			Connection con = DataBase.getInstance().getConnection();
			String querry = "Select MaSach from Sach";

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

		maSach = tienTo + max;

		return maSach;
	}
}
