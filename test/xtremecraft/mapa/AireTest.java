package xtremecraft.mapa;

import static org.junit.Assert.assertFalse;

import xtremecraft.mapa.Aire;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Unidad;

import org.junit.Test;

public class AireTest {

	@Test
	public void nuevoAireCreaAguaConEstadoNoOcupado(){
		
		Terreno terreno=new Aire(1,2);
		assertFalse(terreno.estaOcupada());
		
	}
	
	
	@Test
	public void aireEstaOcupadaDeberiaDevolverFalseSiTratoDeUbicarUnaUnidadTerrestre(){
		
		Terreno terreno=new Aire(1,4);
		Unidad goliat= new Goliat(terreno);
		terreno.ubicar(goliat);
		assertFalse(terreno.estaOcupada());
		
	}
	
	/*@Test
	public void ocuparterrenoConUnidadGuardaLaUnidadEnElterreno(){
		
		Terreno terreno=new Aire(1,3);
		Unidad marine= new Marine(terreno);
		terreno.ubicar(marine);
		Unidad unidad = (Unidad) terreno.getUnidadEnTerreno();
		int vida = unidad.getVida();
		 
		assertEquals(vida, 40);
		
	}*/ //COMPLETAR CUANDO EXISTAN UNIDADES VOLADORAS!!
	
	
	
	




}
