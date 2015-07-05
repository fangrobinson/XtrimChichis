package xtremecraft.vista;

import java.util.Observable;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Ubicable;

public class ObservableSeleccionado extends Observable{
	
	private Coordenada coordenadaSeleccionado;
	private String nombre;
	private String estadoImprimibleObservable;
	private Class<? extends Vista> claseVista;

	public void setCoordenada(Coordenada coordenada) {
		
		this.coordenadaSeleccionado = coordenada;
				
	}
	
	public Coordenada getCoordenadaActualSeleccionado(){
		
		return this.coordenadaSeleccionado;
		
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
		
	}
	
	public String getNombre() {
		
		return this.nombre;
		
	}
	
	public void setEstado(String estado){
		
		this.estadoImprimibleObservable = estado;
		
	}
	
//	public Ubicable devolverUbicableParaMovimiento(Celda celda){
//		
//		return null;
//		
//	}
//	
//	public Terreno devolverTerrenoDestinoParaMovimiento(Celda celda){
//		
//		return null;
//		
//	}
	
	public Terreno devolverTerrenoDestinoParaMovimiento(Celda celda){
		
		return celda.getCapaInferior();
		
	}
	
	public Ubicable devolverUbicableParaMovimiento(Celda celda){
		
		return celda.getCapaInferior().getUbicableEnTerreno();
		
	}
	
	public String getEstado() {
		
		return this.estadoImprimibleObservable ;
		
	}
	
	public String getCoordenadaImprimible() {
		
		String coordenadaImprimible = "Coordenada :" + Integer.toString(this.coordenadaSeleccionado.fila()) + "," + Integer.toString(this.coordenadaSeleccionado.columna());
		return coordenadaImprimible;
	}

	public void notificarObservador() {
		
		this.setChanged();
		this.notifyObservers();
		
	}

	public void setClaseVista(Class<? extends Vista> claseVista) {
		
		this.claseVista = claseVista;
		
	}
	
	public Class<? extends Vista> getClaseVista(){

		return this.claseVista;
		
	}
	
}
