package xtremecraft.partida;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

//import xtremecraft.mapa.Mapa;
//import xtremecraft.mapa.Terreno;
//import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
//import xtremecraft.recursos.MinaDeMinerales;


public class IntegracionTest {
	
	@Test
	public void prueba01CreacionDeRecolectoresDeGasDeUnidadesAtaqueEntreUnidadesYaEdificios(){
		
		ArrayList<String> nombresJugadores = new ArrayList<String>();
		nombresJugadores.add("noob");
		nombresJugadores.add("playerOpCoreano");
		
		Partida xtremGame = new Partida(nombresJugadores);
		
		String nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		assertEquals(nombreJugadorTurno, "noob");
		
		Jugador jugadorTurno = xtremGame.quienJuega();
		
		//Mapa mapa = xtremGame.getMapa();
		
		//Terreno esteTerreno = mapa.obtenerTerrenoConMinaDeMinerales();

		jugadorTurno.nacion().juntarMinerales(200);
		
		//xtremGame.crearRecolectorDeMineral(jugadorTurno,esteTerreno.fila(),esteTerreno.columna());
		
		xtremGame.pasarTiempo();
		
		jugadorTurno.pasarTurno();
		
        nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		assertEquals(nombreJugadorTurno, "playerOpCoreano");
		
	}

	/*
	@Test
	
	public void prueba03_creacionDeRecolectoresDeGasDeUnidadesAtaqueEntreUnidadesYaEdificios(){

		//assertEquals(nombreJugadorTurno, "playerOpCoreano");
        
        for (int i = 0; i < 10; i++){
        	xtremGame.pasarTiempo(); //Espero a que se construyan ambos recolectores y a que stocken minerales
        }
        
        nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		assertEquals(nombreJugadorTurno, "noob");
		
		jugadorTurno = xtremGame.quienJuega();
		IteradorEdificios iter = new IteradorEdificios(jugadorTurno.nacion().edificios());
		Barraca unaBarraca = (Barraca) iter.nextDe(Barraca.class);
		//xtremGame.crearMarine(jugadorTurno, unaBarraca);
		
        xtremGame.pasarTiempo();
		
        nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		//assertEquals(nombreJugadorTurno, "playerOpCoreano");
        
        for (int i = 0; i < 10; i++){
        	//xtremGame.crearMarine(jugadorTurno, unaBarraca);
        }
        
        
        for (int i = 0; i < 2; i++){
        	xtremGame.pasarTiempo();   //espero que se construyan los marines
        }
        
        
        
        for (int i = 0; i < 30; i++){
        	xtremGame.pasarTiempo();   //espero que se construyan los marines
        }
	}
		*/
}
