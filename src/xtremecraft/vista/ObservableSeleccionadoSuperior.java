package xtremecraft.vista;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Ubicable;

public class ObservableSeleccionadoSuperior extends ObservableSeleccionado {
	
	public Terreno devolverTerrenoDestinoParaMovimiento(Celda celda){
		
		return celda.getCapaSuperior();
		
	}
	
	public Ubicable devolverUbicableParaMovimiento(Celda celda){
		
		return celda.getCapaSuperior().getUbicableEnTerreno();
		
	}

}
