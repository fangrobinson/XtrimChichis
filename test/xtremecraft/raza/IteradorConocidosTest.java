package xtremecraft.raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Random;

import java.util.ArrayList;

import org.junit.Test;

public class IteradorConocidosTest {
	@Test
	public void cuantosHayDeconArregloVacioSiempreDaCero(){
		int cant=-1;
		ArrayList<String> array = new ArrayList<String>();
		IteradorConocidos iter = new IteradorConocidos(array);
		
		cant = iter.cuantosHayDe(1);
		
		assertEquals(cant, 0);
	}
	
	@Test
	public void cuantosHayDeconArregloDeAlgunosElementosDaEsaCantidad(){
		int cant=-1;
		Random randomGen = new Random();
		int random = 50;
		int cant_esperada = randomGen.nextInt(random);
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		for (int j = 0; j < cant_esperada; j++){
			array.add(randomGen.nextInt(random));
		}
		
		IteradorConocidos iter = new IteradorConocidos(array);
		cant = iter.cuantosHayDe(1);
		assertEquals(cant, cant_esperada);
	}
	
	@Test
	public void arregloTieneIntconArregloVacioSiempreDaFalse(){
		boolean bool = true;
		ArrayList<String> array = new ArrayList<String>();
		IteradorConocidos iter = new IteradorConocidos(array);
		bool = iter.arregloTiene(1);
		assertFalse(bool);
	}
	
	@Test
	public void arregloTieneconArregloConElementosDaTrue(){
		boolean bool = false;
		ArrayList<Number> array = new ArrayList<Number>();
		int entero = 1;
		Double doble= 0.1;
		
		array.add(entero);
		array.add(doble);
		IteradorConocidos iter = new IteradorConocidos(array);
		
		bool = iter.arregloTiene(entero);
		
		assertTrue(bool);
	}
	
	
	
}
