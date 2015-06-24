package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.mapa.Celda;

public class Radiacion{
	
	private ArrayList<Celda> celdasAfectadas;
	public static int radioDeAlcance = 1;
	public static int danioIrradiado = 10;
	
	public Radiacion(ArrayList<Celda> celdasAfectadas){
		
		this.celdasAfectadas = celdasAfectadas;
		
	}

	public void emitirRadiacion(Unidad unidadAfectada){
		
		//La unidad afectada sufre el doble que las que estan alrededor.
		unidadAfectada.recibirDanio(this);
		for(int posicion=0;posicion<this.celdasAfectadas.size();posicion++){
			Celda CeldaActual = this.celdasAfectadas.get(posicion);
			if(unidadAfectada.estaElevado() && CeldaActual.getCapaSuperior().estaOcupado()){
				Ubicable ubicable = this.celdasAfectadas.get(posicion).getUbicableEnSuperior();
				Atacable atacable = (Atacable)ubicable;
				atacable.recibirDanio(this);
			}else if((!unidadAfectada.estaElevado()) && CeldaActual.getCapaInferior().estaOcupado()){
				Ubicable ubicable = this.celdasAfectadas.get(posicion).getUbicableEnInferior();
				Atacable atacable = (Atacable)ubicable;
				atacable.recibirDanio(this);
			}
		}
		
	}
	
	public int getDanio(){
		
		return danioIrradiado;
		
	}
	
}
