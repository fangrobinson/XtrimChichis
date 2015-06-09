package xtremecraft.mapa;

import xtremecraft.interfaces.Ubicable;

public class Celda {
	
	private Terreno inferior;
	private Terreno superior;
	
    public Celda(Terreno inferior, Terreno superior){
		this.inferior = inferior;
		this.superior = superior;
	}
    
    public Terreno getCapaInferior(){
    	return this.inferior;
    }
    
    public Terreno getCapaSuperior(){
    	return this.superior;
    }
    
    public boolean ubicarCapaSuperior(Ubicable ubicable){
    	return this.superior.ubicar(ubicable);
    }
    
    public boolean ubicarCapaInferior(Ubicable ubicable){
    	return this.inferior.ubicar(ubicable);
    }
}
