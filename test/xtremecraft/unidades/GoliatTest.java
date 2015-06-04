package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Tierra;

public class GoliatTest {
	
	@Test
	public void goliatInicializadoConVidaCompleta(){
		Celda celda=new Tierra(1,4);
		Goliat tanque1 = new Goliat(celda);
		
		assertEquals(tanque1.getVida(),125);
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){
		Celda celda=new Tierra(1,4);
		Goliat tanque1 = new Goliat(celda);
		
		assertEquals(tanque1.getRadioVision(),8);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorAireLeSacaDiezDeVida(){
		Celda unaCelda=new Tierra(1,4);
		Celda otraCelda=new Tierra(1,3);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		tanque1.atacar(tanque2, "aire");
		
		assertEquals(tanque2.vitalidad.devolverValor(),115);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){
		Celda unaCelda=new Tierra(2,4);
		Celda otraCelda=new Tierra(1,2);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),113);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){
		Celda unaCelda=new Tierra(2,4);
		Celda otraCelda=new Tierra(2,14);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),125);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){
		Celda unaCelda=new Tierra(1,1);
		Celda otraCelda=new Tierra(1,2);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2, "tierra");
		}
		
		assertEquals(tanque2.vitalidad.devolverValor(), 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void siIntentoCrearUnaUnidadEnUnaCeldaOcupadaSeLanzaExcepcion(){
		
		Celda unaCelda=new Tierra(1,4);
		Goliat unGoliat = new Goliat(unaCelda);
		unaCelda.ocuparCeldaConUnidad(unGoliat);
		@SuppressWarnings("unused")
		Goliat otroGoliat = new Goliat(unaCelda);
	}	
	
	@Test
	public void moverACeldaCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Celda unaCelda=new Tierra(1,4);
		Celda otraCelda=new Tierra(1,2);
		Goliat unGoliat = new Goliat(unaCelda);
		unGoliat.moverACelda(otraCelda);
		
		assertEquals(unGoliat.getUbicacionActual().getX(),2);
		assertEquals(unGoliat.getUbicacionActual().getY(),1);
		
		
	}


}

