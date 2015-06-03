package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Unidad;


public class Aire extends Celda {
		
    public Aire(int X,int Y){
			
		super(X,Y);
		this.tipo = "aire";
		
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

