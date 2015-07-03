package xtremecraft.unidades;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;

public interface Ubicable {
	
	Coordenada getUbicacionActual();
	
	void setUbicacionInicial(Terreno unTerreno);
		
	boolean puedeUbicarseSobreRecursoNatural();
	
	boolean pertenezcoAEstaRaza(Terran terran);
	
	boolean puedeUbicarseEnTierra();
	
	boolean puedeUbicarseEnAire();
	
	boolean estaElevado();
	
	boolean estaVivo();

	void actualizarUbicacion(Terreno terreno);
	
	Jugador getJugador();
	
}
