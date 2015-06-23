package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;

public class DepositoDeSuministrosTest {
	
	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearDepositoDeSuministrosConRazaSinRecursosLanzaExcepcion(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		Terreno unaTierra = new Tierra(2,2);
		Terreno otraTierra = new Tierra(1,1);
		
		new DepositoDeSuministros(razaTerran, unaTierra);
		new DepositoDeSuministros(razaTerran, otraTierra);
	}
	
	public DepositoDeSuministros construirNuevoDeposito(Terran raza, Terreno tierra){
		
		DepositoDeSuministros deposito = new DepositoDeSuministros(raza, tierra);
		for(int i=0; i<deposito.tiempoConstruccion; i++){
			deposito.pasarTiempo();
		}
		return deposito;
		
	}
	
	@Test
	public void depositoSeInicializaConEstadoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(nacion, tierra);
		
		assertTrue(deposito.estaVivo());		
		
	}
	
	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(nacion, tierra);
		
		assertEquals(deposito.getUbicacionActual().fila(), fila);
		assertEquals(deposito.getUbicacionActual().columna(), columna);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(nacion, tierra);
		
		assertFalse(deposito.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(nacion, tierra);
		
		assertEquals(deposito.getVida(), deposito.vidaMax());
		
	}
	
	@Test
	public void edificioEstaElevadoDevuelveFalseParaEdificiosCreadosEnTierra(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		
		DepositoDeSuministros deposito = construirNuevoDeposito(nacion, tierra);
		
		assertFalse(deposito.estaElevado());
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		DepositoDeSuministros deposito = construirNuevoDeposito(nacion, tierra);
		int valorDanio = 30;
		int vidaEsperada = deposito.vidaMax() - valorDanio;
		
		deposito.recibirDanio(valorDanio);
		vidaEsperada -= valorDanio;
		deposito.recibirDanio(valorDanio);
		
		assertEquals(deposito.getVida(), vidaEsperada);
		
	}
	
	@Test
	public void siUnDepositoEsAtacadoHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(3,2);
		Terreno otraTierra = new Tierra(1,2);
		DepositoDeSuministros deposito = construirNuevoDeposito(nacion, tierra);
		Marine miniSamus = new Marine(nacion);
		int cantidadDeAtaquesADeposito = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		deposito.actualizarUbicacion(otraTierra);
		for(int i=0;i<cantidadDeAtaquesADeposito;i++) miniSamus.atacar(deposito);
		
		assertFalse(deposito.estaVivo());		
		
	}

}
