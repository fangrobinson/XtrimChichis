package xtremecraft.raza;

import java.util.ArrayList;

<<<<<<< HEAD
import xtremecraft.edificios.ConstructorDeEdificios;
import xtremecraft.edificios.Edificio;
import xtremecraft.mapa.Terreno;
=======
import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
>>>>>>> 4fcbbd34c00ba230a96b3b96fc7785b56e08ce35
import xtremecraft.unidades.Unidad;


public class Terran{
	
	private ArrayList<Unidad> unidades;
<<<<<<< HEAD
	private ArrayList<Edificio> edificios;
	private boolean estaViva;

	public Terran(Terreno terreno){
=======
	private ArrayList<RecolectorDeMineral> recolectoresDeMineral;
	private ArrayList<DepositoDeSuministros> depositosDeSuministros;
	private ArrayList<RecolectorDeGasVespeno> recolectoresDeGasVespeno;
	private ArrayList<Barraca> barracas;
	private ArrayList<Fabrica> fabricas;
	private ArrayList<PuertoEstelar> puertosEstelares;
		
	public Terran(){
>>>>>>> 4fcbbd34c00ba230a96b3b96fc7785b56e08ce35
		
		this.estaViva = true;
		this.unidades = new ArrayList<Unidad>();
<<<<<<< HEAD
		this.edificios = new ArrayList<Edificio>();
=======
		this.recolectoresDeMineral = new ArrayList<RecolectorDeMineral>();
		this.recolectoresDeGasVespeno = new ArrayList<RecolectorDeGasVespeno>();
		this.barracas = new ArrayList<Barraca>();
		this.fabricas = new ArrayList<Fabrica>();
		this.puertosEstelares = new ArrayList<PuertoEstelar>();
		this.depositosDeSuministros = new ArrayList<DepositoDeSuministros>();
		
		
					
	}

	public boolean estaViva() {
		
		return (this.depositosDeSuministros.size() != 0);
>>>>>>> 4fcbbd34c00ba230a96b3b96fc7785b56e08ce35
		
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
		//ARREGLAR CON LO NUEVO DEL ITERADOR
		return true;
		
	}

	public boolean tieneFabricas() {
		//ARREGLAR CON LO NUEVO DEL ITERADOR
		return true;
		
	}


	public boolean estaViva() {
		
		return this.estaViva;
		
	}

	

}