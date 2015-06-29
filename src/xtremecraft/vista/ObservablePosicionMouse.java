package xtremecraft.vista;

import java.util.Observable;

import xtremecraft.mapa.Coordenada;

public class ObservablePosicionMouse extends Observable{
	
	private Coordenada coordenadaMouse = new Coordenada(0,0);

	public void setCoordenada(Coordenada coordenada) {
		
		this.coordenadaMouse = coordenada;
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public Coordenada getCoordenadaActualMouse(){
		
		return this.coordenadaMouse;
		
	}
	
}
