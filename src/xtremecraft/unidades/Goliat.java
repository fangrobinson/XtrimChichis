package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;


public class Goliat extends UnidadTerrestre {
	
	public Goliat(Terreno terreno){
		super(terreno);
		this.vitalidad = new BarraDeVitalidad(125);
		this.danio = new Danio (10,12);
		this.vision = 8;
		this.tiempoConstruccion = 6;
		
	}	
	
}