package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EstrategiaDeMovimientoTest {
	
	@Test
	public void avanzarIzquierdaDevuelveCoordenadaAIzquierdaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaIzquierda = new Coordenada(3,2);
		EstrategiaDeMovimiento estrategia = new AvanceAIzquierda();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaIzquierda.columna());
		
	}
	
	@Test
	public void avanzarDerechaDevuelveCoordenadaADerechaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaDerecha = new Coordenada(3,4);
		EstrategiaDeMovimiento estrategia = new AvanceADerecha();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaDerecha.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaDerecha.columna());
		
	}
	
	@Test
	public void avanzarAbajoDevuelveCoordenadaDebajoDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaInferior = new Coordenada(2,3);
		EstrategiaDeMovimiento estrategia = new AvanceAbajo();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaInferior.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaInferior.columna());
		
	}
	
	@Test
	public void avanzarArribaDevuelveCoordenadaSobreDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaSuperior = new Coordenada(4,3);
		EstrategiaDeMovimiento estrategia = new AvanceArriba();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaSuperior.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaSuperior.columna());
		
	}
	
	@Test
	public void avanzarArribaIzquierdaDevuelveCoordenadaSuperiorIzquierdaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaSuperiorIzquierda = new Coordenada(4,2);
		EstrategiaDeMovimiento estrategia = new AvanceArribaIzquierda();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaSuperiorIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaSuperiorIzquierda.columna());
		
	}
	
	@Test
	public void avanzarArribaDerechaDevuelveCoordenadaSuperiorDerechaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaSuperiorDerecha = new Coordenada(4,4);
		EstrategiaDeMovimiento estrategia = new AvanceArribaDerecha();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaSuperiorDerecha.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaSuperiorDerecha.columna());
		
	}
	
	@Test
	public void avanzarAbajoDerechaDevuelveCoordenadaInferiorDerechaDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaInferiorDerecha = new Coordenada(2,4);
		EstrategiaDeMovimiento estrategia = new AvanceAbajoDerecha();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaInferiorDerecha.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaInferiorDerecha.columna());
		
	}
	
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
