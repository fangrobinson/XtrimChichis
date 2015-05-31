package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MarineTests {
	
	@Test
	public void siUnMarineAtacaAOtroPorAireLeSacaSeisDeVida(){
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		
		miniSamus.atacar(miniMasterChief, "aire");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}
	
	@Test
	public void siUnMarineAtacaAOtroPorTierraLeSacaSeisDeVida(){
		Marine miniSamus = new Marine();
		Marine miniMasterChief = new Marine();
		
		miniSamus.atacar(miniMasterChief, "tierra");
		
		assertEquals(miniMasterChief.vitalidad.devolverValor(), 34);
	}

	
}
