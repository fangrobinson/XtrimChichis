package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class NaveCienciaTest {
	
	@Test
	public void naveCienciaSeInicializaConBarraDeVidaCompleta(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertEquals(naveCiencia.getVida(),200);
	
	}
	
	@Test
	public void naveCienciaInicializadoConEstadoVivo(){

		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertTrue(naveCiencia.estaVivo());
		
	}
	
	@Test
	public void naveCienciaPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
	
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertFalse(naveCiencia.puedeUbicarseSobreRecursoNatural());
	
	}
	
	@Test
	public void naveCienciaSeInicializaConRadioDeVision(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertEquals(naveCiencia.getRadioVision(),10);
	
	}
	
	@Test
	public void naveCienciaSeInicializaConEnergia(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertEquals(naveCiencia.getEnergia(),50);
	
	}
	
	@Test
	public void aumentarEnergiaEnTurnoAumentaNivelDeEnergia(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),60);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),70);
	
	}
	
	@Test
	public void aumentarEnergiaEnTurnoSiEnergiaEsMayorOIgualADoscientosNoAumentaNivelDeEnergia(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		for(int i=0;i<15;i++){
			naveCiencia.aumentarEnergiaEnTurno();
		}
		
		assertEquals(naveCiencia.getEnergia(),200);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),200);
	
	}
	
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unTerreno=new Aire(1,4);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(unTerreno);
		
		assertEquals(naveCiencia.getUbicacionActual(),unTerreno.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(tierra1);
		naveCiencia.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(tierra1);
		naveCiencia.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Terreno aire = new Aire(1,2);
		Terreno aireDestino = new Aire(20,20);
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(aire);
		naveCiencia.actualizarUbicacion(aireDestino);
		
	}
	
	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		NaveCiencia naveCiencia = new NaveCiencia();
		NaveTransporte nave = new NaveTransporte();
		
		naveCiencia.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(naveCiencia.subirANaveDeTransporte(nave));
				
	}
	
	@Test
	public void atacarConMisilEMPLeQuitaLaEnergiaAOtraUnidadMagica(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno otroAire = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		NaveCiencia naveCienciaAtacada = new NaveCiencia();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		naveCienciaAtacada.actualizarUbicacion(otroAire);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(naveCienciaAtacada.getEnergia(),0);
				
	}
	
	@Test
	public void atacarConMisilEMPSiHayUnaUnidadNoMagicaEnElRadioDeImpactoNoRecibeDanio(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno otroAire = mapa.getCeldaEnFilaColumna(7,7).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		NaveCiencia naveCienciaAtacada = new NaveCiencia();
		Goliat goliatEnRadioDeImpacto = new Goliat();
		int vidaInicialGoliat = goliatEnRadioDeImpacto.getVida();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		naveCienciaAtacada.actualizarUbicacion(otroAire);
		goliatEnRadioDeImpacto.actualizarUbicacion(tierra);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(goliatEnRadioDeImpacto.getVida(),vidaInicialGoliat);
				
	}
	
	@Test
	public void atacarConMisilEMPSiHayUnaUnidadMagicaEnElRadioDeImpactoPierdeSuEnergia(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		Terreno aire3 = mapa.getCeldaEnFilaColumna(7,7).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		NaveCiencia naveCienciaAtacada = new NaveCiencia();
		NaveCiencia naveCienciaEnRadioDeImpacto = new NaveCiencia();
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaEnRadioDeImpacto.actualizarUbicacion(aire3);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(naveCienciaEnRadioDeImpacto.getEnergia(),0);
				
	}
	
	@Test(expected = AtaqueFueraDelRangoDeVisionException.class)
	public void atacarConMisilEMPSiHayUnaUnidadMagicaEnElRadioPeroNoEnElMismoTerrenoQueElAtacadoNoPierdeEnergia(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(40,40).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		NaveCiencia naveCienciaAtacada = new NaveCiencia();
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
				
	}
	
	@Test
	public void atacarConMisilEMPSiUnaNaveIntentaLanzarUnMisilFueraDeSuRangoDeVisionSeLanzaUnaExcepcion(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(7,7).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		NaveCiencia naveCienciaAtacada = new NaveCiencia();
		NaveCiencia naveCienciaEnRadioDeImpacto = new NaveCiencia();
		int energiaInicialNaveEnRadioDeImpacto = naveCienciaEnRadioDeImpacto.getEnergia();
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaEnRadioDeImpacto.actualizarUbicacion(tierra);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(naveCienciaEnRadioDeImpacto.getEnergia(),energiaInicialNaveEnRadioDeImpacto);
				
	}
	
	@Test
	public void atacarConMisilEMPLeSacaEnergiaALaNaveAtacante(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		NaveCiencia naveCienciaAtacada = new NaveCiencia();
		int energiaInicialNaveAtacante = naveCienciaAtacante.getEnergia();
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertTrue(energiaInicialNaveAtacante > naveCienciaAtacante.getEnergia());
				
	}
	
	@Test
	public void atacarConRadiacionLeSacaEnergiaALaNaveAtacante(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		Goliat goliatAtacado = new Goliat();
		int energiaInicialNaveAtacante = naveCienciaAtacante.getEnergia();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		goliatAtacado.actualizarUbicacion(tierra);
		naveCienciaAtacante.atacarConRadiacion(mapa,goliatAtacado);
		
		assertTrue(energiaInicialNaveAtacante > naveCienciaAtacante.getEnergia());
				
	}
	
	@Test(expected = AtaqueFueraDelRangoDeVisionException.class)
	public void siSeIntentaAtacarConRadiacionAUnaUnidadFueraDelRangoDeVisionSeLanzaExcepcion(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(20,20).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		Goliat goliatAtacado = new Goliat();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		goliatAtacado.actualizarUbicacion(tierra);
		naveCienciaAtacante.atacarConRadiacion(mapa,goliatAtacado);
				
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<naveCiencia.tiempoConstruccion();tiempo++) naveCiencia.pasarTiempo();
		Radiacion radiacion = new Radiacion(mapa);
		naveCiencia.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(naveCiencia.esRadioactivo());
		
	}

	@Test
	public void atacarConRadiacionHaceQueLaUnidadAtacadaMueraDespuesDePasarUnTiempo(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		Goliat goliatAtacado = new Goliat();
		int tiempoMuerteUnidad = (int)(goliatAtacado.getVida()/Radiacion.danioIrradiado);
		
		goliatAtacado.actualizarUbicacion(tierra);
		naveCienciaAtacante.actualizarUbicacion(aire);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		naveCienciaAtacante.atacarConRadiacion(mapa,goliatAtacado);
		for(int tiempo=0;tiempo<tiempoMuerteUnidad;tiempo++) goliatAtacado.pasarTiempo();
		
		assertEquals(goliatAtacado.getVida(),0);
				
	}
	
	@Test
	public void atacarConRadiacionHaceQueLasUnidadesADistanciaMenorOIgualAUnoDeLaAfectadaRecibanDanio(){
		
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno otraTierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia();
		Goliat goliatAtacado = new Goliat();
		Goliat goliatIrradiado = new Goliat();
		int vidaInicialIrradiado = goliatIrradiado.getVida();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		goliatAtacado.actualizarUbicacion(tierra);
		goliatIrradiado.actualizarUbicacion(otraTierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatIrradiado.pasarTiempo();
		naveCienciaAtacante.atacarConRadiacion(mapa,goliatAtacado);
		goliatAtacado.pasarTiempo();
		
		assertTrue(vidaInicialIrradiado > goliatIrradiado.getVida());
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCiencia = new NaveCiencia();
		int vidaInicial = naveCiencia.getVida();
		
		naveCiencia.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<naveCiencia.tiempoConstruccion();tiempo++) naveCiencia.pasarTiempo();
		Radiacion radiacion = new Radiacion(mapa);
		naveCiencia.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), naveCiencia.getVida());
		
	}
	
}
