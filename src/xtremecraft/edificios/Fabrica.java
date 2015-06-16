package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Goliat;

public class Fabrica extends Edificio{
	
	private static int vida = 100;
	private static int tiempoDeConstruccion = 12;
	
	public Fabrica(Terreno terreno){
		
		super(terreno,vida);
		this.tiempoConstruccion = tiempoDeConstruccion;

	}
	
	
	public Goliat entrenarGoliat(Terreno unTerreno){
		
		return new Goliat(unTerreno);
		
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
