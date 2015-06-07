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
		
		Terreno celda=new Tierra(1,1);
		
		assertFalse(celda.estaOcupada());
		
	}
	
	@Test
	public void tierraEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Terreno celda=new Tierra(1,4);
		Unidad goliat= new Goliat(celda);
		celda.ocuparConUnidad(goliat);
		
		assertTrue(celda.estaOcupada());
		
	}
	
	@Test
	public void ocuparCeldaConUnidadGuardaLaUnidadEnLaCelda(){
		
		Terreno celda=new Tierra(1,4);
		Unidad goliat= new Goliat(celda);
		celda.ocuparConUnidad(goliat);
		
		assertEquals(celda.getUnidadEnCelda().getVida(),125);
		
	}
	
	@Test
	public void celdaTieneRecursoNaturalDeberiaDevolverTrueSiGuardoUnMineralEnEsaCelda(){
		
		Tierra tierra=new Tierra(1,4);
		NodoMineral nodoMineral=new NodoMineral(5);
		tierra.ocuparConRecursoNatural(nodoMineral);
		
		assertTrue(tierra.tierraTieneRecursosNaturales());
		assertEquals(tierra.getRecurso(),nodoMineral);
		
	}
	
}
