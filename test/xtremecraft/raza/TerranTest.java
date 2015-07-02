package xtremecraft.raza;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.NoSePudoOcuparElTerrenoException;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.mapa.NoHayRecursoException;
import xtremecraft.partida.Jugador;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Unidad;

public class TerranTest {
	
	public Terran crearRazaTerranConRecursosSuficientesParaConstruir(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador.nacion();
		
	}
	
	@Test
	public void estaVivaDevuelveTrueAlCrearLaRaza(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		
		assertTrue(razaTerran.estaViva());
		
	}
	
	@Test
	public void razaSeInicializaConCantidadDeSuministroEquivalenteAUnDepositoDeSuministros(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = jugador.nacion();
		
		assertEquals(razaTerran.getPoblacionMaxima(),DepositoDeSuministros.getIncrementoPoblacion());
		
	}	
	
	@Test(expected = NoSePudoOcuparElTerrenoException.class)
	public void crearEdificioLanzaExcepcionSiSeIntentaCrearUnEdificioEnUnaCeldaOcupada(){
		
		Terran terran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Tierra tierrita = new Tierra (1,1);
		
		terran.crearBarraca(tierrita);
		terran.crearBarraca(tierrita);
		
	}
	
	@Test(expected = RazaNoTieneBarracasException.class)
	public void siIntentoCrearUnaFabricaPeroNoHayNingunaBarracaEnEstadoConstruidoSeLanzaExcepcion(){

		Terran terran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		Terreno otroTerreno = new Tierra(10,10);
		
		terran.crearBarraca(unTerreno);
		terran.crearFabrica(otroTerreno);
			
	}
	
	@Test(expected = RazaNoTieneBarracasException.class)
	public void crearFabricaLanzaExcepcionSiSeIntenaCrearFabricaCuandoNoHayBarracas(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = new Terran(jugador);
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearFabrica(unTerreno);
			
	}
	
	@Test(expected = RazaNoTieneFabricasException.class)
	public void crearPuertoEstelarLanzaExcepcionSiSeIntenaCrearPuertoCuandoNoHayFabricas(){
		
		Tierra tierra = new Tierra(15,15);
		Jugador jugador = new Jugador("Juan",tierra);
		Terran razaTerran = new Terran(jugador);
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearPuertoEstelar(unTerreno);
			
	}
	
	@Test
	public void crearCentroRecolectorDeMineralCreaNuevoCentroMineralQueRecolecta(){
		
		Terran terran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		MinaDeMinerales mina = new MinaDeMinerales(30);
		unTerreno.agregarRecursoNatural(mina);
		
		RecolectorDeMineral centroMineral = terran.crearRecolectorDeMineral(unTerreno);

		for(int turno=0; turno < centroMineral.tiempoConstruccion(); turno++) centroMineral.pasarTiempo();
		
		centroMineral.pasarTiempo();
		
		assertTrue(terran.getMinerales() > 0);
		
	}
	
	@Test
	public void alCrearCentroRecolectorDeMineralLasReservasSonCero(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		MinaDeMinerales mina = new MinaDeMinerales(30);
		unTerreno.agregarRecursoNatural(mina);
		
		RecolectorDeMineral centroMineral = razaTerran.crearRecolectorDeMineral(unTerreno);
		int mineralesEsperados = razaTerran.getMinerales();
		for(int turno=0; turno < centroMineral.tiempoConstruccion(); turno++) centroMineral.pasarTiempo();
		
		assertEquals(razaTerran.getMinerales(), mineralesEsperados);
	}
	
	@Test
	public void crearCentroRecolectorDeGasVespenoCreaNuevaRefineriaQueRecolecta(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		unTerreno.agregarRecursoNatural(volcan);
		
		RecolectorDeGasVespeno refineria = razaTerran.crearRecolectorDeGasVespeno(unTerreno);
		
		for(int turno=0; turno < refineria.tiempoConstruccion() ;turno++) refineria.pasarTiempo();
		int cantidadEsperada = razaTerran.getGasVespeno() + 10;
		refineria.pasarTiempo();
		
		assertEquals(razaTerran.getGasVespeno(), cantidadEsperada);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeMineralLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinRecursos(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearRecolectorDeMineral(unTerreno);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeMineralLanzaExcepcionSiSeIntentaCrearSobreTerrenoConUnRecursoQueNoSeaMineral(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		VolcanGasVespeno volcan = new VolcanGasVespeno(200);
		unTerreno.agregarRecursoNatural(volcan);
		
		razaTerran.crearRecolectorDeMineral(unTerreno);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeGasVespenoLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinRecursos(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearRecolectorDeGasVespeno(unTerreno);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeGasVespenoLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinGasVespeno(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		MinaDeMinerales mina = new MinaDeMinerales(3);
		
		unTerreno.agregarRecursoNatural(mina);
		
		razaTerran.crearRecolectorDeGasVespeno(unTerreno);
		
	}
	
	@Test
	public void crearDepositoDeSuministrosCreaNuevoDepositoDeSuministros(){

		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		DepositoDeSuministros deposito = razaTerran.crearDepositoDeSuministros(unTerreno);
		
		for(int turno=0;turno<6;turno++) deposito.pasarTiempo();
		
		assertEquals(deposito.tiempoConstruccion(),6);
		assertEquals(deposito.getVida(),100);
		
	}
	
	@Test
	public void construirDepositoDeSuministrosAumentaLaCantidadMaximaDePoblacionPermitida(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		DepositoDeSuministros deposito = razaTerran.crearDepositoDeSuministros(unTerreno);
		
		for(int turno=0;turno<6;turno++) deposito.pasarTiempo();
		razaTerran.pasarTiempo();
		assertEquals(razaTerran.getPoblacionMaxima(),10);
		
	}
	
	@Test (expected = BarracaNoEsDeLaRazaException.class)
	public void crearMarineConBarracaNoPropiaLanzaBarracaNoEsDeLaRazaException(){
		Mapa mapa = new Mapa(4);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terran razaTerran2 = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad unMarine = razaTerran2.crearMarine(unaBarraca, mapa);
	}
	
	@Test(expected = FabricaNoEsDeLaRazaException.class)
	public void crearGoliatConFabricaNoPropiaLanzaFabricaNoEsDeLaRazaException(){
		
		Mapa mapa = new Mapa(4);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terran razaTerran2 = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno otroTerreno = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(otroTerreno);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		@SuppressWarnings("unused")
		Unidad unGoliat = razaTerran2.crearGoliat(fabrica, mapa);
	}
	
	@Test (expected = PuertoEstelarNoEsDeLaRazaException.class)
	public void crearEspectroConPuertoEstelarNoPropioLanzaPuertoEstelarNoEsDeLaRazaException(){
		
		Mapa mapa = new Mapa(4);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terran razaTerran2 = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad unEspectro = razaTerran2.crearEspectro(puerto, mapa);
	}
	
	@Test (expected = PuertoEstelarNoEsDeLaRazaException.class)
	public void crearNaveCienciaConPuertoEstelarNoPropioLanzaPuertoEstelarNoEsDeLaRazaException(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terran razaTerran2 = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad naveCiencia = razaTerran2.crearNaveCiencia(puerto, mapa);
	}
	
	@Test (expected = PuertoEstelarNoEsDeLaRazaException.class)
	public void crearNaveDeTransporteConPuertoEstelarNoPropioLanzaPuertoEstelarNoEsDeLaRazaException(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terran razaTerran2 = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad naveTransporte = razaTerran2.crearNaveTransporte(puerto, mapa);
		
	}

	@Test
	public void crearMarineEntrenaUnNuevoMarineYLoUbicaEnElMapa(){
	
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		
		Unidad unMarine = razaTerran.crearMarine(unaBarraca, mapa);
		
		assertEquals(unMarine.getVida(),40);
		assertEquals(unMarine.getRadioVision(),7);
		assertFalse(unMarine.estaElevado());
		
	}
	
	@Test
	public void crearGoliatEntrenaUnNuevoGoliatYLoUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno otroTerreno = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(otroTerreno);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		Unidad unGoliat = razaTerran.crearGoliat(fabrica, mapa);
		
		assertEquals(unGoliat.getVida(),125);
		assertEquals(unGoliat.getRadioVision(),8);
		assertFalse(unGoliat.estaElevado());
		
	}
	
	
	@Test
	public void crearEspectroCreaUnNuevoEspectroYLoUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		Unidad unEspectro = razaTerran.crearEspectro(puerto, mapa);
		assertFalse(unEspectro.estaElevado());
		
	}
	
	@Test
	public void crearNaveCienciaCreaUnaNuevaNaveYLaUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		Unidad naveCiencia = razaTerran.crearNaveCiencia(puerto, mapa);
		assertEquals(naveCiencia.getVida(),200);
		assertEquals(naveCiencia.getRadioVision(),10);
		assertFalse(naveCiencia.estaElevado());
		
	}
	
	@Test
	public void crearNaveTransporteCreaUnaNuevaNaveYLaUbicaEnElMapa(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		Unidad naveTransporte = razaTerran.crearNaveTransporte(puerto, mapa);
		
		assertEquals(naveTransporte.getVida(),150);
		assertEquals(naveTransporte.getRadioVision(),8);
		assertFalse(naveTransporte.estaElevado());
		
	}
	
	@Test(expected = CantidadDeSuministroInsuficienteException.class)
	public void siIntentoCrearUnaUnidadYNoTengoSuministrosSuificientesSeLanzaExcepcion(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		for(int turno=0;turno<12;turno++) unaBarraca.pasarTiempo();
				
		for(int i=0;i<razaTerran.getPoblacionMaxima()+1;i++){
			razaTerran.crearMarine(unaBarraca, mapa);
		}
		
	}
	

	@Test
	public void pasarTiempoActualizaTodasLosEstadosDeTodosLosEdificiosCreados(){
		
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = new Tierra(1,2);
		Terreno otroTerreno = new Tierra(4,4);
		MinaDeMinerales mina = new MinaDeMinerales(30);
		unTerreno.agregarRecursoNatural(mina);
		
		RecolectorDeMineral centroMineral = razaTerran.crearRecolectorDeMineral(unTerreno);
		Barraca unaBarraca = razaTerran.crearBarraca(otroTerreno);

		for(int turno=0;turno<centroMineral.tiempoConstruccion();turno++) razaTerran.pasarTiempo();
			
		assertFalse(centroMineral.estaEnConstruccion());
		assertTrue(unaBarraca.estaEnConstruccion());
		
		int tiempoRestante = unaBarraca.tiempoConstruccion() - centroMineral.tiempoConstruccion();
		
		for(int turno=0;turno<tiempoRestante;turno++) razaTerran.pasarTiempo();
		
		assertFalse(centroMineral.estaEnConstruccion());
		assertFalse(unaBarraca.estaEnConstruccion());
		
	}
	
	@Test
	public void pasarTiempoActualizaTodasLosEstadosDeTodasLasUnidadesCreadas(){

		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Unidad unMarine = razaTerran.crearMarine(unaBarraca, mapa);
		for(int turno=0;turno<unMarine.tiempoConstruccion();turno++) razaTerran.pasarTiempo();
		
		assertFalse(unMarine.estaEnConstruccion());
		
	}
	/*
	@Test
	public void crearUnidadPermiteCrearHastaSeisUnidadesAlrededorDeUnaBarraca(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaTerranConRecursosSuficientesParaConstruir();
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Unidad marine1 = razaTerran.crearMarine(unaBarraca, mapa);
		Unidad marine2 = razaTerran.crearMarine(unaBarraca, mapa);
		Unidad marine3 = razaTerran.crearMarine(unaBarraca, mapa);
				
		Coordenada coordenada1 = marine1.getUbicacionActual();
		Coordenada coordenada2 = marine2.getUbicacionActual();
		Coordenada coordenada3 = marine3.getUbicacionActual();
	
		assertEquals(marine3.getVida(),40);
		
	}
	*/
}