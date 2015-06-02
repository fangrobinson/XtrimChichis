package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
		assertEquals(unaCelda.getX(),1);
		assertEquals(unaCelda.getY(),7);
		
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
}
