package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.sistema.Actualizable;


public abstract class Recolector extends Edificio implements Actualizable{
	
	protected int reservas;
	protected int aumentoDeReservaEnTurno;
	public static int vida = 100;
	
	public Recolector(Terreno terreno){
		
		super(terreno,vida);
		this.reservas=0;
		this.aumentoDeReservaEnTurno=10;
	
	}
	
	public int getReservas() {
		
		return reservas;
		
	}
	

}
