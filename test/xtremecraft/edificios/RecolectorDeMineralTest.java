package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class RecolectorDeMineralTest {
	
	@Test
	public void testNuevoRecolectorDeMineralIniciaConReservaNula(){
		
		Terran nuevoClanTerran=new Terran();
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(4);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,tierraUno);
		
		assertEquals(centroMineralTerran.getReservas(),0);
		
	}
	
	@Test
	public void testAumentarReservasAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terran nuevoClanTerran=new Terran();
		Terreno tierraUno = new Tierra(1,2);
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral, tierraUno);
		centroMineralTerran.aumentarReservasEnTurno();
		
		assertEquals(centroMineralTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terran nuevoClanTerran=new Terran();
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,tierraUno);
		
		assertEquals(centroMineralTerran.getUbicacionActual().columna(),2);
		assertEquals(centroMineralTerran.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,tierraUno);
		
		assertEquals(centroMineralTerran.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		MinaDeMinerales nuevoNodoMineral=new MinaDeMinerales(20);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeMineral centroMineralTerran=Recolector.nuevoRecolectorDeMineral(nuevoClanTerran,nuevoNodoMineral,tierraUno);
		int valorDanio = 30;
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),70);
		
		centroMineralTerran.recibirDanio(valorDanio);
		assertEquals(centroMineralTerran.getVida(),40);
		
	}


}
