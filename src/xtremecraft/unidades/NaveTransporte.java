package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
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
	
	public boolean transportarNuevaUnidad(Unidad unidad){

		if(unidadesTransportadas.size() < capacidadMaxima){
			unidadesTransportadas.add(unidad);
			unidad.actualizarUbicacion(this);
			return true;
		}
		return false;
		
	}
	
	public void actualizarUbicacion(Terreno terreno){
		
		if(!this.estaUbicada){
			this.terrenoActual = terreno;
			this.estaUbicada = true;
		}
		else{
			if(!this.puedoVer(terreno.getCoordenada())) throw new UbicacionNoValidaException();
			this.terrenoActual.desocupar();
			this.terrenoActual = terreno;
			this.terrenoActual.ubicar(this);
			for(int pos=0;pos<this.unidadesTransportadas.size();pos++){
				this.unidadesTransportadas.get(pos).actualizarUbicacion(this);
			}
		}
		
	 }
	
	public boolean bajarUnidad(Mapa mapa,Unidad unaUnidad){
		
		ArrayList<Celda> celdasAdyacentes = mapa.obtenerCeldasAdyacentesAlUbicable(this);
		for(int posicion=0;posicion<celdasAdyacentes.size();posicion++){
			Celda celdaActual = celdasAdyacentes.get(posicion);
			if(mapa.ubicar(unaUnidad,celdaActual)) return true;
		}
		return false;
	}
	
	public ArrayList<Unidad> getUnidadesTransportadas(){
		
		return this.unidadesTransportadas;
		
	}

}
