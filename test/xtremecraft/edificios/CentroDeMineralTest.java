package xtremecraft.edificios;

import xtremecraft.raza.Terran;
import xtremecraft.recursos.NodoMineral;
import xtremecraft.edificios.Edificio;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class CentroDeMineralTest {
	
	@Test
	public void testNuevoRecolectorDeMineralTerranIniciaConReservaNula(){
		
		Terran nuevoClanTerran=new Terran();
		NodoMineral nuevoNodoMineral=new NodoMineral(4);
		int fila = 1;
		int columna = 2;
		CentroDeMineral centroMineralTerran=Edificio.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,fila, columna);
		
		assertEquals(centroMineralTerran.getReservas(),0);
		
	}
	
	@Test
	public void testAumentarReservasAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terran nuevoClanTerran=new Terran();
		NodoMineral nuevoNodoMineral=new NodoMineral(4);
		int fila = 1;
		int columna = 2;
		CentroDeMineral centroMineralTerran=Edificio.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral, fila, columna);
		centroMineralTerran.aumentarReservasEnTurno();
		
		assertEquals(centroMineralTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terran nuevoClanTerran=new Terran();
		NodoMineral nuevoNodoMineral=new NodoMineral(4);
		int fila = 1;
		int columna = 2;
		CentroDeMineral centroMineralTerran=Edificio.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,fila, columna);
		
		assertEquals(centroMineralTerran.getUbicacionActual().getX(),2);
		assertEquals(centroMineralTerran.getUbicacionActual().getY(),1);
		
	}


}
