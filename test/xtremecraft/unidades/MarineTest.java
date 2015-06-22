package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;

public class MarineTest {
	
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test (expected = RecursosInsuficientesException.class)
	public void unidadCreadaParaRazaSinRecursosLanzaExcepcion(){

		Tierra tierra = new Tierra(15,15);
		Terran nacion = new Terran(tierra);
		
		new Marine(nacion);
	}
	
	@Test
	public void testMarineInicializadoConVidaCompleta(){

		Terran nacion = crearRazaTerranValida();
		Marine miniSamus = new Marine(nacion);

		assertEquals(miniSamus.getVida(),40);
		
	}
	
	@Test
	public void testMarineInicializadoConEstadoVivo(){

		Terran nacion = crearRazaTerranValida();
		Marine miniSamus = new Marine(nacion);

		assertTrue(miniSamus.estaVivo());
		
	}
	
	@Test
	public void marinePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Terran nacion = crearRazaTerranValida();
		Marine miniSamus = new Marine(nacion);
		
		assertFalse(miniSamus.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){

		Terran nacion = crearRazaTerranValida();
		Marine miniSamus = new Marine(nacion);

		assertEquals(miniSamus.getRadioVision(),7);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno aire = new Aire(1,3);
		Marine miniSamus = new Marine(nacion);
		Espectro miniMasterChief = new Espectro(nacion);
		
		miniSamus.actualizarUbicacion(tierra);
		miniMasterChief.actualizarUbicacion(aire);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 114);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine(nacion);
		Marine miniMasterChief = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,1);
		Terreno tierra2 = new Tierra(40,10);
		Marine miniSamus = new Marine(nacion);
		Marine miniMasterChief = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(),40);
	}

	
	@Test
	public void actualizarUbicacionIgualaLasCoordenadasDeLaUnidadALasDelTerreno(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Marine miniSamus = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra);
		
		assertEquals(miniSamus.getUbicacionActual(),tierra.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Marine miniSamus = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra1);
		miniSamus.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Marine miniSamus = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra1);
		miniSamus.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno tierraDestino = new Tierra(20,20);
		Marine miniSamus = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra);
		miniSamus.actualizarUbicacion(tierraDestino);
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoAereoSeLanzaExcepcion(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno aireDestino = new Aire(1,1);
		Marine miniSamus = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra);
		miniSamus.actualizarUbicacion(aireDestino);
		
	}
		
	@Test
	public void siUnMarineAtacaAOtroHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine(nacion);
		Marine miniMasterChief = new Marine(nacion);
		int cantidadDeAtaques = 7;
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		for(int i=0;i<cantidadDeAtaques;i++) miniSamus.atacar(miniMasterChief);
		
		assertFalse(miniMasterChief.estaVivo());
		
	}
	
	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		Marine miniSamus = new Marine(nacion);
		NaveTransporte nave = new NaveTransporte(nacion);
		
		miniSamus.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(miniSamus.subirANaveDeTransporte(nave));
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Marine miniSamus = new Marine(nacion);
		int vidaInicial = miniSamus.getVida();
		
		miniSamus.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<miniSamus.tiempoConstruccion();tiempo++) miniSamus.pasarTiempo();
		Radiacion radiacion = new Radiacion(mapa);
		miniSamus.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), miniSamus.getVida());
		
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioActivo(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Marine miniSamus = new Marine(nacion);
		
		miniSamus.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<miniSamus.tiempoConstruccion();tiempo++) miniSamus.pasarTiempo();
		Radiacion radiacion = new Radiacion(mapa);
		miniSamus.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(miniSamus.esRadioactivo());
		
	}

	//TODO: Faltan Tests
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
