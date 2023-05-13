package Clases;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Excepciones.MontoInvalido;

public class Sistema {
	private MapaGenerico<Socio> mapaSocio;
	private MapaGenerico<Indumentaria> mapaIndumentaria;
	public static int cantSocios;
	public static int cantInd;
	private Caja caja;
	public static int cantCuotas;
	
	public Sistema() {
		super();
		mapaSocio = new MapaGenerico<>();
		mapaIndumentaria = new MapaGenerico<>();
		this.caja=new Caja(0);
	}
	
	public void setCants(int soc,int ind,int cuot) {
		cantCuotas = cuot;
		cantInd = ind;
		cantSocios = soc;
	}
	public void setCaja (Caja cajita) {
		caja=cajita;
	}
	public Iterator<Entry<String, Socio>> devolverIt (){
		return mapaSocio.devolveriterator();
	}
	
	public Iterator<Entry<String, Indumentaria>> devolverIt2 (){
		return mapaIndumentaria.devolveriterator();
	}
	@Override
	public String toString() {
		return "Sistema [mapaSocio=" + mapaSocio + ", mapaIndumentaria=" + mapaIndumentaria + ", caja=" + caja + "]";
	}
	
	public String listarSocios () {
		return mapaSocio.listar();
	}
	
	public String listarIndumentaria () {
		return mapaIndumentaria.listar();
	}
	
	public void agregarSocio (Socio soc) {
		mapaSocio.agregar(soc);
		
	}
	
	public void agregarind (Indumentaria ind) {
		mapaIndumentaria.agregar(ind);
		(cantInd)++;
	}
	
	public boolean existe (String clave) {
		return mapaSocio.existe(clave);
	}
	
	public Socio buscarSocio (int dni) {
		String dniS = ""+dni;
		return mapaSocio.buscar(dniS);
	}
	
	public int sizeInd () {
		return mapaIndumentaria.contar();
	}

	
	public Socio buscarSocio (String nombre) {
		return mapaSocio.buscarNombre(nombre);
	}
	
	public Indumentaria buscarind (int codigo) {
		String codigoS = ""+codigo;
		return mapaIndumentaria.buscar(codigoS);
	}
	
	public Indumentaria buscarInd (String nombre) {
		return mapaIndumentaria.buscarNombre(nombre);
	}
	
	public void balancearCaja() {
		try {
			caja.verificarMontoEnCaja();
			caja.balancearCaja();
			
		} catch (MontoInvalido e) {
			e.printStackTrace();
		}
	}
	
	public boolean egresarDinero (int monto) {
		boolean flag=false;
		try {
			caja.verificarMonto(monto);
			caja.egresarDinero(monto);
			flag=true;
		} catch (MontoInvalido e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return flag;
	}
	
	public void quitarDinero (int monto) {
		caja.restarDinero(monto);
		if(caja.getDinero()<0) {
			caja.balancearCaja();
		}
	}
	
	public void agregarMovACaja (Movimiento cob) {
		caja.agregarMovimiento(cob);
	}
	
	public String mostrarCaja () {
		return caja.toString();
	}
	public boolean venderPrenda (String clave, int cant) throws MontoInvalido{
		Indumentaria aux= mapaIndumentaria.buscar(clave);
		boolean flag=false;
		if(aux.getStock()<cant) {
			throw new MontoInvalido("Ingrese una cantidad menor a la del stock disponible: "+aux.getStock());
		}
		else {
			if(aux.getStock()>=cant) {
				aux.vender(cant);
				flag=true;
			}
		}
		return flag;
	}

	public static int getCantSocios() {
		return cantSocios;
	}

	public static void setCantSocios(int cantSocios) {
		Sistema.cantSocios = cantSocios;
	}

	public static int getCantInd() {
		return cantInd;
	}

	public static void setCantInd(int cantInd) {
		Sistema.cantInd = cantInd;
	}

	public static int getCantCuotas() {
		return cantCuotas;
	}

	public static void setCantCuotas(int cantCuotas) {
		Sistema.cantCuotas = cantCuotas;
	}
	
	public JSONObject toJSON() {
		JSONObject nuevo = new JSONObject();
		JSONArray socs = new JSONArray();
		JSONArray ind = new JSONArray();
		Socio aux=null;
		Indumentaria aux1=null;
		Iterator<Entry<String, Socio>> iterator = mapaSocio.devolveriterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Socio> entrada = iterator.next();
			aux=entrada.getValue();
			socs.put(aux.toJSON());
		}
		
		Iterator<Entry<String, Indumentaria>> iterator2 = mapaIndumentaria.devolveriterator();
		while(iterator2.hasNext()) {
			Map.Entry<String, Indumentaria> entrada2 = iterator2.next();
			aux1=entrada2.getValue();
			ind.put(aux1.toJSON());
		}
		
		try {
			nuevo.put("socios", socs);
			nuevo.put("indumentaria", ind);
			nuevo.put("caja", caja.toJSON());
			nuevo.put("cantidadSoc",getCantSocios());
			nuevo.put("cantidadInd",getCantInd());
			nuevo.put("cantidadCuot", getCantCuotas());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nuevo;
		
	}
	
	
	
	
	
	
	
}
