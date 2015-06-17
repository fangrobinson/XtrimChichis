package xtremecraft.sistema;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import static org.junit.Assert.assertTrue;

public class JugadorTest {
	
	@Test(expected = NombreMuyCortoException.class)
	public void siSeIntentaCrearUnJugadorConUnNombreMuyCortoSeLanzaNombreMuyCortoException(){
		Terreno terreno = new Tierra(1,1);
		new Jugador("Ana", terreno);
	}
	
	@Test
	public void alCrearseUnJugadorElMismoEstaEnJuego(){
		Terreno terreno = new Tierra(1,1);
		Jugador jugador = new Jugador("Juan", terreno);
		boolean juega = jugador.estaEnJuego();
		
		assertTrue(juega);
	}
}
