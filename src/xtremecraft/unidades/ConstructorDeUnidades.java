package xtremecraft.unidades;

import xtremecraft.edificios.Edificio;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public abstract class ConstructorDeUnidades {
	

	public static boolean nuevaUnidad(String tipoDeUnidad,Terran razaTerran,Edificio unEdificio){
		
		Unidad nuevaUnidad = null;
		Terreno unTerreno = null;
		
		if( tipoDeUnidad == "Marine" ){
			
			
			nuevaUnidad = new Marine(unTerreno);
			
		}
		
		if( tipoDeUnidad == "Goliat" ){
			
			nuevaUnidad = new Goliat(unTerreno);
					
			
		}
		
		if( tipoDeUnidad == "Espectro" ){
			
			nuevaUnidad = new Espectro(unTerreno);
						
		}
		
		if( tipoDeUnidad == "Nave Ciencia" ){
			
			nuevaUnidad = new Goliat(unTerreno);
						
		}
		
		if( tipoDeUnidad == "Nave Transporte" ){
			
			nuevaUnidad = new Goliat(unTerreno);
			
		}
		
	
		razaTerran.agregarUnidad(nuevaUnidad);
		
		Ubicable unidad = (Ubicable)nuevaUnidad;
		
		return unTerreno.ubicar(unidad);
		
	}

}
