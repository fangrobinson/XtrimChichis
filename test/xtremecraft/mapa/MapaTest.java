package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.unidades.Goliat;


public class MapaTest {

	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresNegativoDaError(){
		new Mapa(-1);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresCeroDaError(){
		new Mapa(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresUnoDaError(){
		new Mapa(1);
	}

	
	@Test 
	public void getCeldaEnFilaColumnaDevuelveLaCeldaConEsasCoordenadas(){
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		//coordenadaX coorresponde a la columna y coordenadaY corresponde a la fila:
		assertEquals(unaCelda.columna(),7);
		assertEquals(unaCelda.fila(),1);
		
	}
	
	@Test 
	public void ubicarCapaInferiorUnGoliatDevuelveTrue(){
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Terreno tierra = celda.getCapaInferior();
		Goliat goliat = new Goliat(tierra);
		
		boolean bool = celda.ubicarCapaInferior(goliat);
		
		assertTrue(bool);
	}
	
	@Test 
	public void ubicarCapaSuperiorUnGoliatDevuelveFalse(){
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Terreno tierra = celda.getCapaInferior();
		Goliat goliat = new Goliat(tierra);
		
		boolean bool = celda.ubicarCapaSuperior(goliat);
		
		assertFalse(bool);
	}
	
}
