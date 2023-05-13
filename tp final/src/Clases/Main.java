package Clases;

import java.util.Random;

import Enums.CategoriaGym;
import Enums.CategoriasRugby;
import Enums.MetodoPago;
import Excepciones.Incorrecto;

public class Main {

	public static void main(String[] args) {
		Contenedora cont = new Contenedora();
		CategoriaGym[] catGym = CategoriaGym.values();
		CategoriasRugby[] catRug = CategoriasRugby.values();
		MetodoPago[] metodos = MetodoPago.values();
		
		/*
		Random r = new Random();
		r.nextInt(3);
		String nombre;
		String apellido;
		String dni;
		int edad;
		String fechaNac;
		String direccion;
		int num;

		for (int i = 0; i < catGym.length; i++) {
			for (int j = 0; j < 2; j++) {
				nombre = "nombre" + j;
				apellido = "apellido" + j;
				dni = "" + Sistema.cantSocios;
				edad = 20;
				fechaNac = "15/6/2000";
				direccion = "Casa N" + j;
				num = Sistema.cantSocios;
				(Sistema.cantSocios)++;
				cont.cargarSocio(new SocioGym(nombre, apellido, dni, edad, fechaNac, direccion, num,
						metodos[r.nextInt(3)], catGym[i]));
			}
		}
		for (int i = 0; i < catRug.length; i++) {
			for (int j = 0; j < 3; j++) {
				nombre = "nombre" + j;
				apellido = "apellido" + j;
				dni = "dni" + Sistema.cantSocios;
				edad = 20;
				fechaNac = "15/6/2000";
				direccion = "Casa N" + j;
				num = Sistema.cantSocios;
				(Sistema.cantSocios)++;
				cont.cargarSocio(new SocioRugby(nombre, apellido, dni, edad, fechaNac, direccion, num,
						metodos[r.nextInt(3)], catRug[i]));
			}
		}

		cont.cargarInd(new Indumentaria("remera", "s", 10, Sistema.cantInd, 300));

		cont.cargarInd(new Indumentaria("remera", "m", 10, Sistema.cantInd, 400));

		cont.cargarInd(new Indumentaria("remera", "l", 10, Sistema.cantInd, 500));

		cont.cargarInd(new Indumentaria("pantalon", "s", 10, Sistema.cantInd, 150));

		cont.cargarInd(new Indumentaria("pantalon", "m", 10, Sistema.cantInd, 200));

		cont.cargarInd(new Indumentaria("pantalon", "l", 10, Sistema.cantInd, 250));

		cont.cargarInd(new Indumentaria("medias", "s", 10, Sistema.cantInd, 100));

		cont.cargarInd(new Indumentaria("medias", "m", 10, Sistema.cantInd, 120));

		cont.cargarInd(new Indumentaria("medias", "l", 10, Sistema.cantInd, 140));

		cont.cargarCuotaAuto();
		cont.cargarCuotaAuto();
		cont.cargarCuotaAuto();
		cont.guardarSistema();
		Consola.escribir("termino");*/
		if (iniciarSesion()) {

//			cont.setSistema(cont.leerSist()); no puede hacer que carque bien el json nunca
			menuGeneral(cont);

		}

	}

	public static void menuGeneral(Contenedora cont) {
		int opcion = -1;

		do {
			do {
				Consola.escribir("Menu Mandarina Rugby Club.");
				Consola.escribir("1.Menu socios");
				Consola.escribir("2.Menu caja");
				Consola.escribir("3.Menu indumentaria");
				Consola.escribir("0. Para guardar y salir...");
				try {
					opcion = Integer.parseInt(Consola.leer("Ingrese la opcion deseada."));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			} while (opcion > 3 || opcion < 0);

			switch (opcion) {
			case 1:
				menuSocios(cont);
				break;
			case 2:
				menuCaja(cont);
				break;
			case 3:
				menuInd(cont);
				break;
			case 0:
				cont.guardarSistema();
				Consola.escribir("Datos guardados con exito");
				break;
			}
		} while (opcion != 0);

	}

	public static void menuSocios(Contenedora cont) {
		int opcion = -1;
		Socio aux;
		do {
			do {
				Consola.escribir("Menu Mandarina Rugby Club.");
				Consola.escribir("1.Agregar socio.");
				Consola.escribir("2.Generar cuota a socio");
				Consola.escribir("3.Generar cuota mensual a todos los socios");
				Consola.escribir("4.Cobrar a socio.");
				Consola.escribir("5.Anular cobro a socio");
				Consola.escribir("6.Mostrar socio y estado contable");
				Consola.escribir("7.Dar de baja a un socio");
				Consola.escribir("8.Dar de alta a un socio");
				Consola.escribir("9.Listar por categoria");
				Consola.escribir("10.Listar todos los socios");
				Consola.escribir("0. Para volver al menu principal");
				try {
					opcion = Integer.parseInt(Consola.leer("Ingrese la opcion deseada."));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			} while (opcion > 10 || opcion < 0);

			switch (opcion) {
			case 1:
				cont.agregarSocioNuevo();
				break;
			case 2:
				cont.cargarCobroManual();
				break;
			case 3:
				cont.cargarCuotaAuto();
				break;
			case 4:
				aux = cont.mostrarSocio();
				cont.cobrarCuota(aux);
				Consola.escribir(aux.toString());
				break;
			case 5:
				aux = cont.mostrarSocio();
				cont.anularCuota(aux);
				break;
			case 6:
				aux = cont.mostrarSocio();
				if (aux != null) {
					Consola.escribir(aux.stringConCuot());
				} else
					Consola.escribir("Socio no encontrado");
				break;
			case 7:
				aux = cont.mostrarSocio();
				if (aux.getEstado() == true) {
					cont.darDeBaja(aux);
				} else
					Consola.escribir("El socio ya esta dado de baja");
				break;
			case 8:
				aux = cont.mostrarSocio();
				if (aux.getEstado() == false) {
					cont.darDeAlta(aux);
				} else
					Consola.escribir("El socio ya esta dado de alta");
				break;
			case 9:
				Consola.escribir(cont.listarCategoria());
				break;
			case 10:
				Consola.escribir(cont.mostrarSocios());
				break;
			case 0:
				Consola.escribir("Volviendo al menu principal");
				break;
			}
		} while (opcion != 0);
	}

	public static void menuCaja(Contenedora cont) {
		int opcion = -1;

		do {
			do {
				Consola.escribir("Menu Mandarina Rugby Club.");
				Consola.escribir("1. Ver estado de la caja");
				Consola.escribir("2. Egresar dinero");
				Consola.escribir("0. Para volver al menu principal");
				try {
					opcion = Integer.parseInt(Consola.leer("Ingrese la opcion deseada."));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			} while (opcion > 2 || opcion < 0);

			switch (opcion) {
			case 1:
				Consola.escribir(cont.mostrarCaja());
				break;
			case 2:
				cont.retirarDinero();
				break;
			case 0:
				Consola.escribir("Volviendo al menu principal");
				break;
			}
		} while (opcion != 0);
	}

	public static void menuInd(Contenedora cont) {
		int opcion = -1;

		do {
			do {
				Consola.escribir("Menu Mandarina Rugby Club.");
				Consola.escribir("1.Vender indumentaria");
				Consola.escribir("2.Restockear");
				Consola.escribir("3.Listar indumentaria disponible");
				Consola.escribir("0.Para volver al menu principal");
				try {
					opcion = Integer.parseInt(Consola.leer("Ingrese la opcion deseada."));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			} while (opcion > 3 || opcion < 0);

			switch (opcion) {
			case 1:
				cont.venderRopa();
				break;
			case 2:
				cont.reStockerPrenda();
				break;
			case 3:
				Consola.escribir(cont.listarRopa());
				break;
			case 0:
				Consola.escribir("Volviendo al menu principal");
				break;
			}
		} while (opcion != 0);
	}

	public static boolean iniciarSesion() {
		boolean flag = true;
		boolean flag1 = false;
		int intentos = 5;

		do {
			try {
				flag1 = verificarDatos(Consola.leer("Ingrese usuario"), Consola.leer("Ingrese password"));
			} catch (Incorrecto e) {
				Consola.escribir(e.getMessage());
				;
				flag = e.maxError();
				intentos--;
				if (intentos > 0)
					Consola.escribir("Quedan " + intentos + " intentos");
			}

		} while (flag == true && flag1 == false);
		if (flag == false) {
			Consola.escribir("Excedio la cantidad de intentos. Reinicie el programa.");
		}
		return flag;
	}

	public static boolean verificarDatos(String usu, String pas) throws Incorrecto {
		boolean flag = true;
		if (!usu.equals("nacho")) {
			flag = false;
			throw new Incorrecto("Usuario invalido");
		} else if (!pas.equals("1312")) {
			flag = false;
			throw new Incorrecto("Password invalido");
		}
		return flag;
	}
}
