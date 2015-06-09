package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Goliat;

public interface CreadorDeUnidadesTerrestres extends CreadorDeMarines{
	
	public Goliat entrenarGoliat(Terreno unTerreno);

}
