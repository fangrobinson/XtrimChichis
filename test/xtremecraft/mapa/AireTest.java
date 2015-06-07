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
		
		Terreno celda=new Aire(1,1);
		assertFalse(celda.estaOcupada());
		
	}
	
	
	@Test
	public void aireEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Terreno celda=new Aire(1,4);
		Unidad goliat= new Goliat(celda);
		celda.ocuparConUnidad(goliat);
		assertTrue(celda.estaOcupada());
		
	}
	
	@Test
	public void ocuparCeldaConUnidadGuardaLaUnidadEnLaCelda(){
		
		Terreno celda=new Aire(1,9);
		Unidad marine= new Marine(celda);
		celda.ocuparConUnidad(marine);
		
		assertEquals(celda.getUnidadEnCelda().getVida(),40);
		
	}




}
