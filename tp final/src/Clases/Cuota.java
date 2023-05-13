package Clases;

public class Cuota {
	private int mes;
	private int a�o;
	private int monto;
	private int numeroCuota;
	private String cate;

	public Cuota(int mes, int a�o, int monto,int cantCuota,String cate) {
		super();
		this.mes = mes;
		this.a�o = a�o;
		this.monto = monto;
		this.numeroCuota = cantCuota;
		this.cate=cate;
	}

	public String devolverMes () {
		return mes +"/"+a�o;
	}

	public int getMonto() {
		return monto;
	}
	
	
	public int getMes() {
		return mes;
	}

	public int getA�o() {
		return a�o;
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
		return "mes=" + mes+"/"+a�o + ", monto=" + monto + ", numeroCuota=" + numeroCuota + ", cate=" + cate ;
	}

	
	
	
	
	
	
}
