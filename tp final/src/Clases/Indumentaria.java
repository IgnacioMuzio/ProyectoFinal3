package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import Interfaces.I_GetClave;

public class Indumentaria implements I_GetClave<Indumentaria> {
	private String nombre;
	private String codigoSerie;
	private String talle;
	private int stock;
	private int precio;

	public Indumentaria(String nombre, String talle, int stock, int cant, int precio) {
		super();
		this.nombre = nombre;
		codigoSerie = "" + cant;
		this.talle = talle;
		this.stock = stock;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigoSerie;
	}

	@Override
	public String getClave() {
		return getCodigo();
	}

	public String getNombre() {
		return nombre;
	}

	public String getTalle() {
		return talle;
	}

	public int getStock() {
		return stock;
	}

	public int getPrecio() {
		return precio;
	}

	@Override
	public String getNombreComp() {
		return nombre;
	}

	public void agregarStock(int agregado) {
		stock += agregado;
	}

	public void vender(int cant) {
		stock--;
	}

	@Override
	public String toString() {
		return "Indumentaria [nombre=" + nombre + ", codigoSerie=" + codigoSerie + ", talle=" + talle + ", stock="
				+ stock + ", precio=" + precio + "]";
	}
	
	public void setCodigoSerie(String codigoSerie) {
		this.codigoSerie = codigoSerie;
	}

	public JSONObject toJSON() {
		JSONObject nuevo = new JSONObject();
		try {
			nuevo.put("nombre", getNombre());
			nuevo.put("codigoSerie", getCodigo());
			nuevo.put("talle", getTalle());
			nuevo.put("stock", getStock());
			nuevo.put("precio", getPrecio());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nuevo;

	}

}
