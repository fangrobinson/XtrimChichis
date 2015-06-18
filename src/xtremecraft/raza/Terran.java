package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.ConstructorDeEdificios;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.ConstructorDeUnidades;
import xtremecraft.unidades.Ubicable;
import xtremecraft.unidades.Unidad;


public class Terran{
	
	private ArrayList<Unidad> unidades;
	private ArrayList<Edificio> edificios;
	private boolean estaViva;

	public Terran(){

		this.unidades = new ArrayList<Unidad>();
		this.edificios = new ArrayList<Edificio>();
		this.estaViva = true;
					
	}

	public boolean estaViva(){
		
		return this.estaViva;
		
	}
	
	public Edificio crearBarraca(Terreno unTerreno){
		
		Edificio nuevoEdificio = ConstructorDeEdificios.nuevoEdificio("Barraca",unTerreno);
		this.agregarEdificio(nuevoEdificio);
		return nuevoEdificio;
		
	}
	
	public Edificio crearFabrica(Terreno unTerreno){
		
		if(this.tieneBarracas()){
			Edificio nuevoEdificio = ConstructorDeEdificios.nuevoEdificio("Fabrica",unTerreno);
			this.agregarEdificio(nuevoEdificio);
			return nuevoEdificio;
		}else throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
	}
	
	public Edificio crearPuertoEstelar(Terreno unTerreno){
		
		if(this.tieneFabricas()){
			Edificio nuevoEdificio = ConstructorDeEdificios.nuevoEdificio("Puerto Estelar",unTerreno);
			this.agregarEdificio(nuevoEdificio);
			return nuevoEdificio;
		}else{
			throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
		}
	
	}
	
	public Edificio crearRecolectorDeMineral(Terreno unTerreno){
		
		Edificio nuevoEdificio = ConstructorDeEdificios.nuevoEdificio("Recolector de Mineral",unTerreno);
		this.agregarEdificio(nuevoEdificio);
		return nuevoEdificio;
		
	}
	
	public Edificio crearRecolectorDeGasVespeno(Terreno unTerreno){
		
		Edificio nuevoEdificio = ConstructorDeEdificios.nuevoEdificio("Recolector de Gas Vespeno",unTerreno);
		this.agregarEdificio(nuevoEdificio);
		return nuevoEdificio;
		
	}
	
	public Edificio crearDepositoDeSuministros(Terreno unTerreno){
		
		Edificio nuevoEdificio = ConstructorDeEdificios.nuevoEdificio("Deposito de Suministros",unTerreno);
		this.agregarEdificio(nuevoEdificio);
		return nuevoEdificio;
		
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
	
	public boolean tieneBarracas() {
		
		IteradorConocidos iter = new IteradorConocidos(this.edificios);
		Terreno terreno = new Tierra(1,1);
		Barraca barraca = new Barraca(terreno);
		return iter.arregloTiene(barraca);
		
		
	}

	public boolean tieneFabricas() {
		
		IteradorConocidos iter = new IteradorConocidos(this.edificios);
		Terreno terreno = new Tierra(1,1);
		Fabrica barraca = new Fabrica(terreno);
		return iter.arregloTiene(barraca);
		
	}

	public boolean esDeMiPropiedad(Ubicable ubicable) {
		return ubicable.pertenezcoAEstaRaza(this);
	}

	public boolean posee(Edificio edif){
		IteradorConocidos iter = new IteradorConocidos(this.edificios);
		return iter.arregloPosee(edif);
	}

	public boolean posee(Unidad unid){
		IteradorConocidos iter = new IteradorConocidos(this.unidades);
		return iter.arregloPosee(unid);
	}
	
}