package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;

public class DepositoDeSuministros extends Edificio{

	private static int vidaInicial = 100;
	private static int tiempoDeConstruccion = 6;
	private static int incrementoPoblacion = 5;
	private int minerales = 100;
	
	public DepositoDeSuministros(Jugador jugador, Terreno unTerreno) {
		
		super(jugador,unTerreno, vidaInicial);
		this.cobrar();
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}

	public static int getIncrementoPoblacion(){
		
		return incrementoPoblacion;
		
	}
	
	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}
	
	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}

	public boolean puedeUbicarseSobreRecursoNatural() {
		
		return false;
		
	}

	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		
	}
	
}
