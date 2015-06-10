package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public class DepositoDeSuministros extends Edificio{

	private static int vida = 100;
	private static int tiempoDeConstruccion = 6;
	
	private DepositoDeSuministros(Terreno unTerreno) {
		
		super(unTerreno, vida);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}
	
	public static DepositoDeSuministros nuevoDepositoDeSuministros(Terran razaTerran, Terreno unTerreno){
		
		DepositoDeSuministros deposito = new DepositoDeSuministros(unTerreno);
		//razaTerran.agregarDepositoDeSuministros(deposito);
		return deposito;
		
	}

	
	public boolean puedeUbicarseEnTierra() {
		
		return false;
		
	}

	
	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}

}
