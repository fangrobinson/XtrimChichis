package xtremecraft.raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Edificio;
import xtremecraft.mapa.Tierra;

public class IteradorEdificiosTest {
		
	public void cuantosHayDeConstruidosDevuelveCeroConArregloVacio(){
		
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(array);
		Barraca barraca = new Barraca(new Tierra(1,2));
		
		assertEquals(iter.cuantosHayConstruidos(barraca), 0);
		
	}
	
	@Test
	public void cuantosHayConstruidosConArregloDeEdificiosSinConstruirDevuelveCero(){
		
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(array);
		Barraca barraca = new Barraca(new Tierra(1,2));
		
		assertEquals(iter.cuantosHayConstruidos(barraca), 0);
		
	}
	
	public void cuantosHayDeDevuelveCeroConArregloVacio(){
		
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		
		Barraca barraca = new Barraca(new Tierra(1,2));
		
		assertEquals(iter.cuantosHayDe(barraca), 0);
		
	}
	
	@Test
	public void cuantosHayDeConArregloDeEdificiosConstruidosOSinConstruirDevuelveEsaCantidad(){
		
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		
		Barraca barraca = new Barraca(new Tierra(1,2));
		edificios.add(barraca);
		
		assertEquals(iter.cuantosHayConstruidos(barraca), 1);
		
	}

	@Test
	public void arregloTieneBarracaConArregloVacioDaFalse(){
		
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		
		assertFalse(iter.arregloTiene(new Barraca(new Tierra(1,1))));
		
	}
	
	@Test
	public void arregloTieneBarracaConArregloDeUnaBarracaSinConstruirDaFalse(){
		
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertFalse(iter.arregloTiene(unaBarraca));
		
	}
	
	@Test
	public void arregloTieneBarracaConArregloDeUnaBarracaConstruidaDevuelveTrue(){
		
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		for(int i=0;i<unaBarraca.tiempoConstruccion();i++)	unaBarraca.pasarTiempo();
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertTrue(iter.arregloTiene(unaBarraca));
		
	}
	
	
}
