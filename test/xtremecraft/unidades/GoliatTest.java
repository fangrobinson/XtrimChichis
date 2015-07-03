package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;
import xtremecraft.raza.RecursosInsuficientesException;

public class GoliatTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test (expected = RecursosInsuficientesException.class)
	public void unidadCreadaParaRazaSinRecursosLanzaExcepcion(){

		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		
		new Goliat(jugador);
		new Goliat(jugador);
		
	}

	
	@Test
	public void goliatInicializadoConVidaCompleta(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Goliat tanque1 = new Goliat(jugador);
		
		assertEquals(tanque1.getVida(),125);
		
	}
	
	@Test
	public void goliatInicializadoConEstadoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Goliat tanque1 = new Goliat(jugador);
		
		assertTrue(tanque1.estaVivo());
		
	}
	
	@Test
	public void goliatPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Goliat tanque1 = new Goliat(jugador);
		
		assertFalse(tanque1.puedeUbicarseSobreRecursoNatural());
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Goliat tanque1 = new Goliat(jugador);
		
		assertEquals(tanque1.getRadioVision(),8);
	}
	
	@Test
	public void siUnGoliatAtacaAUnEspectroPorAireLeSacaDiezDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,3);
		Terreno aire = new Aire(1,4);
		Goliat tanque1 = new Goliat(jugador);
		Espectro tanque2 = new Espectro(jugador);
		
		tanque1.setUbicacionInicial(tierra);
		tanque2.setUbicacionInicial(aire);
		
		for (int i = 0; i < tanque2.tiempoConstruccion(); i++){
			tanque1.pasarTiempo();
			tanque2.pasarTiempo();
		}
		
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.getValor(),110);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque1 = new Goliat(jugador);
		Goliat tanque2 = new Goliat(jugador);
		
		tanque1.setUbicacionInicial(tierra1);
		tanque2.setUbicacionInicial(tierra2);
		
		for (int i = 0; i < tanque1.tiempoConstruccion(); i++){
			tanque1.pasarTiempo();
			tanque2.pasarTiempo();
		}
		
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.getValor(),113);
	}
	
	
	@Test (expected = AtaqueFueraDelRangoDeVisionException.class)
	public void siUnGoliatAtacaAOtroFueraDeSuRangoSeLanzaAtaqueFueraDelRangoDeVisionException(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,4);
		Terreno tierraDos = new Tierra(24,24);
		Goliat tanque1 = new Goliat(jugador);
		Goliat tanque2 = new Goliat(jugador);
		
		tanque1.setUbicacionInicial(tierra);
		tanque2.setUbicacionInicial(tierraDos);
		
		tanque1.atacar(tanque2);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque1 = new Goliat(jugador);
		Goliat tanque2 = new Goliat(jugador);
		
		tanque1.setUbicacionInicial(tierra1);
		tanque2.setUbicacionInicial(tierra2);
		
		for (int i = 0; i < tanque1.tiempoConstruccion(); i++){
			tanque1.pasarTiempo();
			tanque2.pasarTiempo();
		}
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2);
			tanque1.pasarTiempo();
		}
		
		assertEquals(tanque2.vitalidad.getValor(), 0);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloPasaAEstadoNoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque1 = new Goliat(jugador);
		Goliat tanque2 = new Goliat(jugador);
		
		tanque1.setUbicacionInicial(tierra1);
		tanque2.setUbicacionInicial(tierra2);
		
		for (int i = 0; i < tanque1.tiempoConstruccion(); i++){
			tanque1.pasarTiempo();
			tanque2.pasarTiempo();
		}
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2);
			tanque1.pasarTiempo();
		}
		
		assertFalse(tanque2.estaVivo());
		
	}
	
	@Test
	public void actualizarUbicacionIgualaLasCoordenadasDeLaUnidadALasDelTerreno(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Goliat tanque = new Goliat(jugador);
		
		tanque.setUbicacionInicial(tierra);

		
		assertEquals(tanque.getUbicacionActual(),tierra.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque = new Goliat(jugador);
		
		tanque.setUbicacionInicial(tierra1);
		tanque.setUbicacionInicial(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque = new Goliat(jugador);
		
		tanque.setUbicacionInicial(tierra1);
		tanque.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoAereoSeLanzaExcepcion(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno aireDestino = new Aire(1,1);
		Goliat tanque = new Goliat(jugador);
		
		tanque.setUbicacionInicial(tierra);
		tanque.actualizarUbicacion(aireDestino);
		
	}
		
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno tierraDestino = new Tierra(20,20);
		Goliat tanque = new Goliat(jugador);
		
		tanque.setUbicacionInicial(tierra);
		tanque.actualizarUbicacion(tierraDestino);
		
	}
	
	@Test (expected = NaveFueraDelRangoDeVisionUnidadException.class)
	public void subirANaveDeTransporteLanzaNaveFueraDelRangoDeVisionUnidadExceptionSiNaveNOEstaDentroDelRangoDeVision(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,45);
		Goliat tanque = new Goliat(jugador);
		NaveTransporte nave = new NaveTransporte(jugador);
		
		tanque.setUbicacionInicial(tierra);
		nave.setUbicacionInicial(otraTierra);
		
		nave.transportarNuevaUnidad(tanque);
					
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat(jugador);
		int vidaInicialGoliat = goliatAtacado.getVida();
		
		goliatAtacado.setUbicacionInicial(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicialGoliat-Radiacion.danioIrradiado), goliatAtacado.getVida());
		
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat(jugador);
		
		goliatAtacado.setUbicacionInicial(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(goliatAtacado.esRadioactivo());
		
	}

}

