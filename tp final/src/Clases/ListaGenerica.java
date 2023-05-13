package Clases;

import java.util.ArrayList;

import Interfaces.I_ABM;
import Interfaces.I_GetClave;

public class ListaGenerica <T extends I_GetClave<T>> implements I_ABM<T>{
	
	ArrayList <T> lista;
	
	public ListaGenerica() {
		lista = new ArrayList<T>();
	}
	
	public T getValor(int i) {
		return lista.get(i);
	}

	@Override
	public void agregar(T p) {
		lista.add(p);
	}

	@Override
	public boolean eliminar(T p) {
		boolean flag = false;
		if(lista.remove(p)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public String listar() {
		return lista.toString();
	}
	
	public T buscar (int num) {
		T aux = null;
		String numS = ""+num;
		boolean flag = false;
		int i=0;
		while(i<lista.size() && flag == false) {
			if(lista.get(i).getClave().equals(numS)) {
				aux=lista.get(i);
				flag=true;
			}
			else i++;
		}
		return aux;
	}

	@Override
	public int contar() {
		return lista.size();
	}


	@Override
	public String toString() {
		int i=0;
		String contenido="";
		while(i<lista.size()) {
			contenido+=lista.get(i).toString();
			i++;
		}
		return contenido;
	}
	
}
