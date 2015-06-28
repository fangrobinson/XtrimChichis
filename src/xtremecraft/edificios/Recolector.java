package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Actualizable;
import xtremecraft.partida.Jugador;
import xtremecraft.recursos.Recurso;


public abstract class Recolector extends Edificio implements Actualizable{

	protected int aumentoDeReservaEnTurno;
	public static int vida = 100;
	protected Recurso recurso;
	
	protected Recolector(Jugador jugador,Terreno terreno){
		
		super(jugador,terreno, vida);
		this.aumentoDeReservaEnTurno = 10;
	
	}

	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}

	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}
	
	public boolean puedeUbicarseSobreRecursoNatural() {
	
		return true;
		
	}
	
	public boolean puedeExtraerGasVespeno(){
		
		return false;
		
	}
	
	public boolean puedeExtraerMineral(){
		
		return false;
		
	}

}
