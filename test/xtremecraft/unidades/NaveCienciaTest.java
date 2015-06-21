package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class NaveCienciaTest {
	
	@Test
	public void naveCienciaSeInicializaConBarraDeVidaCompleta(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertEquals(naveCiencia.getVida(),200);
	
	}
	
	@Test
	public void naveCienciaInicializadoConEstadoVivo(){

		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertTrue(naveCiencia.estaVivo());
		
	}
	
	@Test
	public void naveCienciaPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
	
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertFalse(naveCiencia.puedeUbicarseSobreRecursoNatural());
	
	}
	
	@Test
	public void naveCienciaSeInicializaConRadioDeVision(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertEquals(naveCiencia.getRadioVision(),10);
	
	}
	
	@Test
	public void naveCienciaSeInicializaConEnergia(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		assertEquals(naveCiencia.getEnergia(),50);
	
	}
	
	@Test
	public void aumentarEnergiaEnTurnoAumentaNivelDeEnergia(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),60);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),70);
	
	}
	
	@Test
	public void aumentarEnergiaEnTurnoSiEnergiaEsMayorOIgualADoscientosNoAumentaNivelDeEnergia(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		for(int i=0;i<15;i++){
			naveCiencia.aumentarEnergiaEnTurno();
		}
		
		assertEquals(naveCiencia.getEnergia(),200);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),200);
	
	}
	
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unTerreno=new Aire(1,4);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(unTerreno);
		
		assertEquals(naveCiencia.getUbicacionActual(),unTerreno.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(tierra1);
		naveCiencia.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(tierra1);
		naveCiencia.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Terreno aire = new Aire(1,2);
		Terreno aireDestino = new Aire(20,20);
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.actualizarUbicacion(aire);
		naveCiencia.actualizarUbicacion(aireDestino);
		
	}
	
	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		NaveCiencia naveCiencia = new NaveCiencia();
		NaveTransporte nave = new NaveTransporte();
		
		naveCiencia.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(naveCiencia.subirANaveDeTransporte(nave));
				
	}
	
}
