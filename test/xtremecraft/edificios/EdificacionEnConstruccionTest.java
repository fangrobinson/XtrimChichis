package xtremecraft.edificios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;

public class EdificacionEnConstruccionTest {
	
	/*@Test
	public void creoBarracaEnConstruccionYSuVidaEsCero(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, tierra);
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		int vida = edif.getVida();
		
		assertEquals(vida, 0);
		
	}
	
	@Test
	public void creoBarracaEnConstruccionNoEstaLista(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, tierra);
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
	
		assertFalse(edif.estaListo());
		
	}
	
	@Test
	public void creoBarracaEnConstruccionSuVidaAumentaAlPasarElTiempo(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, tierra);
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		edif.pasarTiempo();
		int vida = edif.getVida();
		
		assertTrue(vida > 0);
		
	}
	
	@Test
	public void alPasarElTiempoNecesarioBarracaEstaLista(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, tierra);
		int tiempo = unaBarraca.tiempoConstruccion();
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		for(int i = 0; i <= tiempo ; i = i + 1){
			edif.pasarTiempo();
		}
		
		assertTrue(edif.estaListo());
		
	}
	
	@Test
	public void alPasarTiempoInsuficienteBarracaNoEstaLista(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, tierra);
		int tiempo = unaBarraca.tiempoConstruccion();
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		for(int i = 0; i < tiempo - 1 ; i = i + 1){
			edif.pasarTiempo();
		}
		
		assertFalse(edif.estaListo());
		
	}
	
	@Test
	public void alPasarElTiempoNecesarioPuedoObtenerLaBarraca(){
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran,tierra);
		int tiempo = unaBarraca.tiempoConstruccion();
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		for(int i = 0; i <= tiempo ; i = i + 1){
			edif.pasarTiempo();
		}
		Edificio laBarraca = null;
		try{
			laBarraca = edif.terminarConstruccion();
		} catch (NoEstaListoException e) {
			e.printStackTrace();
		}
		assertEquals(unaBarraca, laBarraca);
		
	}
	
	@Test(expected = NoEstaListoException.class)
	public void alPasarTiempoInsuficienteBarracaTerminarConstruccionLanzaExcepcion() throws NoEstaListoException{
		
		int fila = 1;
		int columna = 2;
		Terran nuevoClanTerran=new Terran();
		Terreno tierra = new Tierra(fila,columna);
		Edificio unaBarraca = Barraca.nuevaBarraca(nuevoClanTerran, tierra);
		int tiempo = unaBarraca.tiempoConstruccion();
		EdificacionEnConstruccion edif = new EdificacionEnConstruccion(unaBarraca);
		
		for(int i = 0; i < tiempo - 1 ; i = i + 1){
			edif.pasarTiempo();
		}
		@SuppressWarnings("unused")
		Edificio laBarraca = edif.terminarConstruccion();
	}
	*/
}
