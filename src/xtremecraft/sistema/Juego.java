package xtremecraft.sistema;

import xtremecraft.mapa.Mapa;

public class Juego {
	private int tiempo;
	private Mapa mapa;
	
	public Juego(int cant_jug){
		this.tiempo = 0;
		this.mapa = new Mapa(cant_jug);	
	}
	
	public void pasarTiempo(){
		this.tiempo = this.tiempo + 1;
		//this.mapa.pasarTiempo();
	}
	
	public int tiempo(){
		return this.tiempo;
	}
	
}
