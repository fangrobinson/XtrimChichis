package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class MarineTest {
	
	@Test
	public void testMarineInicializadoConVidaCompleta(){
		Terreno unaCelda=new Tierra(1,4);
		Marine miniSamus = new Marine(unaCelda);
		
		assertEquals(miniSamus.getVida(),40);
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){
		Terreno unaCelda=new Tierra(1,4);
		Marine miniSamus = new Marine(unaCelda);
		
		assertEquals(miniSamus.getRadioVision(),7);
	}
	
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){
		Terreno unaCelda=new Tierra(1,4);
		Terreno otraCelda=new Tierra(1,3);
		Marine miniSamus = new Marine(unaCelda);
		Marine miniMasterChief = new Marine(otraCelda);
		
		miniSamus.atacar(miniMasterChief, "aire");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){
		Terreno unaCelda=new Tierra(1,4);
		Terreno otraCelda=new Tierra(1,3);
		Marine miniSamus = new Marine(unaCelda);
		Marine miniMasterChief = new Marine(otraCelda);
		
		miniSamus.atacar(miniMasterChief, "tierra");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){
		Terreno unaCelda=new Tierra(1,1);
		Terreno otraCelda=new Tierra(9,1);
		Marine miniSamus = new Marine(unaCelda);
		Marine miniMasterChief = new Marine(otraCelda);
		
		miniSamus.atacar(miniMasterChief, "tierra");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(),40);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void siIntentoCrearUnaUnidadEnUnaCeldaOcupadaSeLanzaExcepcion(){
		
		Terreno unaCelda=new Tierra(1,4);
		Marine miniSamus = new Marine(unaCelda);
		unaCelda.ocuparTerrenoConUnidad(miniSamus);
		@SuppressWarnings("unused")
		Marine miniMasterChief = new Marine(unaCelda);
		
	}
	
	@Test
	public void moverACeldaCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unaCelda=new Tierra(1,4);
		Terreno otraCelda=new Tierra(1,2);
		Marine miniSamus = new Marine(unaCelda);
		miniSamus.moverACelda(otraCelda);
		
		assertEquals(miniSamus.getUbicacionActual().columna(),2);
		assertEquals(miniSamus.getUbicacionActual().fila(),1);
		
		
	}
	
}
