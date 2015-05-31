package xtremecraft.edificios;

import xtremecraft.raza.Terran;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class RecolectorTest {
	
	@Test
	public void testNuevoRecolectorIniciaConReservaNula(){
		
		Terran nuevoTerran=new Terran();
		Recolector centroDeMineral=new Recolector(nuevoTerran);
		assertEquals(centroDeMineral.getReservas(),0);
	}

}
