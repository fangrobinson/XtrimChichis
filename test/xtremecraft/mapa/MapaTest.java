package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.unidades.Goliat;

public class MapaTest {

	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresNegativoDaError(){
		new Mapa(-1);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresCeroDaError(){
		new Mapa(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresUnoDaError(){
		new Mapa(1);
	}
	
	@Test
	public void CrearMapaCreaUnMapaQueTieneAire() {
		Mapa mapa = new Mapa(2);
		assertTrue(mapa.tieneAire());
	}
	
	@Test
	public void CrearMapaCreaUnMapaQueTieneTierra() {
		Mapa mapa = new Mapa(2);
		assertTrue(mapa.tieneTierra());
	}
	
	@Test 
	public void getCeldaEnFilaColumnaDevuelveLaCeldaConEsasCoordenadas(){
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		//coordenadaX coorresponde a la columna y coordenadaY corresponde a la fila:
		assertEquals(unaCelda.getCoordenada().getX(),7);
		assertEquals(unaCelda.getCoordenada().getY(),1);
		
	}
	
	@Test 
	public void calcularDistanciaEntreCeldasDevuelveCeroSiLePasoLaMismaCeldaDosVeces(){
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		double distancia = mapa.calcularDistanciaEntreCeldas(unaCelda, unaCelda);
		
		assertTrue(distancia==0);
	}
	
	@Test 
	public void calcularDistanciaEntreCeldasDevuelveDistanciaMinimaEntreDosCeldas(){
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		Celda otraCelda= mapa.getCeldaEnFilaColumna(4,11);
		double distancia = mapa.calcularDistanciaEntreCeldas(unaCelda, otraCelda);
		
		assertTrue(distancia==5);
	}
	
	@Test 
	public void celdaEstaEnRangoDeAtaqueDevuelveTrueSiUnidadTieneVisionDeLaCeldaAtacada(){
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		Celda otraCelda= mapa.getCeldaEnFilaColumna(4,11);
		Goliat unGoliat = new Goliat(1,7);
		Goliat otroGoliat = new Goliat(4,11);
		unaCelda.ocuparCeldaConUnidad(unGoliat);
		otraCelda.ocuparCeldaConUnidad(otroGoliat);
		
		assertTrue(mapa.celdaAtacadaEstaEnRangoDeVisionDeCeldaAtacante(unaCelda, otraCelda));
		
	}
	
	
	@Test 
	public void celdaEstaEnRangoDeAtaqueDevuelveFalseSiUnidadNoTieneVisionDeLaCeldaAtacada(){
		
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		Celda otraCelda= mapa.getCeldaEnFilaColumna(22,22);
		Goliat unGoliat = new Goliat(1,7);
		Goliat otroGoliat = new Goliat(22,22);
		unaCelda.ocuparCeldaConUnidad(unGoliat);
		otraCelda.ocuparCeldaConUnidad(otroGoliat);
		
		assertFalse(mapa.celdaAtacadaEstaEnRangoDeVisionDeCeldaAtacante(unaCelda, otraCelda));
		
		
	}
	
	
	@Test 
	public void celdaEstaEnRangoDeAtaqueDevuelveFalseSiCeldaDeAtaqueEstaVacia(){
		
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		Celda otraCelda= mapa.getCeldaEnFilaColumna(22,22);
		Goliat unidadGoliat = new Goliat(1,2);
		otraCelda.ocuparCeldaConUnidad(unidadGoliat);
		
		assertFalse(mapa.celdaAtacadaEstaEnRangoDeVisionDeCeldaAtacante(unaCelda, otraCelda));
		
		
	}
	
	@Test 
	public void unidadEnCeldaAtacadaRecibeDanioSiEstaDentroDelRangoDeAtaqueDelAtacante(){
		
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		Celda otraCelda= mapa.getCeldaEnFilaColumna(4,11);
		Goliat unGoliat = new Goliat(1,7);
		Goliat otroGoliat = new Goliat(4,11);
		unaCelda.ocuparCeldaConUnidad(unGoliat);
		otraCelda.ocuparCeldaConUnidad(otroGoliat);
		
		mapa.celdaAtacar(unaCelda,otraCelda);
		
		assertEquals(otroGoliat.getVida(),113);
		
		mapa.celdaAtacar(unaCelda,otraCelda);
		
		assertEquals(otroGoliat.getVida(),101);
		
	}
	
	@Test 
	public void unidadEnCeldaAtacadaNoRecibeDanioSiEstaFueraDelRangoDeAtaqueDelAtacante(){
		
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		Celda otraCelda= mapa.getCeldaEnFilaColumna(22,22);
		Goliat unGoliat = new Goliat(1,7);
		Goliat otroGoliat = new Goliat(22,22);
		unaCelda.ocuparCeldaConUnidad(unGoliat);
		otraCelda.ocuparCeldaConUnidad(otroGoliat);
		
		mapa.celdaAtacar(unaCelda,otraCelda);
		
		assertEquals(otroGoliat.getVida(),125);
		
		mapa.celdaAtacar(unaCelda,otraCelda);
		
		assertEquals(otroGoliat.getVida(),125);
	
	}
}
