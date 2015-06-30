package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelar extends Edificio{
	
	private static int vidaInicial = 100;
	private static int tiempoDeConstruccion = 10;
	private int minerales = 150;
	private int gas = 100;
	
	public PuertoEstelar(Jugador jugador, Fabrica unaFabrica, Terreno terreno){
		
		super(jugador,terreno,vidaInicial);
		this.cobrar();
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}
	
	public Espectro crearEspectro(){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new Espectro(this.jugador);
		
	}
	

	public NaveCiencia crearNaveCiencia(){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new NaveCiencia(this.jugador);
		
	}
	

	public NaveTransporte crearNaveTransporte(){
		
		if(this.estaEnConstruccion()){
			throw new IllegalArgumentException("Este edificio se encuentra en contruccion");
		}
		return new NaveTransporte(this.jugador);
		
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

	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		this.jugador.nacion().quitarGas(this.gas);
		
	}

}
