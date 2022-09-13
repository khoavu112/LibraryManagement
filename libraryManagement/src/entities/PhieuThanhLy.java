package entities;

public class PhieuThanhLy {
	private String maPTL, tenNV, ngayTL;

	public String getMaPTL() {
		return maPTL;
	}

	public void setMaPTL(String maPTL) {
		this.maPTL = maPTL;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getNgayTL() {
		return ngayTL;
	}

	public void setNgayTL(String ngayTL) {
		this.ngayTL = ngayTL;
	}

	public PhieuThanhLy() {
		super();
		
	}

	public PhieuThanhLy(String maPTL, String tenNV, String ngayTL) {
		super();
		this.maPTL = maPTL;
		this.tenNV = tenNV;
		this.ngayTL = ngayTL;
	}

	
	
	
}
