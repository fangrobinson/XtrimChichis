package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.recursos.Recurso;
import xtremecraft.sistema.Actualizable;


public abstract class Recolector extends Edificio implements Actualizable{
	
	protected int reservas; // TODO: esto deberia ser de la raza
	protected int aumentoDeReservaEnTurno;
	public static int vida = 100;
	protected Recurso recurso;
	
	protected Recolector(Terreno terreno){
		
		super(terreno, vida);
		this.reservas=0;
		this.aumentoDeReservaEnTurno=10;
	
	}
	
	public int getReservas() {
		
		return reservas;
		
	}

	public void pasarTiempo(){
		if (!super.estaEnConstruccion){
			this.reservas += this.recurso.explotar(this.aumentoDeReservaEnTurno);
		}
		super.pasarTiempo();
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
