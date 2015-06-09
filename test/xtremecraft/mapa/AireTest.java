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
		
		Terreno terreno=new Aire(1,2);
		assertFalse(terreno.estaOcupada());
		
	}
	
	
	@Test
	public void aireEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Terreno terreno=new Aire(1,4);
		Unidad goliat= new Goliat(terreno);
		terreno.ubicar(goliat);
		assertTrue(terreno.estaOcupada());
		
	}
	
	@Test
	public void ocuparterrenoConUnidadGuardaLaUnidadEnLaterreno(){
		
		Terreno terreno=new Aire(1,3);
		Unidad marine= new Marine(terreno);
		terreno.ubicar(marine);
		Unidad unidad = (Unidad) terreno.getUnidadEnTerreno();
		int vida = unidad.getVida();
		 
		assertEquals(vida, 40);
		
	}
	
	
	
	




}
