package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;

public class Radiacion{
	
	private Mapa mapa;
	public static int radioDeAlcance = 1;
	public static int danioIrradiado = 10;
	
	public Radiacion(Mapa mapa){
		
		this.mapa = mapa;
		
	}

	public void emitirRadiacion(Unidad unidadAfectada){
		
		ArrayList<Celda> celdasEnRadio = this.mapa.obtenerCeldasEnRadio(unidadAfectada, radioDeAlcance);
		//La unidad afectada sufre el doble que las que estan alrededor.
		unidadAfectada.recibirDanio(this);
		for(int posicion=0;posicion<celdasEnRadio.size();posicion++){
			Celda CeldaActual = celdasEnRadio.get(posicion);
			if(unidadAfectada.estaElevado() && CeldaActual.getCapaSuperior().estaOcupado()){
				Ubicable ubicable = celdasEnRadio.get(posicion).getUbicableEnSuperior();
				Atacable atacable = (Atacable)ubicable;
				atacable.recibirDanio(this);
			}else if((!unidadAfectada.estaElevado()) && CeldaActual.getCapaInferior().estaOcupado()){
				Ubicable ubicable = celdasEnRadio.get(posicion).getUbicableEnInferior();
				Atacable atacable = (Atacable)ubicable;
				atacable.recibirDanio(this);
			}
		}
		
	}
	
	public int getDanio(){
		
		return danioIrradiado;
		
	}
	
}
