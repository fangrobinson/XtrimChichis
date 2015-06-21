package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class NaveTransporteTest {
	
	@Test
	public void naveTransporteInicializadoConVidaCompleta(){

		NaveTransporte taxiVolador = new NaveTransporte();
		
		assertEquals(taxiVolador.getVida(),150);
		
	}
	
	@Test
	public void naveTransporteInicializadoConEstadoVivo(){

		NaveTransporte taxiVolador = new NaveTransporte();
		
		assertTrue(taxiVolador.estaVivo());
		
	}
	
	@Test
	public void naveTransportePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		NaveTransporte taxiVolador = new NaveTransporte();
		
		assertFalse(taxiVolador.puedeUbicarseSobreRecursoNatural());
	}
	
	@Test
	public void NaveTransporteGetVisionDevuelveRadioDeVisionDeLaNaveTransporte(){

		NaveTransporte taxiVolador = new NaveTransporte();
		
		assertEquals(taxiVolador.getRadioVision(),8);
	}
	
	@Test
	public void siUnaNaveTransporteAtacaAOtroPorAireLeSacaCeroDeVida(){

		Terreno aire1 = new Aire(1,3);
		Terreno aire2 = new Aire(1,4);
		NaveTransporte taxiVolador = new NaveTransporte();
		NaveTransporte remisVolador = new NaveTransporte();
		
		taxiVolador.actualizarUbicacion(aire1);
		remisVolador.actualizarUbicacion(aire2);
		remisVolador.atacar(taxiVolador);
		
		assertEquals(taxiVolador.vitalidad.getValor(),150);
	}

	@Test
	public void siUnaNaveTransporteAtacaAOtroPorTierraLeSacaCeroDeVida(){

		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		NaveTransporte taxiVolador = new NaveTransporte();
		NaveTransporte remisVolador = new NaveTransporte();
		
		taxiVolador.actualizarUbicacion(aire);
		remisVolador.actualizarUbicacion(tierra);
		remisVolador.atacar(taxiVolador);
		
		assertEquals(taxiVolador.vitalidad.getValor(),150);
		
	}
	
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno aire = new Aire(1,2);
		NaveTransporte taxiVolador = new NaveTransporte();
		
		taxiVolador.actualizarUbicacion(aire);

		assertEquals(taxiVolador.getUbicacionActual(),aire.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		NaveTransporte taxiVolador = new NaveTransporte();
		
		taxiVolador.actualizarUbicacion(aire);
		taxiVolador.actualizarUbicacion(tierra);

		assertFalse(aire.estaOcupado());
		
	}
	
	@Test
	public void siUnaNaveEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){

		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(1,4);
		NaveTransporte taxiVolador = new NaveTransporte();
		Goliat unGoliat = new Goliat();
		int cantidadDeAtaques = 15;
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		for(int i=0;i<cantidadDeAtaques;i++) unGoliat.atacar(taxiVolador);
		
		assertFalse(taxiVolador.estaVivo());
		
	}
	
	@Test
	public void transportarNuevaUnidadDevuelveTrueSiNoSuperaLaCapacidadMaxima(){
		
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte();
		Goliat unGoliat = new Goliat();
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		
		assertTrue( taxiVolador.transportarNuevaUnidad(unGoliat));
		
	}
	
	@Test
	public void transportarUnidadActualizaLaUbicacionDeLaUnidadQueTransporta(){
		
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte();
		Goliat unGoliat = new Goliat();
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		taxiVolador.transportarNuevaUnidad(unGoliat);
		
		assertEquals(unGoliat.getUbicacionActual(),taxiVolador.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionActualizaLaUbicacionDeLasUnidadesQueTransporta(){
		
		Terreno aire1 = new Aire(1,3);
		Terreno aire2 = new Tierra(5,8);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte();
		Goliat unGoliat = new Goliat();
		
		taxiVolador.actualizarUbicacion(aire1);
		unGoliat.actualizarUbicacion(tierra);
		taxiVolador.transportarNuevaUnidad(unGoliat);
		taxiVolador.actualizarUbicacion(aire2);
		
		assertEquals(unGoliat.getUbicacionActual(),taxiVolador.getUbicacionActual());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Terreno aire = new Aire(1,2);
		Terreno aireDestino = new Aire(40,40);
		NaveTransporte taxiVolador = new NaveTransporte();
		
		taxiVolador.actualizarUbicacion(aire);
		taxiVolador.actualizarUbicacion(aireDestino);
		
	}

}
