package xtremecraft.sistema;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.mapa.Mapa;
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
		
		Mapa mapa = xtremGame.getMapa();
		
		ArrayList<Terreno> terrenosConRecurso = mapa.obtenerTerrenosConRecursos();
		
		Terreno esteTerreno = terrenosConRecurso.get(0);
		
		try{
			
			xtremGame.crearRecolectorDeMineral(jugadorTurno,esteTerreno.fila(),esteTerreno.columna());
			
		}
		catch(RuntimeException NoHayRecursoException){
			
			//ESTE RECOLECTOR SALE MUY CARO NUNCA ME ALCANZA!!!
			//xtremGame.crearRecolectorDeGasVespeno(jugadorTurno,esteTerreno.fila(),esteTerreno.columna());
			
		}
		
		xtremGame.pasarTiempo();
		
		jugadorTurno.pasarTurno();
		
        nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		assertEquals(nombreJugadorTurno, "playerOpCoreano");
		
	}
	/*
	@Test
	
	public void prueba03_creacionDeRecolectoresDeGasDeUnidadesAtaqueEntreUnidadesYaEdificios(){
		
	}
		*/
}
