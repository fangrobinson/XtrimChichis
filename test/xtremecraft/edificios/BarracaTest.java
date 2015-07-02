package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.unidades.Marine;
import xtremecraft.raza.RecursosInsuficientesException;

public class BarracaTest {

	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearBarracaConRazaSinRecursosLanzaExcepcion(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terreno otraTierra = new Tierra(1,1);
		new Barraca(jugador, otraTierra);
		
	}
	
	@Test
	public void barracaSeInicializaConEstadoVivo(){
		
		int fila = 1;
		int columna = 2;
		Tierra tierra = new Tierra(15,15);
		Terreno unTerreno = new Tierra(fila,columna);
		Jugador jugador = new Jugador("Juan",tierra);
		
		jugador.nacion().juntarGas(1000);
		jugador.nacion().juntarMinerales(1000);
		
		
		Barraca unaBarraca = jugador.crearBarraca(unTerreno);
		
		assertTrue(unaBarraca.estaVivo());
		
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearLaBarraca(){
		
		int fila = 1;
		int columna = 2;
		Tierra tierra = new Tierra(15,15);
		Terreno unTerreno = new Tierra(fila,columna);
		Jugador jugador = new Jugador("Juan",tierra);
		
		jugador.nacion().juntarGas(1000);
		jugador.nacion().juntarMinerales(1000);
		
		Barraca unaBarraca = new Barraca(jugador,unTerreno);
		
		assertTrue(unaBarraca.estaEnConstruccion());
		
	}
	

	@Test(expected = EdificioEnConstruccionException.class)
	public void siBarracaNoEstaConstruidaYSeIntentaEntrenarUnMarineSeLanzaExcepcion(){
		
		int fila = 1;
		int columna = 2;
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terreno unTerreno = new Tierra(fila,columna);
		
		jugador.nacion().juntarGas(1000);
		jugador.nacion().juntarMinerales(1000);
		
		Barraca unaBarraca = new Barraca(jugador, unTerreno);
		
		unaBarraca.entrenarMarine();
		
	}

	@Test
	public void barracaEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDeLaBarraca(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertFalse(unaBarraca.estaEnConstruccion());
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		int fila = 1;
		int columna = 2;
		Barraca unaBarraca = construirNuevaBarraca(fila, columna);
		
		assertEquals(unaBarraca.getUbicacionActual().fila(), fila);
		assertEquals(unaBarraca.getUbicacionActual().columna(), columna);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertFalse(unaBarraca.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		int vidaEsperada = unaBarraca.vidaMax();
		assertEquals(unaBarraca.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		assertFalse(unaBarraca.estaElevado());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		int valorDanio = 30;
		
		int vidaEsperada = unaBarraca.vidaMax() - valorDanio;
		unaBarraca.recibirDanio(valorDanio);
		vidaEsperada -= valorDanio;
		unaBarraca.recibirDanio(valorDanio);
		
		assertEquals(unaBarraca.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void entrenarMarineDevuelveNuevaUnidadMarine(){
		
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		Marine nuevoMarine = unaBarraca.entrenarMarine();
		
		assertEquals(nuevoMarine.getVida(), 40);
		
	}
	
	public Barraca construirNuevaBarraca(int fila, int columna){
		
		Tierra tierra = new Tierra(fila,columna);
		Jugador jugador = new Jugador("Juan",tierra);
		jugador.nacion().juntarGas(1000);
		jugador.nacion().juntarMinerales(1000);
		Barraca unaBarraca = new Barraca(jugador, tierra);
		for(int i=0; i<unaBarraca.tiempoConstruccion(); i++){
			unaBarraca.pasarTiempo();
		}
		return unaBarraca;
		
	}
	
	@Test
	public void siUnaBarracaEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Tierra tierra1 = new Tierra(15,15);
		Terreno tierra2 = new Tierra(3,2);
		Jugador jugador = new Jugador("Juan",tierra1);
		
		jugador.nacion().juntarGas(1000);
		jugador.nacion().juntarMinerales(1000);
	
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		Marine miniSamus = new Marine(jugador);
		int cantidadDeAtaquesABarraca = 17;
		
		miniSamus.setUbicacionInicial(tierra2);
		for(int i=0;i<cantidadDeAtaquesABarraca;i++) miniSamus.atacar(unaBarraca);
		
		assertFalse(unaBarraca.estaVivo());		
		
	}

}
