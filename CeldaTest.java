package algoritmos_3.starcraft.mapa;

import algoritmos_3.starcraft.mapa.Celda;
import junit.framework.TestCase;

public class CeldaTest extends TestCase {
	
	public void nuevaCeldaCreaCeldaConEstadoNoOcupado(){
		
		Celda celda=new Celda(1,1);
		assert(celda.estaOcupada());
		
	}
	
	public void celdaGetAbcisaDevuelveCoordenadaHorizontalDeLaCelda(){
		
		Celda celda=new Celda(1,2);
		assertEquals(celda.getAbcisa(),1);
		
	}


	public void celdaGetOrdenadaDevuelveCoordenadaVerticalDeLaCelda(){
		
		Celda celda=new Celda(1,2);
		assertEquals(celda.getOrdenada(),2);
		
	}
	
	public void celdaEstaOcupadaDeberiaDevolverFalseLuegoDeOcuparCelda(){
		
		Celda celda=new Celda(1,4);
		celda.Ocupar();
		assertFalse(celda.estaOcupada());
		
	}

}
