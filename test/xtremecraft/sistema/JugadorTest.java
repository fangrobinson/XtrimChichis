package xtremecraft.sistema;

import org.junit.Test;

import xtremecraft.unidades.Marine;
import static org.junit.Assert.assertFalse;
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
	
	@Test
	public void esDeMiPropiedadDevuelveFalseSiNoLePerteneceElEdificioOUnidad (){
		boolean bool = true;
		Jugador juan = new Jugador("Juan");
		Marine marine = new Marine();
		
		bool = juan.esDeMiPropiedad(marine);
		
		assertFalse(bool);
	}
	/*
	@Test
	public void esDeMiPropiedadDevuelveTrueSiLePerteneceElEdificioOUnidad (){
		boolean bool = false;
		Jugador juan = new Jugador("Juan");
		
		juan.crearBarraca()
		
		assertTrue(bool);
	}*/
}
