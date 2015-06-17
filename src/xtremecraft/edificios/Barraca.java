package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
//import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class Barraca extends Edificio{
	
	public static int vida = 100;
	public static int tiempoDeConstruccion = 12;
	
	
	public Barraca(Terreno unTerreno){
		
		super(unTerreno,vida);
		this.tiempoConstruccion = tiempoDeConstruccion;

	}
	
	public Marine entrenarMarine(Terreno unTerreno){
		
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion){
			throw new IllegalArgumentException("Este edificio se encuentra en contruccion");
		}
		return new Marine();
		
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
