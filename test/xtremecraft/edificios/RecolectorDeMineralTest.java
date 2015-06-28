package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.unidades.Marine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RecolectorDeMineralTest {
	
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
		Terreno tierra = new Tierra(1, 1);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		
		RecolectorDeMineral.nuevoRecolectorDeMineral(jugador,tierra);
		RecolectorDeMineral.nuevoRecolectorDeMineral(jugador,tierra);
		RecolectorDeMineral.nuevoRecolectorDeMineral(jugador,tierra);

	}
	
	public RecolectorDeMineral construirNuevoRecolectorDeMineral(int fila, int columna){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(fila,columna);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		RecolectorDeMineral recolector = RecolectorDeMineral.nuevoRecolectorDeMineral(jugador, tierra);
		for(int i=0; i<recolector.tiempoConstruccion; i++){
			recolector.pasarTiempo();
		}
		return recolector;
		
	}
	
	@Test
	public void recolectorSeIniciaConEstadoVivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		
		RecolectorDeMineral recolector = RecolectorDeMineral.nuevoRecolectorDeMineral(jugador, tierra);
		
		assertTrue(recolector.estaVivo());
		
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElRecolectorDeMineral(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		
		RecolectorDeMineral recolector = RecolectorDeMineral.nuevoRecolectorDeMineral(jugador, tierra);
		
		assertTrue(recolector.estaEnConstruccion());
		
	}
	
	@Test
	public void recolectorEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelRecolectorDeMineral(){
		
		RecolectorDeMineral recolector = construirNuevoRecolectorDeMineral(1,2);
		
		assertFalse(recolector.estaEnConstruccion());
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		assertTrue(centroMineralTerran.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(12, 12);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		tierra.agregarRecursoNatural(nuevoNodoMineral);
		RecolectorDeMineral recolector = RecolectorDeMineral.nuevoRecolectorDeMineral(jugador, tierra);
		for(int i=0; i<recolector.tiempoConstruccion; i++){
			recolector.pasarTiempo();
		}
		int valorEsperado = jugador.nacion().getMinerales() + recolector.aumentoDeReservaEnTurno;
		
		recolector.pasarTiempo();
		
		assertEquals(jugador.nacion().getMinerales(), valorEsperado);
				
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		assertEquals(centroMineralTerran.getUbicacionActual().columna(),2);
		assertEquals(centroMineralTerran.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		
		assertEquals(centroMineralTerran.getVida(), centroMineralTerran.vidaMax());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		int valorDanio = 30;
		int vidaEsperada = centroMineralTerran.vidaMax() - valorDanio;
		
		centroMineralTerran.recibirDanio(valorDanio);
		centroMineralTerran.recibirDanio(valorDanio);
		vidaEsperada -= valorDanio;
		
		assertEquals(centroMineralTerran.getVida(), vidaEsperada);
		
	}

	@Test
	public void siUnRecolectorEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra = new Tierra(3,2);
		RecolectorDeMineral centroMineralTerran = construirNuevoRecolectorDeMineral(1,2);
		Marine miniSamus = new Marine(jugador);
		int cantidadDeAtaquesARecolector = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		for(int i=0;i<cantidadDeAtaquesARecolector;i++) miniSamus.atacar(centroMineralTerran);
		
		assertFalse(centroMineralTerran.estaVivo());		
		
	}

}
