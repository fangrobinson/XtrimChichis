package xtremecraft.partida;

import java.util.ArrayList;
import java.util.Observer;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Mapa;
import xtremecraft.unidades.Ubicable;
import xtremecraft.unidades.Unidad;

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
		
		for (int posicion = 0; posicion < nombresJugadores.size(); posicion++){
		
			int numeroDeJugador = posicion + 1;
			Jugador jugadorNuevo = new Jugador(nombresJugadores.get(posicion), this.mapa.obtenerTerrenoJugador(numeroDeJugador), this.mapa);
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
	
	public Jugador getJugadorNumero(int numero){
		
		return this.jugadores.get(numero);
		
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
	
	public void agregarObservadorDeJugadores(Observer observador,Jugador jugador){
		
		for(int posicion = 0; posicion < this.jugadores.size(); posicion++){
		
			Jugador jugadorActual = this.jugadores.get(posicion);
			jugadorActual.addObserver(observador);
		
		}
		
	}

	public boolean ubicableElegidoEsDelJugador(Coordenada coordenada)  {
		
		Ubicable ubicable = mapa.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		return this.quienJuega().esDeMiPropiedad(ubicable);
		
	}

	public void validarQueUbicableElegidoEsDelJugador(Coordenada coordenada) throws SeleccionadoNoEsPropiedadDelJugadorException {
		
		Ubicable ubicable = mapa.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		if( !this.quienJuega().esDeMiPropiedad(ubicable) ){
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		}
		
	}

	public boolean unidadSeleccionadaEstaEnConstruccion(Coordenada coordenada) {
		
		Celda celda = this.mapa.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna());
		Unidad unidad = (Unidad) celda.getUbicableEnInferior();
		return unidad.estaEnConstruccion();
		
	}

	public boolean unidadSeleccionadaPuedeMoverse(Coordenada coordenada) {

		Celda celda = this.mapa.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna());
		Unidad unidad = (Unidad) celda.getUbicableEnInferior();
		return unidad.puedeMoverse();
		
	}

	public boolean unidadSeleccionadaPuedeAtacar(Coordenada coordenada) {
		
		Celda celda = this.mapa.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna());
		Unidad unidad = (Unidad) celda.getUbicableEnInferior();
		return unidad.puedeAtacar();
		
	}
	
	private boolean verificarHayGanador(){
		
		int cantidadVivos = 0;
		
		for (int i = 0; i < this.cantidadJugadores; i++){
			
			if(this.jugadores.get(i).estaEnJuego()){
				
				cantidadVivos ++;
				
			}
			
		}
		
		return cantidadVivos == 1;
		
	}
	
	public Jugador jugadorGanador(){
		
		Jugador ganador = null;
		
		if(!this.verificarHayGanador()){
			return ganador;
		}
		 
		
		for (int i = 0; i < this.cantidadJugadores; i++){
			
			if(this.jugadores.get(i).estaEnJuego()){
				
				ganador = this.jugadores.get(i);
				
			}
			
		}
		
		return ganador;
		
	}
	
}
