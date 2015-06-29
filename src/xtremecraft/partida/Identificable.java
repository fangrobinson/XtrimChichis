package xtremecraft.partida;

import java.util.ArrayList;


public interface Identificable {

	int getJugador();
	
	String getEstadoImprimible();
	
	ArrayList<String> devolverAcciones();
	
}
