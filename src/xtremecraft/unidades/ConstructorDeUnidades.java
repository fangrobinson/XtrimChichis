package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.edificios.Edificio;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.raza.Terran;

public abstract class ConstructorDeUnidades {
	

	public static Unidad nuevaUnidad(String tipoDeUnidad,Terran razaTerran,Edificio unEdificio, Mapa mapa){
		
		Unidad nuevaUnidad = null;
		
		
		ArrayList<Celda> celdasAdyacentesAlEdificio = mapa.obtenerCeldasAdyacentesAlUbicable(unEdificio);
		
		if( tipoDeUnidad == "Marine" ){
			
			nuevaUnidad = new Marine();
			
		}
		
		if( tipoDeUnidad == "Goliat" ){
			
			nuevaUnidad = new Goliat();
					
			
		}
		
		if( tipoDeUnidad == "Espectro" ){
			
			nuevaUnidad = new Espectro();
						
		}
		
		if( tipoDeUnidad == "Nave Ciencia" ){
			
			nuevaUnidad = new NaveCiencia();
						
		}
		
		if( tipoDeUnidad == "Nave Transporte" ){
			
			nuevaUnidad = new NaveTransporte();
			
		}
		
		ubicarUnidadEnElMapa(celdasAdyacentesAlEdificio,mapa,nuevaUnidad);
	
		razaTerran.agregarUnidad(nuevaUnidad);		
		
		return nuevaUnidad;
		
	}
	
	
	private static void ubicarUnidadEnElMapa(ArrayList<Celda> celdasAdyacentesAlEdificio,Mapa mapa,Unidad nuevaUnidad){
		boolean unidadEstaUbicada = false;
		while( !celdasAdyacentesAlEdificio.isEmpty() || !unidadEstaUbicada ){
			Celda unaCelda = celdasAdyacentesAlEdificio.remove(0);
			unidadEstaUbicada = mapa.ubicar(nuevaUnidad, unaCelda);
		}
		if(!unidadEstaUbicada) throw new IllegalArgumentException("Unidad no pudo ser creada.");
	}
	
}
