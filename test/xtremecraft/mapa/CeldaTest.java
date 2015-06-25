package xtremecraft.mapa;

import static org.junit.Assert.*;

import org.junit.Test;

import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Ubicable;

public class CeldaTest {
	
	@Test
	public void ubicarCapaInferiorUnMarineDevuelveLaMismaCelda(){
		
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		Tierra tierraEsperada = new Tierra(2,2);
		Celda celda_vacia = new Celda(tierraEsperada, new Aire(2,2));
		Ubicable marine = new Marine(razaTerran);
		Terreno tierraRecibida = celda_vacia.ubicarCapaInferior(marine);
		
		assertEquals(tierraRecibida, tierraEsperada);
	}
	
	@Test(expected = NoSePudoOcuparElTerrenoException.class)
	public void ubicarCapaSuperiorUnMarineLanzaExcepcion(){
		
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		Celda celda = new Celda(new Tierra(1,2), new Aire(1,2));
		Ubicable marine = new Marine(razaTerran);
		celda.ubicarCapaSuperior(marine);

	}
	
}
