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
	/*
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
	
	}
	
	public void crearMarine(Jugador jugador, Barraca unaBarraca) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if (jugador != this.quienJuega()){
	
			throw new JugadorNoTieneElTurnoException();
		
		}
		
		jugador.crearMarine(unaBarraca, this.mapa);
		
	}
	
	public void crearGoliat(Jugador jugador, Fabrica unaFabrica) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if (jugador != this.quienJuega()){
		
			throw new JugadorNoTieneElTurnoException();
		
		}
		
		jugador.crearGoliat(unaFabrica, this.mapa);
	}
	
	public void crearEspectro(Jugador jugador, PuertoEstelar unPuerto) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if (jugador != this.quienJuega()){
		
			throw new JugadorNoTieneElTurnoException();
		
		}
		
		jugador.crearEspectro(unPuerto, this.mapa);
		
	}
	
	public void crearNaveCiencia(Jugador jugador, PuertoEstelar unPuerto) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if (jugador != this.quienJuega()){
		
			throw new JugadorNoTieneElTurnoException();
		
		}
		
		jugador.crearNaveCiencia(unPuerto, this.mapa);
		
	}
	
	public void crearNaveTransporte(Jugador jugador, PuertoEstelar unPuerto) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if (jugador != this.quienJuega()){
		
			throw new JugadorNoTieneElTurnoException();
		
		}
		
		jugador.crearNaveTransporte(unPuerto, this.mapa);
		
	}
*/
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
	
}
