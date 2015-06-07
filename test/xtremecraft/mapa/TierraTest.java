package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.NodoMineral;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Unidad;

import org.junit.Test;

public class TierraTest {

	@Test
	public void nuevaTierraCreaTierraConEstadoNoOcupado(){
		
		Terreno terreno=new Tierra(1,1);
		
		assertFalse(terreno.estaOcupada());
		
	}
	
	@Test
	public void tierraEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Terreno terreno=new Tierra(1,4);
		Unidad goliat= new Goliat(terreno);
		terreno.ocuparConUnidad(goliat);
		
		assertTrue(terreno.estaOcupada());
		
	}
	
	@Test
	public void ocuparterrenoConUnidadGuardaLaUnidadEnLaterreno(){
		
		Terreno terreno=new Tierra(1,4);
		Unidad goliat= new Goliat(terreno);
		terreno.ocuparConUnidad(goliat);
		
		assertEquals(terreno.getUnidadEnTerreno().getVida(),125);
		
	}
	
	@Test
	public void terrenoTieneRecursoNaturalDeberiaDevolverTrueSiGuardoUnMineralEnEsaterreno(){
		
		Tierra tierra=new Tierra(1,4);
		NodoMineral nodoMineral=new NodoMineral(5);
		tierra.ocuparConRecursoNatural(nodoMineral);
		
		assertTrue(tierra.tierraTieneRecursosNaturales());
		assertEquals(tierra.getRecurso(),nodoMineral);
		
	}
	
}
