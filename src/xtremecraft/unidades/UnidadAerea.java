package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;


public abstract class UnidadAerea extends Unidad{

	protected UnidadAerea(Terreno terreno) {
		super(terreno);
	}
	
	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return true;
	}

}
