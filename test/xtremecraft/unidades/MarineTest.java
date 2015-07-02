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

public class MarineTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test (expected = RecursosInsuficientesException.class)
	public void unidadCreadaParaRazaSinRecursosLanzaExcepcion(){

		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		
		new Marine(jugador);
		new Marine(jugador);
		new Marine(jugador);
		
	}
	
	@Test
	public void testMarineInicializadoConVidaCompleta(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Marine miniSamus = new Marine(jugador);

		assertEquals(miniSamus.getVida(),40);
		
	}
	
	@Test
	public void testMarineInicializadoConEstadoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Marine miniSamus = new Marine(jugador);

		assertTrue(miniSamus.estaVivo());
		
	}
	
	@Test
	public void marinePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Marine miniSamus = new Marine(jugador);
		
		assertFalse(miniSamus.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Marine miniSamus = new Marine(jugador);

		assertEquals(miniSamus.getRadioVision(),7);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno aire = new Aire(1,3);
		Marine miniSamus = new Marine(jugador);
		Espectro miniMasterChief = new Espectro(jugador);
		
		for (int i = 0; i < miniMasterChief.tiempoConstruccion(); i++){
			miniSamus.pasarTiempo();
			miniMasterChief.pasarTiempo();
		}
		
		miniSamus.setUbicacionInicial(tierra);
		miniMasterChief.setUbicacionInicial(aire);
		
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 114);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine(jugador);
		Marine miniMasterChief = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra1);
		miniMasterChief.setUbicacionInicial(tierra2);
		for (int i = 0; i < miniSamus.tiempoConstruccion(); i++){
			miniSamus.pasarTiempo();
			miniMasterChief.pasarTiempo();
		}
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 34);
	}
	
	@Test (expected = AtaqueFueraDelRangoDeVisionException.class)
	public void siUnMarineAtacaAOtroFueraDeSuRangoSeLanzaAtaqueFueraDeRangoExcepcion(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,1);
		Terreno tierra2 = new Tierra(40,10);
		Marine miniSamus = new Marine(jugador);
		Marine miniMasterChief = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra1);
		miniMasterChief.setUbicacionInicial(tierra2);
		
		for (int i = 0; i < miniSamus.tiempoConstruccion(); i++){
			miniSamus.pasarTiempo();
			miniMasterChief.pasarTiempo();
		}
		miniSamus.atacar(miniMasterChief);
	}

	
	@Test
	public void actualizarUbicacionIgualaLasCoordenadasDeLaUnidadALasDelTerreno(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Marine miniSamus = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra);
		
		assertEquals(miniSamus.getUbicacionActual(),tierra.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Marine miniSamus = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra1);
		miniSamus.setUbicacionInicial(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Marine miniSamus = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra1);
		miniSamus.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno tierraDestino = new Tierra(20,20);
		Marine miniSamus = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra);
		miniSamus.actualizarUbicacion(tierraDestino);
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoAereoSeLanzaExcepcion(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno aireDestino = new Aire(1,1);
		Marine miniSamus = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra);
		miniSamus.actualizarUbicacion(aireDestino);
		
	}
		
	@Test
	public void siUnMarineAtacaAOtroHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine(jugador);
		Marine miniMasterChief = new Marine(jugador);
		int cantidadDeAtaques = 7;
		
		miniSamus.setUbicacionInicial(tierra1);
		miniMasterChief.setUbicacionInicial(tierra2);
		for (int i = 0; i < miniSamus.tiempoConstruccion(); i++){
			miniSamus.pasarTiempo();
			miniMasterChief.pasarTiempo();
		}
		for(int i=0;i<cantidadDeAtaques;i++) miniSamus.atacar(miniMasterChief);
		
		assertFalse(miniMasterChief.estaVivo());
		
	}
	
	@Test (expected = NaveFueraDelRangoDeVisionUnidadException.class)
	public void subirANaveDeTransporteLanzaNaveFueraDelRangoDeVisionUnidadExceptionSiNaveNoEstaDentroDelRangoDeVision(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,48);
		Marine miniSamus = new Marine(jugador);
		NaveTransporte nave = new NaveTransporte(jugador);
		
		miniSamus.setUbicacionInicial(tierra);
		nave.setUbicacionInicial(otraTierra);
		
		nave.transportarNuevaUnidad(miniSamus);
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Marine miniSamus = new Marine(jugador);
		int vidaInicial = miniSamus.getVida();
		
		miniSamus.setUbicacionInicial(tierra);
		for(int tiempo=0;tiempo<miniSamus.tiempoConstruccion();tiempo++) miniSamus.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(miniSamus, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		miniSamus.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), miniSamus.getVida());
		
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioActivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Marine miniSamus = new Marine(jugador);
		
		miniSamus.setUbicacionInicial(tierra);
		for(int tiempo=0;tiempo<miniSamus.tiempoConstruccion();tiempo++) miniSamus.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(miniSamus, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		miniSamus.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(miniSamus.esRadioactivo());
		
	}

}
