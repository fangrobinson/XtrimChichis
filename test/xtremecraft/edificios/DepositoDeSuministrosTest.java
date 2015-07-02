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
import xtremecraft.unidades.Marine;

public class DepositoDeSuministrosTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Terreno tierra =  mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearDepositoDeSuministrosConRazaSinRecursosLanzaExcepcion(){
		
		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Terreno tierra =  mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terreno unTerreno = new Tierra(2,2);
		Terreno otroTerreno = new Tierra(1,1);
		
		new DepositoDeSuministros(jugador, unTerreno);
		new DepositoDeSuministros(jugador, otroTerreno);
		
	}
	
	public DepositoDeSuministros construirNuevoDeposito(Jugador jugador, Terreno tierra){
		
		DepositoDeSuministros deposito = new DepositoDeSuministros(jugador,tierra);
		for(int i=0; i<deposito.tiempoConstruccion; i++){
			deposito.pasarTiempo();
		}
		return deposito;
		
	}
	
	@Test
	public void depositoSeInicializaConEstadoVivo(){
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(jugador, tierra);
		
		assertTrue(deposito.estaVivo());		
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(jugador, tierra);
		
		assertEquals(deposito.getUbicacionActual().fila(), fila);
		assertEquals(deposito.getUbicacionActual().columna(), columna);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(jugador, tierra);
		
		assertFalse(deposito.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(jugador, tierra);
		
		assertEquals(deposito.getVida(), deposito.vidaMax());
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(jugador, tierra);
		
		assertFalse(deposito.estaElevado());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(jugador, tierra);
		int valorDanio = 30;
		int vidaEsperada = deposito.vidaMax() - valorDanio;
		
		deposito.recibirDanio(valorDanio);
		vidaEsperada -= valorDanio;
		deposito.recibirDanio(valorDanio);
		
		assertEquals(deposito.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siUnDepositoEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terreno tierra = new Tierra(3,2);
		Terreno otroTerreno = new Tierra(1,2);
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		DepositoDeSuministros deposito = construirNuevoDeposito(jugador, tierra);
		Marine miniSamus = new Marine(jugador);
		int cantidadDeAtaquesADeposito = 17;
		
		miniSamus.setUbicacionInicial(tierra);
		deposito.setUbicacionInicial(otroTerreno);
		for(int i=0;i<cantidadDeAtaquesADeposito;i++){
			miniSamus.atacar(deposito);
			miniSamus.pasarTiempo();
		}
		
		assertFalse(deposito.estaVivo());		
		
	}

}
