package xtremecraft.sistema;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JuegoTest {
	
	@Test
	public void creoUnJuegoElTiempoEsCero(){
		Juego juego = new Juego(2);
		int tiempo = juego.tiempo();
		
		assertEquals(tiempo, 0);
	}
}
