package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;


public class Marine extends Unidad{
	
	public Marine(Terreno celda){
		super(celda);
		this.vitalidad = new BarraDeVitalidad(40);
		this.daño = new Daño (6,6);
		this.vision = 7;
	}
}
