package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;
import xtremecraft.unidades.Marine;

public class Barraca extends Edificio{
	
	private static int vidaInicial = 100;
	private static int tiempoDeConstruccion = 12;
	private int minerales = 150;
	
	public Barraca(Jugador jugador, Terreno unTerreno){
		
		super(jugador,unTerreno,vidaInicial);
		this.cobrar();
		this.tiempoConstruccion = tiempoDeConstruccion;

	}
	
	public Marine entrenarMarine(){
		
		if(this.estaEnConstruccion()){
			throw new EdificioEnConstruccionException();
		}
		return new Marine(this.jugador);
		
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
		
	}

}
