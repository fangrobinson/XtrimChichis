package xtremecraft.edificios;

import xtremecraft.mapa.NoHayRecursoException;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public class RecolectorDeGasVespeno extends Recolector {
	
	private static int tiempoDeConstruccion = 6;
	private int minerales = 100;
	
	private RecolectorDeGasVespeno(Terran raza, Terreno terreno){
		
		super(terreno);
		this.cobrar(raza);
		this.recurso = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion; 
		
	}
	
	//TODO: si el constructor ya recibe un terreno... por que esta este metodo???
	public static RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno(Terran raza, Terreno unTerreno){
		
		RecolectorDeGasVespeno nuevoRecolector = new RecolectorDeGasVespeno(raza, unTerreno);
		if( ( !unTerreno.tieneRecursos() ) || ( !unTerreno.getRecurso().puedeSerExtraidoPor(nuevoRecolector) ) ){
			throw new NoHayRecursoException();
		}	
		return nuevoRecolector;
		
	}

	
	public boolean puedeExtraerGasVespeno(){
		
		return true;
		
	}
		
	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
	}
	
}
