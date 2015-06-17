package xtremecraft.sistema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class PartidaTest {
	
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearPartidaConCantJugadoresCeroDaError(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		new Partida(jugadores);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearPartidaConCantJugadoresUnoDaError(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
		jugadores.add(ninioRata);
	    
		new Partida(jugadores);
	}
	
	@Test
	public void creoUnaPartidaElTiempoEsCero(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Partida partida = new Partida(jugadores);
		int tiempo = partida.tiempo();
		
		assertEquals(tiempo, 0);
	}
	
	@Test
	public void pasoElTiempoYTiempoAvanza(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Partida partida = new Partida(jugadores);
		
		partida.pasarTiempo();
		int tiempo = partida.tiempo();
		
		assertTrue(tiempo > 0);
	}
	
	@Test
	public void creoUnaPartidaYPuedoConocerQuienEmpieza (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Partida partida = new Partida(jugadores);
		
		Jugador jug = null;
		
		jug= partida.quienJuega();

		assertTrue(jug.getClass() == Jugador.class);
	}
	
	@Test
	public void avanzoUnTurnoYVeoQueElJugadorCambio (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Partida partida = new Partida(jugadores);
		
		Jugador jug = null;
		Jugador jug2 = null;
		
		jug = partida.quienJuega();
		partida.pasarTiempo();
		jug2 = partida.quienJuega(); 

		assertFalse(jug == jug2);
	}
	
	@Test
	public void enUnaPartidaDeDosJugadoresSePuedeVerQueLaRondaVuelveAEmpezarDespuesDePasar2Turnos (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Partida partida = new Partida(jugadores);
		
		Jugador jug = null;
		Jugador jug2 = null;
		
		jug = partida.quienJuega();
		partida.pasarTiempo();
		partida.pasarTiempo();
		jug2 = partida.quienJuega(); 

		assertTrue(jug == jug2);
	}
	
	@Test
	public void enUnaPartidaDeDosJugadoresSePuedeVerQueNoSeRepiteElJugador (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Partida partida = new Partida(jugadores);
		
		Jugador jug = null;
		Jugador jug2 = null;
		
		partida.pasarTiempo();
		jug = partida.quienJuega();
		partida.pasarTiempo();
		jug2 = partida.quienJuega(); 

		assertFalse(jug == jug2);
	}
	
}
