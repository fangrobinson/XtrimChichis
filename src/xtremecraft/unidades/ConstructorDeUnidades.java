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
		ubicarUnidadEnElMapa(mapa, unaBarraca, nuevaUnidad);	
		nuevaUnidad.addObserver(mapa);
		
		return nuevaUnidad;	
	}

	public static Unidad nuevaUnidad(Fabrica unaFabrica, Mapa mapa){
		
		Unidad nuevaUnidad = unaFabrica.entrenarGoliat();
		ubicarUnidadEnElMapa(mapa, unaFabrica, nuevaUnidad);		
		
		return nuevaUnidad;
	}
	
	public static Unidad nuevaNaveTransporte(PuertoEstelar puerto, Mapa mapa) {
		
		Unidad nuevaUnidad = puerto.crearNaveTransporte();
		ubicarUnidadEnElMapa(mapa, puerto, nuevaUnidad);
		nuevaUnidad.addObserver(mapa);
		
		return nuevaUnidad;
	}

	public static Unidad nuevoEspectro(PuertoEstelar puerto,Mapa mapa) {

		Unidad nuevaUnidad = puerto.crearEspectro();
		ubicarUnidadEnElMapa(mapa, puerto, nuevaUnidad);
		nuevaUnidad.addObserver(mapa);
		
		return nuevaUnidad;
	}

	public static Unidad nuevaNaveCiencia(PuertoEstelar puerto,Mapa mapa) {

		Unidad nuevaUnidad = puerto.crearNaveCiencia();
		ubicarUnidadEnElMapa(mapa, puerto, nuevaUnidad);	
		nuevaUnidad.addObserver(mapa);
		
		return nuevaUnidad;	
	}
	
	private static void ubicarUnidadEnElMapa(Mapa mapa,Edificio unEdificio,Unidad nuevaUnidad){
		
		boolean unidadEstaUbicada = false;
		ArrayList<Celda> celdasAdyacentesAlEdificio = mapa.obtenerCeldasAdyacentesAlUbicable(unEdificio);
		Celda unaCelda = celdasAdyacentesAlEdificio.remove(0);
		while( ! ( celdasAdyacentesAlEdificio.isEmpty() || unidadEstaUbicada ) ){
			try{
				mapa.ubicar(nuevaUnidad, unaCelda);
				unidadEstaUbicada = true;
			}
			catch(RuntimeException NoSePudoOcuparElTerrenoException){
				unaCelda = celdasAdyacentesAlEdificio.remove(0);
			}
		}
		
	}

}
