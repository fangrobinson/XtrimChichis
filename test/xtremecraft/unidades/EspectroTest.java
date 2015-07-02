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
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;

public class EspectroTest {

	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test (expected = RecursosInsuficientesException.class)
	public void espectroCreadoParaRazaSinRecursosLanzaExcepcion(){

		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		
		new Espectro(jugador);
	}
	
	@Test
	public void espectroInicializadoConVidaCompleta(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Espectro gengar = new Espectro(jugador);
		
		assertEquals(gengar.getVida(),120);
	}
	
	@Test
	public void espectroInicializadoConEstadoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Espectro gengar = new Espectro(jugador);
		
		assertTrue(gengar.estaVivo());
		
	}
	
	@Test
	public void espectroPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Espectro gengar = new Espectro(jugador);
		
		assertFalse(gengar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void espectroGetVisionDevuelveRadioDeVisionDelEspectro(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Espectro gengar = new Espectro(jugador);
		
		assertEquals(gengar.getRadioVision(),7);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorAireLeSacaVeinteDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		Espectro gengar = new Espectro(jugador);
		Espectro misdreavus = new Espectro(jugador);
		
		gengar.setUbicacionInicial(tierra);
		misdreavus.setUbicacionInicial(aire);
		
		for (int i = 0; i < gengar.tiempoConstruccion(); i++){
			gengar.pasarTiempo();
			misdreavus.pasarTiempo();
		}
		
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),100);
	}

	@Test
	public void siUnEspectroAtacaAOtroPorTierraLeSacaOchoDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro gengar = new Espectro(jugador);
		Espectro misdreavus = new Espectro(jugador);
		
		gengar.setUbicacionInicial(tierra1);
		misdreavus.setUbicacionInicial(tierra2);
		
		for (int i = 0; i < gengar.tiempoConstruccion(); i++){
			gengar.pasarTiempo();
			misdreavus.pasarTiempo();
		}
		
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),112);
	}
	
	
	@Test(expected = AtaqueFueraDelRangoDeVisionException.class)
	public void siUnEspectroAtacaAOtroFueraDeSuRangoSeLanzaAtaqueFueraDelRangoDeVisionException(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Aire(1,4);
		Terreno otroTerreno = new Tierra(10,10);
		Espectro gengar = new Espectro(jugador);
		Espectro misdreavus = new Espectro(jugador);
		
		gengar.setUbicacionInicial(unTerreno);
		misdreavus.setUbicacionInicial(otroTerreno);
		
		for (int i = 0; i < gengar.tiempoConstruccion(); i++){
			gengar.pasarTiempo();
			misdreavus.pasarTiempo();
		}
		
		gengar.atacar(misdreavus);
		
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro(jugador);
		Espectro misdreavus = new Espectro(jugador);
		
		gengar.setUbicacionInicial(unTerreno);
		misdreavus.setUbicacionInicial(otroTerreno);
		
		for (int i = 0; i < gengar.tiempoConstruccion(); i++){
			gengar.pasarTiempo();
			misdreavus.pasarTiempo();
		}
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertEquals(misdreavus.vitalidad.getValor(), 0);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloPasaAEstadoNoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro(jugador);
		Espectro misdreavus = new Espectro(jugador);
		
		gengar.setUbicacionInicial(unTerreno);
		misdreavus.setUbicacionInicial(otroTerreno);
		
		for (int i = 0; i < gengar.tiempoConstruccion(); i++){
			gengar.pasarTiempo();
			misdreavus.pasarTiempo();
		}
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertFalse(misdreavus.estaVivo());
		
	}
		
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno unTerreno=new Aire(1,4);
		
		Espectro unEspectro = new Espectro(jugador);
		
		unEspectro.setUbicacionInicial(unTerreno);
		
		assertEquals(unEspectro.getUbicacionActual(),unTerreno.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro unEspectro = new Espectro(jugador);
		
		unEspectro.setUbicacionInicial(tierra1);
		unEspectro.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro unEspectro = new Espectro(jugador);
		
		unEspectro.setUbicacionInicial(tierra1);
		unEspectro.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test (expected = NaveFueraDelRangoDeVisionUnidadException.class)
	public void subirANaveDeTransporteLanzaNaveFueraDelRangoDeVisionUnidadExceptionSiNaveNoEstaDentroDelRangoDeVision(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(35,40);
		Espectro gengar = new Espectro(jugador);
		NaveTransporte nave = new NaveTransporte(jugador);
		
		gengar.setUbicacionInicial(tierra);
		nave.setUbicacionInicial(otraTierra);
		
		nave.transportarNuevaUnidad(gengar);
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Espectro gengar = new Espectro(jugador);
		int vidaInicial = gengar.getVida();
		
		gengar.setUbicacionInicial(tierra);
		for(int tiempo=0;tiempo<gengar.tiempoConstruccion();tiempo++) gengar.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(gengar, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		gengar.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), gengar.getVida());
		
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Espectro gengar = new Espectro(jugador);
		
		gengar.setUbicacionInicial(tierra);
		for(int tiempo=0;tiempo<gengar.tiempoConstruccion();tiempo++) gengar.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(gengar, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		gengar.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(gengar.esRadioactivo());
		
	}

}
