package xtremecraft.mapa;

import java.util.Random;
import xtremecraft.mapa.EstrategiaCuadrante;

public class EstrategiaUbicacion {
	public EstrategiaUbicacion(){}
	
	public int posicionRandom(int jugadores_ubicados){
		Random randomGen = new Random();
		return randomGen.nextInt(25) + (jugadores_ubicados/4 * 50);
	}
	
	public EstrategiaCuadrante conseguirEstrategiaParaCuadrante (int jugadores_ubicados, int alto, int ancho){
		if (jugadores_ubicados % 4 == 0) {
			return new EstrategiaSegundoCuadrante();
		}
		if (jugadores_ubicados % 4 == 1) {
			return new EstrategiaCuartoCuadrante(alto, ancho);
		}
		if (jugadores_ubicados % 4 == 2) {
			return new EstrategiaPrimerCuadrante(ancho);
		}
		//if (jugadores_ubicados % 4 == 3) {
		return new EstrategiaTercerCuadrante(alto);
	}
}
