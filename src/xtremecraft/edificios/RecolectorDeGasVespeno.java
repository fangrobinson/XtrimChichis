package xtremecraft.edificios;

import xtremecraft.recursos.VolcanGasVespeno;

public class RecolectorDeGasVespeno extends Recolector {
	
	private VolcanGasVespeno volcan;
	
	protected RecolectorDeGasVespeno(VolcanGasVespeno unVolcanDeGasVespeno, int fila, int columna){
		
		super(fila,columna);
		this.volcan = unVolcanDeGasVespeno;
		this.tiempoConstruccion = 6; 
		
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
		
}
