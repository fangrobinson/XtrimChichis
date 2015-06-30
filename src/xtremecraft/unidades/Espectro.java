package xtremecraft.unidades;

import xtremecraft.partida.Jugador;

public class Espectro extends UnidadAerea{
	
	private int minerales = 150;
	private int gas = 100;
	private static int vidaInicial = 120;
	
	public Espectro(Jugador jugador) {
		
		super(jugador);
		this.cobrar();
		this.vitalidad = new BarraDeVitalidad(vidaInicial);
		this.danio = new Danio (20,8);
		this.vision = 7;
		this.tiempoConstruccion = 8;
		this.suministro = 2;
		
	}
	
	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		this.jugador.nacion().quitarGas(this.gas);
		
	}
	
}
