package xtremecraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;



public class BarraDeVitalidadTest {

	@Test
	public void alRecibirUnAtaqueLaVidaPasaAOcho(){
		BarraDeVitalidad vida = new BarraDeVitalidad(10);
		vida.recibirAtaque(2);
		assertEquals(vida.devolverValor(),8);
		
	}
	
	@Test
	
	public void alRecibirUnAtaqueMayorAlDeLaVidaEstaPasaACero(){
		BarraDeVitalidad vida = new BarraDeVitalidad(10);
		vida.recibirAtaque(11);
		assertEquals(vida.devolverValor(), 0);
	}
	
	@Test
	public void alRecibirUnAtaqueCuandoLaVidaYaEsCeroEstaNoSeModifica(){
		BarraDeVitalidad vida = new BarraDeVitalidad(0);
		vida.recibirAtaque(40);
		assertEquals(vida.devolverValor(), 0);
	}
}
