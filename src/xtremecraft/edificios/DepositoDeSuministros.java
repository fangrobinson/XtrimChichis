package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public class DepositoDeSuministros extends Edificio{

	private static int vida = 100;
	private static int tiempoDeConstruccion = 6;
	private static int incrementoPoblacion = 5;
	private int minerales = 100;
	
	public DepositoDeSuministros(Terran raza, Terreno unTerreno) {
		
		super(unTerreno, vida);
		this.cobrar(raza);
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

	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
	}
}
