package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public class RecolectorDeGasVespeno extends Recolector {
	
	private static int tiempoDeConstruccion = 6;
	private int minerales = 100;
	
	private RecolectorDeGasVespeno(Terran raza, Terreno terreno){
		
		super(terreno);
		this.raza = raza;
		this.cobrar(raza);
		this.recurso = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion; 
		
	}
	
	//si el constructor ya recibe un terreno... por que esta este metodo???
	//PARA VALIDAR QUE ESTAS CONSTRUYENDO UN RECOLECTOR DE GAS VESPENO SOBRE GAS VESPENO. 
	//FIJATE QUE HACE DOUBLE DISPATCH. NO LO BORRES. FIRMA EUGE.
	public static RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno(Terran raza, Terreno unTerreno){
		
		RecolectorDeGasVespeno nuevoRecolector = new RecolectorDeGasVespeno(raza, unTerreno);
		if( ( !unTerreno.tieneRecursos() ) || ( !unTerreno.getRecurso().puedeSerExtraidoPor(nuevoRecolector) ) ){
			throw new NoHayGasVespenoException();
		}	
		return nuevoRecolector;
		
	}

	
	public boolean puedeExtraerGasVespeno(){
		
		return true;
		
	}
		
	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
	}
	
	public void pasarTiempo(){
		if (!super.estaEnConstruccion){
			this.raza.juntarGas(this.recurso.explotar(this.aumentoDeReservaEnTurno));
		}
		super.pasarTiempo();
	}
	
}
