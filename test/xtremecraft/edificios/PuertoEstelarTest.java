package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelarTest {
	
	public PuertoEstelar construirNuevoPuertoEstelar(Terreno tierra){
		
		PuertoEstelar puerto = new PuertoEstelar(tierra);
		for(int i=0;i<puerto.tiempoConstruccion;i++){
			puerto.pasarTiempo();
		}
		return puerto;
	}

	@Test
	public void getUbicacionActualDevuelveCoordenadasDelEdificio(){
		
		int fila = 5;
		int columna = 6;
		Terreno tierra = new Tierra(fila,columna);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(tierra);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),6);
		
	}
	
	@Test
	public void puedeUbicarseSobreRecursoNaturalDevuelveFalse(){
		
		int fila = 5;
		int columna = 6;
		Terreno tierra = new Tierra(fila,columna);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(tierra);
		
		assertFalse(puertoEstelar.puedeUbicarseSobreRecursoNatural());
		
	}
	
	@Test
	public void getActualizarUbicacionModificaCoordenadasDelEdificio(){
		
		Terreno unTerreno = new Tierra(5,6);
		Terreno otroTerreno = new Tierra(1,3);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(unTerreno);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),5);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),6);
		
		puertoEstelar.actualizarUbicacion(otroTerreno);
		
		assertEquals(puertoEstelar.getUbicacionActual().fila(),1);
		assertEquals(puertoEstelar.getUbicacionActual().columna(),3);
		
	}
	
	@Test
	public void edificioSeInicializaConBarraDeVidaCompleta(){
		
		Terreno unTerreno = new Tierra(5,6);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(unTerreno);
		
		assertEquals(puertoEstelar.getVida(),100);
		
	}
	
	@Test
	public void siElEdificioRecibeDanioSuVidaDecrece(){
		
		Terreno unTerreno = new Tierra(5,6);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(unTerreno);
		int valorDanio = 30;
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),70);
		
		puertoEstelar.recibirDanio(valorDanio);
		assertEquals(puertoEstelar.getVida(),40);
		
	}
	
	@Test
	public void crearEspectroDevuelveUnaNuevaUnidadEspectro(){
		
		Terreno unTerreno = new Tierra(5,6);
		Terreno otroTerreno = new Tierra(1,3);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(unTerreno);
		Espectro unEspectro = puertoEstelar.crearEspectro(otroTerreno);
		
		assertEquals(unEspectro.getVida(),120);
		
	}
	
	@Test
	public void crearNaveCienciaDevuelveNuevaUnidadNaveCiencia(){
	
		Terreno unTerreno = new Tierra(5,6);
		Terreno otroTerreno = new Tierra(1,3);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(unTerreno);
		NaveCiencia nave = puertoEstelar.crearNaveCiencia(otroTerreno);
		
		assertEquals(nave.getVida(),200);
		
	}
	
	@Test
	public void crearNaveTransporteCreaNuevaUnidadNaveTransporte(){
		
		Terreno unTerreno = new Tierra(5,6);
		Terreno otroTerreno = new Tierra(1,3);
		PuertoEstelar puertoEstelar = construirNuevoPuertoEstelar(unTerreno);
		NaveTransporte naveTransporte = puertoEstelar.crearNaveTransporte(otroTerreno);
		
		assertEquals(naveTransporte.getVida(),150);
		
	}


}
