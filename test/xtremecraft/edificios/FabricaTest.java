package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;

public class FabricaTest {
	
	@Test
	public void seInicializaConEstadoVivo(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		assertTrue(fabrica.estaVivo());
		
	}

	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearLaFabrica(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		assertTrue(fabrica.estaEnConstruccion());
		
	}
	
	@Test
	public void fabricaEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDeLaFabrica(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		
		assertFalse(fabrica.estaEnConstruccion());
		
	}
	
	@Test(expected = EdificioEnConstruccionException.class)
	public void siFabricaNoEstaConstruidaYSeIntentaEntrenarUnGoliatSeLanzaExcepcion(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		fabrica.entrenarGoliat();
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		assertEquals(fabrica.getUbicacionActual().fila(),3);
		assertEquals(fabrica.getUbicacionActual().columna(),3);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		assertFalse(fabrica.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		assertFalse(fabrica.estaElevado());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		
		assertEquals(fabrica.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		int valorDanio = 30;
		
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),70);
		
		fabrica.recibirDanio(valorDanio);
		assertEquals(fabrica.getVida(),40);
		
	}

	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		 
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		Goliat unGoliat = fabrica.entrenarGoliat();
		
		assertEquals(unGoliat.getVida(),125);
		
	}
	
	@Test
	public void siUnaFabricaEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		Marine miniSamus = new Marine();
		int cantidadDeAtaquesAFabrica = 17;
		
		miniSamus.actualizarUbicacion(tierra3);
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		for(int i=0;i<cantidadDeAtaquesAFabrica;i++) miniSamus.atacar(fabrica);
		
		assertFalse(fabrica.estaVivo());		
		
	}

}
