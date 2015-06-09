package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class BarracaTest {
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		
		assertEquals(unaBarraca.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
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
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Marine nuevoMarine = Barraca.nuevaBarraca(razaTerran, fila, columna).entrenarMarine(unTerreno);
		
		assertEquals(nuevoMarine.getVida(),40);
		
	}

}
