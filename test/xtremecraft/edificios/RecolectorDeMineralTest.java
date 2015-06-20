package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.unidades.Marine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class RecolectorDeMineralTest {
	
	public RecolectorDeMineral construirNuevoRecolectorDeMineral(int fila, int columna){
		
		Terreno tierra = new Tierra(fila,columna);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		RecolectorDeMineral recolector = RecolectorDeMineral.nuevoRecolectorDeMineral(tierra);
		for(int i=0; i<recolector.tiempoConstruccion; i++){
			recolector.pasarTiempo();
		}
		return recolector;
		
	}
	
	@Test
	public void recolectorSeIniciaConEstadoVivo(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		
		RecolectorDeMineral recolector = RecolectorDeMineral.nuevoRecolectorDeMineral(tierra);
		
		assertTrue(recolector.estaVivo());
		
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElRecolectorDeMineral(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		
		RecolectorDeMineral recolector = RecolectorDeMineral.nuevoRecolectorDeMineral(tierra);
		
		assertTrue(recolector.estaEnConstruccion());
		
	}
	
	@Test
	public void recolectorEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelRecolectorDeMineral(){
		
		RecolectorDeMineral recolector = construirNuevoRecolectorDeMineral(1,2);
		
		assertFalse(recolector.estaEnConstruccion());
		
	}

	
	@Test
	public void testNuevoRecolectorDeMineralIniciaConReservaNula(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		assertEquals(centroMineralTerran.getReservas(),0);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		assertTrue(centroMineralTerran.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		centroMineralTerran.pasarTiempo();
		
		assertEquals(centroMineralTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		assertEquals(centroMineralTerran.getUbicacionActual().columna(),2);
		assertEquals(centroMineralTerran.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		assertEquals(centroMineralTerran.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		int valorDanio = 30;
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),70);
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),40);
		
	}

	@Test
	public void siUnRecolectorEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terreno tierra = new Tierra(3,2);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		Marine miniSamus = new Marine();
		int cantidadDeAtaquesARecolector = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		for(int i=0;i<cantidadDeAtaquesARecolector;i++) miniSamus.atacar(centroMineralTerran);
		
		assertFalse(centroMineralTerran.estaVivo());		
		
	}

}
