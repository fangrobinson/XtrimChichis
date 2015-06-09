package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class GoliatTest {
	
	@Test
	public void goliatInicializadoConVidaCompleta(){
		Terreno celda=new Tierra(1,2);
		Goliat tanque1 = new Goliat(celda);
		
		assertEquals(tanque1.getVida(),125);
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){
		Terreno celda=new Tierra(1,2);
		Goliat tanque1 = new Goliat(celda);
		
		assertEquals(tanque1.getRadioVision(),8);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorAireLeSacaDiezDeVida(){
		Terreno unaCelda=new Tierra(1,3);
		Terreno otraCelda=new Tierra(1,4);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		tanque1.atacar(tanque2, "aire");
		
		assertEquals(tanque2.vitalidad.devolverValor(),115);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){
		Terreno unaCelda=new Tierra(1,2);
		Terreno otraCelda=new Tierra(2,3);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),113);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){
		Terreno unaCelda=new Tierra(1,4);
		Terreno otraCelda=new Tierra(5,5);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),125);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){
		Terreno unaCelda=new Tierra(3,5);
		Terreno otraCelda=new Tierra(7,7);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2, "tierra");
		}
		
		assertEquals(tanque2.vitalidad.devolverValor(), 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void siIntentoCrearUnaUnidadEnUnaCeldaOcupadaSeLanzaExcepcion(){
		
		Terreno unaCelda=new Tierra(6,2);
		Goliat unGoliat = new Goliat(unaCelda);
		unaCelda.ubicar(unGoliat);
		@SuppressWarnings("unused")
		Goliat otroGoliat = new Goliat(unaCelda);
	}	
	
	@Test
	public void moverACeldaCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unaCelda=new Tierra(1,4);
		Terreno otraCelda=new Tierra(2,3);
		Goliat unGoliat = new Goliat(unaCelda);
		unGoliat.moverACelda(otraCelda);
		
		assertEquals(unGoliat.getUbicacionActual().columna(),2);
		assertEquals(unGoliat.getUbicacionActual().fila(),1);
		
		
	}


}

