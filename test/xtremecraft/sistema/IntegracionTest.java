package xtremecraft.sistema;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.mapa.Terreno;

public class IntegracionTest {
	
	@Test
	public void prueba01_creacionDeRecolectoresDeGasDeUnidadesAtaqueEntreUnidadesYaEdificios(){
		ArrayList<String> nombresJugadores = new ArrayList<String>();
		nombresJugadores.add("noob");
		nombresJugadores.add("playerOpCoreano");
		
		Partida xtremGame = new Partida(nombresJugadores);
		
		String nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		assertEquals(nombreJugadorTurno, "noob");
		
		Jugador jugadorTurno = xtremGame.quienJuega();
		//xtremGame.crearRecolectorDeMineral(jugadorTurno, 1, 5);
		
		xtremGame.pasarTiempo();
		
        nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		//assertEquals(nombreJugadorTurno, "playerOpCoreano");
		
	}
	
	@Test
	
	public void prueba03_creacionDeRecolectoresDeGasDeUnidadesAtaqueEntreUnidadesYaEdificios(){
		
	}
		
}
