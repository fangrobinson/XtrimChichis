package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceAbajoIzquierdaTest {
	
	@Test
	public void avanzarAbajoIzquierdaDevuelveCoordenadaInferiorIzquierdaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaInferiorIzquierda = new Coordenada(2,2);
		EstrategiaDeMovimiento estrategia = new AvanceAbajoIzquierda();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaInferiorIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaInferiorIzquierda.columna());
		
	}

}
