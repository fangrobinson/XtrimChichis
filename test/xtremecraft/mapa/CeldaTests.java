package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



import org.junit.Test;

public class CeldaTests {

	@Test
	public void nuevaCeldaCreaCeldaConEstadoNoOcupado(){
		
		Celda celda=new Celda(1,1);
		assertFalse(celda.estaOcupada());
		
	}
	
	@Test
	public void celdaGetAbcisaDevuelveCoordenadaHorizontalDeLaCelda(){
		
		Celda celda=new Celda(1,2);
		assertEquals(celda.getAbcisa(), 1);
	}
	
	@Test
	public void celdaGetOrdenadaDevuelveCoordenadaVerticalDeLaCelda(){
		
		Celda celda=new Celda(1,2);
		assertEquals(celda.getOrdenada(),2);
		
	}
	
	@Test
	public void celdaEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparCelda(){
		
		Celda celda=new Celda(1,4);
		celda.Ocupar();
		assertTrue(celda.estaOcupada());
		
	}


}
