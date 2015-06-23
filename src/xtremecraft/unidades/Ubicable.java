package xtremecraft.unidades;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public interface Ubicable {
	
	Coordenada getUbicacionActual();
	
	void actualizarUbicacion(Terreno unTerreno);
		
	boolean puedeUbicarseSobreRecursoNatural();
	
	boolean pertenezcoAEstaRaza(Terran terran);
	
	boolean puedeUbicarseEnTierra();
	
	boolean puedeUbicarseEnAire();
	
	boolean estaElevado();
	
}
