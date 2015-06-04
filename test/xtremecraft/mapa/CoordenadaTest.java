package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordenadaTest {
	
	@Test
	public void getXDevuelveComponenteHorizontalDeLaCoordenada(){
		
		int fila = 1;
		int columna = 3;
		Coordenada coordenada = new Coordenada(fila,columna);
		
		assertEquals(coordenada.getX(),columna);
		
	}

	@Test
	public void getYDevuelveComponenteVerticalDeLaCoordenada(){
		
		int fila = 1;
		int columna = 3;
		Coordenada coordenada = new Coordenada(fila,columna);
		
		assertEquals(coordenada.getY(),fila);
		
	}
	
	@Test
	public void distanciaDevuleveLaDistanciaMinimaEntreDosCoordenadas(){
		
		Coordenada coordenada1 = new Coordenada(1,2);
		Coordenada coordenada2 = new Coordenada(5,5);
		
		assertTrue(coordenada1.distancia(coordenada2)==5);
		
		
	}
}
