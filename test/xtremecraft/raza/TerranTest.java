package xtremecraft.raza;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class TerranTest {
	
	@Test
	public void testNuevoTerranDevuelveInstanciaDeTerranConEstadoInicialRazaViva(){
		
		Terran razaTerran=new Terran();
		assertTrue(razaTerran.estaViva());
		
	}
	
	
	
	
	
}