package xtremecraft.recursos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MinaDeMineralesTest {
	
	@Test
	public void getCantidadDeMineralesDevuelveNumeroDeCristalesQueFormanLaMina(){
		
		MinaDeMinerales mina=new MinaDeMinerales(4);
		assertEquals(mina.getCantidadDeMinerales(),4);
		
	}
	
	@Test(expected = NumeroDeCristalesNegativoException.class)
	public void minaInicializadaConNumeroDeCristalesNegativoLanzaExcepcion(){
		
			new MinaDeMinerales(-1);
			
	}
	
	@Test
	public void tieneMineralDevuelveTrue(){
		
		MinaDeMinerales mina=new MinaDeMinerales(4);
		
		assertTrue(mina.tieneMineral());
	}
	
	
	@Test
	public void tieneGasVespenoDevuelveFalse(){
		
		MinaDeMinerales mina=new MinaDeMinerales(4);
		
		assertFalse(mina.tieneGasVespeno());
	}


	@Test
	public void minaInicializadaComoNoExplotado(){
		
		MinaDeMinerales mina=new MinaDeMinerales(4);
		assertFalse(mina.estaSiendoExplotado());
		
	}
	
	@Test
	public void minaPasaAEstadoExplotadoLuegoDeSerOcupado(){
		
		MinaDeMinerales mina=new MinaDeMinerales(4);
		mina.ocuparMinaDeMineral();
		assertTrue(mina.estaSiendoExplotado());
		
	}
	
	@Test
	public void laMinaTieneDiezDeMineralLuegoDeSerExplotada(){
		
		MinaDeMinerales mina = new MinaDeMinerales(20);
		mina.explotar(10);
		assertEquals(mina.getCantidadDeMinerales(), 10);
	}
	
	@Test
	public void siLaMinaSigueSiendoExplotadaSuCantidadDeMineralesSeMantieneEnCero(){
		
		MinaDeMinerales mina = new MinaDeMinerales(20);
		for (int i = 0; i < 4; i++){
			mina.explotar(10);
		}
		assertEquals(mina.getCantidadDeMinerales(), 0);
	}
	
	@Test
	public void siLaMinaEsExplotadaConUnValorMayorAlQueTieneDevuelveTodosSusRecursos(){
		
		MinaDeMinerales mina = new MinaDeMinerales(20);
		int recursos = mina.explotar(40);
		assertEquals(recursos, 20);
	}
	

}
