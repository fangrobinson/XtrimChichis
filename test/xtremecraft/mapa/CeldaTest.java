package xtremecraft.mapa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Ubicable;

public class CeldaTest {
	
	@Test
	public void ubicarCapaInferiorUnMarineDevuelveTrue(){
		
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		Celda celda_vacia = new Celda(new Tierra(2,2), new Aire(2,2));
		Ubicable marine = new Marine(razaTerran);
		boolean bool = celda_vacia.ubicarCapaInferior(marine);
		
		assertTrue(bool);
	}
	
	@Test
	public void ubicarCapaSuperiorUnMarineDevuelveFalsee(){
		
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		Celda celda = new Celda(new Tierra(1,2), new Aire(1,2));
		Ubicable marine = new Marine(razaTerran);
		boolean bool = celda.ubicarCapaSuperior(marine);
		
		assertFalse(bool);
	}
	
}
