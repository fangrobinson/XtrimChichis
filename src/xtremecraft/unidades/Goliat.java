package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;


public class Goliat extends Unidad {
	
	public Goliat(Terreno terreno){
		super(terreno);
		this.vitalidad = new BarraDeVitalidad(125);
		this.daño = new Danio (10,12);
		this.vision = 8;
		
	}

	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return false;
	}


	
	
}