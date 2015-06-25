package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceADerechaTest {
	
	@Test
	public void avanzarDerechaDevuelveCoordenadaADerechaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaDerecha = new Coordenada(3,4);
		EstrategiaDeMovimiento estrategia = new AvanceADerecha();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaDerecha.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaDerecha.columna());
		
	}

}
