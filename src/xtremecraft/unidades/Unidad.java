package xtremecraft.unidades;

import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Coordenada;
import xtremecraft.raza.Terran;
import xtremecraft.sistema.Actualizable;

public abstract class Unidad implements Atacable, Defendible, Ubicable, Actualizable{
	BarraDeVitalidad vitalidad;
	Danio danio;
	int vision;
	Coordenada coordenadas;
	int tiempoConstruccion;
	boolean estaElevado;
	boolean puedeAtacar;
	boolean puedeMoverse;
	
	protected Unidad(){
		this.puedeAtacar = true;
		this.puedeMoverse = true;
	}
	
    public void recibirDanio(int daño){
    	
        vitalidad.recibirAtaque(daño);
        	
    }
    
    
    public void atacar (Atacable atacado){
    	
    	Ubicable atacadoUbicado = (Ubicable) atacado;
    	if (this.puedoAtacar(atacadoUbicado)){
    		atacado.recibirDanio(this.danio.devolverDanio(atacadoUbicado.estaElevado()));
    	}
    	
    }
    
    protected boolean puedoAtacar(Ubicable atacado) {
    	if (!this.puedeAtacar){
    		return false;
    	}
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
    	this.estaElevado = terreno.estaElevado();
		
	}
    
    public boolean estaElevado(){
    	
    	return this.estaElevado;
    	
    }
    
    public boolean elevar(){
    	
    	return false;
    	
    }
    
    public boolean puedeUbicarseSobreRecursoNatural(){
    	
    	return false;
    	
    }
    
    public boolean puedeRealizarAccion(){
    	return this.puedeAtacar || this.puedeMoverse;
    }
    //faltan pruebas de supuesto
    public void pasarTiempo(){
    	if (this.puedeAtacar){
    		this.vitalidad.curarPorTurno(1);
    	}
    	if(this.puedeMoverse && this.puedeAtacar){
    		this.vitalidad.curarPorTurno(1);
    	}
    	this.puedeAtacar = true;
    	this.puedeMoverse = true;
    }

    public boolean pertenezcoAEstaRaza(Terran terran){
    	return terran.posee(this);
    }
}
