package xtremecraft.recursos;

import java.util.Observable;

import xtremecraft.edificios.Recolector;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Identificable;

public abstract class Recurso extends Observable implements Identificable{
	
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
	
	@Override
	public int getNumeroJugador() {
		
		throw new IdentificableNoTieneJugadorException();
		
	}

}
