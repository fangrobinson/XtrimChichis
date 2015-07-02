package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;

public abstract class UnidadTerrestre extends Unidad {
	
	protected int distanciaTransporte;
	
	protected UnidadTerrestre(Jugador unJugador) {
		super(unJugador);
	}

	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return false;
	}

	public void setUbicacionInicial(Terreno terreno) {

	   	if(this.estaUbicada){
	   		if(!this.puedoVer( terreno.getCoordenada() ) || terreno.estaElevado() ) throw new UbicacionNoValidaException();
	   		this.terrenoActual.desocupar();
	   	}
	   	this.terrenoActual = terreno;
	   	terrenoActual.ubicar(this);
		this.estaUbicada = true;
			
	}

}
