package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;

public class FabricaTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void siLaRazaCreadoraNoPoseeBarracasYQuiereCrearUnaFabricaSeLanzaUnaExcepcion(){
		
		Terran razaTerran = new Terran();
		@SuppressWarnings("unused")
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,3,4);
		
	}
	
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran razaTerran = new Terran();
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran,1,2);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,3,4);
		
		assertEquals(fabrica.getUbicacionActual().fila(),3);
		assertEquals(fabrica.getUbicacionActual().columna(),4);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,1,2);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,3,4);
		
		assertEquals(unaFabrica.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran razaTerran = new Terran();
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran,1,2);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,3,4);
		int valorDanio = 30;
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),70);
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),40);
		
	}

	
	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		
		Tierra unTerreno = new Tierra(4,4); 
		Terran razaTerran = new Terran();
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran, 1,2);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,3,4);
		Goliat unGoliat = fabrica.entrenarGoliat(unTerreno);
		
		assertEquals(unGoliat.getVida(),125);
		
	}

}
