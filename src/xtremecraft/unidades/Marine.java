package xtremecraft.unidades;



public class Marine extends UnidadTerrestre{
	
	public Marine(){
		this.vitalidad = new BarraDeVitalidad(40);
		this.danio = new Danio (6,6);
		this.vision = 7;
		this.tiempoConstruccion = 3;
	}

}
