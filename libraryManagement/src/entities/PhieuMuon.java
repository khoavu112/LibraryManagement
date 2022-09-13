package entities;

public class PhieuMuon {
	private String maPM, tenDG, tenNV, ngayMuon, ngayTra;

	public String getMaPM() {
		return maPM;
	}

	public void setMaPM(String maPM) {
		this.maPM = maPM;
	}

	public String getTenDG() {
		return tenDG;
	}

	public void setTenDG(String tenDG) {
		this.tenDG = tenDG;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getNgayMuon() {
		return ngayMuon;
	}

	public void setNgayMuon(String ngayMuon) {
		this.ngayMuon = ngayMuon;
	}

	public String getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(String ngayTra) {
		this.ngayTra = ngayTra;
	}

	public PhieuMuon(String maPM, String tenDG, String tenNV, String ngayMuon, String ngayTra) {
		super();
		this.maPM = maPM;
		this.tenDG = tenDG;
		this.tenNV = tenNV;
		this.ngayMuon = ngayMuon;
		this.ngayTra = ngayTra;
	}

	public PhieuMuon() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
