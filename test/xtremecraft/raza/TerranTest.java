package xtremecraft.raza;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;

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
		for(int turno=0;turno<4;turno++) centroMineral.pasarTiempo();
		
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
		for(int turno=0;turno<6;turno++) refineria.pasarTiempo();
		
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
	
	
	
}