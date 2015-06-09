package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.UnidadAerea;

public class NaveCiencia extends UnidadAerea{
	
	private int energia;
	private int aumentoDeEnergiaEnTurno;
	
	public NaveCiencia(Terreno terreno){
		
		super(terreno);
		this.vitalidad = new BarraDeVitalidad(200);
		this.daño = new Danio(0,0);
		this.vision = 10;
		this.energia = 50;
		this.aumentoDeEnergiaEnTurno = 10;
		
	}

	public int getEnergia(){
		
		return this.energia;
		
	}
	
	public void aumentarEnergiaEnTurno(){
		
		if((this.energia+this.aumentoDeEnergiaEnTurno)<=200){
			this.energia+=this.aumentoDeEnergiaEnTurno;
		}
		
	}
	
}
