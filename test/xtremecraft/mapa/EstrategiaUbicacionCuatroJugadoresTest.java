package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class EstrategiaUbicacionCuatroJugadoresTest {
	
	@Test
	public void estrategiaDeUbicacionCuatroJugadoresParDevuelveCoordenadasParaCuatroJugadores(){

		int cantidadDeJugadores = 4;
		EstrategiaUbicacionCuatroJugadores estrategia = new EstrategiaUbicacionCuatroJugadores();
		ArrayList<Coordenada> coordenadasParaCuatroJugadores = estrategia.getCoordenadasDeUbicacion();
	
		assertEquals(coordenadasParaCuatroJugadores.size(),cantidadDeJugadores);
		
	}

}
