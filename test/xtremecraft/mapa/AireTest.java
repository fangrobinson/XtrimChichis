package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import xtremecraft.mapa.Aire;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.unidades.Espectro;
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
	
	@Test
	public void ocuparterrenoConUnidadGuardaLaUnidadEnElterreno(){
		
		Terreno terreno=new Aire(1,3);
		Tierra terreno2 = new Tierra(1,2);
		Terran terran = new Terran(terreno2);
		terran.juntarMinerales(1000);
		terran.juntarGas(1000);
		Unidad espectro = new Espectro(terran);
		terreno.ubicar(espectro);
		Unidad unidad = (Unidad) terreno.getUbicableEnTerreno();
		int vida = unidad.getVida();
		 
		assertEquals(vida, 120);
		
	}	
	
	
	




}
