package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;

public class Espectro extends UnidadAerea{

	protected Espectro(Terreno terreno) {
		super(terreno);
		this.vitalidad = new BarraDeVitalidad(120);
		this.daño = new Danio (20,8);
		this.vision = 7;
	}

}
