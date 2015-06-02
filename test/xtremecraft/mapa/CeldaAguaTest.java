package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import xtremecraft.mapa.CeldaAgua;

import org.junit.Test;

public class CeldaAguaTest {

	@Test
	public void nuevaCeldaAguaCreaCeldaConEstadoNoOcupado(){
		
		Celda celda=new CeldaAgua(1,1);
		assertFalse(celda.estaOcupada());
		
	}
	
	@Test
	public void celdaGetXDevuelveCoordenadaHorizontalDeLaCelda(){
		
		Celda celda=new CeldaAgua(1,2);
		assertEquals(celda.getX(), 1);
		
	}
	
	@Test
	public void celdaGetYDevuelveCoordenadaVerticalDeLaCelda(){
		
		Celda celda=new CeldaAgua(1,2);
		assertEquals(celda.getY(),2);
		
	}
	
	@Test
	public void celdaTierraEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparCelda(){
		
		Celda celda=new CeldaAgua(1,4);
		celda.Ocupar();
		assertTrue(celda.estaOcupada());
		
	}


}
