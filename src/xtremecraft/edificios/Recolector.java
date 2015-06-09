package xtremecraft.edificios;

import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;


public abstract class Recolector extends Edificio {
	
	protected int reservas;
	protected int aumentoDeReservaEnTurno;
	//definir si la vida deberia ser un atributo de clase predeterminado para los edificios.
	public static int vida = 100;
	
	protected Recolector(int fila, int columna){
		super(fila,columna,vida);
		this.reservas=0;
		this.aumentoDeReservaEnTurno=10;
	
	}
	
	public int getReservas() {
		
		return reservas;
		
	}

	public abstract void aumentarReservasEnTurno();
	
	
	public static RecolectorDeMineral nuevoRecolectorDeMineral(Terran razaTerran,MinaDeMinerales nodoMineral, int fila, int columna){
		
		RecolectorDeMineral nuevoRecolectorDeMineral= new RecolectorDeMineral(nodoMineral, fila, columna);
		razaTerran.agregarEdificioRecolectorDeMineral(nuevoRecolectorDeMineral);
		return nuevoRecolectorDeMineral;
		
		
	}

	public static RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno(Terran razaTerran, VolcanGasVespeno volcan, int fila,int columna) {
		
		RecolectorDeGasVespeno nuevoRecolectorDeGasVespeno = new RecolectorDeGasVespeno(volcan,fila,columna);
		razaTerran.agregarEdificioRecolectorDeGasVespeno(nuevoRecolectorDeGasVespeno);
		return nuevoRecolectorDeGasVespeno;
		
	}
	

}
