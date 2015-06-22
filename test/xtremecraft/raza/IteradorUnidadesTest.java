package xtremecraft.raza;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Unidad;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.IteradorUnidades;

public class IteradorUnidadesTest {
	
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	public void cuantosHayDeDevuelveCeroConArregloVacio(){
		
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		IteradorUnidades iter = new IteradorUnidades(unidades);
		
		assertEquals(iter.cuantosHayDe(Marine.class), 0);
		
	}
	
	@Test
	public void cuantosHayDeConArregloDeEdificiosConstruidosOSinConstruirDevuelveEsaCantidad(){
		
		Terran nacion = crearRazaTerranValida();
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		IteradorUnidades iter = new IteradorUnidades(unidades);
		
		Marine marine = new Marine(nacion);
		for(int i=0;i<marine.tiempoConstruccion();i++)	marine.pasarTiempo();
		
		assertEquals(iter.cuantosHayDe(Marine.class), 0);
		
	}


}
