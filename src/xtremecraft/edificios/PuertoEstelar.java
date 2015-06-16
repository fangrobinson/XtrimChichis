package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelar extends Edificio{
	
	private static int vida = 100;
	private static int tiempoDeConstruccion = 10;
	
	public PuertoEstelar(Terreno terreno){
		
		super(terreno,vida);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}
	
	public Espectro crearEspectro(Terreno unTerreno){
		
		return new Espectro(unTerreno);
		
	}
	

	public NaveCiencia crearNaveCiencia(Terreno unTerreno){
		
		return new NaveCiencia(unTerreno);
		
	}
	

	public NaveTransporte crearNaveTransporte(Terreno unTerreno){
		
		return new NaveTransporte(unTerreno);
		
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
