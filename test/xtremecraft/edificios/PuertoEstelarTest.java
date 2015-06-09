package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelarTest {
	

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),1);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		
		assertEquals(puertoEstelar.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		int valorDanio = 30;
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),70);
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),40);
		
	}
	
	@Test
	public void entrenarMarineDevuelveNuevaUnidadMarine(){
		
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		Marine nuevoMarine =puertoEstelar.entrenarMarine(unTerreno);
		
		assertEquals(nuevoMarine.getVida(),40);
		
	}
	
	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		Goliat unGoliat = puertoEstelar.entrenarGoliat(unTerreno);
		
		assertEquals(unGoliat.getVida(),125);
		
	}
	
	@Test
	public void crearEspectroDevuelveUnaNuevaUnidadEspectro(){
		
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		Espectro unEspectro = puertoEstelar.crearEspectro(unTerreno);
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
		
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		NaveCiencia nave = puertoEstelar.crearNaveCiencia(unTerreno);
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(unaFabrica);
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte(unTerreno);
		
		assertEquals(naveTransporte.getVida(),150);
		
	}


}
