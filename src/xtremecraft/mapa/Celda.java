package xtremecraft.mapa;

import xtremecraft.unidades.Unidad;


public abstract class Celda {
	
	protected Coordenada coordenada;
	protected boolean ocupada;
	protected Unidad unidad;
	protected String tipo;
	
	public Celda(int fila, int columna){
		this.coordenada = new Coordenada(fila,columna);
		this.ocupada = false;
	}
	
	public abstract boolean ocuparConUnidad(Unidad unidad);
	
	public boolean estaOcupada(){
		
		return this.ocupada;
		
	}
		
	public Unidad getUnidadEnCelda(){
		
		return this.unidad;
		
	}
	
	public Coordenada getCoordenada() {
		
		return this.coordenada;
		
	}
		
	public boolean ocuparCeldaConUnidad(Unidad unidad){

		return this.ocuparConUnidad(unidad);
	}

	public double calcularDistancia(Celda celda2) {
		
		Coordenada coordenadaCelda1 = this.getCoordenada();
		Coordenada coordenadaCelda2 = celda2.getCoordenada();
		
		return coordenadaCelda1.distancia(coordenadaCelda2);
		
	}

	

}
