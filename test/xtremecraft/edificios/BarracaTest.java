package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Marine;

public class BarracaTest {
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearLaBarraca(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = new Barraca(tierra);
		
		assertTrue(unaBarraca.estaEnConstruccion());
		
	}
	

	@Test(expected = EdificioEnConstruccionException.class)
	public void siBarracaNoEstaConstruidaYSeIntentaEntrenarUnMarineSeLanzaExcepcion(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = new Barraca(tierra);
		
		unaBarraca.entrenarMarine();
		
	}

	@Test
	public void barracaEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDeLaBarraca(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertFalse(unaBarraca.estaEnConstruccion());
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertEquals(unaBarraca.getUbicacionActual().fila(),1);
		assertEquals(unaBarraca.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertFalse(unaBarraca.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertEquals(unaBarraca.getVida(),100);
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertFalse(unaBarraca.estaElevado());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		int valorDanio = 30;
		
		unaBarraca.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(),70);
		
		unaBarraca.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(),40);
		
	}
	
	@Test
	public void entrenarMarineDevuelveNuevaUnidadMarine(){

		Barraca unaBarraca = construirNuevaBarraca(1,2);
		Marine nuevoMarine = unaBarraca.entrenarMarine();
		
		assertEquals(nuevoMarine.getVida(),40);
		
	}
	
	public Barraca construirNuevaBarraca(int fila, int columna){
		
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = new Barraca(tierra);
		for(int i=0;i<unaBarraca.tiempoConstruccion();i++){
			unaBarraca.pasarTiempo();
		}
		return unaBarraca;
		
	}
	
}
