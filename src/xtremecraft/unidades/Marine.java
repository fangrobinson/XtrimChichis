package xtremecraft.unidades;


public class Marine extends Unidad{
	
	public Marine(int coordenadaX, int coordenadaY){
		super(coordenadaX, coordenadaY);
		this.vitalidad = new BarraDeVitalidad(40);
		this.daño = new Daño (6,6);
		this.vision = 7;
	}
}
