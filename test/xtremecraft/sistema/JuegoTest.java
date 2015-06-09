package xtremecraft.sistema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JuegoTest {
	
	@Test
	public void creoUnJuegoElTiempoEsCero(){
		Juego juego = new Juego(2);
		int tiempo = juego.tiempo();
		
		assertEquals(tiempo, 0);
	}
	
	@Test
	public void pasoElTiempoYTiempoAvanza(){
		Juego juego = new Juego(2);
		int tiempo = juego.tiempo();
		
		juego.pasarTiempo();
		
		assertTrue(tiempo > 0);
	}
	
	
}
