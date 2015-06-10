package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class Barraca extends Edificio{
	
	public static int vida = 100;
	public static int tiempoDeConstruccion = 12;
	
	private Barraca(Terreno unTerreno){
		
		super(unTerreno,vida);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}

	public static Barraca nuevaBarraca(Terran razaTerran, Terreno unTerreno){
		
		Barraca barraca = new Barraca(unTerreno);
		razaTerran.agregarBarraca(barraca);
		return barraca;
		
	}
	
	public Marine entrenarMarine(Terreno unTerreno){
		
		return new Marine(unTerreno);
		
	}


	public boolean puedeUbicarseEnTierra() {

		return true;
		
	}

	public boolean puedeUbicarseEnAire() {

		return false;
		
	}

}
