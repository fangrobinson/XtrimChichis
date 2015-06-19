package xtremecraft.unidades;


public class Goliat extends UnidadTerrestre {
	
	public Goliat(){
		
		super();
		this.vitalidad = new BarraDeVitalidad(125);
		this.danio = new Danio(10,12);
		this.vision = 8;
		this.tiempoConstruccion = 6;
		this.suministro = 2;
		
	}	
	
}