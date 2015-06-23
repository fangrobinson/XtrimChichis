package xtremecraft.recursos;

import xtremecraft.edificios.Recolector;
import xtremecraft.mapa.Coordenada;

public abstract class Recurso{
	
	protected boolean esExplotado;
	protected int cantidadDeRecurso;
	protected Coordenada ubicacionActual;
	
	public Recurso(){
		
		esExplotado=false;
		
	}
	
	public boolean estaSiendoExplotado() {
		
		return this.esExplotado;
		
	}
	
	public int explotar(int i) {
		
		if (i > this.cantidadDeRecurso){
			
			i = this.cantidadDeRecurso;
			
		}
		
		this.cantidadDeRecurso-=i;
		
		return i;
		
	}

	public abstract boolean puedeSerExtraidoPor(Recolector nuevoRecolector);

}
