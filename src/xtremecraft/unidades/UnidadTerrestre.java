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
	
	 public void actualizarUbicacion(Terreno terreno) {

	   	if( (!this.puedoVer(terreno.getCoordenada())) || terreno.estaOcupado() || terreno.estaElevado()){
	   		throw new UbicacionNoValidaException();
	   	}
	   	this.terrenoActual.desocupar();
	   	terreno.ubicar(this);    	
	   	this.terrenoActual = terreno;
	   	this.puedeMoverse = false;
			
	}
}
