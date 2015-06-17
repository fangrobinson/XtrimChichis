package xtremecraft.sistema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class JuegoTest {
	
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearJuegoConCantJugadoresCeroDaError(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		new Juego(jugadores);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearJuegoConCantJugadoresUnoDaError(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
		jugadores.add(ninioRata);
	    
		new Juego(jugadores);
	}
	
	@Test
	public void creoUnJuegoElTiempoEsCero(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Juego juego = new Juego(jugadores);
		int tiempo = juego.tiempo();
		
		assertEquals(tiempo, 0);
	}
	
	@Test
	public void pasoElTiempoYTiempoAvanza(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Juego juego = new Juego(jugadores);
		
		juego.pasarTiempo();
		int tiempo = juego.tiempo();
		
		assertTrue(tiempo > 0);
	}
	
	@Test
	public void creoUnJuegoYPuedoConocerQuienEmpieza (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Juego juego = new Juego(jugadores);
		
		Jugador jug = null;
		
		jug= juego.quienJuega();

		assertTrue(jug.getClass() == Jugador.class);
	}
	
	@Test
	public void avanzoUnTurnoYVeoQueElJugadorCambio (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Juego juego = new Juego(jugadores);
		
		Jugador jug = null;
		Jugador jug2 = null;
		
		jug = juego.quienJuega();
		juego.pasarTiempo();
		jug2 = juego.quienJuega(); 

		assertFalse(jug == jug2);
	}
	
	@Test
	public void enUnJuegoDeDosJugadoresSePuedeVerQueLaRondaVuelveAEmpezarDespuesDePasar2Turnos (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Juego juego = new Juego(jugadores);
		
		Jugador jug = null;
		Jugador jug2 = null;
		
		jug = juego.quienJuega();
		juego.pasarTiempo();
		juego.pasarTiempo();
		jug2 = juego.quienJuega(); 

		assertTrue(jug == jug2);
	}
	
	@Test
	public void enUnJuegoDeDosJugadoresSePuedeVerQueNoSeRepiteElJugador (){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	    Jugador ninioRata = new Jugador("Bart");
	    Jugador noob = new Jugador("Jose");
		jugadores.add(ninioRata);
		jugadores.add(noob);
	
		Juego juego = new Juego(jugadores);
		
		Jugador jug = null;
		Jugador jug2 = null;
		
		juego.pasarTiempo();
		jug = juego.quienJuega();
		juego.pasarTiempo();
		jug2 = juego.quienJuega(); 

		assertFalse(jug == jug2);
	}
	
}
