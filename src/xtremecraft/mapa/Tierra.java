package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Ubicable;

public class Tierra extends Terreno {
	
    public Tierra(int fila, int columna){
		
		super(fila, columna);
		
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

	public boolean estaElevado() {
		
		return false;
		
	}

	
	public boolean tieneRecursos() {

		return this.tieneRecursos;
		
	}
	
    public boolean ubicar(Ubicable ubicable){
    	
    	boolean hayMineralYUbicableNoEsRecolector = this.tieneRecursos()&& (!ubicable.puedeUbicarseSobreRecursoNatural());
    	if( (this.ocupada) || hayMineralYUbicableNoEsRecolector ){
    		return false;
    	}
    	if (ubicable.puedeUbicarseEnTierra()){
    		this.ubicable = ubicable;
    		this.ocupada = true;
    		return true;
    	}
    	return false;
    	
    }

}
