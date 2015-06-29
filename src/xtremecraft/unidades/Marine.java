package xtremecraft.unidades;

import xtremecraft.partida.Jugador;


public class Marine extends UnidadTerrestre{
	
	private int minerales = 50;
	private static int vidaInicial = 40;
	
	public Marine(Jugador jugador){

		super(jugador);
		this.cobrar();
		this.vitalidad = new BarraDeVitalidad(vidaInicial);
		this.danio = new Danio (6,6);
		this.vision = 7;
		this.tiempoConstruccion = 3;
		this.suministro = 1;
		this.distanciaTransporte = 1;
		
	}
	
	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		
	}
	

}
