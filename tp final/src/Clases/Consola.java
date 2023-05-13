package Clases;

import java.util.Scanner;

public class Consola {
	private static Scanner tecla = new Scanner (System.in);
	
	public static void escribir(Object x) {
		System.out.println(x);
	}
	
	public static String leer (String mensaje) {
		Consola.escribir(mensaje);
		return tecla.nextLine();
	}
}
