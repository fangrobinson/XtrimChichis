package xtremecraft.mapa;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MapaTests {

	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresNegativoDaError(){
		
		new Mapa(-1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresCeroDaError(){
		
		new Mapa(0);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CrearMapaConCantJugadoresUnoDaError(){
		
		new Mapa(1);
		
	}
	
}
