package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;

public abstract class UnidadTerrestre extends Unidad {
	
	protected UnidadTerrestre(Terreno terreno) {
		super(terreno);
	}

	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return false;
	}

}
