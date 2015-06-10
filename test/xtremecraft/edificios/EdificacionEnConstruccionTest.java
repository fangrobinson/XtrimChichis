package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void creoBarracaEnConstruccionNoEstaLista(){
		Terran nuevoClanTerran = new Terran();
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, 1, 1);
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
	
		assertFalse(edif.estaListo());
		
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
	
	@Test
	public void alPasarElTiempoNecesarioBarracaEstaLista(){
		Terran nuevoClanTerran = new Terran();
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, 1, 1);
		int tiempo = unaBarraca.tiempoConstruccion();
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		for(int i = 0; i < tiempo ; i = i + 1){
			edif.pasarTiempo();
		}
		
		assertTrue(edif.estaListo());
		
	}
	
	@Test
	public void alPasarTiempoInsuficienteBarracaNoEstaLista(){
		Terran nuevoClanTerran = new Terran();
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, 1, 1);
		int tiempo = unaBarraca.tiempoConstruccion();
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		for(int i = 0; i < tiempo - 1 ; i = i + 1){
			edif.pasarTiempo();
		}
		
		assertFalse(edif.estaListo());
		
	}
	
	
}
