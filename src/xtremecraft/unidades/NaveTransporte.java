package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;

public class NaveTransporte extends UnidadAerea {

	public NaveTransporte(Terreno terreno) {
		super(terreno);
		this.vitalidad = new BarraDeVitalidad(150);
		this.danio = new Danio (0,0);
		this.vision = 8;
	}

}
