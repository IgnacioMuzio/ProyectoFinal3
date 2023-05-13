package Clases;

import java.util.Date;

import org.json.JSONObject;

import Interfaces.I_GetClave;

public abstract class Movimiento implements I_GetClave<Movimiento>{

	
	public Movimiento() {
		super();
	}

	@Override
	public String toString() {
		return "Movimiento [";
	}

	public int getSaldoAcreedor() {
		return -1;
	}
	
	public String getMetodo() {
		return null;
	}
	
	public JSONObject toJSON ()
	{
		return null;
	}
}
