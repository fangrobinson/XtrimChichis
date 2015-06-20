package xtremecraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import xtremecraft.edificios.EdificioEnConstruccionException;



public class BarraDeVitalidadTest {

	@SuppressWarnings("unused")
	@Test(expected = VidaNegativaException.class)
	public void alIntentarCrearseUnaBarraDeVitalidadConVidaNegativaSeLanzaVidaNegativaException(){
		BarraDeVitalidad vida = new BarraDeVitalidad(-40);
	}
	
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
	
	@Test
	public void alRegenerarBarraLaVidaAumentaEnEseValor(){
		int vidaInicial = 100;
		int danio = 40; 
		int curacion = 30;
		int valorEsperado = vidaInicial - danio + curacion;
		BarraDeVitalidad vida = new BarraDeVitalidad(vidaInicial);

		vida.recibirAtaque(danio);
		vida.regenerar(curacion);
		
		assertEquals(vida.devolverValor(), valorEsperado);
	}
	
	@Test
	public void regerarNoPuedeCurarMasAllaDeLaVidaMax(){
		int vidaInicial = 100;
		int danio = 40; 
		int curacion = 60;
		int valorEsperado = vidaInicial;
		BarraDeVitalidad vida = new BarraDeVitalidad(vidaInicial);

		vida.recibirAtaque(danio);
		vida.regenerar(curacion);
		
		assertEquals(vida.devolverValor(), valorEsperado);
	}
	
}
