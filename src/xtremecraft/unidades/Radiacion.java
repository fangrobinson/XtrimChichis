package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.edificios.Edificio;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;

public class Radiacion implements Ataque{
	
//	private ArrayList<Celda> celdasAfectadas;
	private int radioDeAlcance;
	private int danioIrradiado;
	private Mapa mapa;
	
	public Radiacion(Mapa mapa, int radioDeAlcance, int danioIrradiado){
//		ArrayList<Celda> celdasAfectadas){
		this.radioDeAlcance = radioDeAlcance;
		this.danioIrradiado = danioIrradiado;
		this.mapa = mapa;
		
	}

	public void emitirRadiacion(Unidad unidadAfectada){
		
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(unidadAfectada, this.radioDeAlcance);
		
		unidadAfectada.recibirAtaqueFisico(this.danioIrradiado);
		
		for(int posicion=0;posicion<celdasAfectadas.size();posicion++){
			Celda CeldaActual = celdasAfectadas.get(posicion);
			if(unidadAfectada.estaElevado() && CeldaActual.getCapaSuperior().estaOcupado()){
				Ubicable ubicable = celdasAfectadas.get(posicion).getUbicableEnSuperior();
				Atacable atacable = (Atacable)ubicable;
				atacable.recibirAtaqueFisico(this.danioIrradiado);
			}else if((!unidadAfectada.estaElevado()) && CeldaActual.getCapaInferior().estaOcupado()){
				Ubicable ubicable = celdasAfectadas.get(posicion).getUbicableEnInferior();
				Atacable atacable = (Atacable)ubicable;
				atacable.recibirAtaqueFisico(this.danioIrradiado);
			}
		}
	}
	
	public int getDanio(){
		
		return danioIrradiado;
		
	}

	@Override
	public void afectar(Unidad unidad) {

		unidad.recibirAtaqueFisico(this.danioIrradiado);
		
		unidad.infectarCon(this);
		
	}

	@Override
	public void afectar(Edificio edificio) {
		//No se puede atacar con Radiacion un Edificio
	}

}
