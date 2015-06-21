package xtremecraft.mapa;

import org.junit.Test;

public class EstrategiaRecorridoDeCoordenadasTest {
	
	@Test
	public void estrategiaDeCoordenadasAvanzarComienzaDevolviendoCoordenadaIzquierdaDeCoordenadaDeOrigen(){
		
		Coordenada coordenadaOrigen = new Coordenada(3,3);
		Coordenada coordenadaIzquierda = new Coordenada(3,2);
		EstrategiaRecorridoDeCoordenadas estrategia = new EstrategiaRecorridoDeCoordenadas(coordenadaOrigen);
		
	}

}
