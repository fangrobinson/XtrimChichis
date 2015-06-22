package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;

public class RadiacionTest {
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLaUnidadAfectada(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		int tiempoMuerteUnidad = (int)(goliatAtacado.getVida()/Radiacion.danioIrradiado);
		
		goliatAtacado.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		for(int tiempo=0;tiempo<tiempoMuerteUnidad;tiempo++) radiacion.emitirRadiacion(goliatAtacado);
		
		assertEquals(goliatAtacado.getVida(),0);
		
	}
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLasUnidadesADistanciaMenorOIgualAUnoDeUnidadRadioactiva(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Terreno otraTierra = mapa.getCeldaEnFilaColumna(6,5).getCapaInferior();
		Goliat goliatAtacado = new Goliat();
		Goliat goliatIrradiado = new Goliat();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(goliatAtacado, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		int vidaInicialGoliatIrradiado = goliatIrradiado.getVida();
		
		goliatAtacado.actualizarUbicacion(tierra);
		goliatIrradiado.actualizarUbicacion(otraTierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		for(int tiempo=0;tiempo<goliatIrradiado.tiempoConstruccion();tiempo++) goliatIrradiado.pasarTiempo();
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		radiacion.emitirRadiacion(goliatAtacado);
		
		assertTrue(vidaInicialGoliatIrradiado > goliatIrradiado.getVida());
		
	}
	
}
