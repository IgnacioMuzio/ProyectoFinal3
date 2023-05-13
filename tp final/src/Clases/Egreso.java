package Clases;

import org.json.JSONException;
import org.json.JSONObject;

public class Egreso extends Movimiento{
	private int salida;

	public Egreso(int salida) {
		super();
		this.salida = salida;
	}

	public int getSalida() {
		return salida;
	}

	@Override
	public String toString() {
		return super.toString()+"Egreso [salida=" + salida + "]";
	}

	@Override
	public String getClave() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombreComp() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public JSONObject toJSON () {
		JSONObject nuevo = new JSONObject();
		try {
			nuevo.put("salida", getSalida());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nuevo;
	}
	
	
	
}
