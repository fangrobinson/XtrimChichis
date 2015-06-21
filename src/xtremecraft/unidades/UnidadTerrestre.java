package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;

public abstract class UnidadTerrestre extends Unidad {
	
	protected int distanciaTransporte;
	
	protected UnidadTerrestre() {
		super();
	}

	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return false;
	}

	public void actualizarUbicacion(Terreno terreno) {

	   	if(this.estaUbicada){
	   		if(!this.puedoVer(terreno.getCoordenada()) || terreno.estaElevado() ) throw new UbicacionNoValidaException();
	   		this.terrenoActual.desocupar();
	   	}
	   	this.terrenoActual = terreno;
	   	terrenoActual.ubicar(this);
		this.estaUbicada = true;
			
	}

}
