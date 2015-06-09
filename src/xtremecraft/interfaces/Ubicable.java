package xtremecraft.interfaces;

import xtremecraft.mapa.Coordenada;

public interface Ubicable {
	
	public Coordenada getUbicacionActual();
	
	public void actualizarUbicacion(int columna, int fila);
	
	public boolean puedeUbicarseEnTierra();
	
	public boolean puedeUbicarseEnAire();

}
