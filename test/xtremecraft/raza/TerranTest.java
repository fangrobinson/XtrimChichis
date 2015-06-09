package xtremecraft.raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.Recolector;
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
		RecolectorDeMineral nuevoCentroMineral = Recolector.nuevoRecolectorDeMineral(razaTerran, nodoMineral, fila, columna);
		RecolectorDeMineral centroMineral= razaTerran.getListaDeRecolectoresDeMineralConstruidos().remove(0);
		
		assertEquals(centroMineral,nuevoCentroMineral);
		
	}
	
	@Test
	public void agregarUnidadGuardaInstanciaDeLaUnidadCreada(){
		
		Terran razaTerran = new Terran();
		Terreno celda=new Tierra(2,3);
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
		RecolectorDeGasVespeno nuevaRefineria = Recolector.nuevoRecolectorDeGasVespeno(razaTerran,volcanDeGasVespeno,fila,columna);
		RecolectorDeGasVespeno refineriaTerranConstruida = razaTerran.getListaDeRecolectoresDeGasVespenoConstruidos().remove(0);
		
		assertEquals(nuevaRefineria,refineriaTerranConstruida);
		
		
		
	}
	
	@Test
	public void agregarBarracaGuardaInstanciaDelEdificioCreadorDeUnidades(){
		
		Terran razaTerran = new Terran();
		int fila = 3;
		int columna = 4;
		Barraca barraca = Barraca.nuevaBarraca(razaTerran, fila, columna);
		Barraca barracaConstruida = razaTerran.getListaDeBarracasConstruidas().remove(0);
		
		assertEquals(barraca,barracaConstruida);
		
		
		
	}
	
	@Test
	public void agregarFabricaGuardaInstanciaDeLaFabricaYBorraBarracaBaseDeLaListaDeBarracasCreadas(){
		
		Terran razaTerran = new Terran();
		int fila = 3;
		int columna = 4;
		Barraca barraca = Barraca.nuevaBarraca(razaTerran, fila, columna);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,barraca);
		
		Fabrica fabricaConstruida = razaTerran.getListaDeFabricasConstruidas().remove(0);
		
		assertEquals(fabrica,fabricaConstruida);
		assertTrue(razaTerran.getListaDeBarracasConstruidas().isEmpty());
	
	}
	
	@Test
	public void agregarPuertoEstelarGuardaInstanciaDelPuertoYBorraFabricaBaseDeLaListaDeFabricasCreadas(){
		
		Terran razaTerran = new Terran();
		int fila = 3;
		int columna = 4;
		Barraca barraca = Barraca.nuevaBarraca(razaTerran, fila, columna);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,barraca);
		PuertoEstelar puerto = PuertoEstelar.nuevoPuertoEstelar(razaTerran, fabrica);
		
		PuertoEstelar puertoConstruido = razaTerran.getListaDePuertosEstelaresConstruidos().remove(0);
		
		assertEquals(puerto,puertoConstruido);
		assertTrue(razaTerran.getListaDeFabricasConstruidas().isEmpty());
	
	}
	
}