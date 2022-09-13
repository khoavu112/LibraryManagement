package entities;

public class Sach {
	private String maSach, tenSach,theLoai, namXB, tenNXB,tinhTrangSach;

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public String getNamXB() {
		return namXB;
	}

	public void setNamXB(String namXB) {
		this.namXB = namXB;
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	public String getTinhTrangSach() {
		return tinhTrangSach;
	}

	public void setTinhTrangSach(String tinhTrangSach) {
		this.tinhTrangSach = tinhTrangSach;
	}

	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tenSach=" + tenSach + ", theLoai=" + theLoai + ", namXB=" + namXB
				+ ", tenNXB=" + tenNXB + ", tinhTrangSach=" + tinhTrangSach + "]";
	}

	public Sach(String maSach, String tenSach, String theLoai, String namXB, String tenNXB, String tinhTrangSach) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.theLoai = theLoai;
		this.namXB = namXB;
		this.tenNXB = tenNXB;
		this.tinhTrangSach = tinhTrangSach;
	}

	public Sach() {
		super();
	}
	
}
