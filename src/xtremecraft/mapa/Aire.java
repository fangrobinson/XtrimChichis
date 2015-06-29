package xtremecraft.mapa;

import xtremecraft.partida.Jugador;
import xtremecraft.recursos.Recurso;
import xtremecraft.unidades.Ubicable;


public class Aire extends Terreno {
		
    public Aire(int fila, int columna){
    	
		super(fila, columna);
		
	}
    
	public boolean ocuparConRecursoNatural(Recurso recursoNatural){
		
		return false;
		
	}

    public Terreno ubicar(Ubicable ubicable){
    	if(super.estaOcupado() || !ubicable.puedeUbicarseEnAire()){
    		throw new NoSePudoOcuparElTerrenoException();
    	}
    	this.ubicable = ubicable;
    	return this;
    }

	public boolean estaElevado() {
		
		return true;
		
	}

	public boolean agregarRecursoNatural(Recurso unRecurso) {
		
		return false;
		
	}

	public Recurso getRecurso() {
		
		throw new NoHayRecursoException();
		
	}

	@Override
	public Jugador getJugador() {
		// TODO Auto-generated method stub
		return null;
	}

}

