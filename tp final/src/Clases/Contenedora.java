package Clases;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Enums.CategoriaGym;
import Enums.CategoriasRugby;
import Enums.Meses;
import Enums.MetodoPago;
import Excepciones.FechaInvalida;
import Excepciones.MontoInvalido;
import Json.JsonUtiles;

public class Contenedora {
	private Sistema sistema;
	private CategoriasRugby[] catRug = CategoriasRugby.values();
	private CategoriaGym[] catGym = CategoriaGym.values();
	private Meses[] meses = Meses.values();
	private MetodoPago[] metodos = MetodoPago.values();

	public Contenedora(Sistema sistema) {
		super();
		this.sistema = sistema;
	}

	public Contenedora() {
		super();
		sistema = new Sistema();
	}

	public void cargarSocio(Socio soc) {
		sistema.agregarSocio(soc);
	}
	
	public void cargarInd (Indumentaria ind) {
		sistema.agregarind(ind);
		
	}
	
	public String mostrarInd() {
		return sistema.listarIndumentaria();
	}
	public void agregarSocioNuevo() {
		boolean flag = false;
		int op = 0;
		String nombre = Consola.leer("Ingrese el nombre del socio");
		String apellido = Consola.leer("Ingrese el apellido del socio");
		String dni = "";
		int aux;
		do {
			try {
				dni = Consola.leer("Ingrese el dni del socio");
				aux = Integer.parseInt(dni);
				flag = true;
			} catch (NumberFormatException ex) {
				Consola.escribir("Ingrese solo numeros");
			}
		} while (flag == false);
		if (sistema.existe(dni)) {
			do {
				try {
					Consola.escribir("Ya existe un socio con ese dni...");
					op = Integer.parseInt(Consola.leer("Desea sobreescribirlo? 0.NO 1.SI"));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} while (op > 1 || op < 0);
		}
		if (op == 1 || !sistema.existe(dni)) {
			int edad = 0;
			do {
				try {
					edad = Integer.parseInt(Consola.leer("Ingrese la edad del socio"));
					flag = true;
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} while (flag == false);

			int dia = 0;
			int mes = 0;
			int año = 0;
			flag = false;

			do {
				try {
					dia = Integer.parseInt(Consola.leer("Ingrese el dia de nacimiento del socio (sin 0 al principio)"));
					mes = Integer.parseInt(Consola.leer("Ingrese el mes de nacimiento del socio (sin 0 al principio)"));
					año = Integer.parseInt(Consola.leer("Ingrese el año de nacimiento del socio..."));
					verificarFecha(dia, mes, año);
					flag = true;
				} catch (FechaInvalida ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} while (flag == false);

			String fecha = dia + "/" + mes + "/" + año;

			String direccion = Consola.leer("Ingrese la direccion del socio");

			int opcion = 0;
			do {
				try {
					Consola.escribir("Elija el metodo de pago de la cuota mensual.");
					Consola.escribir("1.Efectivo");
					Consola.escribir("2.CBU");
					opcion = Integer.parseInt(Consola.leer("3.Tarjeta de credito"));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} while (opcion > 3 || opcion < 1);
			int opcion1 = 0;
			do {
				try {
					Consola.escribir("Elija el tipo de categoria del socio");
					Consola.escribir("1.Rugby");
					opcion1 = Integer.parseInt(Consola.leer("2.Gimnasio"));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} while (opcion1 > 2 || opcion1 < 1);
			switch (opcion1) {
			case 1:
				CategoriasRugby cat = elegirCatRugby();
				sistema.agregarSocio(
						new SocioRugby(nombre, apellido, dni, edad, fecha, direccion, Sistema.cantSocios,metodos[opcion - 1], cat));
				Sistema.cantSocios++;
				Consola.escribir("Socio agregado correctamente");
				break;
			case 2:
				CategoriaGym cate = elegirCatGym();
				sistema.agregarSocio(
						new SocioGym(nombre, apellido, dni, edad, fecha, direccion,Sistema.cantSocios, metodos[opcion - 1], cate));
				Sistema.cantSocios++;
				Consola.escribir("Socio agregado correctamente");
				break;
			}
		} else
			Consola.escribir("Socio no sobreescrito");
	}

	public String mostrarSocios() {
		return sistema.listarSocios();
	}

	private void verificarFecha(int dia, int mes, int año) throws FechaInvalida {
		if (mes > 12 || mes < 1) {
			throw new FechaInvalida("Ingrese un mes valido");
		}
		if (dia > meses[mes - 1].getTope()) {
			throw new FechaInvalida("Ingrese un dia valido");
		}
		if (año > 2022) {
			throw new FechaInvalida("Ingrese un año valido");
		}
	}

	private CategoriasRugby elegirCatRugby() {
		int opcion = 0;

		do {
			for (int i = 0; i < catRug.length; i++) {
				Consola.escribir(i + 1 + "." + catRug[i].name());
			}
			try {
				opcion = Integer.parseInt(Consola.leer("Ingrese la categoria del socio"));
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} while (opcion > 16 || opcion < 1);
		return catRug[opcion - 1];
	}

	private CategoriaGym elegirCatGym() {
		int opcion = 0;
		for (int i = 0; i < catGym.length; i++) {
			Consola.escribir(i + 1 + "." + catGym[i].name());
		}
		do {
			try {
				opcion = Integer.parseInt(Consola.leer("Ingrese la categoria del socio"));
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} while (opcion > 2 || opcion < 1);
		return catGym[opcion - 1];
	}

	public void cargarCobroManual() {
		String buscado = Consola.leer("Ingrese el dni o nombre de la persona que desea buscar");
		int dni;
		Socio aCob;
		try {
			dni = Integer.parseInt(buscado);
			aCob = sistema.buscarSocio(dni);
			if (aCob != null) {
				if (aCob.agregarCobro(generarCobroMan(aCob))) {
					Consola.escribir("Cuota generada correctamente");
				} else
					Consola.escribir("El socio ya contaba con una cuota por este mes y concepto. ");
			} else
				Consola.escribir("Socio no encontrado");
		} catch (NumberFormatException ex) {
			aCob = sistema.buscarSocio(buscado);
			if (aCob != null) {
				if (aCob.agregarCobro(generarCobroMan(aCob))) {
					Consola.escribir("Cuota generada correctamente");
					sistema.agregarSocio(aCob);
				} else
					Consola.escribir("El socio ya contaba con una cuota por este mes y concepto. ");
			}
		} catch (Exception ex) {
			Consola.escribir(ex.getMessage());
		}
	}

	public Cobro generarCobroMan(Socio aCob) {
		Cobro cobro;
		Cuota cuotita;
		int mes = 0;
		int año = 0;
		boolean flag = false;

		do {
			try {
				mes = Integer
						.parseInt(Consola.leer("Ingrese el mes de la cuota que desea generar (sin 0 al principio)"));
				año = Integer.parseInt(Consola.leer("Ingrese el año de la cuota que deseea generar"));
				verificarFecha(1, mes, 2022);
				flag = true;
			} catch (FechaInvalida ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} while (flag == false);
		cuotita = new Cuota(mes, año, aCob.getPrecioCate(),Sistema.cantCuotas ,aCob.getCate());
		Sistema.cantCuotas++;
		cobro = new Cobro(cuotita, aCob.getMetodo());
		Consola.escribir(cobro.toString());
		sistema.agregarSocio(aCob);
		return cobro;
	}

	private Cobro generarCobroAut(Socio aCob, int mes, int año) {
		Cobro cobro;
		Cuota cuotita;
		cuotita = new Cuota(mes, año, aCob.getPrecioCate(),Sistema.cantCuotas ,aCob.getCate());
		cobro = new Cobro(cuotita, aCob.getMetodo());
		return cobro;
	}

	public Socio mostrarSocio() {
		String buscado = Consola.leer("Ingrese el dni o nombre de la persona que desea buscar");
		int dni;
		Socio aCob=null;
		try {
			dni = Integer.parseInt(buscado);
			aCob = sistema.buscarSocio(dni);
		} catch (NumberFormatException ex) {
			aCob = sistema.buscarSocio(buscado);
		} catch (Exception ex) {
			Consola.escribir(ex.getMessage());
		}
		return aCob;
	}
	
	public void darDeBaja (Socio soc) {
		soc.setEstado(false);
		sistema.agregarSocio(soc);
		Consola.escribir("Socio dado de baja exitosamente");
	}
	
	public void darDeAlta (Socio soc) {
		soc.setEstado(true);
		sistema.agregarSocio(soc);
		Consola.escribir("Socio dado de alta exitosamente");
	}
	
	public void cargarCuotaAuto() {
		int mes = 0;
		int año = 0;
		boolean flag = false;
		Socio aux;
		Cobro aux2;
		do {
			try {
				mes = Integer
						.parseInt(Consola.leer("Ingrese el mes de la cuota que desea generar (sin 0 al principio)"));
				año = Integer.parseInt(Consola.leer("Ingrese el año de la cuota que deseea generar"));
				verificarFecha(1, mes, 2022);
				flag = true;
			} catch (FechaInvalida ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} while (flag == false);
		Iterator<Entry<String, Socio>> iterator = sistema.devolverIt();
		while (iterator.hasNext()) {
			Map.Entry<String, Socio> entrada = iterator.next();
			aux = entrada.getValue();
			aux2 = generarCobroAut(aux, mes, año);
			aux.agregarCobro(aux2);
			Sistema.cantCuotas++;
			sistema.agregarSocio(aux);
		}

	}

	public void cobrarCuota(Socio aCob) {
		Consola.escribir(aCob.stringConCuot());
		int numero = 0;
		boolean flag = false;
		Cobro aux;
		if (aCob.getMetodo().equals("EFECTIVO")) {
			do {
				try {
					numero = Integer.parseInt(Consola.leer("Ingrese el numero de recibo de la cuota que desea pagar."));
					flag = true;
				} catch (Exception ex) {
					Consola.escribir(ex.getMessage());
				}
			} while (flag == false);

			aux = (Cobro) aCob.listaCuota.buscar(numero);
			if (aux != null) {
				if (!aux.isPago()) {
					aux.cobrarCuota(aux.getSaldoRestante());
					Consola.escribir("Cuota cobrada correctamente");
					sistema.agregarMovACaja(aux);
					sistema.agregarSocio(aCob);

				} else
					Consola.escribir("Cuota ya paga.");
			} else
				Consola.escribir("Numero de recibo invalido");
		} else
			Consola.escribir("El socio cuenta con debito automatico...");
	}

	public void anularCuota(Socio aCob) {
		Consola.escribir(aCob.stringConCuot());
		int numero = 0;
		boolean flag = false;
		Cobro aux;
		do {
			try {
				numero = Integer.parseInt(Consola.leer("Ingrese el numero de recibo de la cuota que desea anular."));
				flag = true;
			} catch (Exception ex) {
				Consola.escribir(ex.getMessage());
			}
		} while (flag == false);

		aux = (Cobro) aCob.listaCuota.buscar(numero);
		if (aux != null) {
			if (aux.getEstado()) {
				aux.setEstado(false);
				if (aux.getMetodo().equals("EFECTIVO")) {
					sistema.quitarDinero(aux.getSaldoAcreedor());
				}
				sistema.agregarSocio(aCob);
			} else
				Consola.leer("La cuota ya esta anulada");
		}
	}

	public String listarCategoria() {
		int opcion1 = 0;
		String contenido = "";
		do {
			try {
				Consola.escribir("Elija el tipo de categoria del socio");
				Consola.escribir("1.Rugby");
				opcion1 = Integer.parseInt(Consola.leer("2.Gimnasio"));
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} while (opcion1 > 2 || opcion1 < 1);
		switch (opcion1) {
		case 1:
			CategoriasRugby cat = elegirCatRugby();
			contenido = buscarCat(cat.name());
			break;
		case 2:
			CategoriaGym cate = elegirCatGym();
			contenido = buscarCat(cate.name());
			break;
		}
		return contenido;
	}

	private String buscarCat(String cat) {
		Iterator<Entry<String, Socio>> iterator = sistema.devolverIt();
		Socio aux;
		String cont = "";
		while (iterator.hasNext()) {
			Map.Entry<String, Socio> entrada = iterator.next();
			aux = entrada.getValue();
			if (aux.getCate().equals(cat)) {
				cont += aux.toString();
			}
		}
		return cont;

	}

	public void venderRopa() {
		String buscado = Consola.leer("Ingrese el dni o nombre de la persona que desea comprar la indumentaria");
		int dni;
		Socio aCob;
		boolean flag = true;

		try {
			dni = Integer.parseInt(buscado);
			aCob = sistema.buscarSocio(dni);
			if (aCob != null) {
				do {
					Consola.escribir(sistema.listarIndumentaria());
					flag = generarCobroRopa(aCob);
				} while (flag == true);
			} else
				Consola.escribir("Socio no encontrado");
		} catch (NumberFormatException ex) {
			aCob = sistema.buscarSocio(buscado);
			if (aCob != null) {
				do {
					Consola.escribir(sistema.listarIndumentaria());
					flag = generarCobroRopa(aCob);
				} while (flag == true);
			} else
				Consola.escribir("Socio no encontrado");
		}

	}

	private boolean generarCobroRopa(Socio aCob) {
		String opcion = "";
		int cant = 0;
		Cuota cuota;
		Cobro cobro;
		int mes;
		int año;
		Indumentaria aux;
		boolean flag = false;
		int monto;
		opcion = (Consola.leer("Ingrese el codigo de la prenda que desea comprar (-1 para salir.) "));
		if (Integer.parseInt(opcion) != -1) {
			aux = sistema.buscarind(Integer.parseInt(opcion));
			if (aux != null) {
				cant = Integer.parseInt(Consola.leer("ingrese cuantas unidades quiere de la prenda"));
				try {
					if (sistema.venderPrenda(opcion, cant)) {
						do {
							mes = Integer.parseInt(
									Consola.leer("Ingrese el mes de la cuota que desea generar (sin 0 al principio)"));
							año = Integer.parseInt(Consola.leer("Ingrese el año de la cuota que deseea generar"));
							verificarFecha(1, mes, 2022);
							flag = true;
						} while (flag == false);
						monto = aux.getPrecio() * cant;
						cuota = new Cuota(mes, año, monto,Sistema.cantCuotas, "Indumentaria");
						Sistema.cantCuotas++;
						cobro = new Cobro(cuota, aCob.getMetodo());
						aCob.agregarCobro(cobro);
						aux.vender(cant);
						Consola.escribir("Compra realizada");
						sistema.agregarSocio(aCob);
						sistema.agregarind(aux);
					}
				} catch (NumberFormatException | MontoInvalido | FechaInvalida e) {
					e.printStackTrace();
				}
			} else
				Consola.escribir("Codigo incorrecto");
		} else
			flag = false;
		return flag;
	}

	public void retirarDinero () {
		boolean flag=false;
		int monto=0;
		do {
			try {
				do{
					monto=Integer.parseInt(Consola.leer("Ingrese el monto a retirar"));
					flag=sistema.egresarDinero(monto);
				}while(flag==false);
			}catch (Exception ex) {
				Consola.leer(ex.getMessage());
			}
		}while (flag==false);
	}

	public void reStockerPrenda() {
		Consola.escribir(sistema.listarIndumentaria());
		int opcion=0;
		Indumentaria aux;
		int cant=0;
		try {
			do {
			opcion=Integer.parseInt(Consola.leer("Ingrese el codigo de la prende que se quiere restockear"));
			aux = sistema.buscarind(opcion);
			if(aux!=null) {
				cant=Integer.parseInt(Consola.leer("Ingrese la cantidad que desea agregar"));
				aux.agregarStock(cant);
				sistema.agregarind(aux);
			}
			}while(opcion>sistema.sizeInd() || opcion<0);
		} catch(Exception es) {
			Consola.escribir(es.getMessage());
		}
	}

	public void guardarSistema () {
		JsonUtiles.grabarSistema(sistema.toJSON());
	}
	
	public String listarRopa() {
		return sistema.listarIndumentaria();
	}
	
	public String mostrarCaja () {
		return sistema.mostrarCaja();
	}
	
	public Sistema leerSist() {
		JSONObject arch=null;
		try {
			arch = new JSONObject (JsonUtiles.leerSistema());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return devolverSistema(arch);
	}
	
	public void setSistema(Sistema sistema) {
		this.sistema=sistema;
	}
	public Sistema devolverSistema (JSONObject temp) {
		JSONArray socs;
		JSONArray ind;
		int cantSoc;
		int cantCuot;
		int cantInd;
		try {
			socs= temp.getJSONArray("socios");
			ind = temp.getJSONArray("indumentaria");
			Sistema sistema = new Sistema();
			for(int i=0;i<socs.length();i++) {
				sistema.agregarSocio(volverSocio(socs.getJSONObject(i)));
				
			}
			for(int i=0;i<ind.length();i++) {
				sistema.agregarind(volverIndumentaria(ind.getJSONObject(i)));
			}
			sistema.setCaja(volverCaja(temp.getJSONObject("caja")));
			cantSoc=temp.getInt("cantidadSoc");
			cantInd=temp.getInt("cantidadInd");
			cantCuot=temp.getInt("cantidadCuot");
			sistema.setCants(cantSoc, cantInd, cantCuot);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sistema;
	}
	public Socio volverSocio (JSONObject temp) {
		String nombre;
		String apellido;
		String dni;
		int edad;
		String fechaNac;
		String direccion;
		int num;
		String metodo;
		boolean estado;
		String categoria;
		Socio aux=null;
		try {
			nombre=temp.getString("nombre");
			apellido=temp.getString("apellido");
			dni = temp.getString("dni");
			edad = temp.getInt("edad");
			fechaNac=temp.getString("fechaNac");
			direccion=temp.getString("direccion");
			num=temp.getInt("numLegajo");
			metodo=temp.getString("metodo");
			estado=temp.getBoolean("estado");
			categoria=temp.getString("categoria");
			JSONArray lista = temp.getJSONArray("listaCuotas");
			if(tipoSocGym(categoria)>=0) {
				aux = new SocioGym(nombre, apellido, dni, edad, fechaNac, direccion, num, verMetodo(metodo), catGym[tipoSocGym(categoria)]);
			}
			else {
				aux = new SocioRugby(nombre, apellido, dni, edad, fechaNac, direccion, num, verMetodo(metodo), catRug[tipoSocRug(categoria)]);
			}
			for(int i=0;i<lista.length();i++) {
				aux.agregarCobro(volverCobro(lista.getJSONObject(i)));
			}
			aux.setEstado(estado);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
		
	}
	public MetodoPago verMetodo(String metodo) {
		int pos=-1;
		int i=0;
		boolean flag=false;
		while(i<metodos.length && flag == false) {
			if(metodo.equals(metodos[i].name())) {
				pos=i;
				flag=true;
			}
			else i++;
		}
		return metodos[pos];
	}
	public int tipoSocGym(String categoria) {
		int pos=-1;
		int i=0;
		boolean flag=false;
		while(i<catGym.length && flag == false) {
			if(categoria.equals(catGym[i].name())) {
				pos=i;
				flag=true;
			}
			else i++;
		}
		return pos;
	}
	
	public Caja volverCaja (JSONObject temp) {
		Caja aux=new Caja(0);
		JSONArray listaMovs = new JSONArray();
		JSONObject aux2;
		int salida;
		int dinero;
		Movimiento mov;
		try {
			listaMovs=temp.getJSONArray("listaMovimientos");
			for(int i=0;i<listaMovs.length();i++) {
				aux2=listaMovs.getJSONObject(i);
				if(aux2.has("salida")) {
					salida=aux2.getInt("salida");
					mov= new Egreso(salida);
					aux.agregarMovimiento(mov);
				}
				else {
					mov=volverCobro(aux2);
					aux.agregarMovimiento(mov);
				}
			}
			dinero=temp.getInt("dinero");
			aux.setDinero(dinero);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}
	public int tipoSocRug(String categoria) {
		int pos=-1;
		int i=0;
		boolean flag=false;
		while(i<catRug.length && flag == false) {
			if(categoria.equals(catRug[i].name())) {
				pos=i;
				flag=true;
			}
			else i++;
		}
		return pos;
	}
	
	public Cobro volverCobro (JSONObject temp) {
		int mes;
		int año;
		int monto;
		int numeroCuota;
		String categoria;
		int saldoAcreedor;
		int saldoRestante;
		String metodo;
		boolean estado;
		Cobro cobro=null;
		JSONObject temp2;
		try {
			temp2 = temp.getJSONObject("cuota");
			mes=temp2.getInt("mes");
			año=temp2.getInt("año");
			monto=temp2.getInt("monto");
			numeroCuota=temp2.getInt("numeroCuota");
			categoria=temp2.getString("categoria");
			Cuota cuotita = new Cuota(mes, año, monto, numeroCuota, categoria);
			saldoAcreedor=temp.getInt("saldoAcreedor");
			saldoRestante=temp.getInt("saldoRestante");
			metodo=temp.getString("metodo");
			estado=temp.getBoolean("estado");
			cobro = new Cobro(cuotita,metodo);
			cobro.setEstado(estado);
			cobro.setSaldoAcreedor(saldoAcreedor);
			cobro.setSaldoRestante(saldoRestante);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cobro;
		
		
		
	}

	public Indumentaria volverIndumentaria (JSONObject temp) {
		Indumentaria aux=null;
		try {
			String nombre=temp.getString("nombre");
			String codigoSerie=temp.getString("codigoSerie");
			String talle=temp.getString("talle");
			int stock=temp.getInt("stock");
			int precio=temp.getInt("precio");
			aux=new Indumentaria(nombre, talle, stock, Integer.parseInt(codigoSerie), precio);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}
}
