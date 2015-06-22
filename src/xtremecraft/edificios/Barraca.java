package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class Barraca extends Edificio{
	
	public static int vida = 100;
	public static int tiempoDeConstruccion = 12;
	private int minerales = 150;
	
	public Barraca(Terran raza, Terreno unTerreno){
		
		super(unTerreno,vida);
		this.cobrar(raza);
		this.tiempoConstruccion = tiempoDeConstruccion;

	}
	
	public Marine entrenarMarine(Terran raza){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new Marine(raza);
		
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
