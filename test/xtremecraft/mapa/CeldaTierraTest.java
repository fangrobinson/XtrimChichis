package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import xtremecraft.mapa.CeldaTierra;

import org.junit.Test;

public class CeldaTierraTest {

	@Test
	public void nuevaCeldaTierraCreaCeldaConEstadoNoOcupado(){
		
		Celda celda=new CeldaTierra(1,1);
		assertFalse(celda.estaOcupada());
		
	}
	
	@Test
	public void celdaGetXDevuelveCoordenadaHorizontalDeLaCelda(){
		
		Celda celda=new CeldaTierra(1,2);
		assertEquals(celda.getX(), 1);
		
	}
	
	@Test
	public void celdaGetYDevuelveCoordenadaVerticalDeLaCelda(){
		
		Celda celda=new CeldaTierra(1,2);
		assertEquals(celda.getY(),2);
		
	}
	
	@Test
	public void celdaTierraEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparCelda(){
		
		Celda celda=new CeldaTierra(1,4);
		celda.Ocupar();
		assertTrue(celda.estaOcupada());
		
	}


}
