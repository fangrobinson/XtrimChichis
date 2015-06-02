package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import xtremecraft.mapa.Aire;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Unidad;

import org.junit.Test;

public class AireTest {

	@Test
	public void nuevoAireCreaAguaConEstadoNoOcupado(){
		
		Celda celda=new Aire(1,1);
		assertFalse(celda.estaOcupada());
		
	}
	
	@Test
	public void aireGetXDevuelveCoordenadaHorizontalDeLaCelda(){
		
		Celda celda=new Aire(1,2);
		assertEquals(celda.getX(), 1);
		
	}
	
	@Test
	public void aireGetYDevuelveCoordenadaVerticalDeLaCelda(){
		
		Celda celda=new Aire(1,2);
		assertEquals(celda.getY(),2);
		
	}
	
	@Test
	public void aireEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Celda celda=new Aire(1,4);
		Unidad goliat= new Goliat();
		celda.ocuparConUnidad(goliat);
		assertTrue(celda.estaOcupada());
		
	}
	
	@Test
	public void ocuparCeldaConUnidadGuardaLaUnidadEnLaCelda(){
		
		Celda celda=new Aire(1,9);
		Unidad marine= new Marine();
		celda.ocuparConUnidad(marine);
		
		assertEquals(celda.getUnidadEnCelda().getVida(),40);
		
	}



}
