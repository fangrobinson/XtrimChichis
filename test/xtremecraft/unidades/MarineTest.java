package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Tierra;

public class MarineTest {
	
	@Test
	public void testMarineInicializadoConVidaCompleta(){
		Celda unaCelda=new Tierra(1,4);
		Marine miniSamus = new Marine(unaCelda);
		
		assertEquals(miniSamus.getVida(),40);
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){
		Celda unaCelda=new Tierra(1,4);
		Marine miniSamus = new Marine(unaCelda);
		
		assertEquals(miniSamus.getRadioVision(),7);
	}
	
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){
		Celda unaCelda=new Tierra(1,4);
		Celda otraCelda=new Tierra(1,3);
		Marine miniSamus = new Marine(unaCelda);
		Marine miniMasterChief = new Marine(otraCelda);
		
		miniSamus.atacar(miniMasterChief, "aire");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){
		Celda unaCelda=new Tierra(1,4);
		Celda otraCelda=new Tierra(1,3);
		Marine miniSamus = new Marine(unaCelda);
		Marine miniMasterChief = new Marine(otraCelda);
		
		miniSamus.atacar(miniMasterChief, "tierra");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void siIntentoCrearUnaUnidadEnUnaCeldaOcupadaSeLanzaExcepcion(){
		
		Celda unaCelda=new Tierra(1,4);
		Marine miniSamus = new Marine(unaCelda);
		unaCelda.ocuparCeldaConUnidad(miniSamus);
		@SuppressWarnings("unused")
		Marine miniMasterChief = new Marine(unaCelda);
		
	}
	
	@Test
	public void moverACeldaCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Celda unaCelda=new Tierra(1,4);
		Celda otraCelda=new Tierra(1,2);
		Marine miniSamus = new Marine(unaCelda);
		miniSamus.moverACelda(otraCelda);
		
		assertEquals(miniSamus.getUbicacionActual().getX(),2);
		assertEquals(miniSamus.getUbicacionActual().getY(),1);
		
		
	}
	
}
