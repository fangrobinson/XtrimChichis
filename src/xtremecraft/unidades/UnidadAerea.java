package xtremecraft.unidades;


public abstract class UnidadAerea extends Unidad{

	protected UnidadAerea() {
		
	}
	
	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return true;
	}

}
