package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.VolcanGasVespeno;

public class RecolectorDeGasVespenoTest {
	
	@Test
	public void nuevaRecolectorDeGasVespenoIniciaConReservaNula(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = new RecolectorDeGasVespeno(tierra);
		
		assertEquals(refineriaTerran.getReservas(),0);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = new RecolectorDeGasVespeno(tierra);
		
		assertTrue(refineriaTerran.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasEnElEdificioRecolector(){

		Terreno tierra = new Tierra(1,2);
		VolcanGasVespeno volcan = new VolcanGasVespeno(20);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineriaTerran = new RecolectorDeGasVespeno(tierra);
		
		refineriaTerran.pasarTiempo();
		
		assertEquals(refineriaTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = new RecolectorDeGasVespeno(tierra);
		
		assertEquals(refineriaTerran.getUbicacionActual().columna(),2);
		assertEquals(refineriaTerran.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = new RecolectorDeGasVespeno(tierra);
		
		assertEquals(refineriaTerran.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = new RecolectorDeGasVespeno(tierra);
		int valorDanio = 30;
		
		refineriaTerran.recibirDanio(valorDanio);
		assertEquals(refineriaTerran.getVida(),70);
		
		refineriaTerran.recibirDanio(valorDanio);
		assertEquals(refineriaTerran.getVida(),40);
		
	}


}
