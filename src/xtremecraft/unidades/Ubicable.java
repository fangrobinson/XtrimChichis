package xtremecraft.unidades;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public interface Ubicable {
	
	public Coordenada getUbicacionActual();
	
	public void actualizarUbicacion(Terreno unTerreno);
		
	public boolean puedeUbicarseSobreRecursoNatural();
	
	public boolean pertenezcoAEstaRaza(Terran terran);
	
	public boolean puedeUbicarseEnTierra();
	
	public boolean puedeUbicarseEnAire();
	
	public boolean estaElevado();
	
}
