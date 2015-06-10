package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;

public class DepositoDeSuministrosTest {
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = DepositoDeSuministros.nuevoDepositoDeSuministros(nuevoClanTerran,tierra);
		
		assertEquals(deposito.getUbicacionActual().fila(),1);
		assertEquals(deposito.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = DepositoDeSuministros.nuevoDepositoDeSuministros(nuevoClanTerran,tierra);
		
		assertEquals(deposito.getVida(),100);
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = DepositoDeSuministros.nuevoDepositoDeSuministros(nuevoClanTerran,tierra);
		
		assertFalse(deposito.estaElevado());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = DepositoDeSuministros.nuevoDepositoDeSuministros(nuevoClanTerran,tierra);
		int valorDanio = 30;
		
		deposito.recibirDanio(valorDanio);
		assertEquals(deposito.getVida(),70);
		
		deposito.recibirDanio(valorDanio);
		assertEquals(deposito.getVida(),40);
		
	}
	


}
