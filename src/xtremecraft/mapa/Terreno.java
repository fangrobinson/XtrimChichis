package xtremecraft.mapa;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Unidad;

public abstract class Terreno {
	
	/*protected Coordenada coordenada;*/
	protected boolean ocupada;
	protected Ubicable unidad;
	
	public Terreno(){
		//this.coordenada = new Coordenada(fila,columna);
		this.ocupada = false;
	}
	
	public abstract boolean ocuparConUnidad(Unidad unidad);
	
	public abstract boolean ocuparConRecursoNatural(Recurso recursoNatural);
	
	public boolean estaOcupada(){
		
		return this.ocupada;
		
	}
		
	public Ubicable getUnidadEnTerreno(){
		
		return this.unidad;
		
	}
	
	/*public Coordenada getCoordenada() {
		
		return this.coordenada;
		
	}*/
		
	//Para que esta esto???
	public boolean ocuparTerrenoConUnidad(Unidad unidad){

		return this.ocuparConUnidad(unidad);
	}

	public abstract boolean ubicar(Ubicable ubicable);

}
