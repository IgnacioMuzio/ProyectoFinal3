package Clases;

public abstract class Persona{
	private String nombre;
	private String apellido;
	private String dni;
	private int edad;
	private String fechaNac;
	private String direccion;
	
	public Persona(String nombre, String apellido, String dni, int edad, String fechaNac, String direccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad = edad;
		this.fechaNac = fechaNac;
		this.direccion = direccion;
	}
	
	public String getDni () {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getEdad() {
		return edad;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public String getDireccion() {
		return direccion;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", edad=" + edad
				+ ", fechaNac=" + fechaNac + ", direccion=" + direccion;
	}
	
	
	
	
}
