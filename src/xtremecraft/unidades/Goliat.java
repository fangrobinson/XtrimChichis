package xtremecraft.unidades;


public class Goliat extends Unidad {
	
	public Goliat(int coordenadaX, int coordenadaY){
		super(coordenadaX, coordenadaY);
		this.vitalidad = new BarraDeVitalidad(125);
		this.daño = new Daño (10,12);
		this.vision = 8;
		
	}


	
	
}