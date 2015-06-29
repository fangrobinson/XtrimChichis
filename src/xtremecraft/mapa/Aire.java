package xtremecraft.mapa;

import xtremecraft.partida.Jugador;
import java.util.ArrayList;
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
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		
		return acciones;
	}

	@Override
	public Jugador getJugador() {
		// TODO Auto-generated method stub
		return null;
	}

}

