package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class EspectroTest {
	
	@Test
	public void espectroInicializadoConVidaCompleta(){

		Espectro gengar = new Espectro();
		
		assertEquals(gengar.getVida(),120);
	}
	
	@Test
	public void espectroInicializadoConEstadoVivo(){

		Espectro gengar = new Espectro();
		
		assertTrue(gengar.estaVivo());
		
	}
	
	@Test
	public void espectroPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Espectro gengar = new Espectro();
		
		assertFalse(gengar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void espectroGetVisionDevuelveRadioDeVisionDelEspectro(){

		Espectro gengar = new Espectro();
		
		assertEquals(gengar.getRadioVision(),7);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorAireLeSacaVeinteDeVida(){

		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(tierra);
		misdreavus.actualizarUbicacion(aire);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),100);
	}

	@Test
	public void siUnEspectroAtacaAOtroPorTierraLeSacaOchoDeVida(){

		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(tierra1);
		misdreavus.actualizarUbicacion(tierra2);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),112);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terreno unTerreno = new Aire(1,4);
		Terreno otroTerreno = new Tierra(10,10);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),120);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertEquals(misdreavus.vitalidad.getValor(), 0);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloPasaAEstadoNoVivo(){

		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertFalse(misdreavus.estaVivo());
		
	}
		
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unTerreno=new Aire(1,4);
		
		Espectro unEspectro = new Espectro();
		
		unEspectro.actualizarUbicacion(unTerreno);

		assertEquals(unEspectro.getUbicacionActual().fila(),1);
		assertEquals(unEspectro.getUbicacionActual().columna(),4);
		
		Terreno otroTerreno = new Tierra(3,2);
		
		unEspectro.actualizarUbicacion(otroTerreno);
		
		assertEquals(unEspectro.getUbicacionActual().fila(),3);
		assertEquals(unEspectro.getUbicacionActual().columna(),2);
		
	}

}
