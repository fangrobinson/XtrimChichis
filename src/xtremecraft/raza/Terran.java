package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.unidades.Unidad;


public class Terran{
	
	private ArrayList<Unidad> unidades;
	private ArrayList<RecolectorDeMineral> recolectoresDeMineral;
	private ArrayList<RecolectorDeGasVespeno> recolectoresDeGasVespeno;
	private ArrayList<Barraca> barracas;
	private ArrayList<Fabrica> fabricas;
	private ArrayList<PuertoEstelar> puertosEstelares;
	private boolean estaViva;
		
	public Terran(){
		
		this.estaViva=true;
		this.unidades = new ArrayList<Unidad>();
		this.recolectoresDeMineral = new ArrayList<RecolectorDeMineral>();
		this.recolectoresDeGasVespeno = new ArrayList<RecolectorDeGasVespeno>();
		this.barracas = new ArrayList<Barraca>();
		this.fabricas = new ArrayList<Fabrica>();
		this.puertosEstelares = new ArrayList<PuertoEstelar>();
					
	}

	public boolean estaViva() {
		
		return this.estaViva;
		
	}
	
	public void agregarEdificioRecolectorDeMineral(RecolectorDeMineral nuevoCentroMineral) {
		
		this.recolectoresDeMineral.add(nuevoCentroMineral);
		
	}
	
	public ArrayList<RecolectorDeMineral> getListaDeRecolectoresDeMineralConstruidos(){
		
		return this.recolectoresDeMineral;
		
	}
	
	public void agregarUnidad(Unidad unidad) {
		
		this.unidades.add(unidad);
		
	}	
	
	public ArrayList<Unidad> getListaDeUnidadesCreadas(){
		
		return this.unidades;
		
	}

	public void agregarEdificioRecolectorDeGasVespeno(RecolectorDeGasVespeno nuevaRefineriaDeGasVespeno){
		
		this.recolectoresDeGasVespeno.add(nuevaRefineriaDeGasVespeno);
			
	}

	public ArrayList<RecolectorDeGasVespeno> getListaDeRecolectoresDeGasVespenoConstruidos() {
		
		return recolectoresDeGasVespeno;
		
	}

	public void agregarBarraca(Barraca edificioCreadorDeUnidades) {
		
		this.barracas.add(edificioCreadorDeUnidades);
		
	}
	
	public ArrayList<Barraca> getListaDeBarracasConstruidas(){
		
		return this.barracas;
		
	}
	
	public void agregarFabrica(Fabrica fabrica) {
		
		this.fabricas.add(fabrica);
		
	}
	
	public ArrayList<Fabrica> getListaDeFabricasConstruidas(){
		
		return this.fabricas;
		
	}
	
	public void agregarPuertoEstelar(PuertoEstelar unPuertoEstelar) {
		
		this.puertosEstelares.add(unPuertoEstelar);
		
	}
	
	public ArrayList<PuertoEstelar> getListaDePuertosEstelaresConstruidos(){
		
		return this.puertosEstelares;
		
	}

	public boolean tieneBarracas() {
		
		return !this.barracas.isEmpty();
		
	}

	public boolean tieneFabricas() {
		
		return !this.fabricas.isEmpty();
		
	}
	

}