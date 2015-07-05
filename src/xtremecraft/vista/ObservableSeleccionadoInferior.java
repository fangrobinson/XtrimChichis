package xtremecraft.vista;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Ubicable;

public class ObservableSeleccionadoInferior extends ObservableSeleccionado {

	public Terreno devolverTerrenoDestinoParaMovimiento(Celda celda){
		
		return celda.getCapaInferior();
		
	}
	
	public Ubicable devolverUbicableParaMovimiento(Celda celda){
		
		return celda.getCapaInferior().getUbicableEnTerreno();
		
	}
		
}
