package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class EstrategiaUbicacionDosJugadoresTest {
	
	@Test
	public void estrategiaDeUbicacionDosJugadoresParDevuelveCoordenadasParaDosJugadores(){
		
		int cantidadDeJugadores = 2;
		EstrategiaUbicacion estrategia = new EstrategiaUbicacionDosJugadores();
		ArrayList<Coordenada> coordenadasParaDosJugadores = estrategia.getCoordenadasDeUbicacion();
	
		assertEquals(coordenadasParaDosJugadores.size(),cantidadDeJugadores);
		
	}
	
}
