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
	
	public Espectro crearEspectro(){
		
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion){
			throw new IllegalArgumentException("Este edificio se encuentra en contruccion");
		}
		return new Espectro();
		
	}
	

	public NaveCiencia crearNaveCiencia(){
		
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion){
			throw new IllegalArgumentException("Este edificio se encuentra en contruccion");
		}
		return new NaveCiencia();
		
	}
	

	public NaveTransporte crearNaveTransporte(){
		
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion){
			throw new IllegalArgumentException("Este edificio se encuentra en contruccion");
		}
		return new NaveTransporte();
		
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
