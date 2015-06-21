package xtremecraft.raza;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Unidad;
import xtremecraft.raza.IteradorUnidades;

public class IteradorUnidadesTest {
	

	public void cuantosHayDeDevuelveCeroConArregloVacio(){
		
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		IteradorUnidades iter = new IteradorUnidades(unidades);
		
		assertEquals(iter.cuantosHayDe(Marine.class), 0);
		
	}
	
	
	@Test
	public void cuantosHayDeConArregloDeEdificiosConstruidosOSinConstruirDevuelveEsaCantidad(){
		
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		IteradorUnidades iter = new IteradorUnidades(unidades);
		
		Marine marine = new Marine();
		for(int i=0;i<marine.tiempoConstruccion();i++)	marine.pasarTiempo();
		
		assertEquals(iter.cuantosHayDe(Marine.class), 0);
		
	}


}
