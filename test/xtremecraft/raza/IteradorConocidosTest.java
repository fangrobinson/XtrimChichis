package xtremecraft.raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Edificio;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class IteradorConocidosTest {
	@Test
	public void cuantosHayDeconArregloVacioDaCero(){
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
	
	public Barraca construirNuevaBarraca(Terreno tierra){
		
		Barraca unaBarraca = new Barraca(tierra);
		for(int i=0;i<unaBarraca.tiempoConstruccion();i++){
			unaBarraca.pasarTiempo();
		}
		return unaBarraca;	
	}
	
	@Test
	public void arregloTieneBarracaconArregloVacioDaFalse(){
		boolean bool = true;
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		IteradorConocidos iter = new IteradorConocidos(array);
		bool = iter.arregloTiene(new Barraca(new Tierra(1,1)));
		assertFalse(bool);
	}
	
	@Test
	public void arregloTieneBarracaConArregloDeUnaBarracaSinConstruirDaFalse(){
		boolean bool = true;
		Barraca barraca = new Barraca(new Tierra(1,1));
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		array.add(barraca);
		IteradorConocidos iter = new IteradorConocidos(array);
		bool = iter.arregloTiene(barraca);
		assertFalse(bool);
	}
	
	@Test
	public void arregloTieneBarracaConArregloDeUnaBarracaConstruidaDevuelveTrue(){
		boolean bool = true;
		Tierra tierra = new Tierra(1,1);
		Barraca barraca = this.construirNuevaBarraca(tierra);
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		array.add(barraca);
		IteradorConocidos iter = new IteradorConocidos(array);
		bool = iter.arregloTiene(barraca);
		assertTrue(bool);
	}
	
	
}
