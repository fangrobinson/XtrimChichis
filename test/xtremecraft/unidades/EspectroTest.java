package xtremecraft.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;

public class EspectroTest {
	
	@Test
	public void espectroInicializadoConVidaCompleta(){

		Espectro gengar = new Espectro();
		
		assertEquals(gengar.getVida(),120);
	}
	
	@Test
	public void espectroInicializadoConEstadoVivo(){

		Espectro gengar = new Espectro();
		
		assertTrue(gengar.estaVivo());
		
	}
	
	@Test
	public void espectroPuedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Espectro gengar = new Espectro();
		
		assertFalse(gengar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void espectroGetVisionDevuelveRadioDeVisionDelEspectro(){

		Espectro gengar = new Espectro();
		
		assertEquals(gengar.getRadioVision(),7);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorAireLeSacaVeinteDeVida(){

		Terreno aire = new Aire(1,2);
		Terreno tierra = new Tierra(2,3);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(tierra);
		misdreavus.actualizarUbicacion(aire);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),100);
	}

	@Test
	public void siUnEspectroAtacaAOtroPorTierraLeSacaOchoDeVida(){

		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(tierra1);
		misdreavus.actualizarUbicacion(tierra2);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),112);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroFueraDeSuRangoNoLeHaceDaño(){

		Terreno unTerreno = new Aire(1,4);
		Terreno otroTerreno = new Tierra(10,10);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		gengar.atacar(misdreavus);
		
		assertEquals(misdreavus.vitalidad.getValor(),120);
	}
	
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloSuVidaQuedaEnCero(){

		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertEquals(misdreavus.vitalidad.getValor(), 0);
	}
	
	@Test
	public void siUnEspectroAtacaAOtroPorTierraHastaMatarloPasaAEstadoNoVivo(){

		Terreno unTerreno = new Aire(1,1);
		Terreno otroTerreno = new Tierra(1,2);
		Espectro gengar = new Espectro();
		Espectro misdreavus = new Espectro();
		
		gengar.actualizarUbicacion(unTerreno);
		misdreavus.actualizarUbicacion(otroTerreno);
		
		for (int i = 0; i < 16; i++){
			gengar.atacar(misdreavus);
		}
		
		assertFalse(misdreavus.estaVivo());
		
	}
		
	@Test
	public void actualizarUbicacionCambiaLasCoordenadasActualesDeLaUnidad(){
		
		Terreno unTerreno=new Aire(1,4);
		
		Espectro unEspectro = new Espectro();
		
		unEspectro.actualizarUbicacion(unTerreno);
		
		assertEquals(unEspectro.getUbicacionActual(),unTerreno.getUbicacionActual());
		
	}
	
	@Test
	public void actualizarUbicacionLiberaElTerrenoDeUbicacionAnterior(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro unEspectro = new Espectro();
		
		unEspectro.actualizarUbicacion(tierra1);
		unEspectro.actualizarUbicacion(tierra2);

		assertFalse(tierra1.estaOcupado());
		
	}
	
	@Test
	public void actualizarUbicacionOcupaElNuevoTerreno(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(2,3);
		Espectro unEspectro = new Espectro();
		
		unEspectro.actualizarUbicacion(tierra1);
		unEspectro.actualizarUbicacion(tierra2);

		assertTrue(tierra2.estaOcupado());
		
	}
	
	@Test
	public void subirANaveDeTransporteDevuelveTrueSiNaveEstaDentroDelRangoDeVision(){

		Terreno tierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		Espectro gengar = new Espectro();
		NaveTransporte nave = new NaveTransporte();
		
		gengar.actualizarUbicacion(tierra);
		nave.actualizarUbicacion(otraTierra);
		
		assertTrue(gengar.subirANaveDeTransporte(nave));
				
	}
	
	@Test
	public void recibirAtaqueRadiacionLeProduceDanioALaUnidadAfectada(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Espectro gengar = new Espectro();
		int vidaInicial = gengar.getVida();
		
		gengar.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<gengar.tiempoConstruccion();tiempo++) gengar.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(gengar, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		gengar.recibirAtaqueRadiacion(radiacion);
		
		assertEquals((vidaInicial-Radiacion.danioIrradiado), gengar.getVida());
		
	}
	

	@Test
	public void recibirAtaqueRadiacionDejaALaUnidadEnEstadoRadioactivo(){
		
		Mapa mapa = new Mapa(2);
		Terreno tierra = mapa.getCeldaEnFilaColumna(6,6).getCapaInferior();
		Espectro gengar = new Espectro();
		
		gengar.actualizarUbicacion(tierra);
		for(int tiempo=0;tiempo<gengar.tiempoConstruccion();tiempo++) gengar.pasarTiempo();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(gengar, Radiacion.radioDeAlcance);
		Radiacion radiacion = new Radiacion(celdasAfectadas);
		gengar.recibirAtaqueRadiacion(radiacion);
		
		assertTrue(gengar.esRadioactivo());
		
	}

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
