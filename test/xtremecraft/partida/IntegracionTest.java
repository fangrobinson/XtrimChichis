package xtremecraft.partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.unidades.Marine;


public class IntegracionTest {
	
	@Test
	public void prueba01CreacionDeEdificiosYAtaquesValidos(){
		
		ArrayList<String> nombresJugadores = new ArrayList<String>();
		nombresJugadores.add("noob");
		nombresJugadores.add("playerOpCoreano");
		
		Partida xtremGame = new Partida(nombresJugadores);
		
		String nombreJugadorTurno = xtremGame.quienJuega().nombre();
		
		assertEquals(nombreJugadorTurno, "noob");
		
		Jugador jugadorTurno = xtremGame.quienJuega();
		
		int recursoMineral = jugadorTurno.getCantidadDeMinerales();
		
		Mapa mapa = xtremGame.getMapa();

		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2, 4).getCapaInferior(); 
		
		RecolectorDeMineral recolector = jugadorTurno.crearRecolectorDeMineral(unTerreno);
		
		assertTrue(jugadorTurno.getCantidadDeMinerales() < recursoMineral);
		
		//Lo mismo para el otro jugador
		xtremGame.quienJuega().pasarTurno();
		unTerreno = mapa.getCeldaEnFilaColumna(2, 6).getCapaInferior(); 
		RecolectorDeMineral recolector2 = xtremGame.quienJuega().crearRecolectorDeMineral(unTerreno);
		xtremGame.quienJuega().pasarTurno();
		//Volvemos al jugador inical
		
		assertTrue(recolector.estaEnConstruccion());
		assertTrue(recolector2.estaEnConstruccion());
		
		for(int j = 0; j < 40; j++){
			xtremGame.quienJuega().pasarTurno();
		}
		nombreJugadorTurno = xtremGame.quienJuega().nombre();
		assertEquals(nombreJugadorTurno, "noob");
		assertFalse(recolector.estaEnConstruccion());
		assertFalse(recolector2.estaEnConstruccion());
		assertTrue(jugadorTurno.getCantidadDeMinerales() > recursoMineral);
		
		xtremGame.quienJuega().pasarTurno();
		assertTrue(xtremGame.quienJuega().getCantidadDeMinerales() > recursoMineral);
		
		
		recursoMineral = xtremGame.quienJuega().getCantidadDeMinerales();
		unTerreno = mapa.getCeldaEnFilaColumna(0, 0).getCapaInferior(); 
		Barraca barracaJugadorUno = xtremGame.quienJuega().crearBarraca(unTerreno);
		
		assertTrue(xtremGame.quienJuega().getCantidadDeMinerales() < recursoMineral);

		xtremGame.quienJuega().pasarTurno();
		recursoMineral = xtremGame.quienJuega().getCantidadDeMinerales();
		unTerreno = mapa.getCeldaEnFilaColumna(0, 1).getCapaInferior(); 
		Barraca barracaJugadorDos = xtremGame.quienJuega().crearBarraca(unTerreno);
		assertTrue(xtremGame.quienJuega().getCantidadDeMinerales() < recursoMineral);
		
		assertTrue(barracaJugadorUno.estaEnConstruccion());
		assertTrue(barracaJugadorDos.estaEnConstruccion());
		
		for(int j = 0; j < 40; j++){
			xtremGame.quienJuega().pasarTurno();
		}
		
		assertFalse(barracaJugadorUno.estaEnConstruccion());
		assertFalse(barracaJugadorDos.estaEnConstruccion());
		
		Marine marineUno = xtremGame.quienJuega().crearMarine(barracaJugadorDos, mapa);
		
		assertTrue(marineUno.estaEnConstruccion());
		
		for(int j = 0; j < 40; j++){
			xtremGame.quienJuega().pasarTurno();
		}
		
		assertFalse(marineUno.estaEnConstruccion());
		
		unTerreno = mapa.getCeldaEnFilaColumna(0, 2).getCapaInferior(); 
		Fabrica fabricaDos = xtremGame.quienJuega().crearFabrica(unTerreno);
		assertTrue(fabricaDos.estaEnConstruccion());
		for(int j = 0; j < 40; j++){
			xtremGame.quienJuega().pasarTurno();
		}
		assertFalse(fabricaDos.estaEnConstruccion());
		
		unTerreno = mapa.getCeldaEnFilaColumna(1, 10).getCapaInferior();
		RecolectorDeGasVespeno recolectorDeGasDos = xtremGame.quienJuega().crearRecolectorDeGasVespeno(unTerreno);
		assertTrue(recolectorDeGasDos.estaEnConstruccion());
		for(int j = 0; j < 40; j++){
			xtremGame.quienJuega().pasarTurno();
		}
		assertFalse(recolectorDeGasDos.estaEnConstruccion());
			
		unTerreno = mapa.getCeldaEnFilaColumna(0, 3).getCapaInferior(); 
		PuertoEstelar unPuertoDos = xtremGame.quienJuega().crearPuertoEstelar(unTerreno);
		assertTrue(unPuertoDos.estaEnConstruccion());
		for(int j = 0; j < 40; j++){
			xtremGame.quienJuega().pasarTurno();
		}
		assertFalse(unPuertoDos.estaEnConstruccion());
		
		int vidaMarine = marineUno.getVida();
		int vidaBarraca = barracaJugadorUno.getVida();
		xtremGame.quienJuega().atacar(marineUno, barracaJugadorUno);
		assertTrue(vidaBarraca > barracaJugadorUno.getVida());
		vidaBarraca = barracaJugadorUno.getVida();
		
		xtremGame.quienJuega().pasarTurno();
		xtremGame.quienJuega().pasarTurno();
		
		xtremGame.quienJuega().atacar(marineUno, barracaJugadorUno);
		assertTrue(vidaBarraca > barracaJugadorUno.getVida());
		vidaBarraca = barracaJugadorUno.getVida();
		
		assertEquals(marineUno.getVida(), vidaMarine);
	}

}
