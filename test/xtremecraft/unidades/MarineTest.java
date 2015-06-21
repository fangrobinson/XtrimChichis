package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class MarineTest {
	
	@Test
	public void testMarineInicializadoConVidaCompleta(){

		Marine miniSamus = new Marine();

		assertEquals(miniSamus.getVida(),40);
		
	}
	
	@Test
	public void testMarineInicializadoConEstadoVivo(){

		Marine miniSamus = new Marine();

		assertTrue(miniSamus.estaVivo());
		
	}
	
	@Test
	public void marinePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Marine miniSamus = new Marine();
		
		assertFalse(miniSamus.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){

		Marine miniSamus = new Marine();

		assertEquals(miniSamus.getRadioVision(),7);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){

		Terreno tierra = new Tierra(1,2);
		Terreno aire = new Aire(1,3);
		Marine miniSamus = new Marine();
		Espectro miniMasterChief = new Espectro();
		
		miniSamus.actualizarUbicacion(tierra);
		miniMasterChief.actualizarUbicacion(aire);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 114);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){


		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terreno tierra1 = new Tierra(1,1);
		Terreno tierra2 = new Tierra(40,10);
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		miniSamus.atacar(miniMasterChief);
		
		assertEquals(miniMasterChief.vitalidad.getValor(),40);
	}

	
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){

		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,10);
		Marine miniSamus = new Marine();
		
		miniSamus.actualizarUbicacion(tierra1);
		
		assertEquals(miniSamus.getUbicacionActual().fila(),1);
		assertEquals(miniSamus.getUbicacionActual().columna(),2);
		
		miniSamus.actualizarUbicacion(tierra2);
		
		assertEquals(miniSamus.getUbicacionActual().fila(),1);
		assertEquals(miniSamus.getUbicacionActual().columna(),10);
		
	}
	
	@Test
	public void siUnMarineAtacaAOtroHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){

		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(1,3);
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		int cantidadDeAtaques = 7;
		
		miniSamus.actualizarUbicacion(tierra1);
		miniMasterChief.actualizarUbicacion(tierra2);
		for(int i=0;i<cantidadDeAtaques;i++) miniSamus.atacar(miniMasterChief);
		
		assertFalse(miniMasterChief.estaVivo());
		
	}
	
}
