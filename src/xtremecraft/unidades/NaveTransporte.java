package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;

public class NaveTransporte extends UnidadAerea {
	
	ArrayList<Unidad> unidadesTransportadas;
	static int capacidadMaxima = 8;
	
	public NaveTransporte() {
		
		super();
		this.vitalidad = new BarraDeVitalidad(150);
		this.danio = new Danio (0,0);
		this.vision = 8;
		this.tiempoConstruccion = 5;
		this.suministro = 2;
		this.unidadesTransportadas = new ArrayList<Unidad>();
		
	}
	
	//TODO: actualizar coordenadas de la unidad en el mapa.
	public boolean transportarNuevaUnidad(Unidad unaUnidad){
		
		if(unidadesTransportadas.size() < capacidadMaxima){
			unidadesTransportadas.add(unaUnidad);
			return true;
		}
		return false;
		
	}
	
	public void actualizarUbicacion(Terreno terreno){
		
		this.estaElevado = terreno.estaElevado();
		Coordenada nuevaUbicacion = new Coordenada(terreno.getCoordenada().fila(),terreno.getCoordenada().columna());
		this.coordenadas = nuevaUbicacion;
		for(int pos=0;pos<this.unidadesTransportadas.size();pos++){
			this.unidadesTransportadas.get(pos).actualizarUbicacion(terreno);
		}
	 }

}
