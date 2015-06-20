package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Marine;

public class DepositoDeSuministrosTest {
	
	public DepositoDeSuministros construirNuevoDeposito(Terreno tierra){
		
		DepositoDeSuministros deposito = new DepositoDeSuministros(tierra);
		for(int i=0;i<deposito.tiempoConstruccion;i++){
			deposito.pasarTiempo();
		}
		return deposito;
		
	}
	
	@Test
	public void depositoSeInicializaConEstadoVivo(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(tierra);
		
		assertTrue(deposito.estaVivo());		
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(tierra);
		
		assertEquals(deposito.getUbicacionActual().fila(),1);
		assertEquals(deposito.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(tierra);
		
		assertFalse(deposito.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(tierra);
		
		assertEquals(deposito.getVida(),100);
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(tierra);
		
		assertFalse(deposito.estaElevado());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(tierra);
		int valorDanio = 30;
		int vidaEsperada = deposito.vidaMax() - valorDanio;
		
		deposito.recibirDanio(valorDanio);
		assertEquals(deposito.getVida(), vidaEsperada);
		vidaEsperada -= valorDanio;
		deposito.recibirDanio(valorDanio);
		assertEquals(deposito.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siUnDepositoEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terreno tierra = new Tierra(3,2);
		Terreno otraTierra = new Tierra(1,2);
		DepositoDeSuministros deposito = construirNuevoDeposito(tierra);
		Marine miniSamus = new Marine();
		int cantidadDeAtaquesADeposito = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		deposito.actualizarUbicacion(otraTierra);
		for(int i=0;i<cantidadDeAtaquesADeposito;i++) miniSamus.atacar(deposito);
		
		assertFalse(deposito.estaVivo());		
		
	}

}
