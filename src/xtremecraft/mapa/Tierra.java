package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Ubicable;

public class Tierra extends Terreno {
	
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
    
    
	public boolean agregarRecursoNatural(Recurso unRecurso) {
		
		 this.recurso = unRecurso; 
		 this.tieneRecursos = true;
		 return true;
		
	}
    
    public boolean tieneRecursosNaturales(){
    	
    	return this.tieneRecursos;
    	
    }
    
    public boolean ubicar(Ubicable ubicable){
    	
    	if((this.ocupada)||(this.tieneRecursosNaturales()&&(!ubicable.puedeUbicarseSobreRecursoNatural()))){
    		return false;
    	}
    	if (ubicable.puedeUbicarseEnTierra()){
    		this.ubicable = ubicable;
    		this.ocupada = true;
    		ubicable.actualizarUbicacion(this);
    		return true;
    	}
    	return false;
    	
    }

	public boolean estaElevado() {
		
		return false;
		
	}

	

}
