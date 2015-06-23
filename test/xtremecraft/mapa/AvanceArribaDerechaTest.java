package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceArribaDerechaTest {
	
	@Test
	public void avanzarArribaDerechaDevuelveCoordenadaSuperiorDerechaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaSuperiorDerecha = new Coordenada(4,4);
		EstrategiaDeMovimiento estrategia = new AvanceArribaDerecha();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaSuperiorDerecha.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaSuperiorDerecha.columna());
		
	}

}
