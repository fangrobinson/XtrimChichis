package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.raza.Terran;

public class CreadorDeUnidadesTest {
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		CreadorDeUnidades creadorDeUnidades = CreadorDeUnidades.nuevoEdificioCreadorDeUnidades(nuevoClanTerran,fila, columna);
		
		assertEquals(creadorDeUnidades.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		int fila = 1;
		int columna = 2;
		CreadorDeUnidades creadorDeUnidades = CreadorDeUnidades.nuevoEdificioCreadorDeUnidades(nuevoClanTerran,fila, columna);
		int valorDanio = 30;
		
		creadorDeUnidades.recibirDanio(valorDanio);
		assertEquals(creadorDeUnidades.getVida(),70);
		
		creadorDeUnidades.recibirDanio(valorDanio);
		assertEquals(creadorDeUnidades.getVida(),40);
		
	}

}
