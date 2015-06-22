package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;

public class RadiacionTest {
	
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLaUnidadAfectada(){

		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat(nacion);
		Radiacion radiacion = new Radiacion(mapa);
		int tiempoMuerteUnidad = (int)(goliatAtacado.getVida()/Radiacion.danioIrradiado);
		
		goliatAtacado.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		goliatAtacado.recibirAtaqueRadiacion(radiacion);
		for(int tiempo=0;tiempo<tiempoMuerteUnidad;tiempo++) radiacion.emitirRadiacion(goliatAtacado);
		
		assertEquals(goliatAtacado.getVida(),0);
		
	}
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLasUnidadesADistanciaMenorOIgualAUnoDeUnidadRadioactiva(){

		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Terreno otraTierra = mapa.getCeldaEnFilaColumna(6,5).getCapaInferior();
		Goliat goliatAtacado = new Goliat(nacion);
		Goliat goliatIrradiado = new Goliat(nacion);
		Radiacion radiacion = new Radiacion(mapa);
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
