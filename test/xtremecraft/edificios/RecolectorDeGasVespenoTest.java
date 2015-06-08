package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.raza.Terran;
import xtremecraft.recursos.VolcanGasVespeno;

public class RecolectorDeGasVespenoTest {
	
	@Test
	public void testNuevaRecolectorDeGasVespenoIniciaConReservaNula(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(100);
		int fila = 1;
		int columna = 2;
		RecolectorDeGasVespeno refineriaTerran = Recolector.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,fila, columna);
		
		assertEquals(refineriaTerran.getReservas(),0);
		
	}
	
	@Test
	public void testAumentarReservasAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(100);
		int fila = 1;
		int columna = 2;
		RecolectorDeGasVespeno refineriaTerran = Recolector.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,fila, columna);
		refineriaTerran.aumentarReservasEnTurno();
		
		assertEquals(refineriaTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(100);
		int fila = 1;
		int columna = 2;
		RecolectorDeGasVespeno refineriaTerran = Recolector.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,fila, columna);
		
		assertEquals(refineriaTerran.getUbicacionActual().columna(),2);
		assertEquals(refineriaTerran.getUbicacionActual().fila(),1);
		
	}

}
