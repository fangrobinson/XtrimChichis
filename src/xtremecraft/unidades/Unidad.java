package xtremecraft.unidades;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Defendible;
import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Coordenada;

public abstract class Unidad implements Atacable, Defendible, Ubicable{
	BarraDeVitalidad vitalidad;
	Danio daño;
	int vision;
	Coordenada coordenadas;
	int tiempoConstruccion;
	boolean estaElevado;
	
	protected Unidad(Terreno terreno){
		if(terreno.estaOcupada()){
			throw new IllegalArgumentException();
		}
		actualizarUbicacion(terreno);
	}
	
    public void recibirDanio(int daño){
    	
        vitalidad.recibirAtaque(daño);
        	
    }
    
    public void atacar (Atacable atacado, String medio){
    	Ubicable atacadoUbicado = (Ubicable) atacado;
    	if (this.puedoAtacar(atacadoUbicado)){
    		int daño = this.daño.devolverDaño(medio);
        	atacado.recibirDanio(daño);		
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
    
    public int tiempoConstruccion(){
    	return this.tiempoConstruccion;
    }
    
    public Coordenada getUbicacionActual(){
    	
    	return this.coordenadas;
    	
    }
    
    public void actualizarUbicacion(Terreno terreno) {
    	
    	this.coordenadas = new Coordenada(terreno.fila(),terreno.columna());
    	this.estaElevado = terreno.esElevado();
		
	}
    
    public boolean estaElevado(){
    	return this.estaElevado;
    }

}
