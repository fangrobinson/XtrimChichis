package xtremecraft.unidades;

import xtremecraft.partida.Jugador;


public class Goliat extends UnidadTerrestre {
	
	private int minerales = 100;
	private int gas = 50;
	private static int vidaInicial = 125;
	
	public Goliat(Jugador unJugador){
		
		super(unJugador);
		this.cobrar();
		this.vitalidad = new BarraDeVitalidad(vidaInicial);
		this.danio = new Danio(10,12);
		this.vision = 8;
		this.tiempoConstruccion = 6;
		this.suministro = 2;
		this.distanciaTransporte = 2;

	}

	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		this.jugador.nacion().quitarGas(this.gas);
	
	}
	
}