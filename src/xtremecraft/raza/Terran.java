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
import xtremecraft.sistema.Actualizable;
import xtremecraft.unidades.ConstructorDeUnidades;
import xtremecraft.unidades.Ubicable;
import xtremecraft.unidades.UbicacionNoValidaException;
import xtremecraft.unidades.Unidad;


public class Terran implements Actualizable{
	
	private ArrayList<Unidad> unidades;
	private ArrayList<Edificio> edificios;
	private boolean estaViva;
	private int poblacionMaxima;
	private int poblacionInicial = 5;

	public Terran(){
		
		this.poblacionMaxima = poblacionInicial;
		this.unidades = new ArrayList<Unidad>();
		this.edificios = new ArrayList<Edificio>();
		this.estaViva = true;
					
	}

	public boolean estaViva(){
		
		return this.estaViva;
		
	}
	
	public int getPoblacionMaxima(){
		
		return this.poblacionMaxima;
		
	}
	
	//TODO: refactoring codigo repetido
	public Barraca crearBarraca(Terreno unTerreno){
		
		Barraca nuevaBarraca = new Barraca(unTerreno);
		if(!unTerreno.ubicar(nuevaBarraca)) throw new UbicacionNoValidaException();
		this.agregarEdificio(nuevaBarraca);
		return nuevaBarraca;
		
	}
	
	public Fabrica crearFabrica(Terreno unTerreno){
		
		//TODO: pasar validacion al edificio pasando por parametro una barraca
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		Barraca unaBarraca = iter.getBarraca();
		Fabrica nuevaFabrica = new Fabrica(unaBarraca,unTerreno);
		if(!unTerreno.ubicar(nuevaFabrica)) throw new UbicacionNoValidaException();
			this.agregarEdificio(nuevaFabrica);
			return nuevaFabrica;
			
	}
		


	
	public PuertoEstelar crearPuertoEstelar(Terreno unTerreno){
		
		//TODO: pasar validacion al edificio pasando por parametro una fabrica
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		Fabrica unaFabrica = iter.getFabrica();
		PuertoEstelar nuevoPuerto = new PuertoEstelar(unaFabrica,unTerreno);
		if(!unTerreno.ubicar(nuevoPuerto)) throw new UbicacionNoValidaException();
		this.agregarEdificio(nuevoPuerto);
		return nuevoPuerto;
		
	
	}
	
	public RecolectorDeMineral crearRecolectorDeMineral(Terreno unTerreno){
		
		RecolectorDeMineral nuevoCentroMineral = RecolectorDeMineral.nuevoRecolectorDeMineral(unTerreno);
		if(!unTerreno.ubicar(nuevoCentroMineral)) throw new UbicacionNoValidaException();
		this.agregarEdificio(nuevoCentroMineral);
		return nuevoCentroMineral;
		
	}
	
	public RecolectorDeGasVespeno crearRecolectorDeGasVespeno(Terreno unTerreno){
		
		RecolectorDeGasVespeno nuevaRefineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(unTerreno);
		if(!unTerreno.ubicar(nuevaRefineria)) throw new UbicacionNoValidaException();
		this.agregarEdificio(nuevaRefineria);
		return nuevaRefineria;
		
	}
	
	public DepositoDeSuministros crearDepositoDeSuministros(Terreno unTerreno){
		
		DepositoDeSuministros nuevoDeposito = new DepositoDeSuministros(unTerreno);
		if(!unTerreno.ubicar(nuevoDeposito)) throw new UbicacionNoValidaException();
		this.agregarEdificio(nuevoDeposito);
		return nuevoDeposito;
		
	}
	
	public Unidad crearMarine(Barraca unaBarraca, Mapa mapa){
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaUnidad(unaBarraca,mapa);
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public Unidad crearGoliat(Fabrica unaFabrica, Mapa mapa){
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaUnidad(unaFabrica,mapa);
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public Unidad crearEspectro(PuertoEstelar puerto, Mapa mapa){
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevoEspectro(puerto,mapa);
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public Unidad crearNaveCiencia(PuertoEstelar puerto, Mapa mapa){
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaNaveCiencia(puerto,mapa);
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public Unidad crearNaveTransporte(PuertoEstelar puerto, Mapa mapa){
		
		Unidad nuevaUnidad = ConstructorDeUnidades.nuevaNaveTransporte(puerto,mapa);
		this.agregarUnidad(nuevaUnidad);
		return nuevaUnidad;
		
	}
	
	public void agregarEdificio(Edificio nuevoEdificio){
		
		this.edificios.add(nuevoEdificio);
		
	}
	
	public void agregarUnidad(Unidad nuevaUnidad){
		
		this.unidades.add(nuevaUnidad);
		
	}

	public boolean esDeMiPropiedad(Ubicable ubicable) {
		
		return ubicable.pertenezcoAEstaRaza(this);
		
	}

	public boolean posee(Edificio edificio){
		
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		return iter.arregloPosee(edificio);
		
	}

	public boolean posee(Unidad unidad){
		
		IteradorUnidades iter = new IteradorUnidades(this.unidades);
		return iter.arregloPosee(unidad);
		
	}

	public void pasarTiempo() {
		
		IteradorEdificios iter = new IteradorEdificios(this.edificios);
		DepositoDeSuministros deposito = new DepositoDeSuministros(new Tierra(1,2));
		this.poblacionMaxima = poblacionInicial + iter.cuantosHayConstruidos(deposito)*DepositoDeSuministros.getIncrementoPoblacion();
		
		
	}
	
}