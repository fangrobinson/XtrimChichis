package xtremecraft.edificios;

import xtremecraft.edificios.Recolector;
import xtremecraft.recursos.NodoMineral;


public class CentroDeMineral extends Recolector{

	public CentroDeMineral(NodoMineral nodoMineral, int fila, int columna) {
		
		super(fila, columna);
	
	}
	
	public int getReservas() {
		
		return reservas;
		
	}
		
	
	
	

}
