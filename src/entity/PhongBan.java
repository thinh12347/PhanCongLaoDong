package entity;

public class PhongBan {

	private String Mapb;
	private String Tenpb;
	public PhongBan() {
		super();
	}
	public PhongBan(String mapb) {
		super();
		Mapb = mapb;
	}
	public PhongBan(String mapb, String tenpb) {
		super();
		Mapb = mapb;
		Tenpb = tenpb;
	}
	public String getMapb() {
		return Mapb;
	}
	public void setMapb(String mapb) {
		Mapb = mapb;
	}
	public String getTenpb() {
		return Tenpb;
	}
	public void setTenpb(String tenpb) {
		Tenpb = tenpb;
	}
	@Override
	public String toString() {
		return "Phongban [Mapb=" + Mapb + ", Tenpb=" + Tenpb + "]";
	}
}
