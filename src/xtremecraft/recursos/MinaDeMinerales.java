package xtremecraft.recursos;

import xtremecraft.edificios.Recolector;

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

	
	public boolean puedeSerExtraidoPor(Recolector nuevoRecolector) {
		
		return nuevoRecolector.puedeExtraerMineral();
		
	}

}
