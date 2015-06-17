package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.raza.Terran;

public abstract class ConstructorDeUnidades {
	
	public static Unidad nuevaUnidad(Terran razaTerran,Barraca unaBarraca, Mapa mapa){
		
		Unidad nuevaUnidad = null;
				
		nuevaUnidad = unaBarraca.entrenarMarine();
		ubicarUnidadEnElMapa(mapa,unaBarraca,nuevaUnidad);
		razaTerran.agregarUnidad(nuevaUnidad);		
		
		return nuevaUnidad;
		
			
	}

	public static Unidad nuevaUnidad(Terran razaTerran,Fabrica unaFabrica, Mapa mapa){
		
		Unidad nuevaUnidad = null;

		nuevaUnidad = unaFabrica.entrenarGoliat();
		ubicarUnidadEnElMapa(mapa,unaFabrica,nuevaUnidad);
		razaTerran.agregarUnidad(nuevaUnidad);		
		
		return nuevaUnidad;
		
			
	}
	
	public static Unidad nuevaNaveTransporte(Terran razaTerran,PuertoEstelar puerto, Mapa mapa) {
		
		Unidad nuevaUnidad = null;

		nuevaUnidad = puerto.crearNaveTransporte();
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);
		razaTerran.agregarUnidad(nuevaUnidad);		
		
		return nuevaUnidad;
		
	}

	public static Unidad nuevoEspectro(Terran razaTerran, PuertoEstelar puerto,Mapa mapa) {
		
		Unidad nuevaUnidad = null;

		nuevaUnidad = puerto.crearEspectro();
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);
		razaTerran.agregarUnidad(nuevaUnidad);		
		
		return nuevaUnidad;
		
	}

	public static Unidad nuevaNaveCiencia(Terran razaTerran, PuertoEstelar puerto,Mapa mapa) {
		
		Unidad nuevaUnidad = null;

		nuevaUnidad = puerto.crearNaveCiencia();
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);
		razaTerran.agregarUnidad(nuevaUnidad);		
		
		return nuevaUnidad;
		
	}
	
	
	private static void ubicarUnidadEnElMapa(Mapa mapa,Edificio unEdificio,Unidad nuevaUnidad){
		
		boolean unidadEstaUbicada = false;
		ArrayList<Celda> celdasAdyacentesAlEdificio = mapa.obtenerCeldasAdyacentesAlUbicable(unEdificio);
		while( !celdasAdyacentesAlEdificio.isEmpty() || !unidadEstaUbicada ){
			Celda unaCelda = celdasAdyacentesAlEdificio.remove(0);
			unidadEstaUbicada = mapa.ubicar(nuevaUnidad, unaCelda);
		}
		if(!unidadEstaUbicada) throw new IllegalArgumentException("Unidad no pudo ser creada.");
	}
	
}
