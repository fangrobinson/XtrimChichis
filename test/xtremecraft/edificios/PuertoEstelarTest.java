package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelarTest {
	
	public Jugador crearJugadorConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador;
		
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearPuertoConRazaSinRecursosLanzaExcepcion(){
		
		Tierra primerTierra = new Tierra(15,15);
		Tierra segundaTierra = new Tierra(1,1);
		Tierra tercerTierra = new Tierra (2,2);
		Tierra cuartaTierra = new Tierra(3,3);
		Tierra quintaTierra = new Tierra(11,11);
		Tierra sextaTierra = new Tierra(12,12);
		Jugador jugador = new Jugador("Juan",primerTierra);
		Terran razaTerran = jugador.nacion();
		
		
		razaTerran.juntarMinerales(350);
		Barraca barraca = new Barraca(jugador, segundaTierra);
		Fabrica fabrica = new Fabrica(jugador, barraca, tercerTierra);
		
		new PuertoEstelar(jugador, fabrica, cuartaTierra);
		new PuertoEstelar(jugador, fabrica, quintaTierra);
		new PuertoEstelar(jugador, fabrica, sextaTierra);
		
	}
	
	@Test
	public void puertoSeInicializaConEstadoVivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		
		assertTrue(puertoEstelar.estaVivo());
		
	}	

	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElPuerto(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		
		assertTrue(puertoEstelar.estaEnConstruccion());
		
	}

	@Test
	public void puertoEstelarEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelPuerto(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		
		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		
		assertFalse(puertoEstelar.estaEnConstruccion());
		
	}
	
	@Test(expected = EdificioEnConstruccionException.class)
	public void siPuertNoEstaConstruidoYSeIntentaCrearUnidadSeLanzaExcepcion(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		
		puertoEstelar.crearEspectro();
		
	}

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(), 4);
		assertEquals(puertoEstelar.getUbicacionActual().columna(), 4);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		
		assertFalse(puertoEstelar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void getActualizarUbicacionModificaCoordenadasDelEdificio(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Terreno tierra4 = new Tierra(5,7);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		
		
		puertoEstelar.actualizarUbicacion(tierra4);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(), 5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(), 7);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		int vidaEsperada = puertoEstelar.vidaMax();
		
		for (int tiempo=0; tiempo<puertoEstelar.tiempoConstruccion(); tiempo++) puertoEstelar.pasarTiempo();
		
		assertEquals(puertoEstelar.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica,tierra3);
		int valorDanio = 30;
		int vidaEsperada = puertoEstelar.vidaMax() - valorDanio;
		

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		puertoEstelar.recibirDanio(valorDanio);
		puertoEstelar.recibirDanio(valorDanio);
		vidaEsperada -= valorDanio;
		
		assertEquals(puertoEstelar.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void crearEspectroDevuelveUnaNuevaUnidadEspectro(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica,tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		Espectro unEspectro = puertoEstelar.crearEspectro();
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
	
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		NaveCiencia nave = puertoEstelar.crearNaveCiencia();
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte();
		
		assertEquals(naveTransporte.getVida(),150);
		
	}
	
	@Test
	public void siUnPuertoEstelarEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Jugador jugador = crearJugadorConRecursosSuficientesParaConstruir();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Terreno tierra4 = new Tierra(4,6);
		Barraca barraca = new Barraca(jugador, tierra1);
		Fabrica fabrica = new Fabrica(jugador, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(jugador, fabrica, tierra3);
		Marine miniSamus = new Marine(jugador);
		int cantidadDeAtaquesAPuerto = 17;

		miniSamus.actualizarUbicacion(tierra4);
		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		for(int i=0;i<cantidadDeAtaquesAPuerto;i++) miniSamus.atacar(puertoEstelar);
		
		assertFalse(puertoEstelar.estaVivo());		
		
	}

}
