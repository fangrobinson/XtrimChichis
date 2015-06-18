package xtremecraft.sistema;

import java.util.ArrayList;

import xtremecraft.mapa.Mapa;

public class Partida {
	private int tiempo;
	private int cant_jug;
	private Mapa mapa;
	private ArrayList<Jugador> jugadores;
	
	public Partida(ArrayList<String> nombresJugadores){
		if (nombresJugadores.size() <= 1){
			throw new CantidadDeJugadoresMenorAUnoException();
		}
		this.tiempo = 0;
		this.cant_jug = nombresJugadores.size();
		this.mapa = new Mapa(cant_jug);
		this.jugadores = new ArrayList<Jugador>();
		
		for (int i = 0; i < nombresJugadores.size(); i++){
			Jugador jugadorNuevo = new Jugador(nombresJugadores.get(i));
			jugadores.add(jugadorNuevo);
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
