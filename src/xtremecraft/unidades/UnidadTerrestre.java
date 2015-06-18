package xtremecraft.unidades;


public abstract class UnidadTerrestre extends Unidad {
	
	protected UnidadTerrestre() {
		super();
	}

	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return false;
	}

}
