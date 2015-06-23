package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelarTest {
	
	

	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearPuertoConRazaSinRecursosLanzaExcepcion(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		Terreno otraTierra = new Tierra(1,1);
		Tierra tercerTierra = new Tierra (2,2);
		Tierra cuartaTierra = new Tierra(3,3);
		Tierra quintaTierra = new Tierra(11,11);
		Tierra sextaTierra = new Tierra(12,12);
		
		razaTerran.juntarMinerales(350);
		Barraca barraca = new Barraca(razaTerran, otraTierra);
		Fabrica fabrica = new Fabrica(razaTerran, barraca, tercerTierra);
		
		new PuertoEstelar(razaTerran, fabrica, cuartaTierra);
		new PuertoEstelar(razaTerran, fabrica, quintaTierra);
		new PuertoEstelar(razaTerran, fabrica, sextaTierra);
	}
	
	@Test
	public void puertoSeInicializaConEstadoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		
		assertTrue(puertoEstelar.estaVivo());
		
	}	

	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElPuerto(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		
		assertTrue(puertoEstelar.estaEnConstruccion());
		
	}

	@Test
	public void puertoEstelarEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelPuerto(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		
		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		
		assertFalse(puertoEstelar.estaEnConstruccion());
		
	}
	
	@Test(expected = EdificioEnConstruccionException.class)
	public void siPuertNoEstaConstruidoYSeIntentaCrearUnidadSeLanzaExcepcion(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		
		puertoEstelar.crearEspectro(nacion);
		
	}

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(), 4);
		assertEquals(puertoEstelar.getUbicacionActual().columna(), 4);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		
		assertFalse(puertoEstelar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void getActualizarUbicacionModificaCoordenadasDelEdificio(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Terreno tierra4 = new Tierra(5,7);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		
		
		puertoEstelar.actualizarUbicacion(tierra4);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(), 5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(), 7);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		int vidaEsperada = puertoEstelar.vidaMax();
		
		for (int tiempo=0; tiempo<puertoEstelar.tiempoConstruccion(); tiempo++) puertoEstelar.pasarTiempo();
		
		assertEquals(puertoEstelar.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
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
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		Espectro unEspectro = puertoEstelar.crearEspectro(nacion);
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
	
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		NaveCiencia nave = puertoEstelar.crearNaveCiencia(nacion);
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte(nacion);
		
		assertEquals(naveTransporte.getVida(),150);
		
	}
	
	@Test
	public void siUnPuertoEstelarEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Terreno tierra4 = new Tierra(4,6);
		Barraca barraca = new Barraca(nacion, tierra1);
		Fabrica fabrica = new Fabrica(nacion, barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(nacion, fabrica,tierra3);
		Marine miniSamus = new Marine(nacion);
		int cantidadDeAtaquesAPuerto = 17;

		miniSamus.actualizarUbicacion(tierra4);
		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		for(int i=0;i<cantidadDeAtaquesAPuerto;i++) miniSamus.atacar(puertoEstelar);
		
		assertFalse(puertoEstelar.estaVivo());		
		
	}

}
