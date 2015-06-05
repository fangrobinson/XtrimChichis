package xtremecraft.edificios;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Coordenada;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;


abstract class Edificio implements Ubicable {
	

	protected Coordenada coordenadas;

	public Edificio(int fila, int columna){
		
		this.coordenadas = new Coordenada(fila,columna);
		
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.coordenadas;
		
	}

	
	public static CentroDeMineral nuevoRecolectorDeMineral(Terran razaTerran,MinaDeMinerales nodoMineral, int fila, int columna){
		
		CentroDeMineral nuevoCentroMineral= new CentroDeMineral(nodoMineral, fila, columna);
		razaTerran.agregarCentroMineral(nuevoCentroMineral);
		return nuevoCentroMineral;
		
		
	}
	
	
}
