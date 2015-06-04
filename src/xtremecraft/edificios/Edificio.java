package xtremecraft.edificios;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Coordenada;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.NodoMineral;


abstract class Edificio implements Ubicable {
	

	private Coordenada coordenadas;

	public Edificio(int fila, int columna){
		
		@SuppressWarnings("unused")
		Coordenada coordenadas = new Coordenada(fila,columna);
		
	}
	
	
	public static CentroDeMineral nuevoRecolectorDeMineral(Terran razaTerran,NodoMineral nodoMineral, int fila, int columna){
		
		CentroDeMineral nuevoCentroMineral= new CentroDeMineral(nodoMineral, fila, columna);
		razaTerran.agregarCentroMineral(nuevoCentroMineral);
		return nuevoCentroMineral;
		
		
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.coordenadas;
		
	}

}
