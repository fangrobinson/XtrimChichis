package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;

public class NaveTransporte extends UnidadAerea {

	protected NaveTransporte(Terreno terreno) {
		super(terreno);
		this.vitalidad = new BarraDeVitalidad(150);
		this.daño = new Danio (0,0);
		this.vision = 8;
	}

}
