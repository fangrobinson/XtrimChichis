package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.VolcanGasVespeno;

public class RecolectorDeGasVespenoTest {
	
	public RecolectorDeGasVespeno construirNuevoRecolectorDeGasVespeno(Terreno tierra){
		
		RecolectorDeGasVespeno refineria = new RecolectorDeGasVespeno(tierra);
		for(int i=0;i<refineria.tiempoConstruccion;i++){
			refineria.pasarTiempo();
		}
		return refineria;
	}

	
	@Test
	public void nuevaRecolectorDeGasVespenoIniciaConReservaNula(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineria = new RecolectorDeGasVespeno(tierra);
		
		assertEquals(refineria.getReservas(),0);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineria = new RecolectorDeGasVespeno(tierra);
		
		assertTrue(refineria.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasEnElEdificioRecolector(){

		Terreno tierra = new Tierra(1,2);
		VolcanGasVespeno volcan = new VolcanGasVespeno(20);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = new RecolectorDeGasVespeno(tierra);
		
		refineria.pasarTiempo();
		
		assertEquals(refineria.getReservas(),10);
			
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineria = new RecolectorDeGasVespeno(tierra);
		
		assertEquals(refineria.getUbicacionActual().columna(),2);
		assertEquals(refineria.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineria = new RecolectorDeGasVespeno(tierra);
		
		assertEquals(refineria.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno refineria = new RecolectorDeGasVespeno(tierra);
		int valorDanio = 30;
		
		refineria.recibirDanio(valorDanio);
		assertEquals(refineria.getVida(),70);
		
		refineria.recibirDanio(valorDanio);
		assertEquals(refineria.getVida(),40);
		
	}


}
