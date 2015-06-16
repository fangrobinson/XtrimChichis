package xtremecraft.sistema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JuegoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearJuegoConCantJugadoresNegativoDaError(){
		new Juego(-1);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearJuegoConCantJugadoresCeroDaError(){
		new Juego(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearJuegoConCantJugadoresUnoDaError(){
		new Juego(1);
	}
	
	@Test
	public void creoUnJuegoElTiempoEsCero(){
		Juego juego = new Juego(2);
		int tiempo = juego.tiempo();
		
		assertEquals(tiempo, 0);
	}
	
	@Test
	public void pasoElTiempoYTiempoAvanza(){
		Juego juego = new Juego(2);
		
		juego.pasarTiempo();
		int tiempo = juego.tiempo();
		
		assertTrue(tiempo > 0);
	}
	
	@Test
	public void creoUnJuegoYPuedoConocerQuienEmpieza (){
		Juego juego = new Juego(2);
		Jugador jug = null;
		
		jug= juego.quienJuega();

		assertTrue(jug.getClass() == Jugador.class);
	}
	
	@Test
	public void avanzoUnTurnoYVeoQueElJugadorCambio (){
		Juego juego = new Juego(2);
		Jugador jug = null;
		Jugador jug2 = null;
		
		jug = juego.quienJuega();
		juego.pasarTiempo();
		jug2 = juego.quienJuega(); 

		assertFalse(jug == jug2);
	}
	
	@Test
	public void enUnJuegoDeDosJugadoresSePuedeVerQueLaRondaVuelveAEmpezarDespuesDePasar2Turnos (){
		Juego juego = new Juego(2);
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
		Juego juego = new Juego(2);
		Jugador jug = null;
		Jugador jug2 = null;
		
		juego.pasarTiempo();
		jug = juego.quienJuega();
		juego.pasarTiempo();
		jug2 = juego.quienJuega(); 

		assertFalse(jug == jug2);
	}
	
}
