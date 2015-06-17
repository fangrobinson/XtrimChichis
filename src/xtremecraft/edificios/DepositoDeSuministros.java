package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;

public class DepositoDeSuministros extends Edificio{

	private static int vida = 100;
	private static int tiempoDeConstruccion = 6;
	
	public DepositoDeSuministros(Terreno unTerreno) {
		
		super(unTerreno, vida);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
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

}
