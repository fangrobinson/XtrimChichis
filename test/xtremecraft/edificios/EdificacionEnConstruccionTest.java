package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.raza.Terran;

public class EdificacionEnConstruccionTest {
	
	@Test
	public void creoBarracaEnConstruccionYSuVidaEsCero(){
		Terran nuevoClanTerran = new Terran();
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, 1, 1);
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		int vida = edif.getVida();
		
		assertEquals(vida, 0);
		
	}
	
	@Test
	public void creoBarracaEnConstruccionSuVidaAumentaAlPasarElTiempo(){
		Terran nuevoClanTerran = new Terran();
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, 1, 1);
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		edif.pasarTiempo();
		int vida = edif.getVida();
		
		assertTrue(vida > 0);
		
	}
}
