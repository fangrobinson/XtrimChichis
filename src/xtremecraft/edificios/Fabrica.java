package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;
import xtremecraft.unidades.Goliat;

public class Fabrica extends Edificio{
	
	private static int vida = 100;
	private static int tiempoDeConstruccion = 12;
	private int minerales = 200;
	private int gas = 100;
	
	public Fabrica(Jugador jugador, Barraca unaBarraca, Terreno terreno){
		
		super(jugador,terreno,vida);
		this.cobrar();
		this.tiempoConstruccion = tiempoDeConstruccion;

	}
	
	
	public Goliat entrenarGoliat(){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new Goliat(this.jugador);
		
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
