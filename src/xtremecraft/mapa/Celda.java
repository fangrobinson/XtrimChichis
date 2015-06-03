package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
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
	
	public abstract boolean ocuparConUnidad(Unidad unidad);
	
	public abstract boolean ocuparConRecursoNatural(Recurso recursoNatural);
	
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
		
	public boolean ocuparCeldaConUnidad(Unidad unidad){

		return this.ocuparConUnidad(unidad);
	}

}
