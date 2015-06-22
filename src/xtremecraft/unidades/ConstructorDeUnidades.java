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
	
	public static Unidad nuevaUnidad(Terran raza, Barraca unaBarraca, Mapa mapa){
	
		Unidad nuevaUnidad = unaBarraca.entrenarMarine(raza);
		ubicarUnidadEnElMapa(mapa,unaBarraca,nuevaUnidad);	
		
		return nuevaUnidad;	
	}

	public static Unidad nuevaUnidad(Terran raza, Fabrica unaFabrica, Mapa mapa){
		
		Unidad nuevaUnidad = unaFabrica.entrenarGoliat(raza);
		ubicarUnidadEnElMapa(mapa,unaFabrica,nuevaUnidad);		
		
		return nuevaUnidad;
	}
	
	public static Unidad nuevaNaveTransporte(Terran raza, PuertoEstelar puerto, Mapa mapa) {
		
		Unidad nuevaUnidad = puerto.crearNaveTransporte(raza);
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);
		
		return nuevaUnidad;
	}

	public static Unidad nuevoEspectro(Terran raza, PuertoEstelar puerto,Mapa mapa) {

		Unidad nuevaUnidad = puerto.crearEspectro(raza);
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);

		return nuevaUnidad;
	}

	public static Unidad nuevaNaveCiencia(Terran raza, PuertoEstelar puerto,Mapa mapa) {

		Unidad nuevaUnidad = puerto.crearNaveCiencia(raza);
		ubicarUnidadEnElMapa(mapa,puerto,nuevaUnidad);	
		
		return nuevaUnidad;	
	}
	
	private static void ubicarUnidadEnElMapa(Mapa mapa,Edificio unEdificio,Unidad nuevaUnidad){
		
		boolean unidadEstaUbicada = false;
		ArrayList<Celda> celdasAdyacentesAlEdificio = mapa.obtenerCeldasAdyacentesAlUbicable(unEdificio);
		while(!( celdasAdyacentesAlEdificio.isEmpty() || unidadEstaUbicada ) ){
			Celda unaCelda = celdasAdyacentesAlEdificio.remove(0);
			unidadEstaUbicada = mapa.ubicar(nuevaUnidad, unaCelda);
		}
		if(!unidadEstaUbicada) throw new UbicacionNoValidaException();
	}

}
