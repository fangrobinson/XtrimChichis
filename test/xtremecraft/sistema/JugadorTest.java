package xtremecraft.sistema;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
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
	public void esDeMiPropiedadDevuelveFalseSiNoLePerteneceLaUnidad (){
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
	
	@Test
	public void esDeMiPropiedadDevuelveFalseSiNoLePerteneceElEdificio (){
		boolean bool = true;
		Tierra tierra = new Tierra(1,1);
		Tierra tierra2 = new Tierra(1,2);
		Tierra tierra3 = new Tierra(1,3);
		Jugador jugador = new Jugador("Juan",tierra);
		Jugador jugador2 = new Jugador ("OtroJuan", tierra2);
		jugador.nacion.juntarMinerales(1000);
		Barraca barraca = new Barraca(jugador.nacion, tierra3);
		
		bool = jugador2.esDeMiPropiedad(barraca);
		
		assertFalse(bool);
	}
	
	/*@Test
	public void esDeMiPropiedadDevuelveTrueSiLePerteneceLaUnidad (){
		boolean bool = true;
		Tierra tierra = new Tierra(1,1);
		Tierra tierra2 = new Tierra(1,2);
		Mapa unMapa = new Mapa(2);
		
		Jugador jugador = new Jugador("Juan",tierra);		
		jugador.nacion.juntarMinerales(2000);
		Barraca barraca = jugador.crearBarraca(tierra2);
		
        Celda celda = unMapa.getCeldaEnFilaColumna(1, 2);
		
		unMapa.ubicar(barraca, celda);
		
		Marine marine = jugador.crearMarine(barraca, unMapa);
		
		bool = jugador.esDeMiPropiedad(marine);
		
		assertTrue(bool);
	}*/
	
	@Test
	public void esDeMiPropiedadDevuelveTrueSiLePerteneceElEdificio (){
		boolean bool = true;
		Tierra tierra = new Tierra(1,1);
		Tierra tierra2 = new Tierra(1,2);
		Jugador jugador = new Jugador("Juan",tierra);		
		jugador.nacion.juntarMinerales(1000);
		Barraca barraca = jugador.crearBarraca(tierra2);
		
		bool = jugador.esDeMiPropiedad(barraca);
		
		assertTrue(bool);
	}
	
	

	
}
