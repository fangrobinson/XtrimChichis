package xtremecraft.raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.edificios.CentroDeMineral;
import xtremecraft.recursos.NodoMineral;
import xtremecraft.unidades.Unidad;
import xtremecraft.unidades.Goliat;

public class TerranTest {
	
	@Test
	public void testNuevoTerranDevuelveInstanciaDeTerranConEstadoInicialRazaViva(){
		
		Terran razaTerran = new Terran();
		assertTrue(razaTerran.estaViva());
		
	}
	
	@Test
	public void testTerranAgregarCentroMineralGuardaInstanciaDelObjetoCentroMineral(){
		
		Terran razaTerran = new Terran();
		NodoMineral nodoMineral = new NodoMineral(2);
		CentroDeMineral nuevoCentroMineral = new CentroDeMineral(nodoMineral);
		razaTerran. agregarCentroMineral(nuevoCentroMineral);
		CentroDeMineral centroMineral= razaTerran.getListaDeCentrosMineralesConstruidos().remove(0);
		
		assertEquals(centroMineral,nuevoCentroMineral);
		
	}
	
	@Test
	public void testTerranAgregarUnidadGuardaInstanciaDelObjetoCentroMineral(){
		
		Terran razaTerran = new Terran();
		Goliat nuevaUnidadGoliat = new Goliat(1,2);
		razaTerran. agregarUnidad(nuevaUnidadGoliat);
		Unidad unidadGoliat= razaTerran.getListaDeUnidadesTerranCreadas().remove(0);
		
		assertEquals(unidadGoliat.getVida(),125);
		
		
	}
	
	
	
}