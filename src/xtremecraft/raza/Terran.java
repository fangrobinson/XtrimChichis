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
	
	public Unidad crearMarine(Barraca unaBarraca, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaUnidad(this,unaBarraca,mapa);
		
	}
	
	public Unidad crearGoliat(Fabrica unaFabrica, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaUnidad(this,unaFabrica,mapa);
		
	}
	
	public Unidad crearEspectro(PuertoEstelar puerto, Mapa mapa){
		
		return ConstructorDeUnidades.nuevoEspectro(this,puerto,mapa);
		
	}
	
	public Unidad crearNaveCiencia(PuertoEstelar puerto, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaNaveCiencia(this,puerto,mapa);
		
	}
	
	public Unidad crearNaveTransporte(PuertoEstelar puerto, Mapa mapa){
		
		return ConstructorDeUnidades.nuevaNaveTransporte(this,puerto,mapa);
		
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