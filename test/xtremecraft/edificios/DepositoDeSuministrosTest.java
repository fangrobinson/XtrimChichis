package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class DepositoDeSuministrosTest {
	
	public DepositoDeSuministros construirNuevoDeposito(Terreno tierra){
		
		DepositoDeSuministros deposito = new DepositoDeSuministros(tierra);
		for(int i=0;i<deposito.tiempoConstruccion;i++){
			deposito.pasarTiempo();
		}
		return deposito;
		
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
		
		deposito.recibirDanio(valorDanio);
		assertEquals(deposito.getVida(),70);
		
		deposito.recibirDanio(valorDanio);
		assertEquals(deposito.getVida(),40);
		
	}
	


}
