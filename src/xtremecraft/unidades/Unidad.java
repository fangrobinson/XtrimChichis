package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.edificios.Construible;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Actualizable;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;

public abstract class Unidad implements Ubicable,Atacable,Defendible,Actualizable,Construible,Cobrable,Identificable{
	
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
	protected Jugador jugador;
	private Radiacion radiacion;
	private boolean esRadioactivo;
	
	protected Unidad(Jugador unJugador){
		
		this.jugador = unJugador;
		this.puedeAtacar = true;
		this.puedeMoverse = true;
		this.estaViva = true;
		this.estaUbicada = false;
		this.esRadioactivo = false;
		this.tiempoConstruccionActual = 1;
		
	}
	
	public Jugador getJugador(){
		
		return this.jugador;
		
	}
		
    public void recibirDanio(int danio){
    	
    	if(this.estaEnConstruccion()){
    		throw new UnidadEnConstruccionException();
    	}
    	
        vitalidad.recibirAtaque(danio);
        if(this.vitalidad.getValor() == 0){
        	this.estaViva = false;
        }
    
    }
    
    public void atacar (Atacable atacado){
    	
    	Ubicable atacadoUbicado = (Ubicable) atacado;
    	if (!this.puedoAtacar(atacadoUbicado)){
			throw new AtaqueFueraDelRangoDeVisionException();
		}
    	atacado.recibirDanio(this.danio.getDanio(atacadoUbicado.estaElevado()));
		
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
    		if(!this.puedoVer(terreno.getCoordenada()) || terreno.estaOcupado()){
    			throw new UbicacionNoValidaException();
    		}
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
    		if(this.estaEnConstruccion()){
    			this.tiempoConstruccionActual += 1;
    		}
    		else{
    			if(this.esRadioactivo){
    	    		this.radiacion.emitirRadiacion(this);
    	    	}
    			if (this.puedeAtacar){
    				this.vitalidad.curarPorTurno(1);
    				this.puedeAtacar = true;
        			this.puedeMoverse = true;
    			}
    			if(this.puedeMoverse && this.puedeAtacar){
    				this.vitalidad.curarPorTurno(1);
    				this.puedeAtacar = true;
        			this.puedeMoverse = true;
    			}
    		}
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
    
    //las unidades que estan alrededor del irradiado reciben radiacion.
    public boolean recibirDanio(Radiacion radiacion){
    	
        vitalidad.recibirAtaque(radiacion.getDanio());
        if(this.vitalidad.getValor()==0) this.estaViva=false;
        return true;
        
    }
    
    public boolean recibirAtaqueRadiacion(Radiacion radiacion){
    	
        this.radiacion = radiacion;
        this.puedeAtacar = false;
        this.puedeMoverse = false;
        this.esRadioactivo = true;
        return this.recibirDanio(radiacion);
    
    }
    
    public boolean esRadioactivo() {
		
		return this.esRadioactivo;
		
	}
    
    public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Moverse");
		acciones.add("Atacar");
		return acciones;
	}
    
}
