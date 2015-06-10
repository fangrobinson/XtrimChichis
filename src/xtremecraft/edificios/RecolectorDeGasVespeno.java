package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.VolcanGasVespeno;

public class RecolectorDeGasVespeno extends Recolector {
	
	private VolcanGasVespeno volcan;
	private static int tiempoDeConstruccion = 6;
	
	protected RecolectorDeGasVespeno(VolcanGasVespeno unVolcanDeGasVespeno, Terreno terreno){
		
		super(terreno);
		this.volcan = unVolcanDeGasVespeno;
		this.tiempoConstruccion = tiempoDeConstruccion; 
		
	}

	public void aumentarReservasEnTurno(){
		
		this.reservas += this.volcan.explotar(this.aumentoDeReservaEnTurno);
		
	}

	
	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}


	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}

	public static RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno(Terran razaTerran, VolcanGasVespeno volcan, Terreno terreno) {
		
		RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno = new RecolectorDeGasVespeno(volcan,terreno);
		razaTerran.agregarEdificioRecolectorDeGasVespeno(nuevoRecolectorDeGasVespeno);
		return nuevoRecolectorDeGasVespeno;
		
	}
		
}
