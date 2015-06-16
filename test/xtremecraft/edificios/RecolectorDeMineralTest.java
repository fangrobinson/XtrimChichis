package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class RecolectorDeMineralTest {
	
	@Test
	public void testNuevoRecolectorDeMineralIniciaConReservaNula(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = new RecolectorDeMineral(tierra);
		
		assertEquals(centroMineralTerran.getReservas(),0);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = new RecolectorDeMineral(tierra);
		
		assertTrue(centroMineralTerran.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terreno tierra = new Tierra(1,2);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		RecolectorDeMineral centroMineralTerran = new RecolectorDeMineral(tierra);
		
		centroMineralTerran.pasarTiempo();
		
		assertEquals(centroMineralTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = new RecolectorDeMineral(tierra);
		
		assertEquals(centroMineralTerran.getUbicacionActual().columna(),2);
		assertEquals(centroMineralTerran.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = new RecolectorDeMineral(tierra);
		
		assertEquals(centroMineralTerran.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = new RecolectorDeMineral(tierra);
		int valorDanio = 30;
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),70);
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),40);
		
	}


}
