package xtremecraft.edificios;

import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class RecolectorDeMineralTest {
	
	@Test
	public void testNuevoRecolectorDeMineralIniciaConReservaNula(){
		
		Terran nuevoClanTerran=new Terran();
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(4);
		int fila = 1;
		int columna = 2;
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,fila, columna);
		
		assertEquals(centroMineralTerran.getReservas(),0);
		
	}
	
	@Test
	public void testAumentarReservasAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terran nuevoClanTerran=new Terran();
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		int fila = 1;
		int columna = 2;
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral, fila, columna);
		centroMineralTerran.aumentarReservasEnTurno();
		
		assertEquals(centroMineralTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terran nuevoClanTerran=new Terran();
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		int fila = 1;
		int columna = 2;
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,fila, columna);
		
		assertEquals(centroMineralTerran.getUbicacionActual().columna(),2);
		assertEquals(centroMineralTerran.getUbicacionActual().fila(),1);
		
	}


}
