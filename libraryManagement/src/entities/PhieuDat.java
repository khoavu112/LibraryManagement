package entities;

public class PhieuDat {
	private String maPD, maNV, ngayDat;

	public String getMaPD() {
		return maPD;
	}

	public void setMaPD(String maPD) {
		this.maPD = maPD;
	}

	public String getTenNV() {
		return maNV;
	}

	public void setTenNV(String maNV) {
		this.maNV = maNV;
	}

	public String getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}

	@Override
	public String toString() {
		return "PhieuDat [maPD=" + maPD + ", maNV=" + maNV + ", ngayDat=" + ngayDat + ", getMaPD()=" + getMaPD()
				+ ", getTenNV()=" + getTenNV() + ", getNgayDat()=" + getNgayDat() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public PhieuDat(String maPD, String maNV, String ngayDat) {
		super();
		this.maPD = maPD;
		this.maNV = maNV;
		this.ngayDat = ngayDat;
	}

	
	
}
