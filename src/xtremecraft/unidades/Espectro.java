package xtremecraft.unidades;

import xtremecraft.raza.Terran;


public class Espectro extends UnidadAerea{
	
	private int minerales = 150;
	private int gas = 100;
	
	public Espectro(Terran raza) {
		
		super();
		this.cobrar(raza);
		this.vitalidad = new BarraDeVitalidad(120);
		this.danio = new Danio (20,8);
		this.vision = 7;
		this.tiempoConstruccion = 8;
		this.suministro = 2;
		
	}
	
	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
		raza.quitarGas(this.gas);
	}
	
}
