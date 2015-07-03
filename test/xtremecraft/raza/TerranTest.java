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
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.unidades.Unidad;

public class TerranTest {
	
	public Terran crearRazaConRecursosSuficientesParaConstruir(Mapa mapa){
		
		int fila = 15;
		int columna = 16;
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terran razaTerran = jugador.nacion();
		razaTerran.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		return jugador.nacion();
		
	}
	
	@Test
	public void estaVivaDevuelveTrueAlCrearLaRaza(){
		
		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terran razaTerran = jugador.nacion();
		
		assertTrue(razaTerran.estaViva());
		
	}
	
	@Test
	public void razaSeInicializaConCantidadDeSuministroEquivalenteAUnDepositoDeSuministros(){
		
		int fila = 15;
		int columna = 16;
		Mapa mapa = new Mapa(2);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(fila, columna).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Terran razaTerran = jugador.nacion();
		
		assertEquals(razaTerran.getPoblacionMaxima(),DepositoDeSuministros.getIncrementoPoblacion());
		
	}	
	
	@Test(expected = NoSePudoOcuparElTerrenoException.class)
	public void crearEdificioLanzaExcepcionSiSeIntentaCrearUnEdificioEnUnaCeldaOcupada(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Tierra tierrita = (Tierra) mapa.getCeldaEnFilaColumna(1, 1).getCapaInferior();
		
		terran.crearBarraca(tierrita, mapa);
		terran.crearBarraca(tierrita, mapa);
		
	}
	
	@Test(expected = RazaNoTieneBarracasException.class)
	public void siIntentoCrearUnaFabricaPeroNoHayNingunaBarracaEnEstadoConstruidoSeLanzaExcepcion(){

		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(1, 1).getCapaInferior();
		Terreno otroTerreno =  mapa.getCeldaEnFilaColumna(2, 1).getCapaInferior();
		
		terran.crearBarraca(unTerreno, mapa);
		terran.crearFabrica(otroTerreno, mapa);
			
	}
	
	@Test(expected = RazaNoTieneBarracasException.class)
	public void crearFabricaLanzaExcepcionSiSeIntenaCrearFabricaCuandoNoHayBarracas(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(1, 1).getCapaInferior();
				
		terran.crearFabrica(unTerreno, mapa);
			
	}
	
	@Test(expected = RazaNoTieneFabricasException.class)
	public void crearPuertoEstelarLanzaExcepcionSiSeIntenaCrearPuertoCuandoNoHayFabricas(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(1, 1).getCapaInferior();
		
		terran.crearPuertoEstelar(unTerreno, mapa);
			
	}
	
	@Test
	public void crearCentroRecolectorDeMineralCreaNuevoCentroMineralQueRecolecta(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.obtenerTerrenoConMinaDeMinerales();
		
		RecolectorDeMineral centroMineral = terran.crearRecolectorDeMineral(unTerreno, mapa);

		for(int turno=0; turno < centroMineral.tiempoConstruccion(); turno++) centroMineral.pasarTiempo();
		
		centroMineral.pasarTiempo();
		
		assertTrue(terran.getMinerales() > 0);
		
	}
	
	@Test
	public void alCrearCentroRecolectorDeMineralLasReservasSonCero(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.obtenerTerrenoConMinaDeMinerales();
		
		RecolectorDeMineral centroMineral = terran.crearRecolectorDeMineral(unTerreno, mapa);
		int mineralesEsperados = terran.getMinerales();
		for(int turno=0; turno < centroMineral.tiempoConstruccion(); turno++) centroMineral.pasarTiempo();
		
		assertEquals(terran.getMinerales(), mineralesEsperados);
	}
	
	@Test
	public void crearCentroRecolectorDeGasVespenoCreaNuevaRefineriaQueRecolecta(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.obtenerTerrenoConVolcanGasVespeno();
		
		RecolectorDeGasVespeno refineria = terran.crearRecolectorDeGasVespeno(unTerreno, mapa);
		
		for(int turno=0; turno < refineria.tiempoConstruccion() ;turno++) refineria.pasarTiempo();
		int cantidadEsperada = terran.getGasVespeno() + 10;
		refineria.pasarTiempo();
		
		assertEquals(terran.getGasVespeno(), cantidadEsperada);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeMineralLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinRecursos(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(1, 1).getCapaInferior();
		
		terran.crearRecolectorDeMineral(unTerreno, mapa);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeMineralLanzaExcepcionSiSeIntentaCrearSobreTerrenoConUnRecursoQueNoSeaMineral(){
		
		Mapa mapa = new Mapa(2);
		Terran terran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.obtenerTerrenoConVolcanGasVespeno();
		
		terran.crearRecolectorDeMineral(unTerreno, mapa);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeGasVespenoLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinRecursos(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = new Tierra(1,2);
		
		razaTerran.crearRecolectorDeMineral(unTerreno, mapa);
		
	}
	
	@Test(expected = NoHayRecursoException.class)
	public void crearCentroRecolectorDeGasVespenoLanzaExcepcionSiSeIntentaCrearSobreTerrenoSinGasVespeno(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = new Tierra(1,2);;
		
		razaTerran.crearRecolectorDeGasVespeno(unTerreno, mapa);
		
	}
	
	@Test
	public void crearDepositoDeSuministrosCreaNuevoDepositoDeSuministros(){

		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = new Tierra(1,2);
		DepositoDeSuministros deposito = razaTerran.crearDepositoDeSuministros(unTerreno, mapa);
		
		for(int turno=0;turno<6;turno++) deposito.pasarTiempo();
		
		assertEquals(deposito.tiempoConstruccion(),6);
		assertEquals(deposito.getVida(),100);
		
	}
	
	@Test
	public void construirDepositoDeSuministrosAumentaLaCantidadMaximaDePoblacionPermitida(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = new Tierra(1,2);
		DepositoDeSuministros deposito = razaTerran.crearDepositoDeSuministros(unTerreno, mapa);
		
		for(int turno=0;turno<6;turno++) deposito.pasarTiempo();
		razaTerran.pasarTiempo();
		assertEquals(razaTerran.getPoblacionMaxima(),10);
		
	}
	
	@Test (expected = SeleccionadoNoEsPropiedadDelJugadorException.class)
	public void crearMarineConBarracaNoPropiaLanzaBarracaNoEsDeLaRazaException() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(4);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 5).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(11, 11).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Jugador jugador2 = new Jugador("Pepe", tierra2, mapa);
		Terran razaTerran = jugador.nacion();
		Terran razaTerran2 = jugador2.nacion();
		
		razaTerran.juntarGas(1000);
		razaTerran2.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		razaTerran2.juntarMinerales(1000);
		
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno, mapa);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad unMarine = razaTerran2.crearMarine(unaBarraca, mapa);
	}
	
	@Test(expected = SeleccionadoNoEsPropiedadDelJugadorException.class)
	public void crearGoliatConFabricaNoPropiaLanzaFabricaNoEsDeLaRazaException() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(4);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 5).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(11, 11).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Jugador jugador2 = new Jugador("Pepe", tierra2, mapa);
		Terran razaTerran = jugador.nacion();
		Terran razaTerran2 = jugador2.nacion();
		
		razaTerran.juntarGas(1000);
		razaTerran2.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		razaTerran2.juntarMinerales(1000);		
		
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno otroTerreno = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno, mapa);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(otroTerreno, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		@SuppressWarnings("unused")
		Unidad unGoliat = razaTerran2.crearGoliat(fabrica, mapa);
	}
	
	@Test (expected = SeleccionadoNoEsPropiedadDelJugadorException.class)
	public void crearEspectroConPuertoEstelarNoPropioLanzaPuertoEstelarNoEsDeLaRazaException() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(4);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 5).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(11, 11).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Jugador jugador2 = new Jugador("Pepe", tierra2, mapa);
		Terran razaTerran = jugador.nacion();
		Terran razaTerran2 = jugador2.nacion();
		
		razaTerran.juntarGas(1000);
		razaTerran2.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		razaTerran2.juntarMinerales(1000);
		
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1, mapa);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3, mapa);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad unEspectro = razaTerran2.crearEspectro(puerto, mapa);
	}
	
	@Test (expected = SeleccionadoNoEsPropiedadDelJugadorException.class)
	public void crearNaveCienciaConPuertoEstelarNoPropioLanzaPuertoEstelarNoEsDeLaRazaException() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(4);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 5).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(11, 11).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Jugador jugador2 = new Jugador("Pepe", tierra2, mapa);
		Terran razaTerran = jugador.nacion();
		Terran razaTerran2 = jugador2.nacion();
		
		razaTerran.juntarGas(1000);
		razaTerran2.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		razaTerran2.juntarMinerales(1000);
		
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1, mapa);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3, mapa);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad naveCiencia = razaTerran2.crearNaveCiencia(puerto, mapa);
	}
	
	@Test (expected = SeleccionadoNoEsPropiedadDelJugadorException.class)
	public void crearNaveDeTransporteConPuertoEstelarNoPropioLanzaPuertoEstelarNoEsDeLaRazaException() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(4);
		Tierra tierra = (Tierra) mapa.getCeldaEnFilaColumna(1, 5).getCapaInferior();
		Tierra tierra2 = (Tierra) mapa.getCeldaEnFilaColumna(11, 11).getCapaInferior();
		Jugador jugador = new Jugador("Juan", tierra, mapa);
		Jugador jugador2 = new Jugador("Pepe", tierra2, mapa);
		Terran razaTerran = jugador.nacion();
		Terran razaTerran2 = jugador2.nacion();
		
		razaTerran.juntarGas(1000);
		razaTerran2.juntarGas(1000);
		razaTerran.juntarMinerales(1000);
		razaTerran2.juntarMinerales(1000);
		
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1, mapa);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3, mapa);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		@SuppressWarnings("unused")
		Unidad naveTransporte = razaTerran2.crearNaveTransporte(puerto, mapa);
		
	}

	@Test
	public void crearMarineEntrenaUnNuevoMarineYLoUbicaEnElMapa() throws SeleccionadoNoEsPropiedadDelJugadorException{
	
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno, mapa);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		
		Unidad unMarine = razaTerran.crearMarine(unaBarraca, mapa);
		
		assertEquals(unMarine.getVida(),40);
		assertEquals(unMarine.getRadioVision(),7);
		assertFalse(unMarine.estaElevado());
		
	}
	
	@Test
	public void crearGoliatEntrenaUnNuevoGoliatYLoUbicaEnElMapa() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno otroTerreno = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno, mapa);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(otroTerreno, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		Unidad unGoliat = razaTerran.crearGoliat(fabrica, mapa);
		
		assertEquals(unGoliat.getVida(),125);
		assertEquals(unGoliat.getRadioVision(),8);
		assertFalse(unGoliat.estaElevado());
		
	}
	
	@Test
	public void crearEspectroCreaUnNuevoEspectroYLoUbicaEnElMapa() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1, mapa);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3, mapa);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		Unidad unEspectro = razaTerran.crearEspectro(puerto, mapa);
		assertTrue(unEspectro.estaElevado());
		
	}
	
	@Test
	public void crearNaveCienciaCreaUnaNuevaNaveYLaUbicaEnElMapa() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1, mapa);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3, mapa);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		Unidad naveCiencia = razaTerran.crearNaveCiencia(puerto, mapa);
		assertEquals(naveCiencia.getVida(),200);
		assertEquals(naveCiencia.getRadioVision(),10);
		assertTrue(naveCiencia.estaElevado());
		
	}
	
	@Test
	public void crearNaveTransporteCreaUnaNuevaNaveYLaUbicaEnElMapa() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno terreno1 = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Terreno terreno2 = mapa.getCeldaEnFilaColumna(5,6).getCapaInferior();
		Terreno terreno3 = mapa.getCeldaEnFilaColumna(9,8).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(terreno1, mapa);
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Fabrica fabrica = razaTerran.crearFabrica(terreno2, mapa);
		for(int turno=0;turno<fabrica.tiempoConstruccion();turno++) fabrica.pasarTiempo();
		PuertoEstelar puerto = razaTerran.crearPuertoEstelar(terreno3, mapa);
		for(int turno=0;turno<puerto.tiempoConstruccion();turno++) puerto.pasarTiempo();
		
		Unidad naveTransporte = razaTerran.crearNaveTransporte(puerto, mapa);
		
		assertTrue(naveTransporte.estaElevado());
		
	}
	
	@Test(expected = CantidadDeSuministroInsuficienteException.class)
	public void siIntentoCrearUnaUnidadYNoTengoSuministrosSuificientesSeLanzaExcepcion() throws SeleccionadoNoEsPropiedadDelJugadorException{
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno, mapa);
		
		for(int turno=0;turno<12;turno++) unaBarraca.pasarTiempo();		
		for(int i=0;i<razaTerran.getPoblacionMaxima()+1;i++){
			razaTerran.crearMarine(unaBarraca, mapa);
		}
		
	}
	

	@Test
	public void pasarTiempoActualizaTodasLosEstadosDeTodosLosEdificiosCreados(){
		
		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.obtenerTerrenoConMinaDeMinerales();
		Terreno otroTerreno = mapa.getCeldaEnFilaColumna(1, 2).getCapaInferior();
		
		RecolectorDeMineral centroMineral = razaTerran.crearRecolectorDeMineral(unTerreno, mapa);
		Barraca unaBarraca = razaTerran.crearBarraca(otroTerreno, mapa);

		for(int turno=0;turno<centroMineral.tiempoConstruccion();turno++) razaTerran.pasarTiempo();
			
		assertFalse(centroMineral.estaEnConstruccion());
		assertTrue(unaBarraca.estaEnConstruccion());
		
		int tiempoRestante = unaBarraca.tiempoConstruccion() - centroMineral.tiempoConstruccion();
		
		for(int turno=0;turno<tiempoRestante;turno++) razaTerran.pasarTiempo();
		
		assertFalse(centroMineral.estaEnConstruccion());
		assertFalse(unaBarraca.estaEnConstruccion());
		
	}
	
	@Test
	public void pasarTiempoActualizaTodasLosEstadosDeTodasLasUnidadesCreadas() throws SeleccionadoNoEsPropiedadDelJugadorException{

		Mapa mapa = new Mapa(2);
		Terran razaTerran = crearRazaConRecursosSuficientesParaConstruir(mapa);
		Terreno unTerreno = mapa.getCeldaEnFilaColumna(2,3).getCapaInferior();
		Barraca unaBarraca = razaTerran.crearBarraca(unTerreno, mapa);
		
		for(int turno=0;turno<unaBarraca.tiempoConstruccion();turno++) unaBarraca.pasarTiempo();
		Unidad unMarine = razaTerran.crearMarine(unaBarraca, mapa);
		for(int turno=0;turno<unMarine.tiempoConstruccion();turno++) razaTerran.pasarTiempo();
		
		assertFalse(unMarine.estaEnConstruccion());
		
	}
	
}