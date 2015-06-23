package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceArribaIzquierdaTest {
	
	@Test
	public void avanzarArribaIzquierdaDevuelveCoordenadaSuperiorIzquierdaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaSuperiorIzquierda = new Coordenada(4,2);
		EstrategiaDeMovimiento estrategia = new AvanceArribaIzquierda();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaSuperiorIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaSuperiorIzquierda.columna());
		
	}

}
