package xtremecraft.sistema;

import java.util.ArrayList;

import xtremecraft.mapa.Mapa;

public class Juego {
	private int tiempo;
	private int cant_jug;
	private Mapa mapa;
	private ArrayList<Jugador> jugadores;
	
	public Juego(ArrayList<Jugador> jugadores){
		if (jugadores.size() <= 1){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		this.tiempo = 0;
		this.cant_jug = jugadores.size();
		this.mapa = new Mapa(cant_jug);	
		this.jugadores = jugadores;
		
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
