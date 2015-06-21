package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;


public class MapaTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresNegativoDaError(){
		
		new Mapa(-1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresCeroDaError(){
		
		new Mapa(0);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresUnoDaError(){
		
		new Mapa(1);
		
	}

	
	@Test 
	public void getCeldaEnFilaColumnaDevuelveLaCeldaConEsasCoordenadas(){
		
		Mapa mapa = new Mapa(2);
		Celda unaCelda= mapa.getCeldaEnFilaColumna(1,7);
		
		//coordenadaX coorresponde a la columna y coordenadaY corresponde a la fila:
		assertEquals(unaCelda.columna(),7);
		assertEquals(unaCelda.fila(),1);
		
	}
	
	@Test 
	public void ubicarCapaInferiorUnGoliatDevuelveTrue(){
		
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Goliat goliat = new Goliat();
		
		boolean bool = celda.ubicarCapaInferior(goliat);
		
		assertTrue(bool);
		
	}
	
	@Test 
	public void ubicarCapaSuperiorUnGoliatDevuelveFalse(){
		
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Goliat goliat = new Goliat();
		
		boolean bool = celda.ubicarCapaSuperior(goliat);
		
		assertFalse(bool);
		
	}
	
	@Test
	public void obtenerCeldasAdyacentesAlUbicableDevuelveListaCeldasAlrededorDelUbicable(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = new Terran(1,1);
		int fila =14;
		int columna =16;
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(fila,columna).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		
		ArrayList<Celda> celdasAdyacentesAlEdificio = mapa.obtenerCeldasAdyacentesAlUbicable(unaBarraca);
		ArrayList<Celda> celdasObtenidasDesdeElMapa = new ArrayList<Celda>();
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila+1,columna));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila-1,columna));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila,columna+1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila,columna-1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila+1,columna+1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila-1,columna-1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila+1,columna-1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila-1,columna+1));
		
		assertTrue(celdasAdyacentesAlEdificio.containsAll(celdasObtenidasDesdeElMapa));
		
	}
	
	@Test
	public void liberarEspacioCorrespondienteADejaLiberaElEspacioOcupadoPorElUbicable(){
		
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Goliat goliat = new Goliat();
		
		celda.ubicarCapaInferior(goliat);
		goliat.actualizarUbicacion(celda.getCapaInferior());
		
		assertTrue(celda.getCapaInferior().estaOcupado());
		
		mapa.liberarEspacioCorrespondienteA(goliat);
		
		assertFalse(celda.getCapaInferior().estaOcupado());
		
	}
	
	/*@Test
	public void obtenerCeldasEnRadioDevuelveListaCeldasADistanciaRadio(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = new Terran();
		int fila =14;
		int columna =16;
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(fila,columna).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		
		ArrayList<Celda> celdasAdyacentesAlEdificio = mapa.obtenerCeldasEnRadio(unaBarraca,2);
		
		
	}*/
	
}
