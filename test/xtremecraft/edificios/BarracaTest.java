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
	public void barracaSeInicializaConEstadoVivo(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = new Barraca(tierra);
		
		assertTrue(unaBarraca.estaVivo());
		
	}
	
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
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertFalse(unaBarraca.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		int vidaEsperada = unaBarraca.vidaMax();
		assertEquals(unaBarraca.getVida(), vidaEsperada);
		
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
		int vidaEsperada = unaBarraca.vidaMax() - valorDanio;
		
		unaBarraca.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(), vidaEsperada);
		
		vidaEsperada -= valorDanio;
		
		unaBarraca.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(), vidaEsperada);
		
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
	
	@Test
	public void siUnaBarracaEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terreno tierra = new Tierra(3,2);
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		Marine miniSamus = new Marine();
		int cantidadDeAtaquesABarraca = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		for(int i=0;i<cantidadDeAtaquesABarraca;i++) miniSamus.atacar(unaBarraca);
		
		assertFalse(unaBarraca.estaVivo());		
		
	}

}
