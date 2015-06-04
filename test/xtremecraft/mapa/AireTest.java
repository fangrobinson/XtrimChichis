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
	public void aireEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Celda celda=new Aire(1,4);
		Unidad goliat= new Goliat(1,4);
		celda.ocuparConUnidad(goliat);
		assertTrue(celda.estaOcupada());
		
	}
	
	@Test
	public void ocuparCeldaConUnidadGuardaLaUnidadEnLaCelda(){
		
		Celda celda=new Aire(1,9);
		Unidad marine= new Marine(1,9);
		celda.ocuparConUnidad(marine);
		
		assertEquals(celda.getUnidadEnCelda().getVida(),40);
		
	}

	@Test
	public void calcularDistanciaDevuelveLaDistanciaDeLaCeldaRespectoDeOtraCelda(){
		
		Celda celda1=new Aire(1,1);
		Celda celda2=new Aire(5,4);
		
		assertTrue(celda1.calcularDistancia(celda2)==5);
	}


}
