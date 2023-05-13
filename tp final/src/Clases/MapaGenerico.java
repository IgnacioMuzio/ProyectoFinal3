package Clases;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Interfaces.I_ABM;
import Interfaces.I_GetClave;


public class MapaGenerico <T extends I_GetClave<T>> implements I_ABM<T>{
	private HashMap<String,T> mapa;

	public MapaGenerico() {
		super();
		this.mapa = new HashMap<>();
	}

	@Override
	public void agregar(T p) {
		mapa.put(p.getClave(), p);
	}

	@Override
	public boolean eliminar(T p) {
		return mapa.remove(p.getClave(), p);
	}

	@Override
	public String listar() {
		String todo ="";
		Iterator<Entry<String, T>> iterator = mapa.entrySet().iterator(); 
		while(iterator.hasNext()) {
			Map.Entry<String, T> entrada = iterator.next();
			todo += entrada.getValue().toString();
		}
		return todo;
	}
	
	public Iterator<Entry<String, T>> devolveriterator () {
		return mapa.entrySet().iterator();
	}
	
	public T buscar(String key) {
		return mapa.get(key);
	}
	
	public T buscarNombre(String nombre) {
		T aux = null;
		Iterator<Entry<String, T>> iterator = mapa.entrySet().iterator();
		boolean flag = false;
		while(iterator.hasNext() && flag == false) {
			Map.Entry<String, T> entrada = iterator.next();
			if(entrada.getValue().getNombreComp().trim().equalsIgnoreCase(nombre)) {
				aux=entrada.getValue();
				flag=true;
			}
		}
		return aux;
	}
	
	public boolean existe (String k) {
		return mapa.containsKey(k);
	}
	@Override
	public int contar() {
		return mapa.size();
	}
	
	

	
}
