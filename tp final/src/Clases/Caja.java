package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Excepciones.MontoInvalido;

public class Caja {
	private ListaGenerica<Movimiento> listamov;
	private int dinero;
	
	public Caja(int dinero) {
		super();
		listamov = new ListaGenerica<>();
		this.dinero = dinero;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public void agregarDinero (int monto) {
		dinero+=monto;
	}
	
	public void restarDinero (int monto) {
		dinero-=monto;
	}
	
	public void agregarMovimiento(Movimiento x) {
		listamov.agregar(x);
		agregarDinero(x.getSaldoAcreedor());
	}
	
	public void cargarMov(Movimiento x) {
		listamov.agregar(x);
	}
	

	public boolean egresarDinero (int monto) {
		boolean flag=false;
		try {
				verificarMonto(monto);
				flag = true;
				listamov.agregar(new Egreso(monto));
				restarDinero(monto);

		} catch (MontoInvalido e) {
			
			e.getMessage();
		}
		return flag;
	}
	
	public void verificarMonto (int monto) throws MontoInvalido{

		if(monto <=0 || monto>getDinero()) {

			throw new MontoInvalido("El monto ingresado es invalido, ingrese una cantidad menor a " + getDinero());
		}
		
	}
	
	public void verificarMontoEnCaja () throws MontoInvalido{
		if(getDinero()>=0) {
			throw new MontoInvalido("La caja ya esta balanceada");
		}
	}
	
	@Override
	public String toString() {
		return "Caja [listamov=" + listamov + ", dinero=" + dinero + "]";
	}

	public String mostrarLista() {
		return "Caja [listamov=" + listamov + "]";
	}
	
	public void balancearCaja() {
		dinero=0; //en caso que al anular un recibo sea efectivo se resta el dinero de la caja, si la caja se queda en negativo, con esto vuelve a normalizarse.
	}
	
	public JSONObject toJSON () {
		JSONObject nuevo= new JSONObject();
		JSONArray movs = new JSONArray();
		for(int i=0; i<listamov.contar();i++) {
			movs.put(listamov.getValor(i).toJSON());
		}
		try {
			nuevo.put("listaMovimientos", movs);
			nuevo.put("dinero", getDinero());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nuevo;
	}
	
}
