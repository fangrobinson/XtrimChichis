package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Terreno;

public class NaveCienciaTest {
	
	@Test
	public void naveCienciaSeInicializaConBarraDeVidaCompleta(){
		
		Terreno terreno = new Aire(1,2);
		NaveCiencia naveCiencia = new NaveCiencia(terreno);
		
		assertEquals(naveCiencia.getVida(),200);
	
	}
	
	@Test
	public void naveCienciaPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Terreno terreno = new Aire(1,2);
		NaveCiencia naveCiencia = new NaveCiencia(terreno);
		
		assertFalse(naveCiencia.puedeUbicarseSobreRecursoNatural());
	
	}
	
	@Test
	public void naveCienciaSeInicializaConRadioDeVision(){
		
		Terreno terreno = new Aire(1,2);
		NaveCiencia naveCiencia = new NaveCiencia(terreno);
		
		assertEquals(naveCiencia.getRadioVision(),10);
	
	}
	
	@Test
	public void naveCienciaSeInicializaConEnergia(){
		
		Terreno terreno = new Aire(1,2);
		NaveCiencia naveCiencia = new NaveCiencia(terreno);
		
		assertEquals(naveCiencia.getEnergia(),50);
	
	}
	
	@Test
	public void aumentarEnergiaEnTurnoAumentaNivelDeEnergia(){
		
		Terreno terreno = new Aire(1,2);
		NaveCiencia naveCiencia = new NaveCiencia(terreno);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),60);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),70);
	
	}
	
	
	@Test
	public void aumentarEnergiaEnTurnoSiEnergiaEsMayorOIgualADoscientosNoAumentaNivelDeEnergia(){
		
		Terreno terreno = new Aire(1,2);
		NaveCiencia naveCiencia = new NaveCiencia(terreno);
		
		for(int i=0;i<15;i++){
			naveCiencia.aumentarEnergiaEnTurno();
		}
		
		assertEquals(naveCiencia.getEnergia(),200);
		
		naveCiencia.aumentarEnergiaEnTurno();
		
		assertEquals(naveCiencia.getEnergia(),200);
	
	}
	
	
	
}
