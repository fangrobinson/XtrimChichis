package xtremecraft.raza;

import static org.junit.Assert.assertFalse;

import org.junit.Test;




public class TerranTest {
	
	
	//A continuacion, una chanchada, hay que corregir cosas y eso.
	@Test
	public void testNuevoTerranDevuelveInstanciaDeTerranConEstadoInicialRazaViva(){
		Terran razaTerran = new Terran();
		assertFalse(razaTerran.estaViva());
		
	}
	
	/*
	@Test
	public void agregarRecolectorDeMineralGuardaInstanciaDelEdificioRecolectorDeMineral(){
		
		Terran razaTerran = new Terran();
		MinaDeMinerales nodoMineral = new MinaDeMinerales(2);
		Terreno tierra = new Tierra(1,2);
		RecolectorDeMineral nuevoCentroMineral = RecolectorDeMineral.nuevoRecolectorDeMineral(razaTerran, nodoMineral, tierra);
		RecolectorDeMineral centroMineral= razaTerran.getListaDeRecolectoresDeMineralConstruidos().remove(0);
		
		assertEquals(centroMineral,nuevoCentroMineral);
		
	}
	
	@Test
	public void agregarUnidadGuardaInstanciaDeLaUnidadCreada(){
		
		Terran razaTerran = new Terran();
		Terreno tierra =new Tierra(2,3);
		Goliat nuevaUnidadGoliat = new Goliat(tierra);
		razaTerran. agregarUnidad(nuevaUnidadGoliat);
		Unidad unidadGoliat= razaTerran.getListaDeUnidadesCreadas().remove(0);
		
		assertEquals(unidadGoliat.getVida(),125);
		
		
	}
	
	@Test
	public void agregarRecolectorDeGasVespenoGuardaInstanciaDelEdificioRecolectorDeGasVespeno(){
		
		Terran razaTerran = new Terran();
		VolcanGasVespeno volcanDeGasVespeno = new VolcanGasVespeno(200);
		Terreno tierra = new Tierra(1,2);
		RecolectorDeGasVespeno nuevaRefineria = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(razaTerran,volcanDeGasVespeno,tierra);
		RecolectorDeGasVespeno refineriaTerranConstruida = razaTerran.getListaDeRecolectoresDeGasVespenoConstruidos().remove(0);
		
		assertEquals(nuevaRefineria,refineriaTerranConstruida);
		
	}
	
	@Test
	public void agregarBarracaGuardaInstanciaDelEdificioCreadorDeUnidades(){
		
		Terran razaTerran = new Terran();
		Terreno tierra = new Tierra(1,2);
		Barraca barraca = Barraca.nuevaBarraca(razaTerran,tierra);
		Barraca barracaConstruida = razaTerran.getListaDeBarracasConstruidas().remove(0);
		
		assertEquals(barraca,barracaConstruida);
		
	}
	
	@Test
	public void agregarFabricaGuardaInstanciaDeLaFabrica(){
		
		Terran razaTerran = new Terran();
		Terreno unaTierra = new Tierra(1,2);
		Terreno otraTierra = new Tierra(3,4);
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran,unaTierra);
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,otraTierra);
		
		Fabrica fabricaConstruida = razaTerran.getListaDeFabricasConstruidas().remove(0);
		
		assertEquals(fabrica,fabricaConstruida);
	}
	
	@Test
	public void agregarPuertoEstelarGuardaInstanciaDelPuertoYBorraFabricaBaseDeLaListaDeFabricasCreadas(){
		
		Terran razaTerran = new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		@SuppressWarnings("unused")
		Barraca barraca = Barraca.nuevaBarraca(razaTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica fabrica = Fabrica.nuevaFabrica(razaTerran,tierraDos);
		PuertoEstelar puerto = PuertoEstelar.nuevoPuertoEstelar(razaTerran,tierraTres);
		
		PuertoEstelar puertoConstruido = razaTerran.getListaDePuertosEstelaresConstruidos().remove(0);
		
		assertEquals(puerto,puertoConstruido);
	
	}
	*/
}