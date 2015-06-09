package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
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
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),1);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),2);
		
	}
	
	@Test
	public void getActualizarUbicacionModificaCoordenadasDelEdificio(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		Terreno unTerreno = new Tierra(1,3);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),1);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),2);
		
		puertoEstelar.actualizarUbicacion(unTerreno);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),1);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),3);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		
		assertEquals(puertoEstelar.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		int valorDanio = 30;
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),70);
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),40);
		
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
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		Marine nuevoMarine =puertoEstelar.entrenarMarine(unTerreno);
		
		assertEquals(nuevoMarine.getVida(),40);
		
	}
	
	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		Goliat unGoliat = puertoEstelar.entrenarGoliat(unTerreno);
		
		assertEquals(unGoliat.getVida(),125);
		
	}
	
	@Test
	public void crearEspectroDevuelveUnaNuevaUnidadEspectro(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		Espectro unEspectro = puertoEstelar.crearEspectro(unTerreno);
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		NaveCiencia nave = puertoEstelar.crearNaveCiencia(unTerreno);
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,fila, columna);
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,unaBarraca);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaFabrica);
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte(unTerreno);
		
		assertEquals(naveTransporte.getVida(),150);
		
	}


}
