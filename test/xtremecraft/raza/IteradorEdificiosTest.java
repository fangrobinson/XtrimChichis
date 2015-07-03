package xtremecraft.raza;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Edificio;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;

public class IteradorEdificiosTest {
		
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
	
	public void cuantosHayDeConstruidosDevuelveCeroConArregloVacio(){
		
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(array);
		
		assertEquals(iter.cuantosHayCreadosDe(Barraca.class), 0);
		
	}
	
	@Test
	public void cuantosHayConstruidosConArregloDeEdificiosSinConstruirDevuelveCero(){
		
		ArrayList<Edificio> array = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(array);
		
		assertEquals(iter.cuantosHayCreadosDe(Barraca.class), 0);
		
	}
	
	public void cuantosHayDeDevuelveCeroConArregloVacio(){
		
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		
		assertEquals(iter.cuantosHayDe(Barraca.class), 0);
		
	}
	
	@Test
	public void cuantosHayDeConArregloDeEdificiosConstruidosOSinConstruirDevuelveEsaCantidad(){
		
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		
		Barraca barraca = new Barraca(jugador, new Tierra(1,2));
		for(int i=0;i<barraca.tiempoConstruccion();i++)	barraca.pasarTiempo();
		edificios.add(barraca);
		
		assertEquals(iter.cuantosHayCreadosDe(Barraca.class), 1);
		
	}

	@Test
	public void elementoPerteneceConBarracaConArregloVacioDaFalse(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		IteradorEdificios iter = new IteradorEdificios(edificios);
		
		assertFalse(iter.elementoPertenece(new Barraca(jugador, new Tierra(1,1))));
		
	}
	
	@Test
	public void tieneCreadosBarracaConArregloDeUnaBarracaSinConstruirDaFalse(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(jugador, tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertFalse(iter.tieneCreados(Barraca.class));
		
	}
	
	@Test
	public void elementoPerteneceBarracaConArregloDeUnaBarracaConstruidaDevuelveTrue(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(jugador, tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		for(int i=0;i<unaBarraca.tiempoConstruccion();i++)	unaBarraca.pasarTiempo();
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertTrue(iter.elementoPertenece(unaBarraca));
		
	}
	
	@Test
	public void elementoPerteneceBarracaConArregloDeUnaBarracaNoConstruidaDevuelveTrue(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Tierra tierra = new Tierra(1,1);
		Barraca unaBarraca = new Barraca(jugador, tierra);
		ArrayList<Edificio> edificios = new ArrayList<Edificio>();
		
		edificios.add(unaBarraca);
		
		IteradorEdificios iter = new IteradorEdificios(edificios);
		assertTrue(iter.elementoPertenece(unaBarraca));
		
	}
	
	
}
