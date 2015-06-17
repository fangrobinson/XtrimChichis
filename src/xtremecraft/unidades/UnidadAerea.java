package xtremecraft.unidades;


public abstract class UnidadAerea extends Unidad{

	protected UnidadAerea() {
		
	}
	
	public boolean puedeUbicarseEnTierra() {
		return false;
	}

	public boolean puedeUbicarseEnAire() {
		return true;
	}

}
