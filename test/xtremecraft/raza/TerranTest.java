package xtremecraft.raza;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Unidad;

public class TerranTest {
	
	@Test
	public void estaVivaDevuelveTrueAlCrearLaRaza(){
		
		Terran razaTerran = new Terran();
		
		assertTrue(razaTerran.estaViva());
		
	}
	
	@Test
	public void tieneBarracasDeberiaDevolverFalseSiNoSeCrearonBarracas(){
		
		Terran razaTerran = new Terran();
		
		assertFalse(razaTerran.tieneBarracas());
			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearEdificioLanzaExcepcionSiSeIntentaCrearUnEdificioEnUnaCeldaOcupada(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearBarraca(unTerreno);
		razaTerran.crearBarraca(unTerreno);
		
	}
	
	@Test
	public void tieneBarracasDeberiaDevolverTrueSiSeCrearonBarracas(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearBarraca(unTerreno);
		
		assertTrue(razaTerran.tieneBarracas());
	
	}
	
	@Test
	public void tieneFabricasDeberiaDevolverTrueSiSeCrearonFabricas(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		Terreno otroTerreno = new Tierra(10,10);
		
		razaTerran.crearBarraca(unTerreno);
		razaTerran.crearFabrica(otroTerreno);
		
		assertTrue(razaTerran.tieneFabricas());
			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearFabricaLanzaExcepcionSiSeIntenaCrearFabricaCuandoNoHayBarracas(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearFabrica(unTerreno);
			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearPuertoEstelarLanzaExcepcionSiSeIntenaCrearPuertoCuandoNoHayFabricas(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearPuertoEstelar(unTerreno);
			
	}
	
	@Test
	public void crearCentroRecolectorDeMineralCreaNuevoCentroMineral(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		MinaDeMinerales mina = new MinaDeMinerales(30);
		unTerreno.agregarRecursoNatural(mina);
		
		RecolectorDeMineral centroMineral = (RecolectorDeMineral)razaTerran.crearRecolectorDeMineral(unTerreno);
		
		//Dejo el centroMineral en estado construido:
		//Refactoring ---> razaTerran.pasarTiempo() debe actualizar todos los edificios.
		for(int turno=0;turno<3;turno++) centroMineral.pasarTiempo();
		
		assertEquals(centroMineral.getReservas(),0);
		
		centroMineral.pasarTiempo();
		
		assertEquals(centroMineral.getReservas(),10);
		
	}
	
	@Test
	public void crearCentroRecolectorDeGasVespenoCreaNuevoCentroMineral(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		unTerreno.agregarRecursoNatural(volcan);
		
		RecolectorDeGasVespeno refineria = (RecolectorDeGasVespeno)razaTerran.crearRecolectorDeGasVespeno(unTerreno);
		
		//Dejo refineria en estado construido:
		//Refactoring ---> razaTerran.pasarTiempo() debe actualizar todos los edificios.
		for(int turno=0; turno<5 ;turno++) refineria.pasarTiempo();
		
		assertEquals(refineria.getReservas(),0);
		
		refineria.pasarTiempo();
		
		assertEquals(refineria.getReservas(),10);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearCentroRecolectorDeMineralLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinRecursos(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearRecolectorDeMineral(unTerreno);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearCentroRecolectorDeMineralLanzaExcepcionSiSeIntentaCrearSobreTerrenoConUnRecursoQueNoSeaMineral(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		unTerreno.agregarRecursoNatural(volcan);
		
		razaTerran.crearRecolectorDeMineral(unTerreno);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearCentroRecolectorDeGasVespenoLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinRecursos(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearRecolectorDeGasVespeno(unTerreno);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void crearCentroRecolectorDeGasVespenoLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinGasVespeno(){
		
		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		MinaDeMinerales mina = new MinaDeMinerales(3);
		
		unTerreno.agregarRecursoNatural(mina);
		
		razaTerran.crearRecolectorDeGasVespeno(unTerreno);
		
	}
	
	@Test
	public void crearDepositoDeSuministrosCreaNuevoDepositoDeSuministros(){

		Terran razaTerran = new Terran();
		Terreno unTerreno = new Tierra(1,2);
		DepositoDeSuministros deposito = (DepositoDeSuministros)razaTerran.crearDepositoDeSuministros(unTerreno);
		
		//Dejo deposito en estado construido:
		//Refactoring ---> razaTerran.pasarTiempo() debe actualizar todos los edificios.
		for(int turno=0;turno<6;turno++) deposito.pasarTiempo();
		
		assertEquals(deposito.tiempoConstruccion(),6);
		assertEquals(deposito.getVida(),100);
		
	}
	
	@Test
	public void crearMarineEntrenaUnNuevoMarineYLoUbicaEnElMapa(){
		
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = new Terran();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = (Barraca)razaTerran.crearBarraca(unTerreno);
		for(int turno=0;turno<12;turno++) unaBarraca.pasarTiempo();
		
		Unidad unMarine = razaTerran.crearMarine(unaBarraca, mapa);
		
		assertEquals(unMarine.getVida(),40);
		assertEquals(unMarine.getRadioVision(),7);
		assertEquals(unMarine.getUbicacionActual().fila(),1);
		assertEquals(unMarine.getUbicacionActual().columna(),2);
		assertFalse(unMarine.estaElevado());
		
	}
	
	@Test
	public void crearGoliatEntrenaUnNuevoGoliatYLoUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = new Terran();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno otroTerreno = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Edificio unaBarraca = razaTerran.crearBarraca(unTerreno);
		for(int turno=0;turno<12;turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = (Fabrica) razaTerran.crearFabrica(otroTerreno);
		for(int turno=0;turno<12;turno++) fabrica.pasarTiempo();
		
		Unidad unGoliat = razaTerran.crearGoliat(fabrica, mapa);
		
		assertEquals(unGoliat.getVida(),125);
		assertEquals(unGoliat.getRadioVision(),8);
		assertEquals(unGoliat.getUbicacionActual().fila(),4);
		assertEquals(unGoliat.getUbicacionActual().columna(),5);
		assertFalse(unGoliat.estaElevado());
		
	}
	
	
	@Test
	public void crearEspectroCreaUnNuevoEspectroYLoUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = new Terran();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Edificio unaBarraca = razaTerran.crearBarraca(terreno1);
		for(int turno=0;turno<12;turno++) unaBarraca.pasarTiempo();
		Edificio fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<12;turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = (PuertoEstelar)razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<10;turno++) puerto.pasarTiempo();
		
		Unidad unEspectro = razaTerran.crearEspectro(puerto, mapa);
		//SUPONGO QUE LA UNIDAD VOLADORA SE CREA EN LA TIERRA PERO SE ELEVA AUTOMATICAMENTE
		assertEquals(unEspectro.getVida(),120);
		assertEquals(unEspectro.getRadioVision(),7);
		assertEquals(unEspectro.getUbicacionActual().fila(),8);
		assertEquals(unEspectro.getUbicacionActual().columna(),7);
		assertTrue(unEspectro.estaElevado());
		
	}
	
	@Test
	public void crearNaveCienciaCreaUnaNuevaNaveYLaUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = new Terran();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Edificio unaBarraca = razaTerran.crearBarraca(terreno1);
		for(int turno=0;turno<12;turno++) unaBarraca.pasarTiempo();
		Edificio fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<12;turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = (PuertoEstelar)razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<10;turno++) puerto.pasarTiempo();
		
		Unidad naveCiencia = razaTerran.crearNaveCiencia(puerto, mapa);
		//SUPONGO QUE LA UNIDAD VOLADORA SE CREA EN LA TIERRA PERO SE ELEVA AUTOMATICAMENTE
		assertEquals(naveCiencia.getVida(),200);
		assertEquals(naveCiencia.getRadioVision(),10);
		assertEquals(naveCiencia.getUbicacionActual().fila(),8);
		assertEquals(naveCiencia.getUbicacionActual().columna(),7);
		assertTrue(naveCiencia.estaElevado());
		
		
	}
	
	@Test
	public void crearNaveTransporteCreaUnaNuevaNaveYLaUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = new Terran();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Edificio unaBarraca = razaTerran.crearBarraca(terreno1);
		for(int turno=0;turno<12;turno++) unaBarraca.pasarTiempo();
		Edificio fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<12;turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = (PuertoEstelar)razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<10;turno++) puerto.pasarTiempo();
		
		Unidad naveTransporte = razaTerran.crearNaveTransporte(puerto, mapa);
		//SUPONGO QUE LA UNIDAD VOLADORA SE CREA EN LA TIERRA PERO SE ELEVA AUTOMATICAMENTE
		assertEquals(naveTransporte.getVida(),150);
		assertEquals(naveTransporte.getRadioVision(),8);
		assertEquals(naveTransporte.getUbicacionActual().fila(),8);
		assertEquals(naveTransporte.getUbicacionActual().columna(),7);
		assertTrue(naveTransporte.estaElevado());
		
	}
	
	
	
}