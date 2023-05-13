package Excepciones;

public class Incorrecto extends Exception {
	public static int cantErrores=0;
	
	public Incorrecto(String mensaje) {
		super(mensaje);
	}
	
	public void masError() {
		cantErrores++;
	}
	
	public boolean maxError() {
		boolean flag = true;
		if(cantErrores<4) {
			masError();
		}
		else {
			flag = false;
			cantErrores=0;
		}
		return flag;
		
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
