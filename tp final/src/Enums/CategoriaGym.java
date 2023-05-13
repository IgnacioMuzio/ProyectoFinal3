package Enums;

public enum CategoriaGym {
	D3(TipoCat.Infantiles.getPrecio()),
	LIBRE(TipoCat.Mayores.getPrecio());
	
	private int precio;
	
	private CategoriaGym(int precio) {
		this.precio=precio;
	}
	
	public int getPrecio () {
		return precio;
	}
}
