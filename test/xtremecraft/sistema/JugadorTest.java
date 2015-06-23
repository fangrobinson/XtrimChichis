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
	


	@Test
	public void esDeMiPropiedadDevuelveTrueSiLePerteneceLaUnidad (){
		boolean bool = true;
		Tierra tierra = new Tierra(1,1);
		Tierra tierra2 = new Tierra(1,2);
		Mapa unMapa = new Mapa(2);
		
		Jugador jugador = new Jugador("Juan",tierra);		
		jugador.nacion.juntarMinerales(2000);
		Barraca barraca = jugador.crearBarraca(tierra2);
		
		for(int turno=0;turno<barraca.tiempoConstruccion();turno++) barraca.pasarTiempo();

        Celda celda = unMapa.getCeldaEnFilaColumna(1, 2);
		
		unMapa.ubicar(barraca, celda);
		
		Marine marine = jugador.crearMarine(barraca, unMapa);
		
		bool = jugador.esDeMiPropiedad(marine);
		
		assertTrue(bool);
	}
	
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
	
	@Test (expected = ElAtacanteNoEsDelJugadorException.class)
	public void atacarLanzaElAtacanteNoEsDelJugadorExceptionSiSeLoLlamaConUnaUnidadNoPropia (){
		
		Tierra tierra = new Tierra(1,1);
		Tierra tierra2 = new Tierra(1,2);
		Tierra tierra3 = new Tierra(1,3);
		Tierra tierra4 = new Tierra(1,4);
		Mapa unMapa = new Mapa(2);
		
		Jugador jugador = new Jugador("Juan",tierra);	
		jugador.nacion.juntarMinerales(2000);
		
		Barraca barraca = jugador.crearBarraca(tierra2);
		for(int turno=0;turno<barraca.tiempoConstruccion();turno++) barraca.pasarTiempo();
		
        Celda celda = unMapa.getCeldaEnFilaColumna(1, 2);
		
		unMapa.ubicar(barraca, celda);
		
		Marine marine = jugador.crearMarine(barraca, unMapa);
		for(int turno=0;turno<marine.tiempoConstruccion();turno++) marine.pasarTiempo();
		
		
		Jugador jugador2 = new Jugador("Juan",tierra3);	
		jugador2.nacion.juntarMinerales(2000);
		
		Barraca barraca2 = jugador2.crearBarraca(tierra4);
		for(int turno=0;turno<barraca2.tiempoConstruccion();turno++) barraca2.pasarTiempo();
		
        Celda celda2 = unMapa.getCeldaEnFilaColumna(1, 3);
		
		unMapa.ubicar(barraca, celda2);
		
		Marine marine2 = jugador2.crearMarine(barraca2, unMapa);
		for(int turno=0;turno<marine2.tiempoConstruccion();turno++) marine2.pasarTiempo();
		
		
		jugador.atacar(marine2, marine);
		
	}
	
	@Test
	public void setTurnoHaceQueElJugadorTengaElTurno(){
		
		Tierra tierra1 = new Tierra(1,1);
		Jugador jugador1 = new Jugador("Juan",tierra1);
		
		jugador1.setTurno();
		
		assertTrue(jugador1.tieneTurno());
		
	}
	
	@Test
	public void pasarTurnoHaceQueElJugadorDejeElTurnoActual(){
		
		Tierra tierra1 = new Tierra(1,1);
		Tierra tierra2 = new Tierra(1,2);
		Jugador jugador1 = new Jugador("Juan",tierra1);
		Jugador jugador2 = new Jugador("Juan",tierra2);	
		
		jugador1.setJugadorSiguiente(jugador2);
		jugador1.pasarTurno();
		
		assertFalse(jugador1.tieneTurno());
		
	}

	@Test
	public void pasarTurnoHaceQueElJugadorSiguienteTengaElTurno(){
		
		Tierra tierra1 = new Tierra(1,1);
		Tierra tierra2 = new Tierra(1,2);
		Jugador jugador1 = new Jugador("Juan",tierra1);
		Jugador jugador2 = new Jugador("Juan",tierra2);	
		
		jugador1.setJugadorSiguiente(jugador2);
		jugador1.pasarTurno();
		
		assertTrue(jugador2.tieneTurno());
		
	}
	
}
