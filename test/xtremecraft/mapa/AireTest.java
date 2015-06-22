package xtremecraft.mapa;

import static org.junit.Assert.assertFalse;
import xtremecraft.mapa.Aire;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Unidad;

import org.junit.Test;

public class AireTest {

	@Test
	public void nuevoAireCreaAguaConEstadoNoOcupado(){
		
		Terreno terreno=new Aire(1,2);
		assertFalse(terreno.estaOcupado());
		
	}
	
	
	@Test
	public void aireEstaOcupadaDeberiaDevolverFalseSiTratoDeUbicarUnaUnidadTerrestre(){
		
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarMinerales(1000);
		razaTerran.juntarGas(1000);
		Terreno terreno=new Aire(1,4);
		Unidad goliat= new Goliat(razaTerran);
		terreno.ubicar(goliat);
		assertFalse(terreno.estaOcupado());
		
	}
	
	@Test
	public void agregarRecursoNaturalDeberiaDevolverFalse(){
		
		Terreno terreno=new Aire(1,4);
		MinaDeMinerales unRecursoNatural = new MinaDeMinerales(3);
		assertFalse(terreno.agregarRecursoNatural(unRecursoNatural));
		
	}
	
	/*@Test
	public void ocuparterrenoConUnidadGuardaLaUnidadEnElterreno(){
		
		Terreno terreno=new Aire(1,3);
		Unidad marine= new Marine(terreno);
		terreno.ubicar(marine);
		Unidad unidad = (Unidad) terreno.getUnidadEnTerreno();
		int vida = unidad.getVida();
		 
		assertEquals(vida, 40);
		
	}*/ //TODO:COMPLETAR CUANDO EXISTAN UNIDADES VOLADORAS!!
	
	
	
	




}
