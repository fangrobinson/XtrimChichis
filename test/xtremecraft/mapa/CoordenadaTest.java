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
		
		assertEquals(coordenada.columna(),columna);
		
	}

	@Test
	public void getYDevuelveComponenteVerticalDeLaCoordenada(){
		
		int fila = 1;
		int columna = 3;
		Coordenada coordenada = new Coordenada(fila,columna);
		
		assertEquals(coordenada.fila(),fila);
		
	}
	
	@Test
	public void distanciaDevuleveLaDistanciaMinimaEntreDosCoordenadas(){
		
		Coordenada coordenada1 = new Coordenada(1,2);
		Coordenada coordenada2 = new Coordenada(5,5);
		
		assertTrue(coordenada1.distancia(coordenada2)==5);
		
	}
	/*
	@Test
	public void coordenadasAdyacentesDevuelveCoordenadasQueRodeanALaCoordenadaOrigen(){
		
		int filaOrigen = 3;
		int columnaOrigen = 3;
		Coordenada coordenadaOrigen = new Coordenada(filaOrigen,columnaOrigen);
		Coordenada coordenadaIzquierda = new Coordenada(filaOrigen,columnaOrigen-1);
		Coordenada coordenadaSuperiorIzquierda = new Coordenada(filaOrigen+1,columnaOrigen-1);
		Coordenada coordenadaSuperior = new Coordenada(filaOrigen+1,columnaOrigen);
		Coordenada coordenadaSuperiorDerecha = new Coordenada(filaOrigen+1,columnaOrigen+1);
		Coordenada coordenadaDerecha = new Coordenada(filaOrigen,columnaOrigen+1);
		Coordenada coordenadaInferiorDerecha = new Coordenada(filaOrigen-1,columnaOrigen+1);
		Coordenada coordenadaInferior = new Coordenada(filaOrigen-1,columnaOrigen);
		Coordenada coordenadaInferiorIzquierda = new Coordenada(filaOrigen-1,columnaOrigen-1);
		
		ArrayList<Coordenada> coordenadasFinales = coordenadaOrigen.getCoordenadasAdyacentes();
		ArrayList<Coordenada> coordenadasAdyacentes = new ArrayList<Coordenada>();
		coordenadasAdyacentes.add(coordenadaIzquierda);
		coordenadasAdyacentes.add(coordenadaSuperiorIzquierda);
		coordenadasAdyacentes.add(coordenadaSuperior);
		coordenadasAdyacentes.add(coordenadaSuperiorDerecha);
		coordenadasAdyacentes.add(coordenadaDerecha);
		coordenadasAdyacentes.add(coordenadaInferiorDerecha);
		coordenadasAdyacentes.add(coordenadaInferior);
		coordenadasAdyacentes.add(coordenadaInferiorIzquierda);
		
		//assertTrue(coordenadasFinales.containsAll(coordenadasAdyacentes));
		
	}*/
	
}
