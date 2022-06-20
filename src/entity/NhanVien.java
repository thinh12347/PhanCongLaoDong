package entity;

public class NhanVien {
	private String Manv;
	private String Tennv;
	private String Ngaysinh;
	private boolean Phai;
	private String Diachi;
	private PhongBan Pb;
	private String Chucvu;
	private String Pass;
	
	public NhanVien() {
		super();
	}
	public NhanVien(String manv) {
		super();
		Manv = manv;
	}
	public NhanVien(String manv, String tennv, String ngaysinh, boolean phai, String diachi, PhongBan pb, String chucvu,
			String pass) {
		super();
		Manv = manv;
		Tennv = tennv;
		Ngaysinh = ngaysinh;
		Phai = phai;
		Diachi = diachi;
		Pb = pb;
		Chucvu = chucvu;
		Pass = pass;
	}
	public NhanVien(NhanVien detail){
        this.Manv=detail.Manv;
        this.Tennv=detail.Tennv;
        this.Ngaysinh=detail.Ngaysinh;
        this.Phai=detail.Phai;
        this.Diachi=detail.Diachi;
        this.Pb=detail.Pb;
        this.Chucvu=detail.Chucvu;
        this.Pass=detail.Pass;
 }
	public NhanVien(String manv, String tennv, String ngaysinh, boolean phai, String diachi, PhongBan pb,
			String chucvu) {
		super();
		Manv = manv;
		Tennv = tennv;
		Ngaysinh = ngaysinh;
		Phai = phai;
		Diachi = diachi;
		Pb = pb;
		Chucvu = chucvu;
	}

	public String getManv() {
		return Manv;
	}
	public void setManv(String manv) {
		Manv = manv;
	}
	public String getTennv() {
		return Tennv;
	}
	public void setTennv(String tennv) {
		Tennv = tennv;
	}
	public String getNgaysinh() {
		return Ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		Ngaysinh = ngaysinh;
	}
	public boolean isPhai() {
		return Phai;
	}
	public void setPhai(boolean phai) {
		Phai = phai;
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public PhongBan getPb() {
		return Pb;
	}
	public void setPb(PhongBan pb) {
		Pb = pb;
	}
	public String getChucvu() {
		return Chucvu;
	}
	public void setChucvu(String chucvu) {
		Chucvu = chucvu;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	@Override
	public String toString() {
		return "Nhanvien [Manv=" + Manv + ", Tennv=" + Tennv + ", Ngaysinh=" + Ngaysinh + ", Phai=" + Phai + ", Diachi="
				+ Diachi + ", Pb=" + Pb + ", Chucvu=" + Chucvu + ", Pass=" + Pass + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Manv == null) ? 0 : Manv.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		NhanVien other = (NhanVien) obj;
		if (Manv == null) {
			if (other.Manv != null) {
				return false;
			}
		} else if (!Manv.equals(other.Manv)) {
			return false;
		}
		return true;
	}
}
