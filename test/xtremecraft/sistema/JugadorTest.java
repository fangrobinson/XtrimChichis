package xtremecraft.sistema;

import org.junit.Test;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JugadorTest {
	
	@Test(expected = NombreMuyCortoException.class)
	public void siSeIntentaCrearUnJugadorConUnNombreMuyCortoSeLanzaNombreMuyCortoException(){
		new Jugador("Ana");
	}
	
	@Test
	public void alCrearseUnJugadorElMismoEstaEnJuego(){
		Jugador jugador = new Jugador("Juan");
		boolean juega = jugador.estaEnJuego();
		
		assertTrue(juega);
	}
}
