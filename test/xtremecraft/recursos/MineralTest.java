package xtremecraft.recursos;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MineralTest {
	
	@Test
	public void testGetNombreDevuelveStringMineral(){
		
		Mineral mineral = new Mineral();
		assertEquals(mineral.getNombre(),"mineral");
		
	}

}
