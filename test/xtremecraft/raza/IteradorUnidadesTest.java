package xtremecraft.raza;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.unidades.Marine;
import xtremecraft.unidades.Unidad;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.IteradorUnidades;

public class IteradorUnidadesTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	public void cuantosHayDeDevuelveCeroConArregloVacio(){
		
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		IteradorUnidades iter = new IteradorUnidades(unidades);
		
		assertEquals(iter.cuantosHayDe(Marine.class), 0);
		
	}
	
	@Test
	public void cuantosHayDeConArregloDeEdificiosConstruidosOSinConstruirDevuelveEsaCantidad(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		IteradorUnidades iter = new IteradorUnidades(unidades);
		
		Marine marine = new Marine(jugador);
		for(int i=0;i<marine.tiempoConstruccion();i++)	marine.pasarTiempo();
		
		assertEquals(iter.cuantosHayDe(Marine.class), 0);
		
	}


}
