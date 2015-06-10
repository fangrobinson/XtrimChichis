package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;


public class Marine extends UnidadTerrestre{
	
	public Marine(Terreno celda){
		super(celda);
		this.vitalidad = new BarraDeVitalidad(40);
		this.danio = new Danio (6,6);
		this.vision = 7;
		this.tiempoConstruccion = 3;
	}

}
