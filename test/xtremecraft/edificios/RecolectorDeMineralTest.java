package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class RecolectorDeMineralTest {
	
	public RecolectorDeMineral construirNuevoRecolectorDeMineral(Terreno tierra){
		
		RecolectorDeMineral recolector = new RecolectorDeMineral(tierra);
		for(int i=0;i<recolector.tiempoConstruccion - 1;i++){
			recolector.pasarTiempo();
		}
		return recolector;
	}

	
	@Test
	public void testNuevoRecolectorDeMineralIniciaConReservaNula(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(tierra);
		
		assertEquals(centroMineralTerran.getReservas(),0);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(tierra);
		
		assertTrue(centroMineralTerran.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terreno tierra = new Tierra(1,2);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(tierra);
		
		centroMineralTerran.pasarTiempo();
		
		assertEquals(centroMineralTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(tierra);
		
		assertEquals(centroMineralTerran.getUbicacionActual().columna(),2);
		assertEquals(centroMineralTerran.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(tierra);
		
		assertEquals(centroMineralTerran.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(tierra);
		int valorDanio = 30;
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),70);
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),40);
		
	}


}
