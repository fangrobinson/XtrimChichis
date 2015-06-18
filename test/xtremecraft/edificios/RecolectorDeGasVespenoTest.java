package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.VolcanGasVespeno;

public class RecolectorDeGasVespenoTest {
	
	public RecolectorDeGasVespeno construirNuevoRecolectorDeGasVespeno(int fila, int columna){
		
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(tierra);
		for(int i=0;i<refineria.tiempoConstruccion - 1;i++){
			refineria.pasarTiempo();
		}
		return refineria;
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElRecolectorDeGasVespeno(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(tierra);
		
		assertTrue(refineria.estaEnConstruccion());
		
	}
	
	@Test
	public void recolectorEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelRecolectorDeGasVespeno(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertFalse(refineria.estaEnConstruccion());
		
	}


	
	@Test
	public void nuevoRecolectorDeGasVespenoIniciaConReservaNula(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertEquals(refineria.getReservas(),0);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertTrue(refineria.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasEnElEdificioRecolector(){

		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		refineria.pasarTiempo();
		
		assertEquals(refineria.getReservas(),10);
			
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertEquals(refineria.getUbicacionActual().columna(),2);
		assertEquals(refineria.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertEquals(refineria.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		int valorDanio = 30;
		
		refineria.recibirDanio(valorDanio);
		assertEquals(refineria.getVida(),70);
		
		refineria.recibirDanio(valorDanio);
		assertEquals(refineria.getVida(),40);
		
	}


}
