package xtremecraft.edificios;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;

public class Fabrica implements CreadorDeMarines,Ubicable,Atacable{
	
	Barraca barracaBase;

	protected Fabrica(Barraca unaBarraca){
		
		this.barracaBase = unaBarraca;

	}
	
	public static Fabrica nuevaFabrica(Terran razaTerran, Barraca unaBarraca){
		
		Fabrica nuevaFabrica = new Fabrica(unaBarraca);
		razaTerran.agregarFabrica(nuevaFabrica);
		return nuevaFabrica;
		
	}

	public int getVida(){
		
		return this.barracaBase.getVida();
		
	}

	public void recibirDanio(int valorDanio){
		
		this.barracaBase.recibirDanio(valorDanio);
		
	}


	public Marine entrenarMarine(Terreno unTerreno){
		
		return this.barracaBase.entrenarMarine(unTerreno);
		
	}
	
	public Goliat entrenarGoliat(Terreno unTerreno){
		
		return new Goliat(unTerreno);
		
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.barracaBase.getUbicacionActual();
		
	}

	public void actualizarUbicacion(Terreno terreno) {
		
		this.barracaBase.actualizarUbicacion(terreno);
		
	}


	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}

	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}

	public Barraca getBarracaBase() {
		
		return this.barracaBase;
		
	}

	
}
