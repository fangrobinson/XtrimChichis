package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;

public class Fabrica extends Edificio{
	
	private static int vida = 100;
	private static int tiempoDeConstruccion = 12;
	
	public Fabrica(Barraca unaBarraca, Terreno terreno){
		
		super(terreno,vida);
		this.tiempoConstruccion = tiempoDeConstruccion;

	}
	
	
	public Goliat entrenarGoliat(Terran raza){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new Goliat(raza);
		
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
