package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.recursos.Recurso;
import xtremecraft.sistema.Actualizable;


public abstract class Recolector extends Edificio implements Actualizable{
	
	protected int reservas;
	protected int aumentoDeReservaEnTurno;
	public static int vida = 100;
	protected Recurso recurso;
	
	protected Recolector(Terreno terreno){
		
		super(terreno,vida);
		this.reservas=0;
		this.aumentoDeReservaEnTurno=10;
	
	}
	
	public int getReservas() {
		
		return reservas;
		
	}

	public void pasarTiempo(){
		//revisar modelado de paso del tiempo
		if(this.estaEnConstruccion()) this.tiempoDeConstruccionActual += 1;
		else this.reservas += this.recurso.explotar(this.aumentoDeReservaEnTurno);
		
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
