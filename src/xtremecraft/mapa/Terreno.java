package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Unidad;


public abstract class Terreno {
	
	protected Coordenada coordenada;
	protected boolean ocupada;
	protected Unidad unidad;
	protected String tipo;
	
	public Terreno(int fila, int columna){
		this.coordenada = new Coordenada(fila,columna);
		this.ocupada = false;
	}
	
	public abstract boolean ocuparConUnidad(Unidad unidad);
	
	public abstract boolean ocuparConRecursoNatural(Recurso recursoNatural);
	
	public boolean estaOcupada(){
		
		return this.ocupada;
		
	}
		
	public Unidad getUnidadEnTerreno(){
		
		return this.unidad;
		
	}
	
	public Coordenada getCoordenada() {
		
		return this.coordenada;
		
	}
		
	public boolean ocuparTerrenoConUnidad(Unidad unidad){

		return this.ocuparConUnidad(unidad);
	}



}
