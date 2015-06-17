package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.ConstructorDeEdificios;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.ConstructorDeUnidades;
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
		
		return ConstructorDeEdificios.nuevoEdificio("Barraca",this,unTerreno);
		
	}
	
	public Edificio crearFabrica(Terreno unTerreno){
		
		return ConstructorDeEdificios.nuevoEdificio("Fabrica",this,unTerreno);
		
	}
	
	public Edificio crearPuertoEstelar(Terreno unTerreno){
		
		return ConstructorDeEdificios.nuevoEdificio("Puerto Estelar",this,unTerreno);
		
	}
	
	public Edificio crearRecolectorDeMineral(Terreno unTerreno){
		
		return ConstructorDeEdificios.nuevoEdificio("Recolector de Mineral",this,unTerreno);
		
	}
	
	public Edificio crearRecolectorDeGasVespeno(Terreno unTerreno){
		
		return ConstructorDeEdificios.nuevoEdificio("Recolector de Gas Vespeno",this,unTerreno);
		
	}
	
	public Edificio crearDepositoDeSuministros(Terreno unTerreno){
		
		return ConstructorDeEdificios.nuevoEdificio("Deposito de Suministros",this,unTerreno);
		
	}
	
	public Unidad crearMarine(Edificio unEdificio, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaUnidad("Marine",this,unEdificio,mapa);
		
	}
	
	public Unidad crearGoliat(Edificio unEdificio, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaUnidad("Goliat",this,unEdificio,mapa);
		
	}
	
	public Unidad crearEspectro(Edificio unEdificio, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaUnidad("Espectro",this,unEdificio,mapa);
		
	}
	
	public Unidad crearNaveCiencia(Edificio unEdificio, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaUnidad("Nave Ciencia",this,unEdificio,mapa);
		
	}
	
	public Unidad crearNaveTransporte(Edificio unEdificio, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaUnidad("Nave Transporte",this,unEdificio,mapa);
		
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
	
	/*public void ActualizarEdificios(){
		
		
		
	}*/

}