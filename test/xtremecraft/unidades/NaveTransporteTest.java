package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class NaveTransporteTest {
	
	@Test
	public void NaveTransporteInicializadoConVidaCompleta(){

		NaveTransporte taxiVolador = new NaveTransporte();
		
		assertEquals(taxiVolador.getVida(),150);
	}
	
	@Test
	public void NaveTransportePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

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
		
		assertEquals(taxiVolador.vitalidad.devolverValor(),150);
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
		
		assertEquals(taxiVolador.vitalidad.devolverValor(),150);
	}
		
	
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		NaveTransporte taxiVolador = new NaveTransporte();
		
		taxiVolador.actualizarUbicacion(aire);

		assertEquals(taxiVolador.getUbicacionActual().fila(),1);
		assertEquals(taxiVolador.getUbicacionActual().columna(),2);
		
		taxiVolador.actualizarUbicacion(tierra);

		assertEquals(taxiVolador.getUbicacionActual().fila(),2);
		assertEquals(taxiVolador.getUbicacionActual().columna(),3);
		
		
	}

}
