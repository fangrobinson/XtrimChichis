package xtremecraft.unidades;

import xtremecraft.edificios.Construible;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Coordenada;
import xtremecraft.raza.Terran;
import xtremecraft.sistema.Actualizable;

public abstract class Unidad implements Ubicable,Atacable,Defendible,Actualizable,Construible,Cobrable{
	
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
    	//else throw new AtaqueFueraDelRangoDeVisionException(); ???
		
    }
    
    protected boolean puedoAtacar(Ubicable atacado){
    		
    	if (!this.puedeAtacar){
    		return false;
    	}
		return this.puedoVer(atacado.getUbicacionActual());
		
	}
    
    protected boolean puedoVer(Coordenada unaCoordenada){
    	
    	Coordenada miUbicacion = this.getUbicacionActual();
    	double distancia = miUbicacion.distancia(unaCoordenada);
    	return distancia <= this.getRadioVision();
    	
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
    

    public void actualizarUbicacion(NaveTransporte naveTransporte) {
    	 
    	this.terrenoActual.desocupar();
    	this.terrenoActual = naveTransporte.getTerrenoActual();
    	
	}
    
    public void actualizarUbicacion(Terreno terreno) {

    	if(this.estaUbicada){
    		if(!this.puedoVer(terreno.getCoordenada())) throw new UbicacionNoValidaException();
    		this.terrenoActual.desocupar();
    	}
    	this.terrenoActual = terreno;
    	terrenoActual.ubicar(this);
		this.estaUbicada = true;
		
	}
    
	public boolean subirANaveDeTransporte(NaveTransporte unaNave) {
		
		if(this.puedoVer(unaNave.getUbicacionActual())){
			return unaNave.transportarNuevaUnidad(this);
		}
		return false;
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
	
    public boolean recibirDanioMisilEMP(){
    	
    	return false;
    	
    }
    
}
