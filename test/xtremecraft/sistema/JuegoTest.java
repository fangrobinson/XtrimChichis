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
		
		juego.pasarTiempo();
		int tiempo = juego.tiempo();
		
		assertTrue(tiempo > 0);
	}
	
	@Test
	public void pasoElTiempoYAvanzaLaCreacionDeUnaConstruccion(){
		Juego juego = new Juego(2);
		
		juego.pasarTiempo();
		
		
	}
	
	
}
