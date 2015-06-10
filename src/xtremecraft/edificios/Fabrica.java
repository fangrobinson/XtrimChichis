package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;

public class Fabrica extends Edificio{
	
	static int vida = 100;
	
	protected Fabrica(int fila, int columna){
		
		super(fila,columna,vida);
		this.tiempoConstruccion = 12;

	}
	
	public static Fabrica nuevaFabrica(Terran razaTerran, int fila, int columna){
		
		if(razaTerran.tieneBarracas()){
			Fabrica nuevaFabrica = new Fabrica(fila,columna);
			razaTerran.agregarFabrica(nuevaFabrica);
			return nuevaFabrica;
		}
		
		throw new IllegalArgumentException("Para construir una fabrica debes contar con al menos una barraca.");
		
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

	
}
