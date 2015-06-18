package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;

public abstract class ConstructorDeUnidades {
	
	public static Unidad nuevaUnidad(Barraca unaBarraca, Mapa mapa){
	
		Unidad nuevaUnidad = unaBarraca.entrenarMarine();
		ubicarUnidadEnElMapa(mapa,unaBarraca,nuevaUnidad);	
		
		return nuevaUnidad;	
	}

	public static Unidad nuevaUnidad(Fabrica unaFabrica, Mapa mapa){
		
		Unidad nuevaUnidad = unaFabrica.entrenarGoliat();
		ubicarUnidadEnElMapa(mapa,unaFabrica,nuevaUnidad);		
		
		return nuevaUnidad;
	}
	
	public static Unidad nuevaNaveTransporte(PuertoEstelar puerto, Mapa mapa) {
		
		Unidad nuevaUnidad = puerto.crearNaveTransporte();
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);
		
		return nuevaUnidad;
	}

	public static Unidad nuevoEspectro( PuertoEstelar puerto,Mapa mapa) {

		Unidad nuevaUnidad = puerto.crearEspectro();
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);

		return nuevaUnidad;
	}

	public static Unidad nuevaNaveCiencia(PuertoEstelar puerto,Mapa mapa) {

		Unidad nuevaUnidad = puerto.crearNaveCiencia();
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);	
		
		return nuevaUnidad;	
	}
	
	private static void ubicarUnidadEnElMapa(Mapa mapa,Edificio unEdificio,Unidad nuevaUnidad){
		
		boolean unidadEstaUbicada = false;
		ArrayList<Celda> celdasAdyacentesAlEdificio = mapa.obtenerCeldasAdyacentesAlUbicable(unEdificio);
		while( !celdasAdyacentesAlEdificio.isEmpty() || !unidadEstaUbicada ){
			Celda unaCelda = celdasAdyacentesAlEdificio.remove(0);
			unidadEstaUbicada = mapa.ubicar(nuevaUnidad, unaCelda);
		}
		// TODO: arreglar con excepcion especifica
		if(!unidadEstaUbicada) throw new IllegalArgumentException("Unidad no pudo ser creada.");
	}

}