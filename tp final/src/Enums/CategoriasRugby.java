package Enums;

public enum CategoriasRugby {
	
	M4 (TipoCat.Infantiles.getPrecio()),
	M5 (TipoCat.Infantiles.getPrecio()),
	M6 (TipoCat.Infantiles.getPrecio()),
	M7 (TipoCat.Infantiles.getPrecio()),
	M8 (TipoCat.Infantiles.getPrecio()),
	M9 (TipoCat.Infantiles.getPrecio()),
	M10(TipoCat.Infantiles.getPrecio()),
	M11(TipoCat.Infantiles.getPrecio()),
	M12(TipoCat.Infantiles.getPrecio()),
	M13(TipoCat.Infantiles.getPrecio()),
	M14(TipoCat.Infantiles.getPrecio()),
	M15(TipoCat.Mayores.getPrecio()),
	M16(TipoCat.Mayores.getPrecio()),
	M17(TipoCat.Mayores.getPrecio()),
	M19(TipoCat.Mayores.getPrecio()),
	RPS(TipoCat.Mayores.getPrecio());
	
	private int precio;
	
	private CategoriasRugby (int precio) {
		this.precio=precio;
	}
	
	public int getPrecio() {
		return precio;
	}
}
