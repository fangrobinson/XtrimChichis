package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.VolcanGasVespeno;

public class RecolectorDeGasVespenoTest {
	
	@Test
	public void testNuevaRecolectorDeGasVespenoIniciaConReservaNula(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(100);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,tierraUno);
		
		assertEquals(refineriaTerran.getReservas(),0);
		
	}
	
	@Test
	public void testAumentarReservasAumentaLaCantidadDeReservasEnElEdificioRecolector(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(100);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,tierraUno);
		refineriaTerran.aumentarReservasEnTurno();
		
		assertEquals(refineriaTerran.getReservas(),10);
		
		
	}
	
	@Test
	public void edificioGetUbicacionActualDevuelveCoordenadasDelEdificioEnElMapa(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(100);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,tierraUno);
		
		assertEquals(refineriaTerran.getUbicacionActual().columna(),2);
		assertEquals(refineriaTerran.getUbicacionActual().fila(),1);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(260);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,tierraUno);
		
		assertEquals(refineriaTerran.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		VolcanGasVespeno unVolcanDeGasVespeno=new VolcanGasVespeno(260);
		Terreno tierraUno = new Tierra(1,2);
		RecolectorDeGasVespeno refineriaTerran = RecolectorDeGasVespeno.nuevoRecolectorDeGasVespeno(nuevoClanTerran,unVolcanDeGasVespeno,tierraUno);
		int valorDanio = 30;
		
		refineriaTerran.recibirDanio(valorDanio);
		assertEquals(refineriaTerran.getVida(),70);
		
		refineriaTerran.recibirDanio(valorDanio);
		assertEquals(refineriaTerran.getVida(),40);
		
	}


}
