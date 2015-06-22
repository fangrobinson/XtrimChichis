package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelar extends Edificio{
	
	private static int vida = 100;
	private static int tiempoDeConstruccion = 10;
	private int minerales = 150;
	private int gas = 100;
	
	public PuertoEstelar(Terran raza, Fabrica unaFabrica, Terreno terreno){
		
		super(terreno,vida);
		this.cobrar(raza);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}
	
	public Espectro crearEspectro(Terran raza){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new Espectro(raza);
		
	}
	

	public NaveCiencia crearNaveCiencia(Terran raza){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new NaveCiencia(raza);
		
	}
	

	public NaveTransporte crearNaveTransporte(Terran raza){
		
		if(this.estaEnConstruccion()){
			throw new IllegalArgumentException("Este edificio se encuentra en contruccion");
		}
		return new NaveTransporte(raza);
		
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
		raza.quitarGas(this.gas);
	}

}
