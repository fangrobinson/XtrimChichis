package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceAIzquierdaTest {

	@Test
	public void avanzarIzquierdaDevuelveCoordenadaAIzquierdaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaIzquierda = new Coordenada(3,2);
		EstrategiaDeMovimiento estrategia = new AvanceAIzquierda();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaIzquierda.columna());
		
	}
	
}
