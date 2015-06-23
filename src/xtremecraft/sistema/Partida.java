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
		
		//que las coordenadas de jugador las determine partida según lo raro que programó robin en mapa. 
		// Perdon??? Raro D: 
		//TODO: Verdadero: resolver obtenerTerrenoJugador.
		
		for (int i = 0; i < nombresJugadores.size(); i++){
			Jugador jugadorNuevo = new Jugador(nombresJugadores.get(i), this.mapa.obtenerTerrenoJugador(i));
			jugadores.add(jugadorNuevo);
		}
		
		this.crearRondaDeTurnos(jugadores);
		
	}
	
	private void crearRondaDeTurnos(ArrayList<Jugador> listaJugadores) {
		
		for (int posicion = 0; posicion < listaJugadores.size(); posicion++){
			Jugador jugadorActual = listaJugadores.get(posicion);
			if((posicion+1) < listaJugadores.size()) {
				Jugador jugadorSiguiente = listaJugadores.get(posicion+1);
				jugadorActual.setJugadorSiguiente(jugadorSiguiente);
			}
		}
		Jugador primerJugador = listaJugadores.get(0);
		Jugador ultimoJugador = listaJugadores.get(listaJugadores.size() - 1);
		ultimoJugador.setJugadorSiguiente(primerJugador); 
		
		primerJugador.setTurno();
		
	}

	public void pasarTiempo(){
		
		//this.mapa.pasarTiempo();
		
	}
	
	public int tiempo(){
		
		return this.tiempo;
		
	}
	
	public Mapa getMapa(){
		
		return this.mapa;
		
	}
	
	public Jugador quienJuega(){
		
		for(int posicion = 0; posicion < this.jugadores.size(); posicion++){
			Jugador jugadorActual = this.jugadores.get(posicion);
			if(jugadorActual.tieneTurno()){
				return jugadorActual;
			}
		}
		throw new NoSeEncontroNingunJugadorConTurnoAsignadoException();
	}
	
}
