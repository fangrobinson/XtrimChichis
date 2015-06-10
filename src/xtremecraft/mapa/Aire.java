package xtremecraft.mapa;

import xtremecraft.interfaces.Ubicable;
import xtremecraft.recursos.Recurso;
//import xtremecraft.unidades.Unidad;


public class Aire extends Terreno {
		
    public Aire(int fila, int columna){
		super(fila, columna);
	}
    
    /*
	public boolean ocuparConUnidad(Unidad unidad) {
		
		if(!this.ocupada){
			this.unidad=unidad;
			this.ocupada=true;
			return true;
		}
		
		return false;
	}*/
	
	public boolean ocuparConRecursoNatural(Recurso recursoNatural){
		return false;
	}

    public boolean ubicar(Ubicable ubicable){
    	if(this.ocupada){
    		return false;
    	}
    	if (ubicable.puedeUbicarseEnAire()){
    		this.unidad = ubicable;
    		this.ocupada = true;
    		return true;
    	}
    	return false;
    }

	@Override
	public boolean esElevado() {
		return true;
	}
}

