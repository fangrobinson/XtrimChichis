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
		@SuppressWarnings("unused")
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,5,6);
		
	}

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		Terran nuevoClanTerran=new Terran();
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,1,2);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,3,4);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,5,6);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),6);
		
	}
	
	@Test
	public void getActualizarUbicacionModificaCoordenadasDelEdificio(){
		
		Terran nuevoClanTerran=new Terran();
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,1,2);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,3,4);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,5,6);
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
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,1,2);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,3,4);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,5,6);
		
		assertEquals(puertoEstelar.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terran nuevoClanTerran=new Terran();
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,1,2);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(nuevoClanTerran,3,4);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(nuevoClanTerran,5,6);
		int valorDanio = 30;
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),70);
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),40);
		
	}
	
	@Test
	public void crearEspectroDevuelveUnaNuevaUnidadEspectro(){
		
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,1,2);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(razaTerran,3,4);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(razaTerran,5,6);
		Espectro unEspectro = puertoEstelar.crearEspectro(unTerreno);
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
	
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,1,2);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(razaTerran,3,4);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(razaTerran,5,6);
		NaveCiencia nave = puertoEstelar.crearNaveCiencia(unTerreno);
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		Terran razaTerran = new Terran();
		Tierra unTerreno = new Tierra(4,4); 
		@SuppressWarnings("unused")
		Barraca unaBarraca = Barraca.nuevaBarraca(razaTerran,1,2);
		@SuppressWarnings("unused")
		Fabrica unaFabrica = Fabrica.nuevaFabrica(razaTerran,3,4);
		PuertoEstelar puertoEstelar = PuertoEstelar.nuevoPuertoEstelar(razaTerran,5,6);
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte(unTerreno);
		
		assertEquals(naveTransporte.getVida(),150);
		
	}


}
