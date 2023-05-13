package Excepciones;

public class MontoInvalido extends Exception{

	public MontoInvalido(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
