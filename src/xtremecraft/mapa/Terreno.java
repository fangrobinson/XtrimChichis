package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Ubicable;

public abstract class Terreno{
	
	protected Coordenada coordenada;
	protected boolean ocupada;
	protected Ubicable ubicable;
	protected Recurso recurso;
	protected boolean tieneRecursos;
	
	public Terreno(int fila, int columna){
		
		this.coordenada = new Coordenada(fila,columna);
		this.ocupada = false;
		this.tieneRecursos = false;
		
	}
	
	public boolean estaOcupado(){
		
		return this.ocupada;
		
	}
		
	public Ubicable getUbicableEnTerreno(){
		
		return this.ubicable;
		
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
	
	public boolean tieneRecursos() {
		
		return this.tieneRecursos;
		
	}

	public abstract boolean ubicar(Ubicable ubicable);

	public abstract boolean estaElevado();

	public abstract Recurso getRecurso();

	public abstract boolean agregarRecursoNatural(Recurso unRecurso);

	public boolean tieneMineral() {
		
		if(!this.tieneRecursos()) return false;
		return this.recurso.tieneMineral();
		
	}

	public boolean tieneGasVespeno() {
		
		if(!this.tieneRecursos()) return false;
		return this.recurso.tieneGasVespeno();
		
	}
}
