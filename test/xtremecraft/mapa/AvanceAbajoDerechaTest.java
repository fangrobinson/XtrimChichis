package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceAbajoDerechaTest {
	
	@Test
	public void avanzarAbajoDerechaDevuelveCoordenadaInferiorDerechaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaInferiorDerecha = new Coordenada(2,4);
		EstrategiaDeMovimiento estrategia = new AvanceAbajoDerecha();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaInferiorDerecha.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaInferiorDerecha.columna());
		
	}
	
}
