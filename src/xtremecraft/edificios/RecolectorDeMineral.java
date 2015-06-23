package xtremecraft.edificios;

import xtremecraft.mapa.NoHayRecursoException;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public class RecolectorDeMineral extends Recolector{

	private static int tiempoDeConstruccion = 4;
	private int minerales = 50;

	private RecolectorDeMineral(Terran raza, Terreno terreno) {
		
		super(terreno);
		this.raza = raza;
		this.cobrar(raza);
		this.recurso = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion;
	
	}
	//TODO: si el constructor ya recibe un terreno... por que esta este metodo???
	//PARA VALIDAR QUE ESTAS CONSTRUYENDO UN RECOLECTOR DE GAS VESPENO SOBRE GAS VESPENO. 
	//FIJATE QUE HACE DOUBLE DISPATCH. NO LO BORRES. FIRMA EUGE.
	public static RecolectorDeMineral nuevoRecolectorDeMineral(Terran raza, Terreno unTerreno){
		
		RecolectorDeMineral nuevoRecolector = new RecolectorDeMineral(raza, unTerreno);
		if( ( !unTerreno.tieneRecursos() ) || ( !unTerreno.getRecurso().puedeSerExtraidoPor(nuevoRecolector) ) ){
			//TODO: crear excepcion especifica para este caso:
			throw new NoHayRecursoException();
		}	
		return nuevoRecolector;
		
	}

	public boolean puedeExtraerMineral(){
		
		return true;
		
	}
	
	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
	}
	
	public void pasarTiempo(){
		if (!super.estaEnConstruccion){
			this.raza.juntarMinerales(this.recurso.explotar(this.aumentoDeReservaEnTurno));
		}
		super.pasarTiempo();
	}
	

}
