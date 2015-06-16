package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Goliat;

public class FabricaTest {
	
	/*
	@Test(expected = IllegalArgumentException.class)
	public void siLaRazaCreadoraNoPoseeBarracasYQuiereCrearUnaFabricaSeLanzaUnaExcepcion(){
		
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Terreno tierra = new Tierra(fila,columna);
		@SuppressWarnings("unused")
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,tierra);
		
	}*/
	
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = new Fabrica(tierra);
		
		assertEquals(fabrica.getUbicacionActual().fila(),1);
		assertEquals(fabrica.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = new Fabrica(tierra);
		
		assertFalse(fabrica.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = new Fabrica(tierra);
		
		assertFalse(fabrica.estaElevado());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = new Fabrica(tierra);
		
		assertEquals(fabrica.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = new Fabrica(tierra);
		int valorDanio = 30;
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),70);
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),40);
		
	}

	
	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		 
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,4);
		Fabrica fabrica = new Fabrica(tierra1);
		Goliat unGoliat = fabrica.entrenarGoliat(tierra2);
		
		assertEquals(unGoliat.getVida(),125);
		
	}

}
