package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;


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
	

}
