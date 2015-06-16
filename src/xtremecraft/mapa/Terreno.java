package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Ubicable;

public abstract class Terreno {
	
	protected Coordenada coordenada;
	protected boolean ocupada;
	protected Ubicable unidad;
	protected Recurso recurso;
	
	public Terreno(int fila, int columna){
		this.coordenada = new Coordenada(fila,columna);
		this.ocupada = false;
	}
	
	public boolean estaOcupada(){
		
		return this.ocupada;
		
	}
		
	public Ubicable getUnidadEnTerreno(){
		
		return this.unidad;
		
	}
	
	public Coordenada getCoordenada() {
		
		return this.coordenada;
		
	}
	
	public int fila(){
		return this.coordenada.fila();
	}
	
	public int columna(){
		return this.coordenada.columna();
	}

	public abstract boolean ubicar(Ubicable ubicable);

	public abstract boolean estaElevado();

	public abstract Recurso getRecurso();

	public abstract boolean agregarRecursoNatural(Recurso unRecurso);


}
