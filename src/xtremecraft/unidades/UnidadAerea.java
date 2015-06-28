package xtremecraft.unidades;

import xtremecraft.partida.Jugador;


public abstract class UnidadAerea extends Unidad{

	protected UnidadAerea(Jugador unJugador) {
		
		super(unJugador);
		
	}
	
	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}

	public boolean puedeUbicarseEnAire() {
		
		return true;
		
	}

}
