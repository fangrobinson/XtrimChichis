package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceAbajoTest {
	
	@Test
	public void avanzarAbajoDevuelveCoordenadaDebajoDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaInferior = new Coordenada(2,3);
		EstrategiaDeMovimiento estrategia = new AvanceAbajo();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaInferior.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaInferior.columna());
		
	}

}
