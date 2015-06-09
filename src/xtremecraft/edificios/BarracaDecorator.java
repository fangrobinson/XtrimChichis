package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;

public abstract class BarracaDecorator implements CreadorDeMarines{
	
	protected Barraca barracaBase;

	public BarracaDecorator(Barraca barraca){
		
		this.barracaBase = barraca;
		
	}
	
	public Barraca getBarracaBase(){
		
		return this.barracaBase;
		
	}
	
	public abstract Marine entrenarMarine(Terreno unTerreno);
	
	public abstract Goliat entrenarGoliat(Terreno unTerreno);

}
