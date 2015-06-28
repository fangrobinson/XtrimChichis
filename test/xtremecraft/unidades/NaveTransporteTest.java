package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;

public class NaveTransporteTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test (expected = RecursosInsuficientesException.class )
	public void unidadCreadaParaRazaSinRecursosLanzaExcepcion(){

		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		
		new NaveTransporte(jugador);
		new NaveTransporte(jugador);
		new NaveTransporte(jugador);
		new NaveTransporte(jugador);
		new NaveTransporte(jugador);
		
	}
	
	@Test
	public void naveTransporteInicializadoConVidaCompleta(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		assertEquals(taxiVolador.getVida(),150);
		
	}
	
	@Test
	public void naveTransporteInicializadoConEstadoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		assertTrue(taxiVolador.estaVivo());
		
	}
	
	@Test
	public void naveTransportePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		assertFalse(taxiVolador.puedeUbicarseSobreRecursoNatural());
	}
	
	@Test
	public void NaveTransporteGetVisionDevuelveRadioDeVisionDeLaNaveTransporte(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		assertEquals(taxiVolador.getRadioVision(),8);
	}
	
	@Test
	public void siUnaNaveTransporteAtacaAOtroPorAireLeSacaCeroDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire1 = new Aire(1,3);
		Terreno aire2 = new Aire(1,4);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		NaveTransporte remisVolador = new NaveTransporte(jugador);
		
		taxiVolador.actualizarUbicacion(aire1);
		remisVolador.actualizarUbicacion(aire2);
		
		for (int i = 0; i < taxiVolador.tiempoConstruccion(); i++){
			taxiVolador.pasarTiempo();
			remisVolador.pasarTiempo();
		}
		
		remisVolador.atacar(taxiVolador);
		
		assertEquals(taxiVolador.vitalidad.getValor(),150);
	}

	@Test
	public void siUnaNaveTransporteAtacaAOtroPorTierraLeSacaCeroDeVida(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		NaveTransporte remisVolador = new NaveTransporte(jugador);
		
		taxiVolador.actualizarUbicacion(aire);
		remisVolador.actualizarUbicacion(tierra);
		
		for (int i = 0; i < taxiVolador.tiempoConstruccion(); i++){
			taxiVolador.pasarTiempo();
			remisVolador.pasarTiempo();
		}
		
		remisVolador.atacar(taxiVolador);
		
		assertEquals(taxiVolador.vitalidad.getValor(),150);
		
	}
	
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,2);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		taxiVolador.actualizarUbicacion(aire);

		assertEquals(taxiVolador.getUbicacionActual(),aire.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		taxiVolador.actualizarUbicacion(aire);
		taxiVolador.actualizarUbicacion(tierra);

		assertFalse(aire.estaOcupado());
		
	}
	
	@Test
	public void siUnaNaveEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(1,4);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		Goliat unGoliat = new Goliat(jugador);
		int cantidadDeAtaques = 15;
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		
		for (int i = 0; i < taxiVolador.tiempoConstruccion(); i++){
			taxiVolador.pasarTiempo();
			unGoliat.pasarTiempo();
		}
		
		for(int i=0;i<cantidadDeAtaques;i++) unGoliat.atacar(taxiVolador);
		
		assertFalse(taxiVolador.estaVivo());
		
	}
	
	@Test
	public void transportarNuevaUnidadDevuelveTrueSiNoSuperaLaCapacidadMaxima(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		Goliat unGoliat = new Goliat(jugador);
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		
		assertTrue( taxiVolador.transportarNuevaUnidad(unGoliat));
		
	}
	
	@Test
	public void transportarUnidadActualizaLaUbicacionDeLaUnidadQueTransporta(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		Goliat unGoliat = new Goliat(jugador);
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		taxiVolador.transportarNuevaUnidad(unGoliat);
		
		assertEquals(unGoliat.getUbicacionActual(),taxiVolador.getUbicacionActual());
		
	}
	
	@Test(expected = UnidadNoSePudoBajarDeLaNaveException.class)
	public void bajarNaveDevuelveLanzaExcepcionSiTodosLosLugaresAlrededorDeLaNaveEstanOcupados(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		int fila = 2;
		int columna = 5;
		Terreno aire = mapa.getCeldaEnFilaColumna(fila,columna).getCapaSuperior();
		Terreno tierra0 = mapa.getCeldaEnFilaColumna(fila,columna).getCapaInferior();
		Terreno tierra1 = mapa.getCeldaEnFilaColumna(fila+1,columna).getCapaInferior();
		Terreno tierra2 = mapa.getCeldaEnFilaColumna(fila+1,columna+1).getCapaInferior();
		Terreno tierra3 = mapa.getCeldaEnFilaColumna(fila-1,columna-1).getCapaInferior();
		Terreno tierra4 = mapa.getCeldaEnFilaColumna(fila+1,columna-1).getCapaInferior();
		Terreno tierra5 = mapa.getCeldaEnFilaColumna(fila-1,columna+1).getCapaInferior();	
		Goliat goliat0 = new Goliat(jugador);
		Goliat goliat1 = new Goliat(jugador);
		Goliat goliat2 = new Goliat(jugador);
		Goliat goliat3 = new Goliat(jugador);
		Goliat goliat4 = new Goliat(jugador);
		Goliat goliat5 = new Goliat(jugador);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		goliat0.actualizarUbicacion(tierra0);
		goliat1.actualizarUbicacion(tierra1);
		goliat2.actualizarUbicacion(tierra2);
		goliat3.actualizarUbicacion(tierra3);
		goliat4.actualizarUbicacion(tierra4);
		goliat5.actualizarUbicacion(tierra5);
		taxiVolador.actualizarUbicacion(aire);
		goliat0.subirANaveDeTransporte(taxiVolador);
		
		taxiVolador.bajarUnidad(mapa, goliat0);
				
	}
	
	@Test
	public void actualizarUbicacionActualizaLaUbicacionDeLasUnidadesQueTransporta(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire1 = new Aire(1,3);
		Terreno aire2 = new Tierra(5,8);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		Goliat unGoliat = new Goliat(jugador);
		
		taxiVolador.actualizarUbicacion(aire1);
		unGoliat.actualizarUbicacion(tierra);
		taxiVolador.transportarNuevaUnidad(unGoliat);
		taxiVolador.actualizarUbicacion(aire2);
		
		assertEquals(unGoliat.getUbicacionActual(),taxiVolador.getUbicacionActual());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno aire = new Aire(1,2);
		Terreno aireDestino = new Aire(40,40);
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		taxiVolador.actualizarUbicacion(aire);
		taxiVolador.actualizarUbicacion(aireDestino);
		
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		int vidaInicial = taxiVolador.getVida();
		
		taxiVolador.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<taxiVolador.tiempoConstruccion();tiempo++) taxiVolador.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(taxiVolador, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		taxiVolador.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), taxiVolador.getVida());
		
	}
	
	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveTransporte taxiVolador = new NaveTransporte(jugador);
		
		taxiVolador.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<taxiVolador.tiempoConstruccion();tiempo++) taxiVolador.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(taxiVolador, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		taxiVolador.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(taxiVolador.esRadioactivo());
		
	}

}
