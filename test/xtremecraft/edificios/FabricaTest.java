package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Goliat;

public class FabricaTest {
	
	public Fabrica construirNuevaFabrica(Terreno tierra){
		
		Fabrica fabrica = new Fabrica(tierra);
		for(int i=0;i<fabrica.tiempoConstruccion;i++){
			fabrica.pasarTiempo();
		}
		return fabrica;
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearLaFabrica(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = new Fabrica(tierra);
		
		assertTrue(fabrica.estaEnConstruccion());
		
	}
	
	@Test
	public void fabricaEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDeLaFabrica(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = construirNuevaFabrica(tierra);
		
		assertFalse(fabrica.estaEnConstruccion());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void siFabricaNoEstaConstruidaYSeIntentaEntrenarUnGoliatSeLanzaExcepcion(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = new Fabrica(tierra);
		
		fabrica.entrenarGoliat();
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = construirNuevaFabrica(tierra);
		
		assertEquals(fabrica.getUbicacionActual().fila(),1);
		assertEquals(fabrica.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = construirNuevaFabrica(tierra);
		
		assertFalse(fabrica.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = construirNuevaFabrica(tierra);
		
		assertFalse(fabrica.estaElevado());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = construirNuevaFabrica(tierra);
		
		assertEquals(fabrica.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Fabrica fabrica = construirNuevaFabrica(tierra);
		int valorDanio = 30;
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),70);
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),40);
		
	}

	
	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		 
		Terreno tierra = new Tierra(1,2);
		Fabrica fabrica = construirNuevaFabrica(tierra);
		Goliat unGoliat = fabrica.entrenarGoliat();
		
		assertEquals(unGoliat.getVida(),125);
		
	}

}
