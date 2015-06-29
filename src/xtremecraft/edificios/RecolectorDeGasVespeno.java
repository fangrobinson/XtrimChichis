package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;

public class RecolectorDeGasVespeno extends Recolector {
	
	private static int tiempoDeConstruccion = 6;
	private int minerales = 100;
	
	private RecolectorDeGasVespeno(Jugador jugador, Terreno terreno){
		
		super(jugador, terreno);
		this.cobrar();
		this.recurso = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion; 
		
	}
	
	public static RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno(Jugador jugador, Terreno unTerreno){
		
		RecolectorDeGasVespeno nuevoRecolector = new RecolectorDeGasVespeno(jugador, unTerreno);
		if( ( !unTerreno.tieneRecursos() ) || ( !unTerreno.getRecurso().puedeSerExtraidoPor(nuevoRecolector) ) ){
			throw new NoHayGasVespenoException();
		}	
		return nuevoRecolector;
		
	}

	
	public boolean puedeExtraerGasVespeno(){
		
		return true;
		
	}
		
	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		
	}
	
	public void pasarTiempo(){
		
		if (!super.estaEnConstruccion){
			this.jugador.nacion().juntarGas(this.recurso.explotar(this.aumentoDeReservaEnTurno));
		}
		super.pasarTiempo();
		
	}
	
}
