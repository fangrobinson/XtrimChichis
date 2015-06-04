package xtremecraft.unidades;

import xtremecraft.mapa.Celda;


public class Goliat extends Unidad {
	
	public Goliat(Celda celda){
		super(celda);
		this.vitalidad = new BarraDeVitalidad(125);
		this.daño = new Daño (10,12);
		this.vision = 8;
		
	}


	
	
}