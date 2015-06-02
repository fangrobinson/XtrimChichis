package xtremecraft.mapa;

import xtremecraft.unidades.Unidad;


public class Aire extends Celda {
		
    public Aire(int X,int Y){
			
		super(X,Y);
		this.tipo = "aire";
		
	}
    
    
	public boolean ocuparCeldaConUnidad(Unidad unidad) {
		
		if(!this.ocupada){
			this.unidad=unidad;
			this.ocupada=true;
			return true;
		}
		
		return false;
	}
}

