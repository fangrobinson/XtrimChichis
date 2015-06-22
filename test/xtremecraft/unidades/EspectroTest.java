package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.raza.Terran;

public class EspectroTest {

	public Terran crearRazaTerranValida(){
		Tierra tierra = new Tierra(15,15);
		Terran razaTerran = new Terran(tierra);
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return razaTerran;
	}
	
	@Test (expected = RecursosInsuficientesException.class)
	public void espectroCreadoParaRazaSinRecursosLanzaExcepcion(){

		Tierra tierra = new Tierra(15,15);
		Terran nacion = new Terran(tierra);
		
		new Espectro(nacion);
	}
	
	@Test
	public void espectroInicializadoConVidaCompleta(){

		Terran nacion = crearRazaTerranValida();
		Espectro gengar = new Espectro(nacion);
		
		assertEquals(gengar.getVida(),120);
	}
	
	@Test
	public void espectroInicializadoConEstadoVivo(){

		Terran nacion = crearRazaTerranValida();
		Espectro gengar = new Espectro(nacion);
		
		assertTrue(gengar.estaVivo());
		
	}
	
	@Test
	public void espectroPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Terran nacion = crearRazaTerranValida();
		Espectro gengar = new Espectro(nacion);
		
		assertFalse(gengar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void espectroGetVisionDevuelveRadioDeVisionDelEspectro(){

		Terran nacion = crearRazaTerranValida();
		Espectro gengar = new Espectro(nacion);
		
		assertEquals(gengar.getRadioVision(),7);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorAireLeSacaVeinteDeVida(){

		Terran nacion = crearRazaTerranValida();
		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		Espectro gengar = new Espectro(nacion);
		Espectro misdreavus = new Espectro(nacion);
		
		gengar.actualizarUbicacion(tierra);
		misdreavus.actualizarUbicacion(aire);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),100);
	}

	@Test
	public void siUnEspectroAtacaAOtroPorTierraLeSacaOchoDeVida(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro gengar = new Espectro(nacion);
		Espectro misdreavus = new Espectro(nacion);
		
		gengar.actualizarUbicacion(tierra1);
		misdreavus.actualizarUbicacion(tierra2);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),112);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terran nacion = crearRazaTerranValida();
		Terreno unTerreno = new Aire(1,4);
		Terreno otroTerreno = new Tierra(10,10);
		Espectro gengar = new Espectro(nacion);
		Espectro misdreavus = new Espectro(nacion);
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),120);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Terran nacion = crearRazaTerranValida();
		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro(nacion);
		Espectro misdreavus = new Espectro(nacion);
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertEquals(misdreavus.vitalidad.getValor(), 0);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloPasaAEstadoNoVivo(){

		Terran nacion = crearRazaTerranValida();
		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro(nacion);
		Espectro misdreavus = new Espectro(nacion);
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertFalse(misdreavus.estaVivo());
		
	}
		
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno unTerreno=new Aire(1,4);
		
		Espectro unEspectro = new Espectro(nacion);
		
		unEspectro.actualizarUbicacion(unTerreno);
		
		assertEquals(unEspectro.getUbicacionActual(),unTerreno.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro unEspectro = new Espectro(nacion);
		
		unEspectro.actualizarUbicacion(tierra1);
		unEspectro.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Terran nacion = crearRazaTerranValida();
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro unEspectro = new Espectro(nacion);
		
		unEspectro.actualizarUbicacion(tierra1);
		unEspectro.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Terran nacion = crearRazaTerranValida();
		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		Espectro gengar = new Espectro(nacion);
		NaveTransporte nave = new NaveTransporte(nacion);
		
		gengar.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(gengar.subirANaveDeTransporte(nave));
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Espectro gengar = new Espectro(nacion);
		int vidaInicial = gengar.getVida();
		
		gengar.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<gengar.tiempoConstruccion();tiempo++) gengar.pasarTiempo();
		Radiacion radiacion = new Radiacion(mapa);
		gengar.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), gengar.getVida());
		
	}
	

	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Terran nacion = crearRazaTerranValida();
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Espectro gengar = new Espectro(nacion);
		
		gengar.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<gengar.tiempoConstruccion();tiempo++) gengar.pasarTiempo();
		Radiacion radiacion = new Radiacion(mapa);
		gengar.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(gengar.esRadioactivo());
		
	}

	//TODO: Que paso aca?
	/*
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadDisminuyeAlPasarElTiempo(){
	
	}
	
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadNoPuedeMoverse(){
	
	}
	
	@Test
	public void luegoDeRecibirUnAtaqueRadioactivoLaVidaDeLaUnidadNoPuedeAtacar(){
	
	}
	*/

}
