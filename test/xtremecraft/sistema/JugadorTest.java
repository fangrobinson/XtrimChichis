package xtremecraft.sistema;

import org.junit.Test;

import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Marine;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JugadorTest {
	
	@Test(expected = NombreMuyCortoException.class)
	public void siSeIntentaCrearUnJugadorConUnNombreMuyCortoSeLanzaNombreMuyCortoException(){
		Tierra tierra = new Tierra(1,1);
		new Jugador("Ana",tierra);
	}
	
	@Test
	public void alCrearseUnJugadorElMismoEstaEnJuego(){
		Tierra tierra = new Tierra(1,1);
		Jugador jugador = new Jugador("Juan",tierra);
		boolean juega = jugador.estaEnJuego();
		
		assertTrue(juega);
	}
	
	@Test
	public void esDeMiPropiedadDevuelveFalseSiNoLePerteneceElEdificioOUnidad (){
		boolean bool = true;
		Tierra tierra = new Tierra(1,1);
		Tierra tierra2 = new Tierra(2,1);
		Jugador jugador = new Jugador("Juan",tierra);		
		Jugador jugador2 = new Jugador ("OtroJuan", tierra2);
		jugador.nacion.juntarMinerales(1000);
		Marine marine = new Marine(jugador.nacion);
		
		bool = jugador2.esDeMiPropiedad(marine);
		
		assertFalse(bool);
	}
	
	//TODO: Casi no hay pruebas en jugador. Porfa ayudar con esto. Hay mil cosas que probar.
	
}
