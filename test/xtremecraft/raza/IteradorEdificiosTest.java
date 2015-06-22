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
		
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	public void cuantosHayDeConstruidosDevuelveCeroConArregloVacio(){
		
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(array);
		
		assertEquals(iter.cuantosHayCreadosDe(Barraca.class), 0);
		
	}
	
	@Test
	public void cuantosHayConstruidosConArregloDeEdificiosSinConstruirDevuelveCero(){
		
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(array);
		
		assertEquals(iter.cuantosHayCreadosDe(Barraca.class), 0);
		
	}
	
	public void cuantosHayDeDevuelveCeroConArregloVacio(){
		
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		
		assertEquals(iter.cuantosHayDe(Barraca.class), 0);
		
	}
	
	@Test
	public void cuantosHayDeConArregloDeEdificiosConstruidosOSinConstruirDevuelveEsaCantidad(){
		
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		Terran nacion = crearRazaTerranValida();
		
		Barraca barraca = new Barraca(nacion, new Tierra(1,2));
		for(int i=0;i<barraca.tiempoConstruccion();i++)	barraca.pasarTiempo();
		edificios.add(barraca);
		
		assertEquals(iter.cuantosHayCreadosDe(Barraca.class), 1);
		
	}

	@Test
	public void elementoPerteneceConBarracaConArregloVacioDaFalse(){
		
		Terran nacion = crearRazaTerranValida();
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		
		assertFalse(iter.elementoPertenece(new Barraca(nacion, new Tierra(1,1))));
		
	}
	
	@Test
	public void tieneCreadosBarracaConArregloDeUnaBarracaSinConstruirDaFalse(){
		
		Terran nacion = crearRazaTerranValida();
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(nacion, tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertFalse(iter.tieneCreados(Barraca.class));
		
	}
	
	@Test
	public void elementoPerteneceBarracaConArregloDeUnaBarracaConstruidaDevuelveTrue(){
		
		Terran nacion = crearRazaTerranValida();
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(nacion, tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		for(int i=0;i<unaBarraca.tiempoConstruccion();i++)	unaBarraca.pasarTiempo();
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertTrue(iter.elementoPertenece(unaBarraca));
		
	}
	
	@Test
	public void elementoPerteneceBarracaConArregloDeUnaBarracaNoConstruidaDevuelveTrue(){
		
		Terran nacion = crearRazaTerranValida();
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(nacion, tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertTrue(iter.elementoPertenece(unaBarraca));
		
	}
	
	
}
