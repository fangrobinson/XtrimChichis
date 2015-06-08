package xtremecraft.edificios;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Coordenada;


abstract class Edificio implements Ubicable {
	

	protected Coordenada coordenadas;

	public Edificio(int fila, int columna){
		
		this.coordenadas = new Coordenada(fila,columna);
		
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.coordenadas;
		
	}

	
	
	
}
