package xtremecraft.edificios;

import xtremecraft.raza.Terran;
import xtremecraft.edificios.Edificio;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class CentroDeMineralTest {
	
	@Test
	public void testNuevoRecolectorDeMineralTerranIniciaConReservaNula(){
		
		Terran nuevoClanTerran=new Terran();
		CentroDeMineral centroMineralTerran=Edificio.nuevoRecolectorDeMineral(nuevoClanTerran);
		assertEquals(centroMineralTerran.getReservas(),0);
		
	}
	
	@Test
	public void testAumentarReservasAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terran nuevoClanTerran=new Terran();
		CentroDeMineral centroMineralTerran=Edificio.nuevoRecolectorDeMineral(nuevoClanTerran);
		centroMineralTerran.aumentarReservasEnTurno();
		assertEquals(centroMineralTerran.getReservas(),10);
		
		
	}


}
