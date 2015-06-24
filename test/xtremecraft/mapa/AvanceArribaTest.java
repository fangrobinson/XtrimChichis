package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvanceArribaTest {
	
	@Test
	public void avanzarArribaDevuelveCoordenadaSobreDeLaCoordenadaOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaSuperior = new Coordenada(4,3);
		EstrategiaDeMovimiento estrategia = new AvanceArriba();
		Coordenada coordenadaFinal =  estrategia.avanzar(coordenadaOrigen);
		
		assertEquals(coordenadaFinal.fila(),coordenadaSuperior.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaSuperior.columna());
		
	}

}
