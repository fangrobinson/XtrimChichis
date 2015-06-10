package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;

public class Espectro extends UnidadAerea{

	public Espectro(Terreno terreno) {
		super(terreno);
		this.vitalidad = new BarraDeVitalidad(120);
		this.danio = new Danio (20,8);
		this.vision = 7;
		this.tiempoConstruccion = 8;
	}

}
