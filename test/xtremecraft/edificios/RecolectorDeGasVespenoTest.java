package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Marine;

public class RecolectorDeGasVespenoTest {
	
	public RecolectorDeGasVespeno construirNuevoRecolectorDeGasVespeno(int fila, int columna){
		
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(tierra);
		for(int i=0; i < refineria.tiempoConstruccion; i++){
			refineria.pasarTiempo();
		}
		return refineria;
	}
	
	@Test
	public void seInicializaConEstadoVivo(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(tierra);
		
		assertTrue(refineria.estaVivo());
		
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
		
		assertEquals(refineria.getVida(), refineria.vidaMax());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		int valorDanio = 30;
		int vidaEsperada = refineria.vidaMax() - valorDanio;
		
		refineria.recibirDanio(valorDanio);
		assertEquals(refineria.getVida(), vidaEsperada);
		
		vidaEsperada = refineria.vidaMax() - (valorDanio * 2) ;
		refineria.recibirDanio(valorDanio);
		assertEquals(refineria.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siUnRecolectorEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terreno tierra = new Tierra(3,2);
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		Marine miniSamus = new Marine();
		int cantidadDeAtaquesARecolector = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		for(int i=0;i<cantidadDeAtaquesARecolector;i++) miniSamus.atacar(refineria);
		
		assertFalse(refineria.estaVivo());		
		
	}

}
