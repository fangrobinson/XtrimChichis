package xtremecraft.unidades;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Defendible;
import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Coordenada;

public abstract class Unidad implements Atacable, Defendible, Ubicable{
	BarraDeVitalidad vitalidad;
	Daño daño;
	int vision;
	Coordenada coordenadas;
	
	protected Unidad(Terreno celda){
		if(celda.estaOcupada()){
			throw new IllegalArgumentException();
		}
		this.coordenadas = new Coordenada(celda.getCoordenada().columna(),celda.getCoordenada().fila());
	}
	
    public void recibirDaño(int daño){
    	
        vitalidad.recibirAtaque(daño);
        	
    }
    
    public void atacar (Atacable atacado, String medio){
    	Ubicable atacadoUbicado = (Ubicable) atacado;
    	if (this.puedoAtacar(atacadoUbicado)){
    		int daño = this.daño.devolverDaño(medio);
        	atacado.recibirDaño(daño);		
    	}
    	
    	
    }
    
    private boolean puedoAtacar(Ubicable atacado) {
    	
    	Coordenada ubicacionAtacante = this.getUbicacionActual();
    	Coordenada ubicacionAtacado = atacado.getUbicacionActual();
    	double distancia = ubicacionAtacante.distancia(ubicacionAtacado);
    	
		if (distancia <= this.getRadioVision()){
			return true;
		}
		return false;
	}

	public int getVida(){
    	
    	return this.vitalidad.devolverValor();
    	
    }
    
    public int getRadioVision(){
    	
    	return this.vision;
    	
    }
    
    public Coordenada getUbicacionActual(){
    	
    	return this.coordenadas;
    	
    }
    
    public void moverACelda(Terreno celdaDestino){
    	    	
    	this.coordenadas.nuevaFila(celdaDestino.getCoordenada().fila());
    	this.coordenadas.nuevaColumna(celdaDestino.getCoordenada().columna());
    	
    }

}
