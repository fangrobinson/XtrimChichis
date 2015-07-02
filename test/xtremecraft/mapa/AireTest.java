package xtremecraft.mapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import xtremecraft.mapa.Aire;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Unidad;

import org.junit.Test;

public class AireTest {

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
	public void nuevoAireCreaAguaConEstadoNoOcupado(){
		
		Terreno terreno=new Aire(1,2);
		assertFalse(terreno.estaOcupado());
		
	}
	
	@Test(expected = NoSePudoOcuparElTerrenoException.class)
	public void aireEstaOcupadaDeberiaDevolverFalseSiTratoDeUbicarUnaUnidadTerrestre(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno terreno=new Aire(1,4);
		Unidad goliat= new Goliat(jugador);
		terreno.ubicar(goliat);
		
	}
	
	@Test
	public void agregarRecursoNaturalDeberiaDevolverFalse(){
		
		Terreno terreno=new Aire(1,4);
		MinaDeMinerales unRecursoNatural = new MinaDeMinerales(3);
		assertFalse(terreno.agregarRecursoNatural(unRecursoNatural));
		
	}
	
	@Test
	public void ocuparterrenoConUnidadGuardaLaUnidadEnElterreno(){
		
		Aire aire = new Aire(1,3);
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Unidad espectro = new Espectro(jugador);
		aire.ubicar(espectro);
		Unidad unidad = (Unidad) aire.getUbicableEnTerreno();
		int vida = unidad.getVida();
		 
		assertEquals(vida, 120);
		
	}	
	
}
