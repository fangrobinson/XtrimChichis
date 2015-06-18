package xtremecraft.recursos;

public class MinaDeMinerales extends Recurso{
	
	public MinaDeMinerales(int numeroDeCristales){
		
		super();
		if(numeroDeCristales<=0){
			throw new NumeroDeCristalesNegativoException();
		}
		this.cantidadDeRecurso=numeroDeCristales;
		
		
	}
	
	public int getCantidadDeMinerales(){
		
		return this.cantidadDeRecurso;
		
	}
	
	public void ocuparMinaDeMineral(){
		
		this.esExplotado=true;
		
	}

	public boolean tieneMineral(){
		
		return true;
		
	}

}
