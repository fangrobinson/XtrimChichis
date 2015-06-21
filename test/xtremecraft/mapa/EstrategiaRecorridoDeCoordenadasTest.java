package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EstrategiaRecorridoDeCoordenadasTest {
	
	@Test
	public void avanzarComienzaDevolviendoCoordenadaIzquierdaDeCoordenadaDeOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaIzquierda = new Coordenada(3,2);
		EstrategiaRecorridoDeCoordenadas estrategia = new EstrategiaRecorridoDeCoordenadas(coordenadaOrigen);
		
		Coordenada coordenadaFinal = estrategia.avanzar();
		
		assertEquals(coordenadaFinal.fila(),coordenadaIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaIzquierda.columna());
		
	}
	
	@Test
	public void siAvanzoDosVecesSinCambiarDeDireccionObtengoLaSegundaCoordenadaEnLaDireccionEstablecida(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaIzquierda = new Coordenada(3,1);
		EstrategiaRecorridoDeCoordenadas estrategia = new EstrategiaRecorridoDeCoordenadas(coordenadaOrigen);
		
		Coordenada coordenadaFinal = estrategia.avanzar();
		coordenadaFinal = estrategia.avanzar();
		
		assertEquals(coordenadaFinal.fila(),coordenadaIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaIzquierda.columna());
		
	}
	
	@Test
	public void cambiarDireccionCambiaDireccionDeMovimientoEnSentidoHorario(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaSuperiorIzquierda = new Coordenada(4,2);
		EstrategiaRecorridoDeCoordenadas estrategia = new EstrategiaRecorridoDeCoordenadas(coordenadaOrigen);
		
		estrategia.cambiarDireccionDeMovimiento(coordenadaOrigen);
		Coordenada coordenadaFinal = estrategia.avanzar();
		
		assertEquals(coordenadaFinal.fila(),coordenadaSuperiorIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaSuperiorIzquierda.columna());
		
	}
	
	@Test
	public void luegoDeVisitarTodasLasDireccionesDeAvanceCambiarDireccionVuelveALaDireccionDeInicio(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaIzquierda = new Coordenada(3,2);
		EstrategiaRecorridoDeCoordenadas estrategia = new EstrategiaRecorridoDeCoordenadas(coordenadaOrigen);
		
		for(int cantidadDirecciones = 0;cantidadDirecciones<EstrategiaRecorridoDeCoordenadas.getNumeroDeDireccionesDeMovimiento();cantidadDirecciones++){
			estrategia.cambiarDireccionDeMovimiento(coordenadaOrigen);
		}
		Coordenada coordenadaFinal = estrategia.avanzar();
		
		assertEquals(coordenadaFinal.fila(),coordenadaIzquierda.fila());
		assertEquals(coordenadaFinal.columna(),coordenadaIzquierda.columna());
		
	}
		

}
