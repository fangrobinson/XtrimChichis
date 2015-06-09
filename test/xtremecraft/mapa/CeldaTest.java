package xtremecraft.mapa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.unidades.Marine;

public class CeldaTest {
	@Test
	public void ubicarCapaInferiorUnMarineDevuelveTrue(){
		Celda celda_creacion = new Celda(new Tierra(), new Aire());
		Celda celda_vacia = new Celda(new Tierra(), new Aire());
		Terreno tierra = celda_creacion.getCapaInferior();
		Ubicable marine = new Marine(tierra);
		
		boolean bool = celda_vacia.ubicarCapaInferior(marine);
		
		assertTrue(bool);
	}
	
	@Test
	public void ubicarCapaSuperiorUnMarineDevuelveFalsee(){
		Celda celda = new Celda(new Tierra(), new Aire());
		Terreno tierra = celda.getCapaInferior();
		Ubicable marine = new Marine(tierra);
		
		boolean bool = celda.ubicarCapaSuperior(marine);
		
		assertFalse(bool);
	}
	
	/*
	@Test
	public void ubicarCapaSuperior(){
		Celda celda = new Celda(new Tierra(), new Aire());
		Ubicable marine = new Marine();
		
		boolean bool = celda.ubicarCapaSuperior(marine);
		
		assertTrue(bool);
	}*/
	
}
