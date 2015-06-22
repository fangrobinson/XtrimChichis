package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;

public class FabricaTest {
	

	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearFabricaConRazaSinRecursosLanzaExcepcion(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		Terreno otraTierra = new Tierra(1,1);
		Tierra tercerTierra = new Tierra (2,2);
		
		razaTerran.juntarMinerales(150);
		Barraca barraca = new Barraca(razaTerran, otraTierra);

		
		new Fabrica(razaTerran, barraca, tercerTierra);
	}
	
	@Test
	public void seInicializaConEstadoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		assertTrue(fabrica.estaVivo());
		
	}

	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearLaFabrica(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		assertTrue(fabrica.estaEnConstruccion());
		
	}
	
	@Test
	public void fabricaEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDeLaFabrica(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		for (int tiempo=0; tiempo<fabrica.tiempoConstruccion(); tiempo++) fabrica.pasarTiempo();
		
		assertFalse(fabrica.estaEnConstruccion());
		
	}
	
	@Test(expected = EdificioEnConstruccionException.class)
	public void siFabricaNoEstaConstruidaYSeIntentaEntrenarUnGoliatSeLanzaExcepcion(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		fabrica.entrenarGoliat(nacion);
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		assertEquals(fabrica.getUbicacionActual().fila(),3);
		assertEquals(fabrica.getUbicacionActual().columna(),3);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		assertFalse(fabrica.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		assertFalse(fabrica.estaElevado());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		int vida = fabrica.vidaMax();
		
		
		for (int tiempo=0; tiempo<fabrica.tiempoConstruccion(); tiempo++) fabrica.pasarTiempo();
		
		assertEquals(fabrica.getVida(), vida);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		int valorDanio = 30;
		int vidaEsperada = fabrica.vidaMax() - valorDanio;
		
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		fabrica.recibirDanio(valorDanio);
		vidaEsperada = vidaEsperada - valorDanio;
		fabrica.recibirDanio(valorDanio);
		
		assertEquals(fabrica.getVida(), vidaEsperada);
		
	}

	@Test
	public void entrenarGoliatDevuelveNuevaUnidadGoliat(){
		 
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		Goliat unGoliat = fabrica.entrenarGoliat(nacion);
		
		assertEquals(unGoliat.getVida(),125);
		
	}
	
	@Test
	public void siUnaFabricaEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		Marine miniSamus = new Marine(nacion);
		int cantidadDeAtaquesAFabrica = 17;
		
		miniSamus.actualizarUbicacion(tierra3);
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		for(int i=0;i<cantidadDeAtaquesAFabrica;i++) miniSamus.atacar(fabrica);
		
		assertFalse(fabrica.estaVivo());		
		
	}

}
