package entity;

public class ChiTietDuAn {
	private String Mactdu;
	private DuAn Mada;
	private String Diachi;
	private String NCP;
	private String NKC;
	private String NHT;
	private NhanVien Manv;
	private int ngaycong;

	public ChiTietDuAn() {
		super();
	}

	public ChiTietDuAn(String mactdu) {
		super();
		Mactdu = mactdu;
	}

	public ChiTietDuAn(String mactdu, DuAn mada, String diachi, String nCP, String nKC, String nHT, NhanVien manv,
			int ngaycong) {
		super();
		Mactdu = mactdu;
		Mada = mada;
		Diachi = diachi;
		NCP = nCP;
		NKC = nKC;
		NHT = nHT;
		Manv = manv;
		this.ngaycong = ngaycong;
	}

	public String getMactdu() {
		return Mactdu;
	}

	public void setMactdu(String mactdu) {
		Mactdu = mactdu;
	}

	public DuAn getMada() {
		return Mada;
	}

	public void setMada(DuAn mada) {
		Mada = mada;
	}

	public String getDiachi() {
		return Diachi;
	}

	public void setDiachi(String diachi) {
		Diachi = diachi;
	}

	public String getNCP() {
		return NCP;
	}

	public void setNCP(String nCP) {
		NCP = nCP;
	}

	public String getNKC() {
		return NKC;
	}

	public void setNKC(String nKC) {
		NKC = nKC;
	}

	public String getNHT() {
		return NHT;
	}

	public void setNHT(String nHT) {
		NHT = nHT;
	}

	public NhanVien getManv() {
		return Manv;
	}

	public void setManv(NhanVien manv) {
		Manv = manv;
	}

	public int getNgaycong() {
		return ngaycong;
	}

	public void setNgaycong(int ngaycong) {
		this.ngaycong = ngaycong;
	}

	@Override
	public String toString() {
		return "ChiTietDuAn [Mactdu=" + Mactdu + ", Mada=" + Mada + ", Diachi=" + Diachi + ", NCP=" + NCP + ", NKC="
				+ NKC + ", NHT=" + NHT + ", Manv=" + Manv + ", ngaycong=" + ngaycong + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Mactdu == null) ? 0 : Mactdu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDuAn other = (ChiTietDuAn) obj;
		if (Mactdu == null) {
			if (other.Mactdu != null)
				return false;
		} else if (!Mactdu.equals(other.Mactdu))
			return false;
		return true;
	}

}
