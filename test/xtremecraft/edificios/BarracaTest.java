package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class BarracaTest {
	

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierra);
		
		assertEquals(unaBarraca.getUbicacionActual().fila(),1);
		assertEquals(unaBarraca.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierra);
		
		assertEquals(unaBarraca.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierra);
		int valorDanio = 30;
		
		unaBarraca.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(),70);
		
		unaBarraca.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(),40);
		
	}
	
	@Test
	public void entrenarMarineDevuelveNuevaUnidadMarine(){

		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Terreno otraTierra = new Tierra(1,1);
		Marine nuevoMarine = Barraca.nuevaBarraca(nuevoClanTerran,tierra).entrenarMarine(otraTierra);
		
		assertEquals(nuevoMarine.getVida(),40);
		
	}

}
