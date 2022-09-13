package entities;

public class ChitietPhieuThanhLy {
	private String maPTL, tenSach, donGia, maCTPTL;

	public String getMaPTL() {
		return maPTL;
	}

	public void setMaPTL(String maPTL) {
		this.maPTL = maPTL;
	}

	public ChitietPhieuThanhLy() {
		super();
		
	}

	public ChitietPhieuThanhLy(String maPTL, String tenSach, String donGia,String maCTPTL) {
		super();
		this.maPTL = maPTL;		
		this.tenSach = tenSach;
		this.donGia = donGia;
		this.maCTPTL = maCTPTL;
	}

	public String getMaCTPTL() {
		return maCTPTL;
	}

	public void setMaCTPTL(String maCTPTL) {
		this.maCTPTL = maCTPTL;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getDonGia() {
		return donGia;
	}

	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}
}
