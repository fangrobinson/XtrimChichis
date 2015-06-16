package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.mapa.Aire;
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
	public void goliatPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Terreno celda=new Tierra(1,2);
		Goliat tanque1 = new Goliat(celda);
		
		assertFalse(tanque1.puedeUbicarseSobreRecursoNatural());
	}
	
	@Test
	public void goliatGetVisionDevuelveRadioDeVisionDelGoliat(){

		Terreno celda=new Tierra(1,2);
		Goliat tanque1 = new Goliat(celda);
		
		assertEquals(tanque1.getRadioVision(),8);
	}
	
	@Test
	public void siUnGoliatAtacaAUnEspectroPorAireLeSacaDiezDeVida(){

		Terreno unaCelda=new Tierra(1,3);
		Terreno otraCelda=new Aire(1,4);
		Goliat tanque1 = new Goliat(unaCelda);
		Espectro tanque2 = new Espectro(otraCelda);
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.devolverValor(),110);
	}

	@Test
	public void siUnGoliatAtacaAOtroPorTierraLeSacaDoceDeVida(){

		Terreno unaCelda=new Tierra(1,2);
		Terreno otraCelda=new Tierra(2,3);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.devolverValor(),113);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terreno unaCelda=new Tierra(1,4);
		Terreno otraCelda=new Tierra(10,10);
		Goliat tanque1 = new Goliat(unaCelda);
		Goliat tanque2 = new Goliat(otraCelda);
		tanque1.atacar(tanque2);
		
		assertEquals(tanque2.vitalidad.devolverValor(),125);
	}
	
	
	@Test
	public void siUnGoliatAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Terreno unTerreno = new Tierra(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Goliat tanque1 = new Goliat(unTerreno);
		Goliat tanque2 = new Goliat(otroTerreno);
		
		for (int i = 0; i < 11; i++){
			tanque1.atacar(tanque2);
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
		
		Terreno unaCelda=new Tierra(1,4);
		Terreno otraCelda=new Tierra(2,3);
		Goliat unGoliat = new Goliat(unaCelda);
		unGoliat.actualizarUbicacion(otraCelda);

		assertEquals(unGoliat.getUbicacionActual().columna(),3);
		assertEquals(unGoliat.getUbicacionActual().fila(),2);
		
		
	}

}

