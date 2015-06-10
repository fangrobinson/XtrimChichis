package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
//import xtremecraft.unidades.Unidad;
import xtremecraft.unidades.Ubicable;


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
    		ubicable.actualizarUbicacion(this);
    		return true;
    	}
    	return false;
    }

	@Override
	public boolean estaElevado() {
		return true;
	}
}

