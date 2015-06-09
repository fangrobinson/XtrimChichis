package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Unidad;


public class Aire extends Terreno {
		
    public Aire(){
		super();
	}
    
    
	public boolean ocuparConUnidad(Unidad unidad) {
		
		if(!this.ocupada){
			this.unidad=unidad;
			this.ocupada=true;
			return true;
		}
		
		return false;
	}
	
	public boolean ocuparConRecursoNatural(Recurso recursoNatural){
		return false;
	}
}

