package xtremecraft.mapa;

import java.util.Observable;

import xtremecraft.partida.Identificable;
import xtremecraft.recursos.IdentificableNoTieneJugadorException;
import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Ubicable;

public abstract class Terreno extends Observable implements Identificable{
	
	protected Coordenada coordenada;
	protected Ubicable ubicable;
	protected Recurso recurso;
	protected boolean tieneRecursos;
	protected boolean estaOcupado;
	
	public Terreno(int fila, int columna){
		
		this.coordenada = new Coordenada(fila,columna);
		this.estaOcupado = false;
		this.tieneRecursos = false;
		
	}
	
	public boolean estaOcupado(){
		
		return this.estaOcupado;
		
	}
		
	public Ubicable getUbicableEnTerreno(){
		
		return this.ubicable;
		
	}
	
	private String generarEstadoImprimible(){
		
		return "No ocupado"; 
		
	}
	

    public String getEstadoImprimible(){
    	
    	return this.generarEstadoImprimible();
    	
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
		
		this.estaOcupado = false;
		
	}

	public Coordenada getUbicacionActual() {
		
		return this.coordenada;
		
	}
	
	@Override
	public int getJugador() {
		
		throw new IdentificableNoTieneJugadorException();
		
	}

}
