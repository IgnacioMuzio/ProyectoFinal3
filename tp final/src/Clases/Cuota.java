package Clases;

public class Cuota {
	private int mes;
	private int año;
	private int monto;
	private int numeroCuota;
	private String cate;

	public Cuota(int mes, int año, int monto,int cantCuota,String cate) {
		super();
		this.mes = mes;
		this.año = año;
		this.monto = monto;
		this.numeroCuota = cantCuota;
		this.cate=cate;
	}

	public String devolverMes () {
		return mes +"/"+año;
	}

	public int getMonto() {
		return monto;
	}
	
	
	public int getMes() {
		return mes;
	}

	public int getAño() {
		return año;
	}

	public String getCate() {
		return cate;
	}

	public int getNum() {
		return numeroCuota;
	}
	
	public String getCat() {
		return cate;
	}

	@Override
	public String toString() {
		return "mes=" + mes+"/"+año + ", monto=" + monto + ", numeroCuota=" + numeroCuota + ", cate=" + cate ;
	}

	
	
	
	
	
	
}
