package xtremecraft.unidades;

import xtremecraft.raza.Terran;


public class Goliat extends UnidadTerrestre {
	
	private int minerales = 100;
	private int gas = 50;
	
	public Goliat(Terran raza){
		
		super();
		this.cobrar(raza);
		this.vitalidad = new BarraDeVitalidad(125);
		this.danio = new Danio(10,12);
		this.vision = 8;
		this.tiempoConstruccion = 6;
		this.suministro = 2;
		this.distanciaTransporte = 2;

		
	}

	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
		raza.quitarGas(this.gas);
	}
	
}