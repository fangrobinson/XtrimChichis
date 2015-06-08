package xtremecraft.recursos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VolcanGasVespenoTest {
	
	@Test
	public void getVolumenDeGasVespenoDevuelveVolumenDeGasContenidoEnElVolcan(){
		
		VolcanGasVespeno volcan=new VolcanGasVespeno(100);
		assertEquals(volcan.getVolumenDeGasVespeno(),100);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void volcanSeInicializaConVolumenNegativoDeGasVespenoLanzaExcepcion(){
		
			new VolcanGasVespeno(-1);
			
	}
	
	@Test
	public void volcanSeInicializaComoNoExplotado(){
		
		VolcanGasVespeno volcan=new VolcanGasVespeno(100);
		assertFalse(volcan.estaSiendoExplotado());
		
	}
	
	@Test
	public void volcanPasaAEstadoExplotadoLuegoDeSerOcupado(){
		
		VolcanGasVespeno volcan=new VolcanGasVespeno(100);
		volcan.ocuparVolcan();
		assertTrue(volcan.estaSiendoExplotado());
		
	}
	
	@Test
	public void volcanDecrementaVolumenDeGasLuegoDeSerExplotado(){
		
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		volcan.explotar(10);
		assertEquals(volcan.getVolumenDeGasVespeno(),190);
	}
	
	@Test
	public void siSeQuiereExtraerMasVolumenDeGasDeLoQueTieneElVolcanSuVolumenSeMantieneEnCero(){
		
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		for (int i = 0; i < 4; i++){
			volcan.explotar(100);
		}
		assertEquals(volcan.getVolumenDeGasVespeno(),0);
	}
	
	@Test
	public void siElVolcanEsExplotadoConUnValorMayorAlDeSusReservasDevuelveTodosSusRecursos(){
		
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		int recursos = volcan.explotar(400);
		assertEquals(recursos, 200);
	}

}
