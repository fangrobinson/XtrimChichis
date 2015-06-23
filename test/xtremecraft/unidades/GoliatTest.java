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
import xtremecraft.raza.Terran;

public class GoliatTest {
	
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	/*
	@Test (expected = RecursosInsuficientesException.class)
	public void unidadCreadaParaRazaSinRecursosLanzaExcepcion(){

		Tierra tierra = new Tierra(15,15);
		Terran nacion = new Terran(tierra);
		
		new Goliat(nacion);
	}
	*/
	
	@Test
	public void goliatInicializadoConVidaCompleta(){
		
		Terran nacion = crearRazaTerranValida();
		Goliat tanque1 = new Goliat(nacion);
		
		assertEquals(tanque1.getVida(),125);
		
	}
	
	@Test
	public void goliatInicializadoConEstadoVivo(){

		Terran nacion = crearRazaTerranValida();
		Goliat tanque1 = new Goliat(nacion);
		
		assertTrue(tanque1.estaVivo());
		
	}
	
	@Test
	public void goliatPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Terran nacion = crearRazaTerranValida();
		Goliat tanque1 = new Goliat(nacion);
		
		assertFalse(tanque1.puedeUbicarseSobreRecursoNatural());
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){

		Terran nacion = crearRazaTerranValida();
		Goliat tanque1 = new Goliat(nacion);
		
		assertEquals(tanque1.getRadioVision(),8);
	}
	
	@Test
	public void siUnGoliatAtacaAUnEspectroPorAireLeSacaDiezDeVida(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,3);
		Terreno aire = new Aire(1,4);
		Goliat tanque1 = new Goliat(nacion);
		Espectro tanque2 = new Espectro(nacion);
		
		tanque1.actualizarUbicacion(tierra);
		tanque2.actualizarUbicacion(aire);
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.getValor(),110);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque1 = new Goliat(nacion);
		Goliat tanque2 = new Goliat(nacion);
		
		tanque1.actualizarUbicacion(tierra1);
		tanque2.actualizarUbicacion(tierra2);
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.getValor(),113);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,4);
		Terreno aire = new Aire(10,10);
		Goliat tanque1 = new Goliat(nacion);
		Goliat tanque2 = new Goliat(nacion);
		
		tanque1.actualizarUbicacion(tierra);
		tanque2.actualizarUbicacion(aire);
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.getValor(),125);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque1 = new Goliat(nacion);
		Goliat tanque2 = new Goliat(nacion);
		
		tanque1.actualizarUbicacion(tierra1);
		tanque2.actualizarUbicacion(tierra2);
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2);
		}
		
		assertEquals(tanque2.vitalidad.getValor(), 0);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloPasaAEstadoNoVivo(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque1 = new Goliat(nacion);
		Goliat tanque2 = new Goliat(nacion);
		
		tanque1.actualizarUbicacion(tierra1);
		tanque2.actualizarUbicacion(tierra2);
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2);
		}
		
		assertFalse(tanque2.estaVivo());
		
	}
	
	@Test
	public void actualizarUbicacionIgualaLasCoordenadasDeLaUnidadALasDelTerreno(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Goliat tanque = new Goliat(nacion);
		
		tanque.actualizarUbicacion(tierra);

		
		assertEquals(tanque.getUbicacionActual(),tierra.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque = new Goliat(nacion);
		
		tanque.actualizarUbicacion(tierra1);
		tanque.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Goliat tanque = new Goliat(nacion);
		
		tanque.actualizarUbicacion(tierra1);
		tanque.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoAereoSeLanzaExcepcion(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno aireDestino = new Aire(1,1);
		Goliat tanque = new Goliat(nacion);
		
		tanque.actualizarUbicacion(tierra);
		tanque.actualizarUbicacion(aireDestino);
		
	}
		
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno tierraDestino = new Tierra(20,20);
		Goliat tanque = new Goliat(nacion);
		
		tanque.actualizarUbicacion(tierra);
		tanque.actualizarUbicacion(tierraDestino);
		
	}
	

	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		Goliat tanque = new Goliat(nacion);
		NaveTransporte nave = new NaveTransporte(nacion);
		
		tanque.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(tanque.subirANaveDeTransporte(nave));
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat(nacion);
		int vidaInicialGoliat = goliatAtacado.getVida();
		
		goliatAtacado.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicialGoliat-Radiacion.danioIrradiado), goliatAtacado.getVida());
		
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat(nacion);
		
		goliatAtacado.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(goliatAtacado.esRadioactivo());
		
	}

	//TODO: Faltan tests
	/*
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadDisminuyeAlPasarElTiempo(){
	
	}
	
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadNoPuedeMoverse(){
	
	}
	
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadNoPuedeAtacar(){
	
	}
	*/
}

