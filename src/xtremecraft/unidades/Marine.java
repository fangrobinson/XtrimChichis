package xtremecraft.unidades;

public class Marine extends Unidad {
	
	public Marine(){
		this.vitalidad = new BarraDeVitalidad(40);
		this.daño = new Daño (6,6);
		this.vision = 7;
	}
}
