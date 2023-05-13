package Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Enums.CategoriaGym;
import Enums.MetodoPago;

public class SocioGym extends Socio{
	private CategoriaGym cate;

	public SocioGym(String nombre, String apellido, String dni, int edad, String fechaNac, String direccion,int num,
			MetodoPago metodo, CategoriaGym cate) {
		super(nombre, apellido, dni, edad, fechaNac, direccion,num, metodo);
		this.cate = cate;
	}

	public String getCate() {
		return cate.name();
	}

	@Override
	public String toString() {
		return "SocioGym"+ super.toString() +"cate=" + cate + "]";
	}
	
	public String stringConCuot() {
		return "SocioGym"+ super.stringConCuot() +"cate=" + cate + "]";
	}
	
	public int getPrecioCate() {
		return cate.getPrecio();
	}
	
	public JSONObject toJSON () {
		JSONObject nuevo = new JSONObject();
		JSONArray movs = new JSONArray();
		try {
			nuevo.put("nombre", getNombre());
			nuevo.put("apellido", getApellido());
			nuevo.put("dni",getDni());
			nuevo.put("edad",getEdad());
			nuevo.put("fechaNac",getFechaNac());
			nuevo.put("direccion",getDireccion());
			nuevo.put("numLegajo", getNumLegajo());
			for(int i=0;i<listaCuota.contar();i++) {
				movs.put(listaCuota.getValor(i).toJSON());
			}
			nuevo.put("listaCuotas", movs);
			nuevo.put("metodo", getMetodo());
			nuevo.put("estado", getEstado());
			nuevo.put("categoria", getCate());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nuevo;
		
	}
	
	
}
