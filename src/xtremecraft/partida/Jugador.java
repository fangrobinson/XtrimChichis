package xtremecraft.partida;

import java.util.Observable;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.AtaqueFueraDelRangoDeVisionException;
import xtremecraft.unidades.Defendible;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;
import xtremecraft.unidades.Ubicable;
import xtremecraft.unidades.Unidad;

public class Jugador extends Observable implements Actualizable {
	
	private String nombre;
	private Terran nacion;
	private Jugador siguienteJugador;
	private int numeroDeJugador;
	private boolean esMiTurno;

	public Jugador (String nombre, Terreno terreno, Mapa mapa) throws NombreMuyCortoException{
		
		if (nombre.length() < 4){
		
			throw new NombreMuyCortoException();
		
		}
		this.nombre = nombre;
		this.nacion = new Terran(this);
		this.crearBaseInicial(terreno, mapa);
		this.esMiTurno = false;
		
	}
		
	public String nombre(){
		
		return this.nombre;
		
	}
	
	public Terran nacion(){
		
		return this.nacion;
		
	}
	
	public void setNumeroDeJugador(int numero){
		
		this.numeroDeJugador = numero;
		
	}
	
	public int getNumeroDeJugador(){
		
		return this.numeroDeJugador;
		
	}

	public boolean estaEnJuego(){
		
		return this.nacion.estaViva();
		
	}
	
	public void verificarSigueEnJuego(){
		
		if (!this.estaEnJuego()){
			
			Jugador anterior = this.siguienteJugador;
			
			while (anterior.siguienteJugador != this){
				
				anterior = anterior.siguienteJugador;
				
			}
			
			anterior.siguienteJugador = this.siguienteJugador;
			
		}
		
	}
	
	public boolean esDeMiPropiedad(Ubicable ubicable){
		
		return this.nacion.esDeMiPropiedad(ubicable);
		
	}
	
	public void atacar(Defendible atacante, Atacable atacado) throws AtaqueFueraDelRangoDeVisionException, SeleccionadoNoEsPropiedadDelJugadorException{
		
		Ubicable atacanteUbicado = (Ubicable) atacante;
		
		if (!this.esDeMiPropiedad(atacanteUbicado)){
			
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		
		}
		
		atacante.atacar(atacado);
	}
	
	public void atacarConRadiacion(NaveCiencia naveCiencia, Unidad unidad, Mapa mapa) throws SeleccionadoNoEsPropiedadDelJugadorException {
		
		if (!this.esDeMiPropiedad(naveCiencia)){
			
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		
		}
		
		naveCiencia.atacarConRadiacion(mapa, unidad);
		
	}
	
	
	private void crearBaseInicial(Terreno terreno, Mapa mapa){
		
		DepositoDeSuministros deposito = this.nacion.crearDepositoDeSuministros(terreno, mapa);

		for(int tiempo = 0; tiempo<deposito.tiempoConstruccion();tiempo++){
		
			deposito.pasarTiempo();
		
		}
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public Barraca crearBarraca(Terreno unTerreno, Mapa mapa){
		
		Barraca nuevaBarraca = this.nacion.crearBarraca(unTerreno, mapa);
		this.setChanged();
		this.notifyObservers();
		
		return nuevaBarraca;
		
	}
	
	public Fabrica crearFabrica(Terreno unTerreno, Mapa mapa){
		
		Fabrica nuevaFabrica = this.nacion.crearFabrica(unTerreno, mapa);
		this.setChanged();
		this.notifyObservers();
		
		return nuevaFabrica;
		
	}
	
	public PuertoEstelar crearPuertoEstelar(Terreno unTerreno, Mapa mapa){
		
		PuertoEstelar nuevoPuerto = this.nacion.crearPuertoEstelar(unTerreno, mapa);
		this.setChanged();
		this.notifyObservers();
	
		return nuevoPuerto;
		
	}
	
	public RecolectorDeGasVespeno crearRecolectorDeGasVespeno(Terreno unTerreno, Mapa mapa){
		
		RecolectorDeGasVespeno recolectorDeGas = this.nacion.crearRecolectorDeGasVespeno(unTerreno, mapa);
		this.setChanged();
		this.notifyObservers();
		
		return recolectorDeGas;
		
	}
	
	public RecolectorDeMineral crearRecolectorDeMineral(Terreno unTerreno, Mapa mapa){
		
		RecolectorDeMineral recolectorDeMineral = this.nacion.crearRecolectorDeMineral(unTerreno, mapa);
		this.setChanged();
		this.notifyObservers();
		
		return recolectorDeMineral;
		
	}
	
	public Marine crearMarine(Barraca unaBarraca, Mapa unMapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Marine nuevoMarine = (Marine) this.nacion.crearMarine(unaBarraca, unMapa);
		this.setChanged();
		this.notifyObservers();
	
		return nuevoMarine;
		
	}
	
	public Goliat crearGoliat(Fabrica unaFabrica, Mapa unMapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Goliat nuevoGoliat = (Goliat) this.nacion.crearGoliat(unaFabrica, unMapa);
		this.setChanged();
		this.notifyObservers();
	
		return nuevoGoliat;
		
	}
	
	public Espectro crearEspectro(PuertoEstelar unPuerto, Mapa unMapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Espectro nuevoEspectro = (Espectro) this.nacion.crearEspectro(unPuerto, unMapa);
		this.setChanged();
		this.notifyObservers();
	
		return nuevoEspectro;
		
	}
	
	public NaveCiencia crearNaveCiencia(PuertoEstelar unPuerto, Mapa unMapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		NaveCiencia nuevaNaveCiencia = (NaveCiencia) this.nacion.crearNaveCiencia(unPuerto, unMapa);
		this.setChanged();
		this.notifyObservers();
	
		return nuevaNaveCiencia;
		
	}
	
	public NaveTransporte crearNaveTransporte(PuertoEstelar unPuerto, Mapa unMapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		NaveTransporte nuevaNaveTransporte = (NaveTransporte) this.nacion.crearNaveTransporte(unPuerto, unMapa);
		this.setChanged();
		this.notifyObservers();
	
		return nuevaNaveTransporte;
		
	}
	
	public DepositoDeSuministros crearDepositoDeSuministros(Terreno unTerreno, Mapa mapa){
		
		DepositoDeSuministros nuevoDeposito = this.nacion.crearDepositoDeSuministros(unTerreno, mapa);
		this.setChanged();
		this.notifyObservers();
	
		return nuevoDeposito;
		
	}

	public void setJugadorSiguiente(Jugador jugador) {
		
		this.siguienteJugador = jugador;
		
	}

	public void setTurno() {
		
		this.esMiTurno = true;
		
	}

	public void pasarTurno() {
		
		if(!this.esMiTurno){
		
			throw new JugadorNoTieneElTurnoException();
	
		}
		
		this.pasarTiempo();
		this.esMiTurno = false;
		this.siguienteJugador.setTurno();
		this.setChanged();
		this.notifyObservers();
		
	}

	public boolean tieneTurno() {
		
		return this.esMiTurno;
		
	}

	public void pasarTiempo() {

		this.nacion.pasarTiempo();
		
	}
	
	public int getCantidadDeMinerales(){
		
		return this.nacion.getMinerales();
		
	}
	
	public int getCantidadDeGasVespeno(){
		
		return this.nacion.getGasVespeno();
		
	}
	
	public int getPoblacionDisponible(){
		
		return this.nacion.getPoblacionMaxima();
		
	}


}
