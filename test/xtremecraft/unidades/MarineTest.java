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

public class MarineTest {
	
	@Test
	public void testMarineInicializadoConVidaCompleta(){

		Marine miniSamus = new Marine();

		assertEquals(miniSamus.getVida(),40);
		
	}
	
	@Test
	public void testMarineInicializadoConEstadoVivo(){

		Marine miniSamus = new Marine();

		assertTrue(miniSamus.estaVivo());
		
	}
	
	@Test
	public void marinePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Marine miniSamus = new Marine();
		
		assertFalse(miniSamus.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){

		Marine miniSamus = new Marine();

		assertEquals(miniSamus.getRadioVision(),7);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){

		Terreno tierra = new Tierra(1,2);
		Terreno aire = new Aire(1,3);
		Marine miniSamus = new Marine();
		Espectro miniMasterChief = new Espectro();
		
		miniSamus.actualizarUbicacion(tierra);
		miniMasterChief.actualizarUbicacion(aire);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 114);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){


		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terreno tierra1 = new Tierra(1,1);
		Terreno tierra2 = new Tierra(40,10);
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(),40);
	}

	
	@Test
	public void actualizarUbicacionIgualaLasCoordenadasDeLaUnidadALasDelTerreno(){

		Terreno tierra = new Tierra(1,2);
		Marine miniSamus = new Marine();
		
		miniSamus.actualizarUbicacion(tierra);
		
		assertEquals(miniSamus.getUbicacionActual(),tierra.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Marine miniSamus = new Marine();
		
		miniSamus.actualizarUbicacion(tierra1);
		miniSamus.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Marine miniSamus = new Marine();
		
		miniSamus.actualizarUbicacion(tierra1);
		miniSamus.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Terreno tierra = new Tierra(1,2);
		Terreno tierraDestino = new Tierra(20,20);
		Marine miniSamus = new Marine();
		
		miniSamus.actualizarUbicacion(tierra);
		miniSamus.actualizarUbicacion(tierraDestino);
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoAereoSeLanzaExcepcion(){

		Terreno tierra = new Tierra(1,2);
		Terreno aireDestino = new Aire(1,1);
		Marine miniSamus = new Marine();
		
		miniSamus.actualizarUbicacion(tierra);
		miniSamus.actualizarUbicacion(aireDestino);
		
	}
		
	@Test
	public void siUnMarineAtacaAOtroHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){

		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		int cantidadDeAtaques = 7;
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		for(int i=0;i<cantidadDeAtaques;i++) miniSamus.atacar(miniMasterChief);
		
		assertFalse(miniMasterChief.estaVivo());
		
	}
	
	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		Marine miniSamus = new Marine();
		NaveTransporte nave = new NaveTransporte();
		
		miniSamus.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(miniSamus.subirANaveDeTransporte(nave));
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Marine miniSamus = new Marine();
		int vidaInicial = miniSamus.getVida();
		
		miniSamus.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<miniSamus.tiempoConstruccion();tiempo++) miniSamus.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(miniSamus, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		miniSamus.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), miniSamus.getVida());
		
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioActivo(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Marine miniSamus = new Marine();
		
		miniSamus.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<miniSamus.tiempoConstruccion();tiempo++) miniSamus.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(miniSamus, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		miniSamus.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(miniSamus.esRadioactivo());
		
	}

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
