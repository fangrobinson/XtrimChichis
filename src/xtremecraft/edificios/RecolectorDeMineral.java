package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;

public class RecolectorDeMineral extends Recolector{

	private static int tiempoDeConstruccion = 4;
	private int minerales = 50;

	private RecolectorDeMineral(Jugador jugador, Terreno terreno) {
		
		super(jugador,terreno);
		this.cobrar();
		this.recurso = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion;
	
	}
	
	public static RecolectorDeMineral nuevoRecolectorDeMineral(Jugador jugador, Terreno unTerreno){
		
		RecolectorDeMineral nuevoRecolector = new RecolectorDeMineral(jugador, unTerreno);
		if( ( !unTerreno.tieneRecursos() ) || ( !unTerreno.getRecurso().puedeSerExtraidoPor(nuevoRecolector) ) ){
			throw new NoHayGasVespenoException();
		}	
		return nuevoRecolector;
		
	}

	public boolean puedeExtraerMineral(){
		
		return true;
		
	}
	
	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		
	}
	
	public void pasarTiempo(){
		if (!super.estaEnConstruccion){
			this.jugador.nacion().juntarMinerales(this.recurso.explotar(this.aumentoDeReservaEnTurno));
		}
		super.pasarTiempo();
	}
	

}
