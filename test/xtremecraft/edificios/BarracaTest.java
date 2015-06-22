package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Marine;
import xtremecraft.raza.RecursosInsuficientesException;

public class BarracaTest {

	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test(expected = RecursosInsuficientesException.class)
	public void crearBarracaConRazaSinRecursosLanzaExcepcion(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		Terreno otraTierra = new Tierra(1,1);
		new Barraca(razaTerran, otraTierra);
	}
	
	@Test
	public void barracaSeInicializaConEstadoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		
		Barraca unaBarraca = new Barraca(nacion, tierra);
		
		assertTrue(unaBarraca.estaVivo());
		
	}
	
	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearLaBarraca(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = new Barraca(nacion, tierra);
		
		assertTrue(unaBarraca.estaEnConstruccion());
		
	}
	

	@Test(expected = EdificioEnConstruccionException.class)
	public void siBarracaNoEstaConstruidaYSeIntentaEntrenarUnMarineSeLanzaExcepcion(){
		
		Terran nacion = crearRazaTerranValida();
		int fila = 1;
		int columna = 2;
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = new Barraca(nacion, tierra);
		
		unaBarraca.entrenarMarine(nacion);
		
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
		
		Terran nacion = crearRazaTerranValida();
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		
		Marine nuevoMarine = unaBarraca.entrenarMarine(nacion);
		
		assertEquals(nuevoMarine.getVida(), 40);
		
	}
	
	public Barraca construirNuevaBarraca(int fila, int columna){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(fila,columna);
		Barraca unaBarraca = new Barraca(nacion, tierra);
		for(int i=0; i<unaBarraca.tiempoConstruccion(); i++){
			unaBarraca.pasarTiempo();
		}
		return unaBarraca;
		
	}
	
	@Test
	public void siUnaBarracaEsAtacadaHastaQueSuVidaLlegaACeroPasaAEstadoNoVivo(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(3,2);
		Barraca unaBarraca = construirNuevaBarraca(1,2);
		Marine miniSamus = new Marine(nacion);
		int cantidadDeAtaquesABarraca = 17;
		
		miniSamus.actualizarUbicacion(tierra);
		for(int i=0;i<cantidadDeAtaquesABarraca;i++) miniSamus.atacar(unaBarraca);
		
		assertFalse(unaBarraca.estaVivo());		
		
	}

}
