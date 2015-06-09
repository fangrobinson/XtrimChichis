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
		
		Terreno terreno=new Aire();
		assertFalse(terreno.estaOcupada());
		
	}
	
	
	@Test
	public void aireEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Terreno terreno=new Aire();
		Unidad goliat= new Goliat(terreno);
		terreno.ocuparConUnidad(goliat);
		assertTrue(terreno.estaOcupada());
		
	}
	
	@Test
	public void ocuparterrenoConUnidadGuardaLaUnidadEnLaterreno(){
		
		Terreno terreno=new Aire();
		Unidad marine= new Marine(terreno);
		terreno.ocuparConUnidad(marine);
		
		assertEquals(terreno.getUnidadEnTerreno().getVida(), 40);
		
	}




}
