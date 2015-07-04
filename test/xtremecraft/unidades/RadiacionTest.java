package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.raza.Terran;

public class RadiacionTest {
	
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
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLaUnidadAfectada(){

		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Goliat goliatAtacado = new Goliat(jugador);
		int tiempoMuerteUnidad = (int)(goliatAtacado.getVida()/2);
		
		goliatAtacado.setUbicacionInicial(tierra);
		for(int tiempo=0;tiempo<goliatAtacado.tiempoConstruccion();tiempo++) goliatAtacado.pasarTiempo();
		Radiacion radiacion = new Radiacion(mapa, 2, 10);
		goliatAtacado.recibirDanio(radiacion);
		for(int tiempo=0;tiempo<tiempoMuerteUnidad;tiempo++) radiacion.emitirRadiacion(goliatAtacado);
		
		
		assertEquals(goliatAtacado.getVida(),0);
		
	}
	
	@Test
	public void emitirRadiacionDisminuyeLaVidaDeLasUnidadesADistanciaMenorOIgualAUnoDeUnidadRadioactiva() throws SeleccionadoNoEsPropiedadDelJugadorException{

		Jugador jugador1 = crearJugadorConRecursosSuficientesParaConstruir();
		Jugador jugador2 = crearJugadorConRecursosSuficientesParaConstruir();
		Mapa mapa = new Mapa(2);
		Terreno tierra1 = mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		Terreno tierra2 = mapa.getCeldaEnFilaColumna(3, 3).getCapaInferior();
		Terreno tierra3 = mapa.getCeldaEnFilaColumna(4, 4).getCapaInferior();
		Terreno tierra4 = mapa.getCeldaEnFilaColumna(5, 5).getCapaInferior();
		Terreno tierra5 = mapa.getCeldaEnFilaColumna(9, 9).getCapaInferior();
		Terreno tierra6 = mapa.getCeldaEnFilaColumna(4, 6).getCapaInferior();
		Terreno tierra7 = mapa.getCeldaEnFilaColumna(10, 11).getCapaInferior();
		Terreno tierra8 = mapa.getCeldaEnFilaColumna(10, 10).getCapaInferior();
		Terreno tierra9 = mapa.getCeldaEnFilaColumna(11, 10).getCapaSuperior();
		
		Barraca barracaJugador1 = jugador1.crearBarraca(tierra1, mapa);
		Barraca barracaJugador2 = jugador2.crearBarraca(tierra4, mapa);
		for (int tiempo=0;tiempo<barracaJugador1.tiempoConstruccion();tiempo++) barracaJugador1.pasarTiempo();
		for (int tiempo=0;tiempo<barracaJugador2.tiempoConstruccion();tiempo++) barracaJugador2.pasarTiempo();
		
		Fabrica fabricaJugador1 = jugador1.crearFabrica(tierra2, mapa);
		Fabrica fabricaJugador2 = jugador2.crearFabrica(tierra5, mapa);
		for (int tiempo=0;tiempo<fabricaJugador1.tiempoConstruccion();tiempo++) fabricaJugador1.pasarTiempo();
		for (int tiempo=0;tiempo<fabricaJugador2.tiempoConstruccion();tiempo++) fabricaJugador2.pasarTiempo();
		
		PuertoEstelar puertoEstelarJugador1 = jugador1.crearPuertoEstelar(tierra3, mapa);	
		PuertoEstelar puertoEstelarJugador2 = jugador2.crearPuertoEstelar(tierra6, mapa);	
		for (int tiempo=0;tiempo<puertoEstelarJugador1.tiempoConstruccion();tiempo++) puertoEstelarJugador1.pasarTiempo();
		for (int tiempo=0;tiempo<puertoEstelarJugador2.tiempoConstruccion();tiempo++) puertoEstelarJugador2.pasarTiempo();
		
		NaveCiencia naveCienciaAtacante = jugador1.crearNaveCiencia(puertoEstelarJugador1, mapa);
		Goliat goliatAtacado = jugador2.crearGoliat(fabricaJugador2, mapa);
		Goliat goliatIrradiado = jugador2.crearGoliat(fabricaJugador2, mapa);
		
		naveCienciaAtacante.actualizarUbicacion(tierra9);
		goliatAtacado.actualizarUbicacion(tierra7);
		goliatIrradiado.actualizarUbicacion(tierra8);		
		for (int tiempo=0;tiempo<450;tiempo++) goliatAtacado.pasarTiempo();
		for (int tiempo=0;tiempo<450;tiempo++) goliatIrradiado.pasarTiempo();
		int vidaInicialGoliatIrradiado = goliatIrradiado.getVida();
		
		Radiacion radiacion = new Radiacion(mapa, 2, 10);
		goliatAtacado.recibirDanio(radiacion);
		radiacion.emitirRadiacion(goliatAtacado);
		
		assertTrue(vidaInicialGoliatIrradiado > goliatIrradiado.getVida());
		
	}
	
}
