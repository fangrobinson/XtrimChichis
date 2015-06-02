package xtremecraft.mapa;

import xtremecraft.unidades.Unidad;

public abstract class Celda {
	
	protected int coordenadaX;
	protected int coordenadaY;
	protected boolean ocupada;
	protected Unidad unidad;
	protected String tipo;
	
	public Celda(int fila, int columna){
		this.coordenadaX = fila;
		this.coordenadaY = columna;
		this.ocupada = false;
	}
	
	public boolean estaOcupada(){
		
		return this.ocupada;
		
	}
	
	public int getX(){
		
		return this.coordenadaX;
		
	}

	public int getY() {
		
		return this.coordenadaY;
		
	}


	
	public Unidad getUnidadEnCelda(){
		
		return this.unidad;
		
	}
	//NO ME DEJA HACER ESTO...
	/* public boolean ocuparCeldaConUnidad(Tierra tierra,Unidad unidad) {
		 
		 return tierra.ocuparCeldaConUnidad(unidad);
		 
	 }
	 
	 public boolean ocuparCeldaConUnidad(Aire aire,Unidad unidad) {
		 
		 return aire.ocuparCeldaConUnidad(unidad);
		 
	 }*/ 
	 
	//FEOOOOOOO!!!!!!!!!!!!!!!!!!:
	public boolean ocuparCeldaConUnidad(Unidad unidad){
		if(this.tipo=="tierra"){
			Tierra celdaTierra= (Tierra)this;
			return celdaTierra.ocuparCeldaConUnidad(unidad);
		}
		Aire celdaAire =(Aire)this;
		return celdaAire.ocuparCeldaConUnidad(unidad);
		
	}

	
	

}
