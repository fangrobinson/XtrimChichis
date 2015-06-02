package xtremecraft.recursos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class NodoMineralTest {
	
	@Test
	public void testGetCantidadDeMineralesDevuelveNumeroDeCristalesQueFormanElNodoMineral(){
		
		NodoMineral nodoMineral=new NodoMineral(4);
		assertEquals(nodoMineral.getCantidadDeMinerales(),4);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNodoMineralInicializadoConNumeroDeCristalesNegativoLanzaExcepcion(){
		
			new NodoMineral(-1);
			
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
