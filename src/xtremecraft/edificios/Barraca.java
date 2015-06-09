package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class Barraca extends Edificio implements CreadorDeMarines{
	
	public static int vida = 100;
	
	private Barraca(int fila,int columna){
		
		super(fila,columna,vida);
		
	}

	public static Barraca nuevaBarraca(Terran razaTerran, int fila, int columna){
		
		Barraca edificioCreadorDeUnidades = new Barraca(fila,columna);
		razaTerran.agregarBarraca(edificioCreadorDeUnidades);
		return edificioCreadorDeUnidades;
		
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
