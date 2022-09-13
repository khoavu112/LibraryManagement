package entities;

public class DocGia_PM {
	String maDG,tenDG,cmnd;

	public DocGia_PM(String maDG, String tenDG, String cmnd) {
		super();
		this.maDG = maDG;
		this.tenDG = tenDG;
		this.cmnd = cmnd;
	}

	public DocGia_PM() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	
}
