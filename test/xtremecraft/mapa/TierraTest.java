package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import xtremecraft.mapa.Tierra;

import org.junit.Test;

public class TierraTest {

	@Test
	public void nuevaTierraCreaTierraConEstadoNoOcupado(){
		
		Celda celda=new Tierra(1,1);
		assertFalse(celda.estaOcupada());
		
	}
	
	@Test
	public void tierraGetXDevuelveCoordenadaHorizontalDeLaCelda(){
		
		Celda celda=new Tierra(1,2);
		assertEquals(celda.getX(), 1);
		
	}
	
	@Test
	public void tierraGetYDevuelveCoordenadaVerticalDeLaCelda(){
		
		Celda celda=new Tierra(1,2);
		assertEquals(celda.getY(),2);
		
	}
	
	@Test
	public void tierraEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Celda celda=new Tierra(1,4);
		celda.Ocupar();
		assertTrue(celda.estaOcupada());
		
	}


}
