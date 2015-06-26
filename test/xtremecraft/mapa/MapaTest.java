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
	
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
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
	public void ubicarCapaInferiorUnGoliatDevuelveElTerreno(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Goliat goliat = new Goliat(nacion);
		Terreno tierraEsperada = celda.getCapaInferior();
		
		Terreno tierra = celda.ubicarCapaInferior(goliat);
		
		assertEquals(tierra, tierraEsperada);
		
	}
	
	@Test (expected = NoSePudoOcuparElTerrenoException.class)
	public void ubicarCapaSuperiorUnGoliatDevuelveFalse(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Goliat goliat = new Goliat(nacion);
		
		celda.ubicarCapaSuperior(goliat);
		
	}
	
	@Test
	public void ubicarBasesParaCuatroJugadoresUbicaBaseUnoEnEsquinaInferiorIzquierdaParaPrimerJugador(){
		
		Mapa mapa = new Mapa(4);
		Tierra terrenoPrimerJugador = mapa.obtenerTerrenoJugador(1);
		
		assertEquals(mapa.getCeldaEnFilaColumna(4,4).getCapaInferior(),terrenoPrimerJugador);
		
	}
	
	@Test
	public void ubicarBasesParaCuatroJugadoresUbicaBaseUnoEnEsquinaSuperiorDerechaParaPrimerJugador(){
		
		Mapa mapa = new Mapa(4);
		Tierra terrenoSegundoJugador = mapa.obtenerTerrenoJugador(2);
		
		assertEquals(mapa.getCeldaEnFilaColumna(20,20).getCapaInferior(),terrenoSegundoJugador);
		
	}
	
	@Test
	public void ubicarBasesParaCuatroJugadoresUbicaBaseTresEnEsquinaInferiorDerecha(){
		
		Mapa mapa = new Mapa(4);
		Tierra terrenoTercerJugador = mapa.obtenerTerrenoJugador(3);
		
		assertEquals(mapa.getCeldaEnFilaColumna(20,4).getCapaInferior(),terrenoTercerJugador);
		
	}
	
	@Test
	public void ubicarBasesParaCuatroJugadoresUbicaBaseCuatroEnEsquinaSuperiorIzquierda(){
		
		Mapa mapa = new Mapa(4);
		Tierra terrenoCuartoJugador = mapa.obtenerTerrenoJugador(4);
		
		assertEquals(mapa.getCeldaEnFilaColumna(4,20).getCapaInferior(),terrenoCuartoJugador);
		
	}
	
	@Test
	public void obtenerCeldasAdyacentesAlUbicableDevuelveListaCeldasAlrededorDelUbicable(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranValida();;
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
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Celda celda = mapa.getCeldaEnFilaColumna(1,1);
		Goliat goliat = new Goliat(nacion);
		
		celda.ubicarCapaInferior(goliat);
		celda = mapa.getCeldaEnFilaColumna(1,2);
		goliat.actualizarUbicacion(celda.getCapaInferior());
		
		assertTrue(celda.getCapaInferior().estaOcupado());
		
		mapa.liberarEspacioCorrespondienteA(goliat);
		
		assertFalse(celda.getCapaInferior().estaOcupado());
		
	}
	
	@Test
	public void obtenerCeldasEnRadioDevuelveListaCeldasADistanciaRadio(){
		
		Mapa mapa = new Mapa(2);
		Terran nacion = crearRazaTerranValida();
		int fila =14;
		int columna =16;
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(fila,columna).getCapaInferior();
		Barraca unaBarraca = nacion.crearBarraca(unTerreno);
		ArrayList<Celda> celdasEnRadio = mapa.obtenerCeldasEnRadio(unaBarraca,2);
		ArrayList<Celda> celdasObtenidasDesdeElMapa = new ArrayList<Celda>();
		
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila,columna));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila+1,columna));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila-1,columna));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila,columna+1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila,columna-1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila+1,columna+1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila-1,columna-1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila-1,columna+1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila+1,columna-1));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila+2,columna));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila-2,columna));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila,columna+2));
		celdasObtenidasDesdeElMapa.add(mapa.getCeldaEnFilaColumna(fila,columna-2));
		
		assertTrue(celdasEnRadio.containsAll(celdasObtenidasDesdeElMapa));
		
	}
	
	@Test
	public void obtenerTerrenoDevuelveUnTerrenoDistintoParaCadaJugador(){
		
		Mapa mapa = new Mapa(4);
				
		Tierra unTerreno = mapa.obtenerTerrenoJugador(1);
		Tierra otroTerreno = mapa.obtenerTerrenoJugador(2);
		
		assertTrue(unTerreno!=otroTerreno);
		
	}
	
	@Test
	public void elMapaSeInicializaConRecursos(){
		
		Mapa mapa = new Mapa(2);
		
		ArrayList<Terreno> terrenosConRecursos = mapa.obtenerTerrenosConRecursos();
		
		assertTrue(terrenosConRecursos.size()!=0);		
		
	}
		
}
