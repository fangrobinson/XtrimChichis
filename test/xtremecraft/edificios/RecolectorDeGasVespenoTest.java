package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Marine;

public class RecolectorDeGasVespenoTest {

	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearBarracaConRazaSinRecursosLanzaExcepcion(){
		Tierra tierraNacion = new Tierra(15,15);
		Terran nacion = new Terran(tierraNacion);
		Terreno tierra = new Tierra(1,1);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		
		RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nacion, tierra);
		RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nacion, tierra);
		RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nacion, tierra);

	}
	
	public RecolectorDeGasVespeno construirNuevoRecolectorDeGasVespeno(int fila, int columna){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nacion, tierra);
		for(int i=0; i < refineria.tiempoConstruccion; i++){
			refineria.pasarTiempo();
		}
		return refineria;
	}
	
	@Test
	public void seInicializaConEstadoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nacion, tierra);
		
		assertTrue(refineria.estaVivo());
		
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElRecolectorDeGasVespeno(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nacion, tierra);
		
		assertTrue(refineria.estaEnConstruccion());
		
	}
	
	@Test
	public void recolectorEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelRecolectorDeGasVespeno(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertFalse(refineria.estaEnConstruccion());
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveTrue(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertTrue(refineria.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void testPasarTiempoAumentaLaCantidadDeReservasDeLaRaza(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(12,12);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		tierra.agregarRecursoNatural(volcan);
		RecolectorDeGasVespeno refineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nacion, tierra);
		for(int i=0; i < refineria.tiempoConstruccion; i++){
			refineria.pasarTiempo();
		}
		int valorEsperado = nacion.getGasVespeno() + refineria.aumentoDeReservaEnTurno;
		
		refineria.pasarTiempo();
		
		assertEquals(nacion.getGasVespeno(), valorEsperado);
			
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertEquals(refineria.getUbicacionActual().columna(),2);
		assertEquals(refineria.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		
		assertEquals(refineria.getVida(), refineria.vidaMax());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){

		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		int valorDanio = 30;
		int vidaEsperada = refineria.vidaMax() - valorDanio;
		
		refineria.recibirDanio(valorDanio);
		vidaEsperada = refineria.vidaMax() - (valorDanio * 2) ;
		refineria.recibirDanio(valorDanio);
		
		assertEquals(refineria.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siUnRecolectorEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(3,2);
		RecolectorDeGasVespeno refineria = construirNuevoRecolectorDeGasVespeno(1,2);
		Marine miniSamus = new Marine(nacion);
		int cantidadDeAtaquesARecolector = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		for(int i=0;i<cantidadDeAtaquesARecolector;i++) miniSamus.atacar(refineria);
		
		assertFalse(refineria.estaVivo());		
		
	}

}
