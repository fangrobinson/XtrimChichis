package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;

public class FabricaTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void siLaRazaCreadoraNoPoseeBarracasYQuiereCrearUnaFabricaSeLanzaUnaExcepcion(){
		
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Terreno tierra = new Tierra(fila,columna);
		@SuppressWarnings("unused")
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,tierra);
		
	}
	
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran razaTerran = new Terran();
		Terreno unaTierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran,unaTierra);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,otraTierra);
		
		assertEquals(fabrica.getUbicacionActual().fila(),3);
		assertEquals(fabrica.getUbicacionActual().columna(),4);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		Terreno unaTierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,unaTierra);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,otraTierra);
		
		assertEquals(unaFabrica.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran razaTerran = new Terran();
		Terreno unaTierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran,unaTierra);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,otraTierra);
		int valorDanio = 30;
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),70);
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),40);
		
	}

	
	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		 
		Terran razaTerran = new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Tierra tierraTres = new Tierra(4,4);
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran, tierraUno);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,tierraDos);
		Goliat unGoliat = fabrica.entrenarGoliat(tierraTres);
		
		assertEquals(unGoliat.getVida(),125);
		
	}

}
