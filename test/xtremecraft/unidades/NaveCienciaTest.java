package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	
}
