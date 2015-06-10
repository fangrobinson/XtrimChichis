package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;


public abstract class Recolector extends Edificio {
	
	protected int reservas;
	protected int aumentoDeReservaEnTurno;
	public static int vida = 100;
	
	protected Recolector(Terreno terreno){
		
		super(terreno,vida);
		this.reservas=0;
		this.aumentoDeReservaEnTurno=10;
	
	}
	
	public int getReservas() {
		
		return reservas;
		
	}

	public abstract void aumentarReservasEnTurno();
	
	
	public static RecolectorDeMineral nuevoRecolectorDeMineral(Terran razaTerran,MinaDeMinerales nodoMineral, Terreno terreno){
		
		RecolectorDeMineral nuevoRecolectorDeMineral= new RecolectorDeMineral(nodoMineral,terreno);
		razaTerran.agregarEdificioRecolectorDeMineral(nuevoRecolectorDeMineral);
		return nuevoRecolectorDeMineral;
		
	}

	public static RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno(Terran razaTerran, VolcanGasVespeno volcan, Terreno terreno) {
		
		RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno = new RecolectorDeGasVespeno(volcan,terreno);
		razaTerran.agregarEdificioRecolectorDeGasVespeno(nuevoRecolectorDeGasVespeno);
		return nuevoRecolectorDeGasVespeno;
		
	}
	

}
