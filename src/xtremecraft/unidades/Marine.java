package xtremecraft.unidades;

import xtremecraft.raza.Terran;



public class Marine extends UnidadTerrestre{
	
	private int minerales = 50;
	
	public Marine(Terran raza){

		super();
		this.cobrar(raza);
		this.vitalidad = new BarraDeVitalidad(40);
		this.danio = new Danio (6,6);
		this.vision = 7;
		this.tiempoConstruccion = 3;
		this.suministro = 1;
		this.distanciaTransporte = 1;
		
	}
	
	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
	}
	

}
