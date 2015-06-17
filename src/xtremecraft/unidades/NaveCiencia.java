package xtremecraft.unidades;

import xtremecraft.unidades.UnidadAerea;

public class NaveCiencia extends UnidadAerea{
	
	private int energiaMax = 200;
	private int energia = 50;
	private int aumentoDeEnergiaEnTurno = 10;
	
	public NaveCiencia(){
		
		this.vitalidad = new BarraDeVitalidad(200);
		this.danio = new Danio(0,0);
		this.vision = 10;
		this.tiempoConstruccion = 10;
		
	}

	public int getEnergia(){
		
		return this.energia;
		
	}
	
	public void aumentarEnergiaEnTurno(){
		
		if( (this.energia + this.aumentoDeEnergiaEnTurno) >= this.energiaMax ){
			
			this.energia = this.energiaMax;
			
		}else{
			
			this.energia += this.aumentoDeEnergiaEnTurno;
			
		}
	}
	
	public boolean elevar(){
		
		this.estaElevado = true;
		return true;
		
	}
	

}
