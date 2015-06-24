package xtremecraft.mapa;

//import xtremecraft.partida.ElAtacanteNoEsDelJugadorException;
//import xtremecraft.partida.NoSeEncontroNingunJugadorConTurnoAsignadoException;
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
	
//    public boolean ubicar(Ubicable ubicable){
//    	
//    	boolean hayMineralYUbicableNoEsRecolector = this.tieneRecursos()&& (!ubicable.puedeUbicarseSobreRecursoNatural());
//    	if( (super.estaOcupado()) || hayMineralYUbicableNoEsRecolector ){
//    		return false;
//    	}
//    	if (ubicable.puedeUbicarseEnTierra()){
//    		this.ubicable = ubicable;
//    		return true;
//    	}
//    	return false;
//    	
//    }
    
    public Terreno ubicar(Ubicable ubicable){
    	
    	boolean hayMineralYUbicableNoEsRecolector = this.tieneRecursos() && (!ubicable.puedeUbicarseSobreRecursoNatural());
    	/*
    	if( (super.estaOcupado())){
    		throw new ElAtacanteNoEsDelJugadorException();
    	}
    	if(hayMineralYUbicableNoEsRecolector){
    		throw new NoSeEncontroNingunJugadorConTurnoAsignadoException();
    	}
    	if(!ubicable.puedeUbicarseEnTierra() ){
    		throw new NoSePudoOcuparElTerrenoException();
    	}*/
    	if( (super.estaOcupado()) || hayMineralYUbicableNoEsRecolector || !ubicable.puedeUbicarseEnTierra() ){
    		throw new NoSePudoOcuparElTerrenoException();
    	}
    	this.ubicable = ubicable;
    	return this;
    
    }
    
}
