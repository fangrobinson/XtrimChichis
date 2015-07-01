package xtremecraft.vista;

import java.util.ArrayList;
import java.util.Observable;

import xtremecraft.mapa.Coordenada;

public class ObservableSeleccionado extends Observable{
	
	private Coordenada coordenadaSeleccionado = new Coordenada(0,0);
	private String nombre;
	private String estadoImprimibleObservable;
	private ArrayList<String> acciones;
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
	
	public String getEstado() {
		
		return this.estadoImprimibleObservable ;
		
	}
	
	
	public String getCoordenadaImprimible() {
		
		String coordenadaImprimible = "Coordenada :" + Integer.toString(this.coordenadaSeleccionado.fila()) + "," + Integer.toString(this.coordenadaSeleccionado.columna());
		return coordenadaImprimible;
	}

	public void notificarObservado() {
		
		this.setChanged();
		this.notifyObservers();
		
	}

	public void setAcciones(ArrayList<String> acciones) {
		
		this.acciones = acciones;
	}
	
	public ArrayList<String> getAcciones(){
		
		return this.acciones;
	}

	public void setClaseVista(Class<? extends Vista> claseVista) {
		
		this.claseVista = claseVista;
		
	}
	
	public Class<? extends Vista> getClaseVista(){
		
		return this.claseVista;
		
	}

}
