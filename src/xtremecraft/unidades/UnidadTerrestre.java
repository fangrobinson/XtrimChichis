package xtremecraft.unidades;


public abstract class UnidadTerrestre extends Unidad {
	
	protected UnidadTerrestre() {
		
	}

	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return false;
	}

}
