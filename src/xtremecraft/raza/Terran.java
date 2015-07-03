package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Actualizable;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.unidades.ConstructorDeUnidades;
import xtremecraft.unidades.Ubicable;
import xtremecraft.unidades.Unidad;


public class Terran implements Actualizable{

		private ArrayList<Unidad> unidades;
		private ArrayList<Edificio> edificios;
    	private int poblacionMaxima;
    	private int minerales;
    	private int gas;
		private Jugador jugador;

	
	public Terran(Jugador jugador){
		
		this.jugador = jugador;
		this.unidades = new ArrayList<Unidad>();
		this.edificios = new ArrayList<Edificio>();
		this.poblacionMaxima = 5;
		this.minerales = 200; 
		this.gas = 200;

	}

	public boolean estaViva(){
		
		return !(this.edificios.isEmpty() && this.unidades.isEmpty());
		
	}
	
	public int getPoblacionMaxima(){
		
		return this.poblacionMaxima;
		
	}
	
	public Barraca crearBarraca(Terreno unTerreno, Mapa mapa){
		
		Barraca nuevaBarraca = new Barraca(this.jugador, unTerreno);
		unTerreno.ubicar(nuevaBarraca);
		this.agregarEdificio(nuevaBarraca);
		nuevaBarraca.addObserver(mapa);
		return nuevaBarraca;
		
	}
	
	public Fabrica crearFabrica(Terreno unTerreno, Mapa mapa){
		
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		Barraca unaBarraca = iter.getBarraca();
		Fabrica nuevaFabrica = new Fabrica(this.jugador, unaBarraca, unTerreno);
		unTerreno.ubicar(nuevaFabrica);
		this.agregarEdificio(nuevaFabrica);
		nuevaFabrica.addObserver(mapa);
		return nuevaFabrica;
			
	}
		
	public PuertoEstelar crearPuertoEstelar(Terreno unTerreno, Mapa mapa){
		
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		Fabrica unaFabrica = iter.getFabrica();
		PuertoEstelar nuevoPuerto = new PuertoEstelar(this.jugador, unaFabrica, unTerreno);
		unTerreno.ubicar(nuevoPuerto);
		this.agregarEdificio(nuevoPuerto);
		nuevoPuerto.addObserver(mapa);
		return nuevoPuerto;
	
	}
	
	public RecolectorDeMineral crearRecolectorDeMineral(Terreno unTerreno, Mapa mapa){
		
		RecolectorDeMineral nuevoCentroMineral = RecolectorDeMineral.nuevoRecolectorDeMineral(this.jugador, unTerreno);
		unTerreno.ubicar(nuevoCentroMineral);
		this.agregarEdificio(nuevoCentroMineral);
		nuevoCentroMineral.addObserver(mapa);
		return nuevoCentroMineral;
		
	}
	
	public RecolectorDeGasVespeno crearRecolectorDeGasVespeno(Terreno unTerreno, Mapa mapa){
		
		RecolectorDeGasVespeno nuevaRefineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(this.jugador, unTerreno);
		unTerreno.ubicar(nuevaRefineria);
		this.agregarEdificio(nuevaRefineria);
		nuevaRefineria.addObserver(mapa);
		return nuevaRefineria;
		
	}
	
	public DepositoDeSuministros crearDepositoDeSuministros(Terreno unTerreno, Mapa mapa){
		
		DepositoDeSuministros nuevoDeposito = new DepositoDeSuministros(this.jugador, unTerreno);
		unTerreno.ubicar(nuevoDeposito);
		this.agregarEdificio(nuevoDeposito);
		nuevoDeposito.addObserver(mapa);
		return nuevoDeposito;
		
	}
	
	public Unidad crearMarine(Barraca unaBarraca, Mapa mapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if(!this.esDeMiPropiedad(unaBarraca)){
	
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaUnidad(unaBarraca,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
		
			throw new CantidadDeSuministroInsuficienteException();
		
		}
		this.agregarUnidad(nuevaUnidad);
		
		return nuevaUnidad;
		
	}
	
	public Unidad crearGoliat(Fabrica unaFabrica, Mapa mapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if(!this.esDeMiPropiedad(unaFabrica)){
		
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaUnidad(unaFabrica,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
		
			throw new CantidadDeSuministroInsuficienteException();
		
		}
		this.agregarUnidad(nuevaUnidad);
		
		return nuevaUnidad;
		
	}
	
	public Unidad crearEspectro(PuertoEstelar puerto, Mapa mapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if(!this.esDeMiPropiedad(puerto)){
		
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevoEspectro(puerto,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
		
			throw new CantidadDeSuministroInsuficienteException();
		
		}
		this.agregarUnidad(nuevaUnidad);
		
		return nuevaUnidad;
		
	}
	
	public Unidad crearNaveCiencia(PuertoEstelar puerto, Mapa mapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if(!this.esDeMiPropiedad(puerto)){
		
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaNaveCiencia(puerto,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
		
			throw new CantidadDeSuministroInsuficienteException();
		
		}
		this.agregarUnidad(nuevaUnidad);
		
		return nuevaUnidad;
		
	}

	public Unidad crearNaveTransporte(PuertoEstelar puerto, Mapa mapa) throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		if(!this.esDeMiPropiedad(puerto)){
		
			throw new SeleccionadoNoEsPropiedadDelJugadorException();
		
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaNaveTransporte(puerto,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
		
			throw new CantidadDeSuministroInsuficienteException();
		
		}
		this.agregarUnidad(nuevaUnidad);
		
		return nuevaUnidad;
		
	}
	
	private void agregarEdificio(Edificio nuevoEdificio){
		
		this.edificios.add(nuevoEdificio);
		
	}
	
	private void agregarUnidad(Unidad nuevaUnidad){
		
		this.unidades.add(nuevaUnidad);
		
	}

	public boolean esDeMiPropiedad(Ubicable ubicable) {
		
		return ubicable.pertenezcoAEstaRaza(this);
		
	}

	public boolean posee(Edificio edificio){
		
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		
		return iter.elementoPertenece(edificio);
		
	}

	public boolean posee(Unidad unidad){
		
		IteradorUnidades iter = new IteradorUnidades(this.unidades);
		
		return iter.elementoPertenece(unidad);
		
	}
	
	private int poblacionActual() {
		
		int poblacionOcupada = 0;
		for(int posicionActual = 0; posicionActual < this.unidades.size(); posicionActual++){
			poblacionOcupada += this.unidades.get(posicionActual).getPoblacionOcupada();
		}
		
		return poblacionOcupada;
		
	}

	public void pasarTiempo() {
		
		IteradorEdificios iterEdificios = new IteradorEdificios(this.edificios);
		this.poblacionMaxima = iterEdificios.cuantosHayCreadosDe(DepositoDeSuministros.class)*DepositoDeSuministros.getIncrementoPoblacion();
		extraerEdificiosMuertos();
		extraerUnidadesMuertas();
		
	}
	
	private void extraerUnidadesMuertas(){
		
		for(int posicionActual = 0; posicionActual < this.unidades.size(); posicionActual++){
			
			Unidad unidadActual = this.unidades.get(posicionActual);
			if(!unidadActual.estaVivo()){
		
				this.unidades.remove(unidadActual);
			
			}
			else unidadActual.pasarTiempo();
		}
		
	}
	
	private void extraerEdificiosMuertos(){
		
		for(int posicionActual = 0; posicionActual < this.edificios.size(); posicionActual++){
		
			Edificio edificioActual = this.edificios.get(posicionActual);
			if(!edificioActual.estaVivo()){
			
				this.edificios.remove(edificioActual);
			
			}
			else edificioActual.pasarTiempo();
		}
				
	}

	public void juntarMinerales(int cantidad){
		
		this.minerales += cantidad;
		
	}
	
	public void juntarGas(int cantidad){
		
		this.gas += cantidad;
		
	}
	
	public void quitarMinerales(int cantidad) {
		
		if (cantidad > this.minerales){
	
			throw new RecursosInsuficientesException();
		}
		else{
			
			this.minerales -= cantidad;
		
		}
		
	}
	
	public void quitarGas(int cantidad) {
		
		if (cantidad > this.gas){
		
			throw new RecursosInsuficientesException();
		
		}
		else{
		
			this.gas -= cantidad;
		
		}
		
	}
	
	public int getMinerales(){
		
		return this.minerales;
		
	}
	
	public int getGasVespeno(){
		
		return this.gas;
		
	}
	
	public ArrayList<Edificio> edificios(){
		
		return this.edificios;
		
	}
	
	public ArrayList<Unidad> unidades(){
		
		return this.unidades;
		
	}
	
}
