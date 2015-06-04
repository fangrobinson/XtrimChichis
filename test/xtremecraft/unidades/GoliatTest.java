package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GoliatTest {
	
	@Test
	public void goliatInicializadoConVidaCompleta(){
		Goliat tanque1 = new Goliat(1,2);
		
		assertEquals(tanque1.getVida(),125);
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){
		Goliat tanque1 = new Goliat(1,2);
		
		assertEquals(tanque1.getRadioVision(),8);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorAireLeSacaDiezDeVida(){
		Goliat tanque1 = new Goliat(1,2);
		Goliat tanque2 = new Goliat(1,3);
		
		tanque1.atacar(tanque2, "aire");
		
		assertEquals(tanque2.vitalidad.devolverValor(),115);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){
		Goliat tanque1 = new Goliat(2,4);
		Goliat tanque2 = new Goliat(1,2);
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),113);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){
		Goliat tanque1 = new Goliat(1,1);
		Goliat tanque2 = new Goliat(1,2);
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2, "tierra");
		}
		
		assertEquals(tanque2.vitalidad.devolverValor(), 0);
	}
	


}

