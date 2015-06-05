package xtremecraft.edificios;

import xtremecraft.edificios.Recolector;
import xtremecraft.recursos.MinaDeMinerales;


public class CentroDeMineral extends Recolector{

	private MinaDeMinerales minaDeMinerales;

	public CentroDeMineral(MinaDeMinerales mina, int fila, int columna) {
		
		super(fila, columna);
		this.minaDeMinerales = mina;
	
	}
	
	public int getReservas() {
		
		return reservas;
		
	}
	
	@Override
	public void aumentarReservasEnTurno(){
		
		int nuevasReservas = this.minaDeMinerales.explotar(this.aumentoDeReservaEnTurno);
		this.reservas += nuevasReservas;
		
	}
		
	
	
	

}
