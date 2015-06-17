package xtremecraft.mapa;

import xtremecraft.recursos.Recurso;
//import xtremecraft.unidades.Unidad;
import xtremecraft.unidades.Ubicable;


public class Aire extends Terreno {
		
    public Aire(int fila, int columna){
    	
		super(fila, columna);
		
	}
    
	
	public boolean ocuparConRecursoNatural(Recurso recursoNatural){
		
		return false;
		
	}

    public boolean ubicar(Ubicable ubicable){
    	if(this.ocupada){
    		return false;
    	}
    	if (ubicable.puedeUbicarseEnAire()){
    		this.ubicable = ubicable;
    		this.ocupada = true;
    		ubicable.elevar();
    		return true;
    	}
    	return false;
    }

	public boolean estaElevado() {
		
		return true;
		
	}


	
	public boolean agregarRecursoNatural(Recurso unRecurso) {
		
		return false;
		
	}

	public Recurso getRecurso() {
		
		throw new IllegalArgumentException("No hay recursos en terrenos aereos.");
		
	}


}

