package xtremecraft.edificios;

import xtremecraft.raza.Terran;
import xtremecraft.recursos.NodoMineral;


abstract class Edificio {
	

	public Edificio(){
		
		
	}
	
	
	public static CentroDeMineral nuevoRecolectorDeMineral(Terran razaTerran,NodoMineral nodoMineral){
		
		CentroDeMineral nuevoCentroMineral= new CentroDeMineral(nodoMineral);
		razaTerran.agregarCentroMineral(nuevoCentroMineral);
		return nuevoCentroMineral;
		
		
	}

}
