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

public class NaveCienciaTest {
	
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
		
		new NaveCiencia(jugador);
	}
	
	@Test
	public void naveCienciaSeInicializaConBarraDeVidaCompleta(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		assertEquals(naveCiencia.getVida(),200);
	
	}
	
	@Test
	public void naveCienciaInicializadoConEstadoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		assertTrue(naveCiencia.estaVivo());
		
	}
	
	@Test
	public void naveCienciaPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
	
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		assertFalse(naveCiencia.puedeUbicarseSobreRecursoNatural());
	
	}
	
	@Test
	public void naveCienciaSeInicializaConRadioDeVision(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		assertEquals(naveCiencia.getRadioVision(),10);
	
	}
	
	@Test
	public void naveCienciaSeInicializaConEnergia(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		assertEquals(naveCiencia.getEnergia(),50);
	
	}
	
	@Test
	public void aumentarEnergiaEnTurnoAumentaNivelDeEnergia(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),60);
		
		//naveCiencia.aumentarEnergiaEnTurno();
		
		//assertEquals(naveCiencia.getEnergia(),70);
	
	}
	
	@Test
	public void aumentarEnergiaEnTurnoSiEnergiaEsMayorOIgualADoscientosNoAumentaNivelDeEnergia(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		for(int i=0;i<15;i++){
			naveCiencia.aumentarEnergiaEnTurno();
		}
		//assertEquals(naveCiencia.getEnergia(),200);
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),200);
	
	}
	
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno unTerreno=new Aire(1,4);
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		naveCiencia.actualizarUbicacion(unTerreno);
		
		assertEquals(naveCiencia.getUbicacionActual(),unTerreno.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		naveCiencia.actualizarUbicacion(tierra1);
		naveCiencia.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		naveCiencia.actualizarUbicacion(tierra1);
		naveCiencia.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,2);
		Terreno aireDestino = new Aire(20,20);
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		naveCiencia.actualizarUbicacion(aire);
		naveCiencia.actualizarUbicacion(aireDestino);
		
	}
	
	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		NaveTransporte nave = new NaveTransporte(jugador);
		
		naveCiencia.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(naveCiencia.subirANaveDeTransporte(nave));
				
	}
	
	@Test
	public void atacarConMisilEMPLeQuitaLaEnergiaAOtraUnidadMagica(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno otroAire = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		NaveCiencia naveCienciaAtacada = new NaveCiencia(jugador);
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		naveCienciaAtacada.actualizarUbicacion(otroAire);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(naveCienciaAtacada.getEnergia(),0);
				
	}
	
	@Test
	public void atacarConMisilEMPSiHayUnaUnidadNoMagicaEnElRadioDeImpactoNoRecibeDanio(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno otroAire = mapa.getCeldaEnFilaColumna(7,7).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		NaveCiencia naveCienciaAtacada = new NaveCiencia(jugador);
		Goliat goliatEnRadioDeImpacto = new Goliat(jugador);
		int vidaInicialGoliat = goliatEnRadioDeImpacto.getVida();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		naveCienciaAtacada.actualizarUbicacion(otroAire);
		goliatEnRadioDeImpacto.actualizarUbicacion(tierra);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(goliatEnRadioDeImpacto.getVida(),vidaInicialGoliat);
				
	}
	
	@Test
	public void atacarConMisilEMPSiHayUnaUnidadMagicaEnElRadioDeImpactoPierdeSuEnergia(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		Terreno aire3 = mapa.getCeldaEnFilaColumna(7,7).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		NaveCiencia naveCienciaAtacada = new NaveCiencia(jugador);
		NaveCiencia naveCienciaEnRadioDeImpacto = new NaveCiencia(jugador);
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaEnRadioDeImpacto.actualizarUbicacion(aire3);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(naveCienciaEnRadioDeImpacto.getEnergia(),0);
				
	}
	
	@Test(expected = AtaqueFueraDelRangoDeVisionException.class)
	public void atacarConMisilEMPSiHayUnaUnidadMagicaEnElRadioPeroNoEnElMismoTerrenoQueElAtacadoNoPierdeEnergia(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(20,21).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		NaveCiencia naveCienciaAtacada = new NaveCiencia(jugador);
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
				
	}
	
	@Test
	public void atacarConMisilEMPSiUnaNaveIntentaLanzarUnMisilFueraDeSuRangoDeVisionSeLanzaUnaExcepcion(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(7,7).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		NaveCiencia naveCienciaAtacada = new NaveCiencia(jugador);
		NaveCiencia naveCienciaEnRadioDeImpacto = new NaveCiencia(jugador);
		int energiaInicialNaveEnRadioDeImpacto = naveCienciaEnRadioDeImpacto.getEnergia();
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaEnRadioDeImpacto.actualizarUbicacion(tierra);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertEquals(naveCienciaEnRadioDeImpacto.getEnergia(),energiaInicialNaveEnRadioDeImpacto);
				
	}
	
	@Test
	public void atacarConMisilEMPLeSacaEnergiaALaNaveAtacante(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire1 = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno aire2 = mapa.getCeldaEnFilaColumna(6,6).getCapaSuperior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		NaveCiencia naveCienciaAtacada = new NaveCiencia(jugador);
		int energiaInicialNaveAtacante = naveCienciaAtacante.getEnergia();
		
		naveCienciaAtacante.actualizarUbicacion(aire1);
		naveCienciaAtacada.actualizarUbicacion(aire2);
		naveCienciaAtacante.atacarConMisilEMP(mapa,naveCienciaAtacada);
		
		assertTrue(energiaInicialNaveAtacante > naveCienciaAtacante.getEnergia());
				
	}
	
	@Test
	public void atacarConRadiacionLeSacaEnergiaALaNaveAtacante(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		Goliat goliatAtacado = new Goliat(jugador);
		int energiaInicialNaveAtacante = naveCienciaAtacante.getEnergia();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		goliatAtacado.actualizarUbicacion(tierra);
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		naveCienciaAtacante.atacarConRadiacion(celdasAfectadas,goliatAtacado);
		
		assertTrue(energiaInicialNaveAtacante > naveCienciaAtacante.getEnergia());
				
	}
	
	@Test(expected = AtaqueFueraDelRangoDeVisionException.class)
	public void siSeIntentaAtacarConRadiacionAUnaUnidadFueraDelRangoDeVisionSeLanzaExcepcion(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(20,18).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		Goliat goliatAtacado = new Goliat(jugador);
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		goliatAtacado.actualizarUbicacion(tierra);
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		naveCienciaAtacante.atacarConRadiacion(celdasAfectadas,goliatAtacado);
				
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		
		naveCiencia.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<naveCiencia.tiempoConstruccion();tiempo++) naveCiencia.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(naveCiencia, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		naveCiencia.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(naveCiencia.esRadioactivo());
		
	}

	@Test
	public void atacarConRadiacionHaceQueLaUnidadAtacadaMueraDespuesDePasarUnTiempo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		Goliat goliatAtacado = new Goliat(jugador);
		int tiempoMuerteUnidad = (int)(goliatAtacado.getVida()/Radiacion.danioIrradiado);
		
		goliatAtacado.actualizarUbicacion(tierra);
		naveCienciaAtacante.actualizarUbicacion(aire);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		naveCienciaAtacante.atacarConRadiacion(celdasAfectadas,goliatAtacado);
		for(int tiempo=0;tiempo<tiempoMuerteUnidad;tiempo++) goliatAtacado.pasarTiempo();
		
		assertEquals(goliatAtacado.getVida(),0);
				
	}
	
	@Test
	public void atacarConRadiacionHaceQueLasUnidadesADistanciaMenorOIgualAUnoDeLaAfectadaRecibanDanio(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno aire = mapa.getCeldaEnFilaColumna(5,5).getCapaSuperior();
		Terreno tierra = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno otraTierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCienciaAtacante = new NaveCiencia(jugador);
		Goliat goliatAtacado = new Goliat(jugador);
		Goliat goliatIrradiado = new Goliat(jugador);
		int vidaInicialIrradiado = goliatIrradiado.getVida();
		
		naveCienciaAtacante.actualizarUbicacion(aire);
		goliatAtacado.actualizarUbicacion(tierra);
		goliatIrradiado.actualizarUbicacion(otraTierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatIrradiado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		naveCienciaAtacante.atacarConRadiacion(celdasAfectadas,goliatAtacado);
		goliatAtacado.pasarTiempo();
		
		assertTrue(vidaInicialIrradiado > goliatIrradiado.getVida());
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveCiencia naveCiencia = new NaveCiencia(jugador);
		int vidaInicial = naveCiencia.getVida();
		
		naveCiencia.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<naveCiencia.tiempoConstruccion();tiempo++) naveCiencia.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(naveCiencia, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		naveCiencia.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), naveCiencia.getVida());
		
	}
	
}
