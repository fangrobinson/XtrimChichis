package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Unidad;

public class Tierra extends Celda {
	
	private Recurso recurso;
	private boolean tieneRecursos;
	
    public Tierra(int X,int Y){
		
		super(X,Y);
		this.tipo = "tierra";
		this.tieneRecursos=false;
		
	}
    
    public boolean ocuparTierraConRecursoNatural(Recurso recursoNatural){
    	
    	//NOTA:no verifico que este ocupada porque los recursos se agregan a las celdas a la hora de crear el mapa.
    	//NOTA:no cambio estado de celda a ocupada para permitir la creacion de edificios en esas celdas.
    	if(!this.tieneRecursos){  	
    		this.recurso=recursoNatural;
    		this.tieneRecursos=true;
    		return true;
    	}

    	return false;
    	
    }
    
    public Recurso getRecurso(){
    	
    	return this.recurso;
    	
    }
    
    public boolean tierraTieneRecursosNaturales(){
    	
    	return this.tieneRecursos;
    	
    }
    
    public boolean ocuparConUnidad(Unidad unidad) {
		
		if((!this.ocupada)&&(!this.tierraTieneRecursosNaturales())){
			this.unidad=unidad;
			this.ocupada=true;
			return true;
		}
		
		return false;
	}

}
