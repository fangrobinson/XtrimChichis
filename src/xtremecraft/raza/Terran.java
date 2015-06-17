package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.ConstructorDeEdificios;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Unidad;


public class Terran{
	
	private ArrayList<Unidad> unidades;
	private ArrayList<Edificio> edificios;
	private boolean estaViva = true;

	public Terran(){
		
		this.estaViva = true;
		this.unidades = new ArrayList<Unidad>();
		this.edificios = new ArrayList<Edificio>();

					
	}

	public boolean estaViva() {
		
		return this.estaViva;
		
	}
	
	
	public void agregarEdificio(Edificio nuevoEdificio){
		
		this.edificios.add(nuevoEdificio);
		
	}
	
	public void crearBarraca(Terreno unTerreno){
		
		ConstructorDeEdificios.nuevoEdificio("Barraca",this,unTerreno);
		
	}
	
	public void crearFabrica(Terreno unTerreno){
		
		ConstructorDeEdificios.nuevoEdificio("Fabrica",this,unTerreno);
		
	}
	
	public void crearPuertoEstelar(Terreno unTerreno){
		
		ConstructorDeEdificios.nuevoEdificio("Puerto Estelar",this,unTerreno);
		
	}
	
	public void crearRecolectorDeMineral(Terreno unTerreno){
		
		ConstructorDeEdificios.nuevoEdificio("Recolector de Mineral",this,unTerreno);
		
	}
	
	public void crearRecolectorDeGasVespeno(Terreno unTerreno){
		
		ConstructorDeEdificios.nuevoEdificio("Recolector de Gas Vespeno",this,unTerreno);
		
	}
	
	public void crearDepositoDeSuministros(Terreno unTerreno){
		
		ConstructorDeEdificios.nuevoEdificio("Deposito de Suministros",this,unTerreno);
		
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


}