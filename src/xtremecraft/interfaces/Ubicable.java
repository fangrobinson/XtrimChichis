package xtremecraft.interfaces;

import xtremecraft.mapa.Coordenada;

public interface Ubicable {
	
	public Coordenada getUbicacionActual();
	
	public void actualizarUbicacion();
	
	public boolean puedeUbicarseEnTierra(int columna, int fila);
	
	public boolean puedeUbicarseEnAire();

}
