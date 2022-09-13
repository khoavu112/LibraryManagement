package entities;

public class ChiTietPhieuDat {
	private String maPD, maCTPD ,maNXB, tenSach, soLuong, donGia;
	
	public String getMaCTPD() {
		return maCTPD;
	}
	public String getMaPD() {
		return maPD;
	}
	public ChiTietPhieuDat() {
		super();
	}
	public ChiTietPhieuDat(String maPD,String maCTPD , String maNXB, String tenSach, String soLuong, String donGia) {
		super();
		this.maPD = maPD;
		this.maCTPD = maCTPD;
		this.maNXB = maNXB;
		this.tenSach = tenSach;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public void setMaPD(String maPD) {
		this.maPD = maPD;
	}
	public void setMaCTPD(String maCTPD) {
		this.maCTPD = maCTPD;
	}

	public String getMaNXB() {
		return maNXB;
	}
	
	public void setMaNXB(String maNXB) {
		this.maNXB = maNXB;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDat [maPD=" + maPD + ", maNXB=" + maNXB + ", tenSach=" + tenSach + ", soLuong=" + soLuong
				+ ", donGia=" + donGia + "]";
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonGia() {
		return donGia;
	}

	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}
}
