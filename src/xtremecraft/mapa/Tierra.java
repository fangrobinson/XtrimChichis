package xtremecraft.mapa;

import java.util.ArrayList;
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
    
    public Terreno ubicar(Ubicable ubicable){
    	
    	boolean hayMineralYUbicableNoEsRecolector = this.tieneRecursos() && (!ubicable.puedeUbicarseSobreRecursoNatural());
    	if( (super.estaOcupado()) || hayMineralYUbicableNoEsRecolector || !ubicable.puedeUbicarseEnTierra() ){
    	
    		throw new NoSePudoOcuparElTerrenoException();
    	
    	}
    	this.ubicable = ubicable;
    	
    	return this;
    
    }

    public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Crear barraca");
		acciones.add("Crear fabrica");
		acciones.add("Crear puerto estelar");
		
		return acciones;
	
    }

}
