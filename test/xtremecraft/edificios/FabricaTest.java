package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;

public class FabricaTest {

	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearFabricaConRazaSinRecursosLanzaExcepcion(){

		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terreno otraTierra = new Tierra(1,1);
		Tierra tercerTierra = new Tierra (2,2);
		
		jugador.nacion().juntarMinerales(150);
		Barraca barraca = new Barraca(jugador, otraTierra);

		
		new Fabrica(jugador, barraca, tercerTierra);
		
	}
	
	@Test
	public void seInicializaConEstadoVivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		assertTrue(fabrica.estaVivo());
		
	}

	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearLaFabrica(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		assertTrue(fabrica.estaEnConstruccion());
		
	}
	
	@Test
	public void fabricaEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDeLaFabrica(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		for (int tiempo=0; tiempo<fabrica.tiempoConstruccion(); tiempo++) fabrica.pasarTiempo();
		
		assertFalse(fabrica.estaEnConstruccion());
		
	}
	
	@Test(expected = EdificioEnConstruccionException.class)
	public void siFabricaNoEstaConstruidaYSeIntentaEntrenarUnGoliatSeLanzaExcepcion(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		fabrica.entrenarGoliat();
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		assertEquals(fabrica.getUbicacionActual().fila(),3);
		assertEquals(fabrica.getUbicacionActual().columna(),3);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		assertFalse(fabrica.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		assertFalse(fabrica.estaElevado());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		int vida = fabrica.vidaMax();
		
		
		for (int tiempo=0; tiempo<fabrica.tiempoConstruccion(); tiempo++) fabrica.pasarTiempo();
		
		assertEquals(fabrica.getVida(), vida);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
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
		 
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		Goliat unGoliat = fabrica.entrenarGoliat();
		
		assertEquals(unGoliat.getVida(),125);
		
	}
	
	@Test
	public void siUnaFabricaEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		Marine miniSamus = new Marine(jugador);
		int cantidadDeAtaquesAFabrica = 17;
		
		miniSamus.setUbicacionInicial(tierra3);
		for (int tiempo=0;tiempo<fabrica.tiempoConstruccion();tiempo++) fabrica.pasarTiempo();
		for(int i=0;i<cantidadDeAtaquesAFabrica;i++){
			miniSamus.atacar(fabrica);
			miniSamus.pasarTiempo();
		}
		
		assertFalse(fabrica.estaVivo());		
		
	}

}
