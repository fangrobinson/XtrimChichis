package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MarineTest {
	
	@Test
	public void testMarineInicializadoConVidaCompleta(){
		Marine miniSamus = new Marine(1,2);
		
		assertEquals(miniSamus.getVida(),40);
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){
		Marine miniSamus = new Marine(1,2);
		
		assertEquals(miniSamus.getRadioVision(),7);
	}
	
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){
		Marine miniSamus = new Marine(1,2);
		Marine miniMasterChief = new Marine(1,2);
		
		miniSamus.atacar(miniMasterChief, "aire");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){
		Marine miniSamus = new Marine(1,2);
		Marine miniMasterChief = new Marine(1,2);
		
		miniSamus.atacar(miniMasterChief, "tierra");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}

	
}
