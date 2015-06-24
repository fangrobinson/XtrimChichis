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
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Actualizable;
import xtremecraft.unidades.ConstructorDeUnidades;
import xtremecraft.unidades.Ubicable;
//import xtremecraft.unidades.UbicacionNoValidaException;
import xtremecraft.unidades.Unidad;


public class Terran implements Actualizable{

	private ArrayList<Unidad> unidades;
	private ArrayList<Edificio> edificios;
    	private int poblacionMaxima;
    	private int minerales;
    	private int gas;

	
	public Terran(Tierra terreno){
		
		this.unidades = new ArrayList<Unidad>();
		this.edificios = new ArrayList<Edificio>();
		this.poblacionMaxima = 5;
		this.minerales = 200; 
		this.gas = 200;


		DepositoDeSuministros baseInicial = crearDepositoDeSuministros(terreno);
		for(int tiempo=0;tiempo<baseInicial.tiempoConstruccion();tiempo++){
			baseInicial.pasarTiempo();
		}
		
	}

	public boolean estaViva(){
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		return (iter.tieneCreados(DepositoDeSuministros.class));
		
		
	}
	
	public int getPoblacionMaxima(){
		
		return this.poblacionMaxima;
		
	}
	
	//TODO: refactoring codigo repetido
	public Barraca crearBarraca(Terreno unTerreno){
		
		Barraca nuevaBarraca = new Barraca(this, unTerreno);
		unTerreno.ubicar(nuevaBarraca);
		/*
		if(!unTerreno.ubicar(nuevaBarraca)){
			throw new UbicacionNoValidaException();
		}*/
		this.agregarEdificio(nuevaBarraca);
		return nuevaBarraca;
		
	}
	
	public Fabrica crearFabrica(Terreno unTerreno){
		
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		Barraca unaBarraca = iter.getBarraca();
		Fabrica nuevaFabrica = new Fabrica(this, unaBarraca,unTerreno);
		unTerreno.ubicar(nuevaFabrica);
		/*
		if(!unTerreno.ubicar(nuevaFabrica)){
			throw new UbicacionNoValidaException();
		}
		*/
		this.agregarEdificio(nuevaFabrica);
		return nuevaFabrica;
			
	}
		
	public PuertoEstelar crearPuertoEstelar(Terreno unTerreno){
		
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		Fabrica unaFabrica = iter.getFabrica();
		PuertoEstelar nuevoPuerto = new PuertoEstelar(this,unaFabrica,unTerreno);
		unTerreno.ubicar(nuevoPuerto);
		/*if(!unTerreno.ubicar(nuevoPuerto)){
			throw new UbicacionNoValidaException();
		}*/
		this.agregarEdificio(nuevoPuerto);
		return nuevoPuerto;
	
	}
	
	public RecolectorDeMineral crearRecolectorDeMineral(Terreno unTerreno){
		
		RecolectorDeMineral nuevoCentroMineral = RecolectorDeMineral.nuevoRecolectorDeMineral(this,unTerreno);
		unTerreno.ubicar(nuevoCentroMineral);
		/*
		if(!unTerreno.ubicar(nuevoCentroMineral)){
			throw new UbicacionNoValidaException();
		}*/
		this.agregarEdificio(nuevoCentroMineral);
		return nuevoCentroMineral;
		
	}
	
	public RecolectorDeGasVespeno crearRecolectorDeGasVespeno(Terreno unTerreno){
		
		RecolectorDeGasVespeno nuevaRefineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(this, unTerreno);
		unTerreno.ubicar(nuevaRefineria);
		/*
		if(!unTerreno.ubicar(nuevaRefineria)){
			throw new UbicacionNoValidaException();
		}*/
		this.agregarEdificio(nuevaRefineria);
		return nuevaRefineria;
		
	}
	
	public DepositoDeSuministros crearDepositoDeSuministros(Terreno unTerreno){
		
		DepositoDeSuministros nuevoDeposito = new DepositoDeSuministros(this, unTerreno);
		unTerreno.ubicar(nuevoDeposito);
		/*
		if(!unTerreno.ubicar(nuevoDeposito)){
			throw new UbicacionNoValidaException();
		}*/
		this.agregarEdificio(nuevoDeposito);
		return nuevoDeposito;
		
	}
	
	public Unidad crearMarine(Barraca unaBarraca, Mapa mapa){
		
		if(!this.esDeMiPropiedad(unaBarraca)){
			throw new BarracaNoEsDeLaRazaException();
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaUnidad(this, unaBarraca,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
			throw new CantidadDeSuministroInsuficienteException();
		}
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public Unidad crearGoliat(Fabrica unaFabrica, Mapa mapa){
		
		if(!this.esDeMiPropiedad(unaFabrica)){
			throw new FabricaNoEsDeLaRazaException();
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaUnidad(this, unaFabrica,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
			throw new CantidadDeSuministroInsuficienteException();
		}
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public Unidad crearEspectro(PuertoEstelar puerto, Mapa mapa){
		
		if(!this.esDeMiPropiedad(puerto)){
			throw new PuertoEstelarNoEsDeLaRazaException();
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevoEspectro(this, puerto,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
			throw new CantidadDeSuministroInsuficienteException();
		}
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public Unidad crearNaveCiencia(PuertoEstelar puerto, Mapa mapa){
		
		if(!this.esDeMiPropiedad(puerto)){
			throw new PuertoEstelarNoEsDeLaRazaException();
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaNaveCiencia(this, puerto,mapa);
		if( this.poblacionMaxima < this.poblacionActual() + nuevaUnidad.getPoblacionOcupada() ){
			throw new CantidadDeSuministroInsuficienteException();
		}
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}

	public Unidad crearNaveTransporte(PuertoEstelar puerto, Mapa mapa){
		
		if(!this.esDeMiPropiedad(puerto)){
			throw new PuertoEstelarNoEsDeLaRazaException();
		}
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaNaveTransporte(this, puerto,mapa);
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
		//TODO: resolver que hacemos con las unidades/edificios muertos para ver como acutalizamos el mapa.
		//posible solucion guardarlos y hacer un getter para que el mapa pueda pedirlos y removerlos.
		IteradorEdificios iterEdificios = new IteradorEdificios(this.edificios);
		this.poblacionMaxima = iterEdificios.cuantosHayCreadosDe(DepositoDeSuministros.class)*DepositoDeSuministros.getIncrementoPoblacion();
		extraerEdificiosMuertos();
		extraerUnidadesMuertas();
		
	}
	
	//TODO: Refactory. Hacer merge de los dos metodos siguientes:
	
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
