package Clases;

import org.json.JSONObject;

import Enums.MetodoPago;
import Interfaces.I_GetClave;

public abstract class Socio extends Persona implements I_GetClave<Socio>{
	private int numLegajo;
	ListaGenerica <Movimiento> listaCuota;
	private MetodoPago metodo;
	private boolean estado;
	
	

	public Socio(String nombre, String apellido, String dni, int edad, String fechaNac, String direccion, int num,
			MetodoPago metodo) {
		super(nombre, apellido, dni, edad, fechaNac, direccion);
		listaCuota= new ListaGenerica<>();
		numLegajo = num;
		this.metodo = metodo;
		estado=true;
		
	}
	
	public void setEstado(boolean est) {
		estado = est;
	}
	
	public boolean getEstado () {
		return estado;
	}
	
	public int getNumLegajo() {
		return numLegajo;
	}
	public String getNombreComp() {
		return getNombre() + " " + getApellido();
	}
	public String getMetodo() {
		return metodo.name();
	}

	@Override
	public String getClave() {
		return getDni();
	}

	@Override
	public String toString() {
		return super.toString() +"numLegajo=" + numLegajo +", metodo=" + metodo +", estado=" + estado;
	}
	
	public String stringConCuot() {
		return super.toString() +"numLegajo=" + numLegajo + ", listaCuota=" + listaCuota.listar() + ", metodo=" + metodo + ", estado=" + estado;
	}
	
	public String getCate() {
		return null;
	}
	
	public int getPrecioCate() {
		return -1;
	}
	
	public boolean agregarCobro(Cobro cob) {
		boolean flag = true;
		int i=0;
		while(i<listaCuota.contar() && flag== true) {
			if(cob.equals(listaCuota.getValor(i))) {
				flag=false;
			}
			else i++;
		}
		if(flag==true) {
			listaCuota.agregar(cob);
		}
		return flag;
	}
	
	public JSONObject toJSON () {
		return null;
	}
}
