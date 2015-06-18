package xtremecraft.edificios;

import xtremecraft.mapa.NoHayRecursoException;
import xtremecraft.mapa.Terreno;

public class RecolectorDeGasVespeno extends Recolector {
	
	private static int tiempoDeConstruccion = 6;
	
	private RecolectorDeGasVespeno(Terreno terreno){
		
		super(terreno);
		this.recurso = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion; 
		
	}
	
	public static RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno(Terreno unTerreno){
		
		RecolectorDeGasVespeno nuevoRecolector = new RecolectorDeGasVespeno(unTerreno);
		if( ( !unTerreno.tieneRecursos() ) || ( !unTerreno.getRecurso().puedeSerExtraidoPor(nuevoRecolector) ) ){
			throw new NoHayRecursoException();
		}	
		return nuevoRecolector;
		
	}

	
	public boolean puedeExtraerGasVespeno(){
		
		return true;
		
	}
		
}
