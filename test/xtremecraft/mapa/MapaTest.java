package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;


import org.junit.Test;


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
		assertEquals(unaCelda.getCoordenada().columna(),7);
		assertEquals(unaCelda.getCoordenada().fila(),1);
		
	}
	

	
}
