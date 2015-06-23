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
import xtremecraft.raza.Terran;

public class NaveTransporteTest {
	
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	/*
	@Test (expected = RecursosInsuficientesException.class)
	public void unidadCreadaParaRazaSinRecursosLanzaExcepcion(){

		Tierra tierra = new Tierra(15,15);
		Terran nacion = new Terran(tierra);
		
		new NaveTransporte(nacion);
	}*/
	
	@Test
	public void naveTransporteInicializadoConVidaCompleta(){

		Terran nacion = crearRazaTerranValida();
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		assertEquals(taxiVolador.getVida(),150);
		
	}
	
	@Test
	public void naveTransporteInicializadoConEstadoVivo(){

		Terran nacion = crearRazaTerranValida();
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		assertTrue(taxiVolador.estaVivo());
		
	}
	
	@Test
	public void naveTransportePuedeUbicarseSobreRecursoNaturalDevuelveFalse(){

		Terran nacion = crearRazaTerranValida();
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		assertFalse(taxiVolador.puedeUbicarseSobreRecursoNatural());
	}
	
	@Test
	public void NaveTransporteGetVisionDevuelveRadioDeVisionDeLaNaveTransporte(){

		Terran nacion = crearRazaTerranValida();
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		assertEquals(taxiVolador.getRadioVision(),8);
	}
	
	@Test
	public void siUnaNaveTransporteAtacaAOtroPorAireLeSacaCeroDeVida(){

		Terran nacion = crearRazaTerranValida();
		Terreno aire1 = new Aire(1,3);
		Terreno aire2 = new Aire(1,4);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		NaveTransporte remisVolador = new NaveTransporte(nacion);
		
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

		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		NaveTransporte remisVolador = new NaveTransporte(nacion);
		
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
		
		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,2);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		taxiVolador.actualizarUbicacion(aire);

		assertEquals(taxiVolador.getUbicacionActual(),aire.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		taxiVolador.actualizarUbicacion(aire);
		taxiVolador.actualizarUbicacion(tierra);

		assertFalse(aire.estaOcupado());
		
	}
	
	@Test
	public void siUnaNaveEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){

		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(1,4);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		Goliat unGoliat = new Goliat(nacion);
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
		
		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		Goliat unGoliat = new Goliat(nacion);
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		
		assertTrue( taxiVolador.transportarNuevaUnidad(unGoliat));
		
	}
	
	@Test
	public void transportarUnidadActualizaLaUbicacionDeLaUnidadQueTransporta(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,3);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		Goliat unGoliat = new Goliat(nacion);
		
		taxiVolador.actualizarUbicacion(aire);
		unGoliat.actualizarUbicacion(tierra);
		taxiVolador.transportarNuevaUnidad(unGoliat);
		
		assertEquals(unGoliat.getUbicacionActual(),taxiVolador.getUbicacionActual());
		
	}
	
	@Test
	public void bajarNaveDevuelveFalseSiTodasSiTodosLosLugaresAlrededorDeLaNaveEstanOcupados(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		int fila = 4;
		int columna = 4;
		Terreno aire = mapa.getCeldaEnFilaColumna(fila,columna).getCapaSuperior();
		Terreno tierra0 = mapa.getCeldaEnFilaColumna(fila,columna).getCapaInferior();
		Terreno tierra1 = mapa.getCeldaEnFilaColumna(fila-1,columna).getCapaInferior();
		Terreno tierra2 = mapa.getCeldaEnFilaColumna(fila+1,columna).getCapaInferior();
		Terreno tierra3 = mapa.getCeldaEnFilaColumna(fila,columna-1).getCapaInferior();
		Terreno tierra4 = mapa.getCeldaEnFilaColumna(fila,columna+1).getCapaInferior();
		Terreno tierra5 = mapa.getCeldaEnFilaColumna(fila+1,columna+1).getCapaInferior();
		Terreno tierra6 = mapa.getCeldaEnFilaColumna(fila-1,columna-1).getCapaInferior();
		Terreno tierra7 = mapa.getCeldaEnFilaColumna(fila+1,columna-1).getCapaInferior();
		Terreno tierra8 = mapa.getCeldaEnFilaColumna(fila-1,columna+1).getCapaInferior();	
		Goliat goliat0 = new Goliat(nacion);
		Goliat goliat1 = new Goliat(nacion);
		Goliat goliat2 = new Goliat(nacion);
		Goliat goliat3 = new Goliat(nacion);
		Goliat goliat4 = new Goliat(nacion);
		Goliat goliat5 = new Goliat(nacion);
		Goliat goliat6 = new Goliat(nacion);
		Goliat goliat7 = new Goliat(nacion);
		Goliat goliat8 = new Goliat(nacion);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		goliat0.actualizarUbicacion(tierra0);
		goliat1.actualizarUbicacion(tierra1);
		goliat2.actualizarUbicacion(tierra2);
		goliat3.actualizarUbicacion(tierra3);
		goliat4.actualizarUbicacion(tierra4);
		goliat5.actualizarUbicacion(tierra5);
		goliat6.actualizarUbicacion(tierra6);
		goliat7.actualizarUbicacion(tierra7);
		goliat8.actualizarUbicacion(tierra8);
		taxiVolador.actualizarUbicacion(aire);
		goliat0.subirANaveDeTransporte(taxiVolador);
		
		assertFalse(taxiVolador.bajarUnidad(mapa, goliat0));
		
	}
	
	@Test
	public void actualizarUbicacionActualizaLaUbicacionDeLasUnidadesQueTransporta(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno aire1 = new Aire(1,3);
		Terreno aire2 = new Tierra(5,8);
		Terreno tierra = new Tierra(4,4);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		Goliat unGoliat = new Goliat(nacion);
		
		taxiVolador.actualizarUbicacion(aire1);
		unGoliat.actualizarUbicacion(tierra);
		taxiVolador.transportarNuevaUnidad(unGoliat);
		taxiVolador.actualizarUbicacion(aire2);
		
		assertEquals(unGoliat.getUbicacionActual(),taxiVolador.getUbicacionActual());
		
	}
	
	@Test(expected = UbicacionNoValidaException.class)
	public void siLaUnidadTrataDeUbicarseEnUnTerrenoQueNoPuedeVerSeLanzaExcepcion(){

		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,2);
		Terreno aireDestino = new Aire(40,40);
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		taxiVolador.actualizarUbicacion(aire);
		taxiVolador.actualizarUbicacion(aireDestino);
		
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
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
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		NaveTransporte taxiVolador = new NaveTransporte(nacion);
		
		taxiVolador.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<taxiVolador.tiempoConstruccion();tiempo++) taxiVolador.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(taxiVolador, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		taxiVolador.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(taxiVolador.esRadioactivo());
		
	}

	//TODO: Faltan tests
	/*
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadDisminuyeAlPasarElTiempo(){
	
	}
	
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadNoPuedeMoverse(){
	
	}
	
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadNoPuedeAtacar(){
	
	}
	*/

}
