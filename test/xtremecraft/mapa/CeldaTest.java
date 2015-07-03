package xtremecraft.mapa;

import static org.junit.Assert.*;

import org.junit.Test;

import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Ubicable;

public class CeldaTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}	
	
	@Test
	public void ubicarCapaInferiorUnMarineDevuelveLaMismaCelda(){
		
		Jugador jugador =  crearJugadorConRecursosSuficientesParaConstruir();
		Tierra tierraEsperada = new Tierra(2,2);
		Celda celda_vacia = new Celda(tierraEsperada, new Aire(2,2));
		Ubicable marine = new Marine(jugador);
		Terreno tierraRecibida = celda_vacia.ubicarCapaInferior(marine);
		
		assertEquals(tierraRecibida, tierraEsperada);
	}
	
	@Test(expected = NoSePudoOcuparElTerrenoException.class)
	public void ubicarCapaSuperiorUnMarineLanzaExcepcion(){
		
		Jugador jugador =  crearJugadorConRecursosSuficientesParaConstruir();
		Celda celda = new Celda(new Tierra(1,2), new Aire(1,2));
		Ubicable marine = new Marine(jugador);
		celda.ubicarCapaSuperior(marine);

	}
	
}
