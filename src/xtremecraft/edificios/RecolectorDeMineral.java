package xtremecraft.edificios;

import xtremecraft.mapa.NoHayRecursoException;
import xtremecraft.mapa.Terreno;

public class RecolectorDeMineral extends Recolector{

	private static int tiempoDeConstruccion = 4;

	private RecolectorDeMineral(Terreno terreno) {
		
		super(terreno);
		this.recurso = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion;
	
	}
	
	public static RecolectorDeMineral nuevoRecolectorDeMineral(Terreno unTerreno){
		
		RecolectorDeMineral nuevoRecolector = new RecolectorDeMineral(unTerreno);
		if( ( !unTerreno.tieneRecursos() ) || ( !unTerreno.getRecurso().puedeSerExtraidoPor(nuevoRecolector) ) ){
			//TODO: crear excepcion especifica para este caso:
			throw new NoHayRecursoException();
		}	
		return nuevoRecolector;
		
	}

	public boolean puedeExtraerMineral(){
		
		return true;
		
	}

}
