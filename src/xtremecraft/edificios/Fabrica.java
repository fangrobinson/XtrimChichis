package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;

public class Fabrica extends BarracaDecorator{

	protected Fabrica(Barraca unaBarraca) {
		
		super(unaBarraca);

	}
	
	public static Fabrica nuevaFabrica(Barraca unaBarraca){
		
		//AGREGAR A LAS REFERENCIAS DE LA RAZA TERRAN.
		return new Fabrica(unaBarraca);
		
	}

	public Object getVida() {
		
		return this.getBarracaBase().getVida();
		
	}

	public void recibirDanio(int valorDanio) {
		
		this.getBarracaBase().recibirDanio(valorDanio);
		
	}


	public Marine entrenarMarine(Terreno unTerreno){
		
		return this.getBarracaBase().entrenarMarine(unTerreno);
		
	}
	
	public Goliat entrenarGoliat(Terreno unTerreno) {
		
		return new Goliat(unTerreno);
		
	}



	
}
