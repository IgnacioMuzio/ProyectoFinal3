package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import Excepciones.MontoInvalido;


public class Cobro extends Movimiento{
	
	private Cuota cuotita;
	private int saldoAcreedor;
	private int saldoRestante;
	private String metodo;
	private boolean estado;
	
	public Cobro(Cuota cuotita, String metodo) {
		super();
		this.cuotita = cuotita;
		this.metodo = metodo;
		saldoAcreedor = verificarEfectivo();
		saldoRestante = cuotita.getMonto()-saldoAcreedor;
		estado=true;
	}
	
	public JSONObject toJSON () {
		JSONObject nuevo = new JSONObject();
		JSONObject dos = new JSONObject();
		try {
			dos.put("mes", cuotita.getMes());
			dos.put("año", cuotita.getAño());
			dos.put("monto",cuotita.getMonto());
			dos.put("numeroCuota", cuotita.getNum());
			dos.put("categoria", cuotita.getCate());
			nuevo.put("cuota",dos);
			nuevo.put("saldoAcreedor",getSaldoAcreedor());
			nuevo.put("saldoRestante", getSaldoRestante());
			nuevo.put("metodo",getMetodo());
			nuevo.put("estado",getEstado());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nuevo;
		
	}
	
	public int verificarEfectivo () {
		int saldo;
		if(metodo.equals("EFECTIVO")) {
			saldo=0;
		}
		else saldo=cuotita.getMonto();
		return saldo;
	}
	public boolean isPago () {
		return (saldoRestante==0);
	}
	
	public void cobrarCuota (int monto) {
		try {
			if(verificarMonto(monto)) {
				saldoAcreedor+=monto;
				saldoRestante-=monto;
			}
		} catch (MontoInvalido e) {
			e.getMessage();
		}
	}
	
	public boolean getEstado () {
		return estado;
	}
	public boolean verificarMonto(int monto) throws MontoInvalido{
		boolean flag = false;
		if(saldoRestante==0) {
			throw new MontoInvalido("La cuota ya esta paga.");
		}
		else {
			if(monto>saldoRestante || monto<=0) {
				throw new MontoInvalido("El monto ingresado es invalido, ingrese una cantidad menor a " + saldoRestante);
			}
			else flag = true;
		}
		
		return flag;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Cobro [Cuota=" + cuotita + ", saldoAcreedor=" + saldoAcreedor + ", saldoRestante=" + saldoRestante
				+ ", metodo=" + metodo + ", estado=" + estado + "]";
	}

	public int getSaldoAcreedor() {
		return saldoAcreedor;
	}

	public int getSaldoRestante() {
		return saldoRestante;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cobro other = (Cobro) obj;
		return (cuotita.devolverMes().equals(other.cuotita.devolverMes()) && cuotita.getCat().equals(other.cuotita.getCat()) && getEstado()==true) ;
	}

	@Override
	public String getClave() {
		return ""+cuotita.getNum();
	}

	@Override
	public String getNombreComp() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEstado(boolean estado) {
		this.estado=estado;
	}
	
	public String getMetodo() {
		return metodo;
	}

	public void setSaldoAcreedor(int saldoAcreedor) {
		this.saldoAcreedor = saldoAcreedor;
	}

	public void setSaldoRestante(int saldoRestante) {
		this.saldoRestante = saldoRestante;
	}
	
	
}
