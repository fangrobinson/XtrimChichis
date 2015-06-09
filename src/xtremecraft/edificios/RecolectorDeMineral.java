package xtremecraft.edificios;

import xtremecraft.recursos.MinaDeMinerales;

public class RecolectorDeMineral extends Recolector{

	private MinaDeMinerales minaDeMinerales;

	protected RecolectorDeMineral(MinaDeMinerales unaMinaDeMinerales, int fila, int columna) {
		
		super(fila, columna);
		this.minaDeMinerales = unaMinaDeMinerales;
	
	}
	
	public void aumentarReservasEnTurno(){
		
		this.reservas += this.minaDeMinerales.explotar(this.aumentoDeReservaEnTurno);
		
	}

	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}

	
	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}
	

}
