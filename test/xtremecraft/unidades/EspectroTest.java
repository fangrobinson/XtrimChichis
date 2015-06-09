package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;

public class EspectroTest {
	
	@Test
	public void EspectroInicializadoConVidaCompleta(){

		Terreno terreno = new Aire(1,2);
		Espectro gengar = new Espectro(terreno);
		
		assertEquals(gengar.getVida(),120);
	}
	
	@Test
	public void EspectroGetVisionDevuelveRadioDeVisionDelEspectro(){

		Terreno terreno=new Aire(1,2);
		Espectro gengar = new Espectro(terreno);
		
		assertEquals(gengar.getRadioVision(),7);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorAireLeSacaVeinteDeVida(){

		Terreno unaterreno = new Aire(1,3);
		Terreno otraterreno = new Aire(1,4);
		Espectro gengar = new Espectro(unaterreno);
		Espectro misdreavus = new Espectro(otraterreno);
		gengar.atacar(misdreavus, "aire");
		
		assertEquals(misdreavus.vitalidad.devolverValor(),100);
	}

	@Test
	public void siUnEspectroAtacaAOtroPorTierraLeSacaOchoDeVida(){

		Terreno unaterreno=new Aire(1,2);
		Terreno otraterreno=new Aire(2,3);
		Espectro gengar = new Espectro(unaterreno);
		Espectro misdreavus = new Espectro(otraterreno);
		gengar.atacar(misdreavus, "tierra");
		
		assertEquals(misdreavus.vitalidad.devolverValor(),112);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terreno unaterreno=new Aire(1,4);
		Terreno otraterreno=new Aire(10,10);
		Espectro gengar = new Espectro(unaterreno);
		Espectro misdreavus = new Espectro(otraterreno);
		gengar.atacar(misdreavus, "tierra");
		
		assertEquals(misdreavus.vitalidad.devolverValor(),120);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Aire(1,2);
		Espectro gengar = new Espectro(unTerreno);
		Espectro misdreavus = new Espectro(otroTerreno);
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus, "tierra");
		}
		
		assertEquals(misdreavus.vitalidad.devolverValor(), 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void siIntentoCrearUnaUnidadEnunTerrenoOcupadaSeLanzaExcepcion(){
		
		Terreno unTerreno = new Aire(1,1);
		Espectro unEspectro = new Espectro(unTerreno);
		unTerreno.ubicar(unEspectro);
		@SuppressWarnings("unused")
		Espectro otroEspectro = new Espectro(unTerreno);
		
	}	
	
	@Test
	public void moverAterrenoCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unTerreno=new Aire(1,4);
		Terreno otroTerreno=new Aire(2,3);
		Espectro unEspectro = new Espectro(unTerreno);
		unEspectro.actualizarUbicacion(otroTerreno);

		assertEquals(unEspectro.getUbicacionActual().columna(),3);
		assertEquals(unEspectro.getUbicacionActual().fila(),2);
		
		
	}

}
