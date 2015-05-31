package xtremecraft.raza;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class TerranTest {
	
	@Test
	public void testNuevoTerranDevuelveInstanciaDeTerranConEstadoInicialVivo(){
		
		Terran nuevoTerran=new Terran();
		assertTrue(nuevoTerran.estaVivo());
		
	}
	
	
	
	
	
}