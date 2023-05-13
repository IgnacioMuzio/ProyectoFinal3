package Enums;

public enum TipoCat {
	Infantiles(2400),
	Mayores(2800);
	
	private int precio;

	private TipoCat(int precio) {
		this.precio = precio;
	}
	
	public int getPrecio () {
		return precio;
	}
	
	
	
}
