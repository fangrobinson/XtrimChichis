package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Marine;

public class RecolectorDeGasVespenoTest {

	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearBarracaConRazaSinRecursosLanzaExcepcion(){
		
		Tierra tierraNacion = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierraNacion);
		Terreno tierra = new Tierra(1,1);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		
		RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(jugador, tierra);
		RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(jugador, tierra);
		RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(jugador, tierra);

	}
	
	public RecolectorDeGasVespeno construirNuevoRecolectorDeGasVespeno(int fila, int columna){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(jugador, tierra);
		for(int i=0; i < refineria.tiempoConstruccion; i++){
			refineria.pasarTiempo();
		}
		return refineria;
	}
	
	@Test
	public void seInicializaConEstadoVivo(){
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(jugador, tierra);
		
		assertTrue(refineria.estaVivo());
		
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElRecolectorDeGasVespeno(){
		
		int fila = 1;
		int columna = 2;
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(jugador, tierra);
		
		assertTrue(refineria.estaEnConstruccion());
		
	}
	
	@Test
	public void recolectorEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelRecolectorDeGasVespeno(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertFalse(refineria.estaEnConstruccion());
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertTrue(refineria.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasDeLaRaza(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(12,12);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(jugador, tierra);
		for(int i=0; i < refineria.tiempoConstruccion; i++){
			refineria.pasarTiempo();
		}
		int valorEsperado = jugador.nacion().getGasVespeno() + refineria.aumentoDeReservaEnTurno;
		
		refineria.pasarTiempo();
		
		assertEquals(jugador.nacion().getGasVespeno(), valorEsperado);
			
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertEquals(refineria.getUbicacionActual().columna(),2);
		assertEquals(refineria.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertEquals(refineria.getVida(), refineria.vidaMax());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		int valorDanio = 30;
		int vidaEsperada = refineria.vidaMax() - valorDanio;
		
		refineria.recibirDanio(valorDanio);
		vidaEsperada = refineria.vidaMax() - (valorDanio * 2) ;
		refineria.recibirDanio(valorDanio);
		
		assertEquals(refineria.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siUnRecolectorEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(3,2);
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		Marine miniSamus = new Marine(jugador);
		int cantidadDeAtaquesARecolector = 17;
		
		miniSamus.setUbicacionInicial(tierra);
		for(int i=0;i<cantidadDeAtaquesARecolector;i++){
			miniSamus.atacar(refineria);
			miniSamus.pasarTiempo();
		}
		
		assertFalse(refineria.estaVivo());		
		
	}

}
