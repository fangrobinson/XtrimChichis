package xtremecraft.partida;

import java.util.ArrayList;
import java.util.Observer;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Ubicable;

public class Partida {
	
	private int tiempo;
	private int cantidadJugadores;
	private Mapa mapa;
	private ArrayList<Jugador> jugadores;
	
	public Partida(ArrayList<String> nombresJugadores){
		if (nombresJugadores.size() <= 1){
			throw new CantidadDeJugadoresMenorAUnoException();
		}
		this.tiempo = 0;
		this.cantidadJugadores = nombresJugadores.size();
		this.mapa = new Mapa(cantidadJugadores);
		this.jugadores = new ArrayList<Jugador>();
		
		//que las coordenadas de jugador las determine partida según lo raro que programó robin en mapa. 
		// Perdon??? Raro ??? D: 
		//TODO: Verdadero: resolver obtenerTerrenoJugador.
		
		for (int posicion = 0; posicion < nombresJugadores.size(); posicion++){
			int numeroDeJugador = posicion + 1;
			Jugador jugadorNuevo = new Jugador(nombresJugadores.get(posicion), this.mapa.obtenerTerrenoJugador(numeroDeJugador));
			jugadores.add(jugadorNuevo);
		}
		
		this.crearRondaDeTurnos();
		
	}

	private void crearRondaDeTurnos() {
		
		int ultimaPosicion = this.jugadores.size() - 1;
		for (int posicion = 0; posicion < ultimaPosicion; posicion++){
			Jugador jugadorActual = this.jugadores.get(posicion);
			jugadorActual.setNumeroDeJugador(posicion+1);
			if((posicion+1) < this.jugadores.size()) {
				Jugador jugadorSiguiente = this.jugadores.get(posicion+1);
				jugadorActual.setJugadorSiguiente(jugadorSiguiente);
			}
		}
		Jugador primerJugador = this.jugadores.get(0);
		Jugador ultimoJugador = this.jugadores.get(ultimaPosicion);
		ultimoJugador.setNumeroDeJugador(ultimaPosicion+1);
		ultimoJugador.setJugadorSiguiente(primerJugador); 
		
		primerJugador.setTurno();
		
	}

	public void pasarTiempo(){
		
		for (int posicion = 0; posicion < this.jugadores.size(); posicion++){
			Jugador jugadorActual = this.jugadores.get(posicion);
			jugadorActual.pasarTiempo();
		}
		
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
		mapa.ubicarCapaInferior(recolectorMineral, this.mapa.getCeldaEnFilaColumna(fila, columna));
		
		//mapa.ubicar(recolectorMineral, this.mapa.getCeldaEnFilaColumna(fila, columna));
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

	public void agregarObservadorDeJugadores(Observer observador,Jugador jugador){
		
		for(int posicion = 0; posicion < this.jugadores.size(); posicion++){
			Jugador jugadorActual = this.jugadores.get(posicion);
			jugadorActual.addObserver(observador);
		}
		
	}
	
}
