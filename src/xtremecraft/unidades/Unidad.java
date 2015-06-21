package xtremecraft.unidades;

import xtremecraft.edificios.Construible;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Coordenada;
import xtremecraft.raza.Terran;
import xtremecraft.sistema.Actualizable;

public abstract class Unidad implements Ubicable,Atacable,Defendible,Actualizable,Construible{
	
	BarraDeVitalidad vitalidad;
	Danio danio;
	int vision;
	int tiempoConstruccion;
	int tiempoConstruccionActual;
	boolean puedeAtacar;
	boolean puedeMoverse;
	boolean estaViva;
	boolean estaUbicada;
	int suministro;
	protected Terreno terrenoActual;
	
	protected Unidad(){
		
		this.puedeAtacar = true;
		this.puedeMoverse = true;
		this.estaViva = true;
		this.estaUbicada = false;
		this.tiempoConstruccionActual = 1;
		
	}
	
	//TODO: manejar excepciones UnidadEnConstruccion!!
    public void recibirDanio(int danio){
    	
        vitalidad.recibirAtaque(danio);
        if(this.vitalidad.getValor() == 0){
        	this.estaViva = false;
        }
    
    }
    
    public void atacar (Atacable atacado){
    	
    	Ubicable atacadoUbicado = (Ubicable) atacado;
    	if (this.puedoAtacar(atacadoUbicado)){
			atacado.recibirDanio(this.danio.getDanio(atacadoUbicado.estaElevado()));
		}
		
    }
    
    protected boolean puedoAtacar(Ubicable atacado){
    		
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
    	
    	return this.vitalidad.getValor();
    	
    }
    
    public int getRadioVision(){
    	
    	return this.vision;
    	
    }
    
    public int tiempoConstruccion(){
    	
    	return this.tiempoConstruccion;
    	
    }
    
    public boolean estaEnConstruccion(){
    	
    	return this.tiempoConstruccion > this.tiempoConstruccionActual;
    	
    }
    
    public Coordenada getUbicacionActual(){
    	
    	return this.terrenoActual.getCoordenada();
    	
    }
    
    public Terreno getTerrenoActual(){
    	
    	return this.terrenoActual;
    	
    }

    public void actualizarUbicacion(Terreno terreno) {
    	//TODO: Validacion transporte distancia para marine y goliat.
    	//TODO: validar que una unidad terrestre no se pueda ubicar en una celda aerea(?)(consultar).
    	//opcion solo le mostramos al jugador los terrenos validos cuando selecciona la celda de destino.
    	if(this.estaUbicada){
    		this.terrenoActual.desocupar();
    	}
    	this.terrenoActual = terreno;
    	terrenoActual.ubicar(this);
		this.estaUbicada = true;
		
	}
    
    public void actualizarUbicacion(NaveTransporte naveTransporte) {
    	 
    	this.terrenoActual.desocupar();
    	this.terrenoActual = naveTransporte.getTerrenoActual();
    	
	}
    
    public boolean estaElevado(){
    	
    	return this.terrenoActual.estaElevado();
    	
    }
    
    public boolean elevar(){
    	
    	return false;
    	
    }
    
    public int getPoblacionOcupada(){
    	
    	return this.suministro;
    	
    }
    
    public boolean puedeUbicarseSobreRecursoNatural(){
    	
    	return false;
    	
    }
    
    public boolean puedeRealizarAccion(){
    	
    	return this.puedeAtacar || this.puedeMoverse;
    
    }
    //faltan pruebas de supuesto
    public void pasarTiempo(){
    	
    	if(this.estaVivo()){
    		if(!this.estaEnConstruccion()){
    			if (this.puedeAtacar){
    				this.vitalidad.curarPorTurno(1);
    			}
    			if(this.puedeMoverse && this.puedeAtacar){
    				this.vitalidad.curarPorTurno(1);
    			}
    			this.puedeAtacar = true;
    			this.puedeMoverse = true;
    		}else this.tiempoConstruccionActual += 1;
    	}
    	
    }

    public boolean estaVivo() {
		
		return this.estaViva;
		
	}

	public boolean pertenezcoAEstaRaza(Terran terran){
    
    	return terran.posee(this);
    
    }
    
    /*public boolean recibirDanioMisilEMP(){
    	
    	return false;
    	
    }*/
    
}
