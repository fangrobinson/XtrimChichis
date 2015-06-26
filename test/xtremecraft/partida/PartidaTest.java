package xtremecraft.partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.partida.CantidadDeJugadoresMenorAUnoException;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;

public class PartidaTest {
	
	@Test(expected = CantidadDeJugadoresMenorAUnoException.class)
	public void CrearPartidaConCantJugadoresCeroDaError(){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		new Partida(jugadores);
		
	}
	
	@Test(expected = CantidadDeJugadoresMenorAUnoException.class)
	public void CrearPartidaConCantJugadoresUnoDaError(){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		jugadores.add("ninioRata");
	    
		new Partida(jugadores);
		
	}
	
	@Test
	public void creoUnaPartidaElTiempoEsCero(){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		jugadores.add("ninioRata");
		jugadores.add("noob");
	
		Partida partida = new Partida(jugadores);
		int tiempo = partida.tiempo();
		
		
		assertEquals(tiempo, 0);
		
	}
	/*
	@Test
	public void pasoElTiempoYTiempoAvanza(){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		jugadores.add("ninioRata");
		jugadores.add("noob");
	
		Partida partida = new Partida(jugadores);
		
		partida.pasarTiempo();
		int tiempo = partida.tiempo();
		
		assertTrue(tiempo > 0);
		
	}*/
	
	@Test
	public void creoUnaPartidaYPuedoConocerQuienEmpieza (){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		jugadores.add("ninioRata");
		jugadores.add("noob");
	
		Partida partida = new Partida(jugadores);
		
		Jugador jug = null;
		
		jug= partida.quienJuega();

		assertTrue(jug.getClass() == Jugador.class);
		
	}
	
	@Test
	public void quienJuegaDeberiaDevolverElPrimerJugadorSiNoSePasoElTurno(){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		
		jugadores.add("ninioRata");
		jugadores.add("noob");
		jugadores.add("batman");
		
		Partida partida = new Partida(jugadores);
		
		assertEquals(partida.quienJuega().nombre(),"ninioRata");
		
	}
	
	@Test
	public void quienJuegaDevuelveElSiguienteJugadorSiSePasoElTurno(){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		
		jugadores.add("ninioRata");
		jugadores.add("batman");
		jugadores.add("aquaman");
		jugadores.add("noob");
		
		Partida partida = new Partida(jugadores);
		
		Jugador primerJugador = partida.quienJuega();
		
		primerJugador.pasarTurno();
		
		Jugador segundoJugador = partida.quienJuega();
		
		segundoJugador.pasarTurno();
		
		assertEquals(partida.quienJuega().nombre(),"aquaman");
		
	}
	
	@Test
	public void alPasarLosTurnosDeTodosLosJugadoresDeLaRondaElTurnoVuelveAlPrimerJugador(){
		
		ArrayList<String> jugadores = new ArrayList<String>();
		
		jugadores.add("ninioRata");
		jugadores.add("batman");
		jugadores.add("noob");
		
		Partida partida = new Partida(jugadores);
		
		Jugador primerJugador = partida.quienJuega();
		
		primerJugador.pasarTurno();
		
		Jugador segundoJugador = partida.quienJuega();
		
		segundoJugador.pasarTurno();
		
		Jugador tercerJugador = partida.quienJuega();
		
		tercerJugador.pasarTurno();
		
		assertEquals(partida.quienJuega().nombre(),"ninioRata");
		
	}
	
}
