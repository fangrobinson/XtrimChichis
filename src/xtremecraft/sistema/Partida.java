package xtremecraft.sistema;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Ubicable;

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
	
	public void crearBarraca(Jugador jugador, int fila, int columna){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		Terreno unTerreno = this.mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Ubicable barraca = jugador.crearBarraca(unTerreno);
		mapa.ubicar(barraca, this.mapa.getCeldaEnFilaColumna(fila, columna));
	}
	
	public void crearFabrica(Jugador jugador, int fila, int columna){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		Terreno unTerreno = this.mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Ubicable fabrica = jugador.crearFabrica(unTerreno);
		mapa.ubicar(fabrica, this.mapa.getCeldaEnFilaColumna(fila, columna));
	}
	
	public void crearPuertoEstelar(Jugador jugador, int fila, int columna){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		Terreno unTerreno = this.mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Ubicable puerto = jugador.crearPuertoEstelar(unTerreno);
		mapa.ubicar(puerto, this.mapa.getCeldaEnFilaColumna(fila, columna));
	}
	
	public void crearRecolectorDeGasVespeno(Jugador jugador, int fila, int columna){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		Terreno unTerreno = this.mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Ubicable recolectorGas = jugador.crearRecolectorDeGasVespeno(unTerreno);
		mapa.ubicar(recolectorGas, this.mapa.getCeldaEnFilaColumna(fila, columna));
	}
	
	public void crearRecolectorDeMineral(Jugador jugador, int fila, int columna){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		Terreno unTerreno = this.mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Ubicable recolectorMineral = jugador.crearRecolectorDeMineral(unTerreno);
		mapa.ubicar(recolectorMineral, this.mapa.getCeldaEnFilaColumna(fila, columna));
	}
	
	public void crearMarine(Jugador jugador, Barraca unaBarraca){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		jugador.crearMarine(unaBarraca, this.mapa);
		
	}
	
	public void crearGoliat(Jugador jugador, Fabrica unaFabrica){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		jugador.crearGoliat(unaFabrica, this.mapa);
	}
	
	public void crearEspectro(Jugador jugador, PuertoEstelar unPuerto){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		jugador.crearEspectro(unPuerto, this.mapa);
		
	}
	
	public void crearNaveCiencia(Jugador jugador, PuertoEstelar unPuerto){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		jugador.crearNaveCiencia(unPuerto, this.mapa);
		
	}
	
	public void crearNaveTransporte(Jugador jugador, PuertoEstelar unPuerto){
		
		if (jugador != this.quienJuega()){
			throw new JugadorNoTieneElTurnoException();
		}
		
		jugador.crearNaveTransporte(unPuerto, this.mapa);
		
	}
}
