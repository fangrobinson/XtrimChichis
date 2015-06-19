package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelarTest {

	@Test
	public void estaEnContruccionDeberiaDevolverTrueAlCrearElPuerto(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		
		assertTrue(puertoEstelar.estaEnConstruccion());
		
	}

	@Test
	public void puertoEstelarEstaEnConstruccionDevuelveFalsePasadoElTiempoDeConstruccionDelPuerto(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		
		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		
		assertFalse(puertoEstelar.estaEnConstruccion());
		
	}
	
	@Test(expected = EdificioEnConstruccionException.class)
	public void siPuertNoEstaConstruidoYSeIntentaCrearUnidadSeLanzaExcepcion(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		
		puertoEstelar.crearEspectro();
		
	}

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),4);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),4);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		
		assertFalse(puertoEstelar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void getActualizarUbicacionModificaCoordenadasDelEdificio(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Terreno tierra4 = new Tierra(5,7);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),4);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),4);
		
		puertoEstelar.actualizarUbicacion(tierra4);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),7);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		
		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		
		assertEquals(puertoEstelar.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);
		int valorDanio = 30;
		

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		puertoEstelar.recibirDanio(valorDanio);
		
		assertEquals(puertoEstelar.getVida(),70);
		
		puertoEstelar.recibirDanio(valorDanio);
		
		assertEquals(puertoEstelar.getVida(),40);
		
	}
	
	@Test
	public void crearEspectroDevuelveUnaNuevaUnidadEspectro(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		Espectro unEspectro = puertoEstelar.crearEspectro();
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
	
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		NaveCiencia nave = puertoEstelar.crearNaveCiencia();
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		Terreno tierra1 = new Tierra(1,2);
		Terreno tierra2 = new Tierra(3,3);
		Terreno tierra3 = new Tierra(4,4);
		Barraca barraca = new Barraca(tierra1);
		Fabrica fabrica = new Fabrica(barraca,tierra2);
		PuertoEstelar puertoEstelar = new PuertoEstelar(fabrica,tierra3);

		for (int tiempo=0;tiempo<puertoEstelar.tiempoConstruccion();tiempo++) puertoEstelar.pasarTiempo();
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte();
		
		assertEquals(naveTransporte.getVida(),150);
		
	}


}
