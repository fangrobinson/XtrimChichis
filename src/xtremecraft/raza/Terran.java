package xtremecraft.raza;
import xtremecraft.edificios.Recolector;

public class Terran extends Raza {
	
	private boolean estaVivo;
	private static Recolector centroDeMineral;
	
	public Terran(){
		
		this.estaVivo=true;
		
	}

	public boolean estaVivo() {
		
		return estaVivo;
		
	}

	public static Recolector getCentroDeMineral() {
		
		return centroDeMineral;
		
	}

	

}
