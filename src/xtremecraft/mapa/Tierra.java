package xtremecraft.mapa;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.recursos.Recurso;
//import xtremecraft.unidades.Unidad;

public class Tierra extends Terreno {
	
	private Recurso recurso;
	private boolean tieneRecursos;
	
    public Tierra(int fila, int columna){
		
		super(fila, columna);

		this.tieneRecursos = false;
		
	}
    
    public boolean ocuparConRecursoNatural(Recurso recursoNatural){

    	if(!this.tieneRecursos){  	
    		this.recurso = recursoNatural;
    		this.tieneRecursos = true;
    		return true;
    	}

    	return false;
    	
    }
    
    public Recurso getRecurso(){
    	
    	return this.recurso;
    	
    }
    
    public boolean tieneRecursosNaturales(){
    	
    	return this.tieneRecursos;
    	
    }
    
    public boolean ubicar(Ubicable ubicable){
    	if((this.ocupada)&&(this.tieneRecursosNaturales())){
    		return false;
    	}
    	if (ubicable.puedeUbicarseEnTierra()){
    		this.unidad = ubicable;
    		this.ocupada = true;
    		return true;
    	}
    	return false;
    }

	public boolean estaElevado() {
		return false;
	}

}
