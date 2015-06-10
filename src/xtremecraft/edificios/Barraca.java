package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class Barraca extends Edificio{
	
	public static int vida = 100;
	public static int tiempoDeConstruccion = 12;
	
	private Barraca(int fila,int columna){
		
		super(fila,columna,vida);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}

	public static Barraca nuevaBarraca(Terran razaTerran, int fila, int columna){
		
		Barraca barraca = new Barraca(fila,columna);
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
