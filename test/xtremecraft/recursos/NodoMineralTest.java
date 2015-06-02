package xtremecraft.recursos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class NodoMineralTest {
	
	@Test
	public void testGetCantidadDeMineralesDevuelveNumeroDeCristalesQueFormanElNodoMineral(){
		
		NodoMineral nodoMineral=new NodoMineral(4);
		assertEquals(nodoMineral.getCantidadDeMinerales(),4);
		
	}
	
	@Test
	public void testNodoMineralInicializadoConNumeroDeCristalesNegativoLanzaExcepcion(){
		
		try{
			new NodoMineral(-1);
			fail();
		}catch(Exception IllegalArgumentException) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testNodoMineralInicializadoComoNoExplotado(){
		
		NodoMineral nodoMineral=new NodoMineral(4);
		assertFalse(nodoMineral.estaSiendoExplotado());
		
	}
	
	@Test
	public void testNodoMineralPasaAEstadoExplotadoLuegoDeSerOcupado(){
		
		NodoMineral nodoMineral=new NodoMineral(4);
		nodoMineral.ocuparNodo();
		assertTrue(nodoMineral.estaSiendoExplotado());
		
	}
	

}
