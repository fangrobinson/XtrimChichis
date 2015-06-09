package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class GoliatTest {
	
	@Test
	public void goliatInicializadoConVidaCompleta(){
		Terreno celda = new Tierra(1,1);
		Goliat tanque1 = new Goliat(celda);
		
		assertEquals(tanque1.getVida(),125);
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){
		Terreno celda = new Tierra(1,1);
		Goliat tanque1 = new Goliat(celda);
		
		assertEquals(tanque1.getRadioVision(),8);
	}
	
	@Test
	public void siUnGoliatAtacaAOtroPorAireLeSacaDiezDeVida(){
		Terreno unTerreno = new Tierra(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Goliat tanque1 = new Goliat(unTerreno);
		Goliat tanque2 = new Goliat(otroTerreno);
		
		tanque1.atacar(tanque2, "aire");
		
		assertEquals(tanque2.vitalidad.devolverValor(),115);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){
		Terreno unTerreno = new Tierra(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Goliat tanque1 = new Goliat(unTerreno);
		Goliat tanque2 = new Goliat(otroTerreno);
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),113);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){
		Terreno unTerreno = new Tierra(1,1);
		Terreno otroTerreno = new Tierra(1,10);
		Goliat tanque1 = new Goliat(unTerreno);
		Goliat tanque2 = new Goliat(otroTerreno);
		
		tanque1.atacar(tanque2, "tierra");
		
		assertEquals(tanque2.vitalidad.devolverValor(),125);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){
		Terreno unTerreno = new Tierra(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Goliat tanque1 = new Goliat(unTerreno);
		Goliat tanque2 = new Goliat(otroTerreno);
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2, "tierra");
		}
		
		assertEquals(tanque2.vitalidad.devolverValor(), 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void siIntentoCrearUnaUnidadEnunTerrenoOcupadaSeLanzaExcepcion(){
		
		Terreno unTerreno = new Tierra(1,1);
		Goliat unGoliat = new Goliat(unTerreno);
		unTerreno.ubicar(unGoliat);
		@SuppressWarnings("unused")
		Goliat otroGoliat = new Goliat(unTerreno);
	}	
	
	@Test
	public void moverACeldaCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unTerreno = new Tierra(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Goliat unGoliat = new Goliat(unTerreno);
		unGoliat.moverACelda(otroTerreno);
		
		assertEquals(unGoliat.getUbicacionActual().columna(),2);
		assertEquals(unGoliat.getUbicacionActual().fila(),1);
		
		
	}


}

