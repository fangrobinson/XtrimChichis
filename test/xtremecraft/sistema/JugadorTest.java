package xtremecraft.sistema;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class JugadorTest {
	
	@Test(expected = NombreMuyCortoException.class)
	public void siSeIntentaCrearUnJugadorConUnNombreMuyCortoSeLanzaNombreMuyCortoException(){
		new Jugador("Ana");
	}
	
	@Test
	public void alCrearseUnJugadorElMismoNoEstaEnJuego(){
		Jugador jugador = new Jugador("Juan");
		boolean juega = jugador.estaEnJuego();
		
		assertFalse(juega);
	}
}
