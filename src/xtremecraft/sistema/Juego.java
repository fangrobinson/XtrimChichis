package xtremecraft.sistema;

import java.util.ArrayList;

import xtremecraft.mapa.Mapa;

public class Juego {
	private int tiempo;
	private int cant_jug;
	private Mapa mapa;
	private ArrayList<Jugador> jugadores;
	
	public Juego(int cant_jug){
		if (cant_jug <= 1){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		this.tiempo = 0;
		this.cant_jug = cant_jug;
		this.mapa = new Mapa(cant_jug);	
		this.jugadores = new ArrayList<Jugador>();
		
		for (int i = 0; i<cant_jug; i = i + 1){
			this.jugadores.add(new Jugador());
		}
	}
	
	public void pasarTiempo(){
		this.tiempo = this.tiempo + 1;
		//this.mapa.pasarTiempo();
	}
	
	public int tiempo(){
		return this.tiempo;
	}
	
	public Mapa getMapa(){
		
		return this.mapa;
		
	}
	
	public Jugador quienJuega(){
		int pos = this.tiempo % this.cant_jug;
		return this.jugadores.get(pos);
	}
	
}
