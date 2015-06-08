package xtremecraft.raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Unidad;
import xtremecraft.unidades.Goliat;

public class TerranTest {
	
	@Test
	public void testNuevoTerranDevuelveInstanciaDeTerranConEstadoInicialRazaViva(){
		
		Terran razaTerran = new Terran();
		assertTrue(razaTerran.estaViva());
		
	}
	
	@Test
	public void agregarRecolectorDeMineralGuardaInstanciaDelEdificioRecolectorDeMineral(){
		
		Terran razaTerran = new Terran();
		MinaDeMinerales nodoMineral = new MinaDeMinerales(2);
		int fila = 3;
		int columna = 4;
		RecolectorDeMineral nuevoCentroMineral = new RecolectorDeMineral(nodoMineral,fila,columna);
		razaTerran. agregarEdificioRecolectorDeMineral(nuevoCentroMineral);
		RecolectorDeMineral centroMineral= razaTerran.getListaDeRecolectoresDeMineralConstruidos().remove(0);
		
		assertEquals(centroMineral,nuevoCentroMineral);
		
	}
	
	@Test
	public void agregarUnidadGuardaInstanciaDeLaUnidadCreada(){
		
		Terran razaTerran = new Terran();
		Terreno celda=new Tierra(1,4);
		Goliat nuevaUnidadGoliat = new Goliat(celda);
		razaTerran. agregarUnidad(nuevaUnidadGoliat);
		Unidad unidadGoliat= razaTerran.getListaDeUnidadesCreadas().remove(0);
		
		assertEquals(unidadGoliat.getVida(),125);
		
		
	}
	
	@Test
	public void agregarRecolectorDeGasVespenoGuardaInstanciaDelEdificioRecolectorDeGasVespeno(){
		
		Terran razaTerran = new Terran();
		VolcanGasVespeno volcanDeGasVespeno = new VolcanGasVespeno(200);
		int fila = 3;
		int columna = 4;
		RecolectorDeGasVespeno nuevaRefineria = new RecolectorDeGasVespeno(volcanDeGasVespeno,fila,columna);
		razaTerran. agregarEdificioRecolectorDeGasVespeno(nuevaRefineria);
		RecolectorDeGasVespeno refineriaTerranConstruida = razaTerran.getListaDeRecolectoresDeGasVespenoConstruidos().remove(0);
		
		assertEquals(nuevaRefineria,refineriaTerranConstruida);
		
		
		
	}
	
	
	
}