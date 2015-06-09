package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;

public class NaveTransporteTest {
	
	@Test
	public void NaveTransporteInicializadoConVidaCompleta(){

		Terreno terreno = new Aire(1,2);
		NaveTransporte taxiVolador = new NaveTransporte(terreno);
		
		assertEquals(taxiVolador.getVida(),150);
	}
	
	@Test
	public void NaveTransporteGetVisionDevuelveRadioDeVisionDeLaNaveTransporte(){

		Terreno terreno = new Aire(1,2);
		NaveTransporte taxiVolador = new NaveTransporte(terreno);
		
		assertEquals(taxiVolador.getRadioVision(),8);
	}
	
	@Test
	public void siUnaNaveTransporteAtacaAOtroPorAireLeSacaCeroDeVida(){

		Terreno unaterreno = new Aire(1,3);
		Terreno otraterreno = new Aire(1,4);
		NaveTransporte taxiVolador = new NaveTransporte(unaterreno);
		NaveTransporte remisVolador = new NaveTransporte(otraterreno);
		remisVolador.atacar(taxiVolador, "aire");
		
		assertEquals(taxiVolador.vitalidad.devolverValor(),150);
	}

	@Test
	public void siUnaNaveTransporteAtacaAOtroPorTierraLeSacaCeroDeVida(){

		Terreno unaterreno=new Aire(1,2);
		Terreno otraterreno=new Aire(2,3);
		NaveTransporte taxiVolador = new NaveTransporte(unaterreno);
		NaveTransporte remisVolador = new NaveTransporte(otraterreno);
		remisVolador.atacar(taxiVolador, "tierra");
		
		assertEquals(taxiVolador.vitalidad.devolverValor(),150);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void siIntentoCrearUnaUnidadEnunTerrenoOcupadaSeLanzaExcepcion(){
		
		Terreno unTerreno = new Aire(1,1);
		NaveTransporte unaNaveTransporte = new NaveTransporte(unTerreno);
		unTerreno.ubicar(unaNaveTransporte);
		@SuppressWarnings("unused")
		NaveTransporte otraNaveTransporte = new NaveTransporte(unTerreno);
		
	}	
	
	@Test
	public void moverAterrenoCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unTerreno = new Aire(1,4);
		Terreno otroTerreno = new Aire(2,3);
		NaveTransporte unaNaveTransporte = new NaveTransporte(unTerreno);
		unaNaveTransporte.actualizarUbicacion(otroTerreno);

		assertEquals(unaNaveTransporte.getUbicacionActual().columna(),3);
		assertEquals(unaNaveTransporte.getUbicacionActual().fila(),2);
		
		
	}

}
