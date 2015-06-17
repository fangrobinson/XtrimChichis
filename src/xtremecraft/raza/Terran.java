package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.Recolector;
import xtremecraft.unidades.Unidad;


public class Terran{
	
	private boolean estaViva;
	private ArrayList<Unidad> unidades;
	private ArrayList<Recolector> recolectores;
	private ArrayList<Barraca> barracas;
	private ArrayList<Fabrica> fabricas;
	private ArrayList<PuertoEstelar> puertosEstelares;
	private ArrayList<DepositoDeSuministros> depositosDeSuministros;
		
	public Terran(){
		
		this.estaViva=true;
		this.unidades = new ArrayList<Unidad>();
		this.recolectores = new ArrayList<Recolector>();
		this.barracas = new ArrayList<Barraca>();
		this.fabricas = new ArrayList<Fabrica>();
		this.puertosEstelares = new ArrayList<PuertoEstelar>();
					
	}

	public boolean estaViva() {
		
		return this.estaViva;
		
	}
	
	public void agregarEdificioRecolector(Recolector edificioRecolector) {
		
		this.recolectores.add(edificioRecolector);
		
	}
	
	public void agregarUnidad(Unidad unidad) {
		
		this.unidades.add(unidad);
		
	}	
	
	public ArrayList<Unidad> getListaDeUnidadesCreadas(){
		
		return this.unidades;
		
	}

	
	public void agregarBarraca(Barraca edificioCreadorDeUnidades) {
		
		this.barracas.add(edificioCreadorDeUnidades);
		
	}

	public void agregarFabrica(Fabrica fabrica) {
		
		this.fabricas.add(fabrica);
		
	}
	
	
	public void agregarPuertoEstelar(PuertoEstelar unPuertoEstelar) {
		
		this.puertosEstelares.add(unPuertoEstelar);
		
	}
	

	public void agregarDepositoDeSuministros(DepositoDeSuministros unDeposito) {
		
		this.depositosDeSuministros.add(unDeposito);
		
	}
	
	public boolean tieneBarracas() {
		
		return !this.barracas.isEmpty();
		
	}

	public boolean tieneFabricas() {
		
		return !this.fabricas.isEmpty();
		
	}

	

}