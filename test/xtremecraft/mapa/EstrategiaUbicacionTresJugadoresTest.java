package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class EstrategiaUbicacionTresJugadoresTest {
	
	@Test
	public void estrategiaDeUbicacionBasesParDevuelveTerrenosParaTresJugadores(){
		
		int cantidadDeJugadores = 3;
		EstrategiaUbicacion estrategia = new EstrategiaUbicacionTresJugadores();
		ArrayList<Coordenada> coordenadasParaTresJugadores = estrategia.getCoordenadasDeUbicacion();
	
		assertEquals(coordenadasParaTresJugadores.size(),cantidadDeJugadores);
		
	}

}
