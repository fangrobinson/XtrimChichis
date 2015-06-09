package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Goliat;

public class FabricaTest {
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila,columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		
		assertEquals(unaFabrica.getUbicacionActual().fila(),1);
		assertEquals(unaFabrica.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		
		assertEquals(unaFabrica.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		int valorDanio = 30;
		
		unaFabrica.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(),70);
		
		unaFabrica.recibirDanio(valorDanio);
		assertEquals(unaBarraca.getVida(),40);
		
	}
	
	@Test
	public void entrenarMarineDevuelveNuevaUnidadMarine(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		Marine nuevoMarine =unaFabrica.entrenarMarine(unTerreno);
		
		assertEquals(nuevoMarine.getVida(),40);
		
	}
	
	@Test
	public void entrenarGoliatDevuelveNuevaUnidadMarine(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		Goliat unGoliat =unaFabrica.entrenarGoliat(unTerreno);
		
		assertEquals(unGoliat.getVida(),125);
		
	}

}
