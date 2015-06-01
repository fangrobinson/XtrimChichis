package xtremecraft.recursos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NodoMineralTest {
	
	@Test
	public void testMineralDevuelveInstanciaDeMineralAlImplementarInterfazRecolectar(){
		
		NodoMineral nodoMineral=new NodoMineral();
		Mineral nuevoMineral=nodoMineral.recolectar();
		assertEquals(nuevoMineral.getNombre(),"mineral");
		
	}

}
