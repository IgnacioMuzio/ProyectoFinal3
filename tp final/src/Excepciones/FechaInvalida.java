package Excepciones;

public class FechaInvalida extends Exception {
	public FechaInvalida(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
