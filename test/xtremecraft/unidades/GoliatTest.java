package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GoliatTest {
	
	@Test
	public void testGoliatInicializadoConVidaCompleta(){
		Goliat tanque1 = new Goliat();
		
		assertEquals(tanque1.getVida(),125);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorAireLeSacaDiezDeVida(){
		Goliat tanque1 = new Goliat();
		Goliat tanque2 = new Goliat();
		
		tanque1.atacar(tanque2, "aire");
		
		assertEquals(tanque2.vitalidad.devolverValor(),115);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){
		Goliat tanque1 = new Goliat();
		Goliat tanque2 = new Goliat();
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),113);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){
		Goliat tanque1 = new Goliat();
		Goliat tanque2 = new Goliat();
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2, "tierra");
		}
		
		assertEquals(tanque2.vitalidad.devolverValor(), 0);
	}
	

}

