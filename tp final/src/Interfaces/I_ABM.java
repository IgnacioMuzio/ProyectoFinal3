package Interfaces;



public interface I_ABM <T>{
		
	void agregar (T p);
	boolean eliminar (T p);
	String listar ();
	int contar ();
}
