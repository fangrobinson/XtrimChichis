package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Unidad;

import org.junit.Test;

public class TierraTest {

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
	public void nuevaTierraCreaTierraConEstadoNoOcupado(){
		
		Terreno terreno = new Tierra(1,1);
		
		assertFalse(terreno.estaOcupado());
		
	}
	
	@Test
	public void agregarRecursoNaturalDeberiaDevolverTrueSiSeAgregaUnaMinaDeMinerales(){
		
		Terreno terreno = new Tierra(1,1);
		MinaDeMinerales mina = new MinaDeMinerales(3);
		
		assertTrue(terreno.agregarRecursoNatural(mina));
		
	}
	
	@Test
	public void tierraEstaOcupadaDeberiaDevolverTrueLuegoDeOcuparla(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno terreno=new Tierra(1,1);
		Unidad goliat= new Goliat(jugador);
		terreno.ubicar(goliat);
		
		assertTrue(terreno.estaOcupado());
		
	}
	
	@Test
	public void ocuparTerrenoConUnidadGuardaLaUnidadEnLaterreno(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno terreno=new Tierra(1,1);
		Unidad goliat= new Goliat(jugador);	
	
		terreno.ubicar(goliat);
		Unidad unidad = (Unidad) terreno.getUbicableEnTerreno();
		int vida = unidad.getVida();
		
		assertEquals(vida,125);
		
	}
	
	@Test
	public void terrenoTieneRecursoNaturalDeberiaDevolverTrueSiGuardoUnMineralEnEseTerreno(){
		
		Tierra tierra=new Tierra(1,1);
		MinaDeMinerales nodoMineral=new MinaDeMinerales(5);
		tierra.ocuparConRecursoNatural(nodoMineral);
		
		assertTrue(tierra.tieneRecursos());
		assertEquals(tierra.getRecurso(),nodoMineral);
		
	}
	
}
