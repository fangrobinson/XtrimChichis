package xtremecraft.mapa;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.recursos.Recurso;
//import xtremecraft.unidades.Unidad;

public class Tierra extends Terreno {
	
	private Recurso recurso;
	private boolean tieneRecursos;
	
    public Tierra(){
		
		super();

		this.tieneRecursos = false;
		
	}
    
    public boolean ocuparConRecursoNatural(Recurso recursoNatural){
    	
    	//NOTA: no verifico que este ocupada porque los recursos se agregan a las celdas a la hora de crear el mapa.
    	//NOTA: no cambio estado de celda a ocupada para permitir la creacion de edificios en esas celdas.
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
    /*
    public boolean ocuparConUnidad(Unidad unidad) {
		
		if((!this.ocupada)&&(!this.tieneRecursosNaturales())){
			this.unidad = unidad;
			this.ocupada = true;
			return true;
		}
		
		return false;
	}*/
    
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

}
