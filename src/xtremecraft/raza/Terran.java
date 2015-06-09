package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.unidades.Unidad;


public class Terran{
	
	private ArrayList<Unidad> unidades;
	private ArrayList<RecolectorDeMineral> recolectoresDeMineral;
	private ArrayList<RecolectorDeGasVespeno> recolectoresDeGasVespeno;
	private ArrayList<Barraca> barracas;
	private boolean estaViva;
		
	public Terran(){
		
		this.estaViva=true;
		this.unidades = new ArrayList<Unidad>();
		this.recolectoresDeMineral = new ArrayList<RecolectorDeMineral>();
		this.recolectoresDeGasVespeno = new ArrayList<RecolectorDeGasVespeno>();
		this.barracas = new ArrayList<Barraca>();
					
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
	

}
