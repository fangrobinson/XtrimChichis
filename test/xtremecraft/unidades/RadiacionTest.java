package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;

public class RadiacionTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLaUnidadAfectada(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat(jugador);
		int tiempoMuerteUnidad = (int)(goliatAtacado.getVida()/Radiacion.danioIrradiado);
		
		goliatAtacado.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		for(int tiempo=0;tiempo<tiempoMuerteUnidad;tiempo++) radiacion.emitirRadiacion(goliatAtacado);
		
		
		assertEquals(goliatAtacado.getVida(),0);
		
	}
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLasUnidadesADistanciaMenorOIgualAUnoDeUnidadRadioactiva(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Terreno otraTierra = mapa.getCeldaEnFilaColumna(6,5).getCapaInferior();
		Goliat goliatAtacado = new Goliat(jugador);
		Goliat goliatIrradiado = new Goliat(jugador);
		int vidaInicialGoliatIrradiado = goliatIrradiado.getVida();
		
		goliatAtacado.actualizarUbicacion(tierra);
		goliatIrradiado.actualizarUbicacion(otraTierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		for(int tiempo=0;tiempo<goliatIrradiado.tiempoConstruccion();tiempo++) goliatIrradiado.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		radiacion.emitirRadiacion(goliatAtacado);
		
		assertTrue(vidaInicialGoliatIrradiado > goliatIrradiado.getVida());
		
	}
	
}
