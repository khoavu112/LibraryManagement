package entities;

public class DocGia {
	private String maDG, tenDG, ngaySinh, cmnd, sdt;

	public DocGia(String maDG, String tenDG, String ngaySinh, String cmnd, String sdt) {
		super();
		this.maDG = maDG;
		this.tenDG = tenDG;
		this.ngaySinh = ngaySinh;
		this.cmnd = cmnd;
		this.sdt = sdt;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getMaDG() {
		return maDG;
	}

	public void setMaDG(String maDG) {
		this.maDG = maDG;
	}

	public String getTenDG() {
		return tenDG;
	}

	public void setTenDG(String tenDG) {
		this.tenDG = tenDG;
	}

	public DocGia() {
		super();
	}

	@Override
	public String toString() {
		return "DocGia [maDG=" + maDG + ", tenDG=" + tenDG + ", ngaySinh=" + ngaySinh + ", cmnd=" + cmnd + ", sdt="
				+ sdt + "]";
	}
}
