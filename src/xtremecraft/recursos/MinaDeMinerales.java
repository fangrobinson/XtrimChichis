package xtremecraft.recursos;

import xtremecraft.edificios.Recolector;

public class MinaDeMinerales extends Recurso{
	
	public MinaDeMinerales(int numeroDeCristales){
		
		super();
		if(numeroDeCristales<=0){
			throw new IllegalArgumentException("La cantidad de minerales en un nodo mineral debe ser un numero entero positivo.");
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
