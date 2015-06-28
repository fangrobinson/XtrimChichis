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

	@Test
	public void nuevoAireCreaAguaConEstadoNoOcupado(){
		
		Terreno terreno=new Aire(1,2);
		assertFalse(terreno.estaOcupado());
		
	}
	
	@Test(expected = NoSePudoOcuparElTerrenoException.class)
	public void aireEstaOcupadaDeberiaDevolverFalseSiTratoDeUbicarUnaUnidadTerrestre(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarMinerales(1000);
		razaTerran.juntarGas(1000);
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
		Tierra tierra = new Tierra(1,2);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarMinerales(1000);
		razaTerran.juntarGas(1000);
		Unidad espectro = new Espectro(jugador);
		aire.ubicar(espectro);
		Unidad unidad = (Unidad) aire.getUbicableEnTerreno();
		int vida = unidad.getVida();
		 
		assertEquals(vida, 120);
		
	}	
	
}
