package xtremecraft.unidades;

import static org.junit.Assert.*;

import org.junit.Test;



public class BarraDeVitalidadTest {

	@SuppressWarnings("unused")
	@Test(expected = VidaNulaException.class)
	public void alIntentarCrearseUnaBarraDeVitalidadConVidaNulaSeLanzaVidaNulaException(){
		BarraDeVitalidad vida = new BarraDeVitalidad(0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = VidaNegativaException.class)
	public void alIntentarCrearseUnaBarraDeVitalidadConVidaNegativaSeLanzaVidaNegativaException(){
		BarraDeVitalidad vida = new BarraDeVitalidad(-40);
	}
	
	@Test
	public void alRecibirUnAtaqueLaVidaPasaAOcho(){
		BarraDeVitalidad vida = new BarraDeVitalidad(10);
		vida.recibirAtaque(2);
		assertEquals(vida.getValor(),8);
		
	}
	
	@Test	
	public void alRecibirUnAtaqueMayorAlDeLaVidaEstaPasaACero(){
		BarraDeVitalidad vida = new BarraDeVitalidad(10);
		vida.recibirAtaque(11);
		assertEquals(vida.getValor(), 0);
	}
	
	@Test
	public void alRecibirUnAtaqueCuandoLaVidaYaEsCeroEstaNoSeModifica(){
		BarraDeVitalidad vida = new BarraDeVitalidad(40);
		vida.recibirAtaque(40);
		vida.recibirAtaque(40);
		assertEquals(vida.getValor(), 0);
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
		
		assertEquals(vida.getValor(), valorEsperado);
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
		
		assertEquals(vida.getValor(), valorEsperado);
	}
	
}
