package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelarTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void siLaRazaCreadoraNoPoseeFabricasYSeQuiereCrearUnPuertoEstelarSeLanzaUnaExcepcion(){
		
		Terran nuevoClanTerran=new Terran();
		Terreno unaTierra = new Tierra(5,6);
		@SuppressWarnings("unused")
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,unaTierra);
		
	}

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran nuevoClanTerran=new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,tierraDos);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,tierraTres);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),6);
		
	}
	
	@Test
	public void getActualizarUbicacionModificaCoordenadasDelEdificio(){
		
		Terran nuevoClanTerran=new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,tierraDos);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,tierraTres);
		Terreno unTerreno = new Tierra(1,3);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),6);
		
		puertoEstelar.actualizarUbicacion(unTerreno);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),1);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),3);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terran nuevoClanTerran=new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,tierraDos);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,tierraTres);
		
		assertEquals(puertoEstelar.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,tierraDos);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,tierraTres);
		int valorDanio = 30;
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),70);
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),40);
		
	}
	
	@Test
	public void crearEspectroDevuelveUnaNuevaUnidadEspectro(){
		
		Terran razaTerran = new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		Terreno tierraCuatro = new Tierra(4,4);
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(razaTerran,tierraDos);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(razaTerran,tierraTres);
		Espectro unEspectro = puertoEstelar.crearEspectro(tierraCuatro);
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
	
		Terran razaTerran = new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		Terreno tierraCuatro = new Tierra(4,4);
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(razaTerran,tierraDos);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(razaTerran,tierraTres);
		NaveCiencia nave = puertoEstelar.crearNaveCiencia(tierraCuatro);
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		Terran razaTerran = new Terran();
		Terreno tierraUno = new Tierra(1,2);
		Terreno tierraDos = new Tierra(3,4);
		Terreno tierraTres = new Tierra(5,6);
		Terreno tierraCuatro = new Tierra(4,4); 
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,tierraUno);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(razaTerran,tierraDos);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(razaTerran,tierraTres);
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte(tierraCuatro);
		
		assertEquals(naveTransporte.getVida(),150);
		
	}


}
