package entity;

public class DuAn {
	private String Mada;
	private String Tenda;
	public DuAn() {
		super();
	}
	public DuAn(String mada, String tenda) {
		super();
		Mada = mada;
		Tenda = tenda;
	}
	public DuAn(String mada) {
		super();
		Mada = mada;
	}
	
	public String getMada() {
		return Mada;
	}
	public void setMada(String mada) {
		Mada = mada;
	}
	public String getTenda() {
		return Tenda;
	}
	public void setTenda(String tenda) {
		Tenda = tenda;
	}
	@Override
	public String toString() {
		return "DuAn [Mada=" + Mada + ", Tenda=" + Tenda + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Mada == null) ? 0 : Mada.hashCode());
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
		DuAn other = (DuAn) obj;
		if (Mada == null) {
			if (other.Mada != null)
				return false;
		} else if (!Mada.equals(other.Mada))
			return false;
		return true;
	}
	
}
