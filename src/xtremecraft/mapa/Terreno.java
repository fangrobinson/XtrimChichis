package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Ubicable;

public abstract class Terreno{
	
	protected Coordenada coordenada;
	protected Ubicable ubicable;
	protected Recurso recurso;
	protected boolean tieneRecursos;
	
	public Terreno(int fila, int columna){
		
		this.coordenada = new Coordenada(fila,columna);
		this.ubicable = null;
		this.tieneRecursos = false;
		
	}
	
	public boolean estaOcupado(){
		
		return !(this.ubicable == null);
		
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

	public abstract Terreno ubicar(Ubicable ubicable);

	public abstract boolean estaElevado();

	public abstract Recurso getRecurso();

	public abstract boolean agregarRecursoNatural(Recurso unRecurso);

	public void desocupar() {
		
		this.ubicable = null;
		
	}

	public Coordenada getUbicacionActual() {
		
		return this.coordenada;
		
	}

}
