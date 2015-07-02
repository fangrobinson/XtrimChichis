package xtremecraft.partida;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.JugadorNoTieneElTurnoException;
import xtremecraft.partida.NombreMuyCortoException;
import xtremecraft.raza.IteradorEdificios;
import xtremecraft.raza.IteradorUnidades;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.AtaqueFueraDelRangoDeVisionException;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JugadorTest {
	
	@Test(expected = NombreMuyCortoException.class)
	public void siSeIntentaCrearUnJugadorConUnNombreMuyCortoSeLanzaNombreMuyCortoException(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		new Jugador("Ana",tierra, mapa);
		
	}
	
	@Test
	public void alCrearseUnJugadorElMismoEstaEnJuego(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Jugador jugador = new Jugador("Juan",tierra, mapa);
		boolean juega = jugador.estaEnJuego();
		
		assertTrue(juega);
		
	}
	
	@Test
	public void esDeMiPropiedadDevuelveFalseSiNoLePerteneceLaUnidad (){
		
		boolean unidadEsDeMiPropiedad = true;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(1, 5).getCapaInferior();
		Jugador jugador = new Jugador("Juan",tierra, mapa);
		Jugador jugador2 = new Jugador("OtroJuan",tierra2, mapa);
		jugador.nacion().juntarMinerales(1000);
		Marine marine = new Marine(jugador);
		
		unidadEsDeMiPropiedad = jugador2.esDeMiPropiedad(marine);
		
		assertFalse(unidadEsDeMiPropiedad);
		
	}
	
	@Test
	public void esDeMiPropiedadDevuelveFalseSiNoLePerteneceElEdificio (){
		
		boolean unidadEsDeMiPropiedad = true;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(1, 5).getCapaInferior();
		Tierra tierra3 = (Tierra) mapa.getCeldaEnFilaColumna(1, 3).getCapaInferior();
		Jugador jugador = new Jugador("Juan",tierra, mapa);
		Jugador jugador2 = new Jugador("OtroJuan",tierra2, mapa);
		jugador.nacion().juntarMinerales(1000);
		jugador.nacion().juntarMinerales(1000);
		Barraca barraca = new Barraca(jugador, tierra3);
		
		unidadEsDeMiPropiedad = jugador2.esDeMiPropiedad(barraca);
		
		assertFalse(unidadEsDeMiPropiedad);
		
	}
	
	@Test
	public void esDeMiPropiedadDevuelveTrueSiLePerteneceLaUnidad () throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		boolean unidadEsDeMiPropiedad = true;
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(1, 4).getCapaInferior();
		Jugador jugador = new Jugador("Juan",tierra, mapa);
		
		jugador.nacion().juntarMinerales(2000);
		
		Barraca barraca = jugador.crearBarraca(tierra2, mapa);
		
		for(int turno=0;turno<barraca.tiempoConstruccion();turno++) barraca.pasarTiempo();
		
		Marine marine = jugador.crearMarine(barraca, mapa);
		
		unidadEsDeMiPropiedad = jugador.esDeMiPropiedad(marine);
		
		assertTrue(unidadEsDeMiPropiedad);
		
	}
	
	@Test
	public void esDeMiPropiedadDevuelveTrueSiLePerteneceElEdificio (){
		
		Mapa mapa = new Mapa(2);
		boolean unidadEsDeMiPropiedad = true;
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(2, 2).getCapaInferior();
		Jugador jugador = new Jugador("Juan",tierra, mapa);
		
		jugador.nacion().juntarMinerales(1000);
		Barraca barraca = jugador.crearBarraca(tierra2, mapa);
		
		unidadEsDeMiPropiedad = jugador.esDeMiPropiedad(barraca);
		assertTrue(unidadEsDeMiPropiedad);
		
	}
	
	@Test (expected = SeleccionadoNoEsPropiedadDelJugadorException.class)
	public void atacarLanzaElAtacanteNoEsDelJugadorExceptionSiSeLoLlamaConUnaUnidadNoPropia () throws AtaqueFueraDelRangoDeVisionException, SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(1, 4).getCapaInferior();
		Tierra tierra3 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Tierra tierra4 = (Tierra) mapa.getCeldaEnFilaColumna(5, 2).getCapaInferior();
		
		Jugador jugador = new Jugador("Juan",tierra, mapa);	
		jugador.nacion().juntarMinerales(2000);
		
		Barraca barraca = jugador.crearBarraca(tierra2, mapa);
		for(int turno=0;turno<barraca.tiempoConstruccion();turno++) barraca.pasarTiempo();
		
		Marine marine = jugador.crearMarine(barraca, mapa);
		for(int turno=0;turno<marine.tiempoConstruccion();turno++) marine.pasarTiempo();
		
		
		Jugador jugador2 = new Jugador("Juan",tierra3, mapa);	
		jugador2.nacion().juntarMinerales(2000);
		
		Barraca barraca2 = jugador2.crearBarraca(tierra4, mapa);
		for(int turno=0;turno<barraca2.tiempoConstruccion();turno++) barraca2.pasarTiempo();
		
		
		Marine marine2 = jugador2.crearMarine(barraca2, mapa);
		for(int turno=0;turno<marine2.tiempoConstruccion();turno++) marine2.pasarTiempo();
		
		jugador.atacar(marine2, marine);
		
	}
	
	@Test
	public void setTurnoHaceQueElJugadorTengaElTurno(){
		

		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Jugador jugador1 = new Jugador("Juan",tierra, mapa);
		
		jugador1.setTurno();
		
		assertTrue(jugador1.tieneTurno());
		
	}
	
	@Test
	public void pasarTurnoHaceQueElJugadorDejeElTurnoActual(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Jugador jugador1 = new Jugador("Juan", tierra, mapa);
		Jugador jugador2 = new Jugador("Pepe", tierra2, mapa);	
		
		jugador1.setTurno();
		jugador1.setJugadorSiguiente(jugador2);
		jugador1.pasarTurno();
		
		assertFalse(jugador1.tieneTurno());
		
	}

	@Test
	public void pasarTurnoHaceQueElJugadorSiguienteTengaElTurno(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Jugador jugador1 = new Jugador("Juan", tierra, mapa);
		Jugador jugador2 = new Jugador("Pepe", tierra2, mapa);	
		
		jugador1.setTurno();
		jugador1.setJugadorSiguiente(jugador2);
		jugador1.pasarTurno();
		
		assertTrue(jugador2.tieneTurno());
		
	}
	
	@Test(expected = JugadorNoTieneElTurnoException.class)
	public void siUnJugadorQueNoTieneTurnoIntentaPasarElTurnoSeLanzaJugadorNoTieneElTurnoException(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Tierra tierra3 = (Tierra) mapa.getCeldaEnFilaColumna(5, 2).getCapaInferior();
		Jugador jugador1 = new Jugador("Flash", tierra, mapa);
		Jugador jugador2 = new Jugador("Batman", tierra2, mapa);
		Jugador jugador3 = new Jugador("LinternaVerde", tierra3, mapa);
		
		jugador1.setJugadorSiguiente(jugador2);
		jugador2.setJugadorSiguiente(jugador3);
		jugador3.setTurno();
		jugador1.pasarTurno();
		
		
	}
	
	@Test
	public void alPasarTiempoSeConstruyeLaBarraca(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Jugador jugador = new Jugador("Batman", tierra, mapa);
		
		jugador.nacion().juntarMinerales(2000);
		
		jugador.crearBarraca(tierra2, mapa);
		
		for (int i =0; i < 12; i++ ){
			jugador.pasarTiempo();
		}
		
		IteradorEdificios iter = new IteradorEdificios(jugador.nacion().edificios());
		
		assertEquals(iter.cuantosHayCreadosDe(Barraca.class), 1);
		
	
	}
	
	@Test
	public void alPasarTiempoSeConstruyeElDepositoDeSuministros(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Jugador jugador = new Jugador("Batman", tierra, mapa);
		
		jugador.nacion().juntarMinerales(2000);
		
		jugador.crearDepositoDeSuministros(tierra2, mapa);
		
		for (int i =0; i < 6; i++ ){
			jugador.pasarTiempo();
		}
		
		IteradorEdificios iter = new IteradorEdificios(jugador.nacion().edificios());
		
		assertEquals(iter.cuantosHayCreadosDe(DepositoDeSuministros.class), 2);
		
	
	}
	
	@Test
	public void alPasarTiempoSeConstruyeLaFabrica(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Tierra tierra3 = (Tierra) mapa.getCeldaEnFilaColumna(5, 2).getCapaInferior();
		Jugador jugador = new Jugador("Batman", tierra, mapa);
		
		jugador.nacion().juntarMinerales(2000);
		
		jugador.crearBarraca(tierra2, mapa);
		
		for (int i =0; i < 12; i++ ){
			jugador.pasarTiempo();
		}
		
		jugador.crearFabrica(tierra3, mapa);
		
		for (int i =0; i < 12; i++ ){
			jugador.pasarTiempo();
		}
		
		IteradorEdificios iter = new IteradorEdificios(jugador.nacion().edificios());
		
		assertEquals(iter.cuantosHayCreadosDe(Fabrica.class), 1);
		
	
	}
	
	@Test
	public void alPasarTiempoSeConstruyeElRecolectorDeMineral(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Jugador jugador = new Jugador("Batman", tierra, mapa);
		
		jugador.nacion().juntarMinerales(2000);
		
		MinaDeMinerales mina = new MinaDeMinerales(30);
		tierra2.agregarRecursoNatural(mina);
		
		jugador.crearRecolectorDeMineral(tierra2, mapa);
		
		for (int i =0; i < 4; i++ ){
			jugador.pasarTiempo();
		}
		
		IteradorEdificios iter = new IteradorEdificios(jugador.nacion().edificios());
		
		assertEquals(iter.cuantosHayCreadosDe(RecolectorDeMineral.class), 1);
		
	
	}
	
	@Test
	public void alPasarTiempoSeConstruyeElRecolectorDeGasVespeno(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Jugador jugador = new Jugador("Batman", tierra, mapa);
		
		jugador.nacion().juntarMinerales(2000);
		
		VolcanGasVespeno volcancito = new VolcanGasVespeno(40);
		tierra2.agregarRecursoNatural(volcancito);
		jugador.crearRecolectorDeGasVespeno(tierra2, mapa);
		
		for (int i =0; i < 6; i++ ){
			jugador.pasarTiempo();
		}
		
		IteradorEdificios iter = new IteradorEdificios(jugador.nacion().edificios());
		
		assertEquals(iter.cuantosHayCreadosDe(RecolectorDeGasVespeno.class), 1);
		
	}
	
	@Test
	public void alPasarTiempoSeConstruyeElPuertoEstelar(){
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Tierra tierra3 = (Tierra) mapa.getCeldaEnFilaColumna(5, 2).getCapaInferior();
		Tierra tierra4 = (Tierra) mapa.getCeldaEnFilaColumna(4, 5).getCapaInferior();
		Jugador jugador = new Jugador("Flash", tierra, mapa);
		
		jugador.nacion().juntarMinerales(2000);
		
        jugador.crearBarraca(tierra2, mapa);
		
		for (int i =0; i < 12; i++ ){
			jugador.pasarTiempo();
		}
		
		jugador.crearFabrica(tierra3, mapa);
		
		for (int i =0; i < 12; i++ ){
			jugador.pasarTiempo();
		}
		
		jugador.crearPuertoEstelar(tierra4, mapa);
		
		for (int i =0; i < 10; i++ ){
			jugador.pasarTiempo();
		}
		
		IteradorEdificios iter = new IteradorEdificios(jugador.nacion().edificios());
		
		assertEquals(iter.cuantosHayCreadosDe(PuertoEstelar.class), 1);
		
	}
	
	@Test
	public void alPasarTiempoSeConstruyeUnaUnidad() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(3, 2).getCapaInferior();
		Tierra tierra3 = (Tierra) mapa.getCeldaEnFilaColumna(5, 2).getCapaInferior();
		Jugador jugador = new Jugador("Batman", tierra, mapa);
		
		
		jugador.nacion().juntarMinerales(2000);
		
		jugador.crearBarraca(tierra2, mapa);
		
		for (int i =0; i < 12; i++ ){
			jugador.pasarTiempo();
		}
		
		Fabrica unaFabrica = jugador.crearFabrica(tierra3, mapa);
		
		for (int i =0; i < 12; i++ ){
			jugador.pasarTiempo();
		}
		
		Mapa unMapa = new Mapa(2);
		
		jugador.crearGoliat(unaFabrica, unMapa);
		
		for (int i =0; i < 6; i++ ){
			jugador.pasarTiempo();
		}
		
		IteradorUnidades iter = new IteradorUnidades(jugador.nacion().unidades());
		
		assertEquals(iter.cuantosHayCreadosDe(Goliat.class),1);
		
	}
	
	
}
