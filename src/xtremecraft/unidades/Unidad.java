package xtremecraft.unidades;

import java.util.Observable;
import xtremecraft.edificios.Construible;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Actualizable;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;

public abstract class Unidad extends Observable implements IdentificableUbicable, Atacable, Defendible, Actualizable, Construible, Cobrable{
	
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
	protected static int vidaInicial;
	
	protected Unidad(Jugador unJugador){
		
		this.jugador = unJugador;
		this.puedeAtacar = true;
		this.puedeMoverse = true;
		this.estaViva = true;
		this.estaUbicada = false;
		this.tiempoConstruccionActual = 1;
		
	}
	
	public int getNumeroJugador(){
		
		return this.jugador.getNumeroDeJugador();
		
	}
	
	public static int getVidaInicial(){
		
		return vidaInicial;
		
	}
    
    public static String getEstadoInicial(){
    	
    	return "En construccion";
    	
    }
    
    private String generarEstadoImprimible(){
		
		return "vida: "+Integer.toString(this.vitalidad.getValor());
		
	}
    
    public String getEstadoImprimible(){
    	
    	if(this.estaEnConstruccion()) return getEstadoInicial();
    	if(!this.estaVivo()) return "Unidad muerta";
    	return this.generarEstadoImprimible();
    	
    }
    
    public void atacar (Atacable atacado) throws AtaqueFueraDelRangoDeVisionException{
    	
    	Ubicable atacadoUbicado = (Ubicable) atacado;
    	if (!this.puedoVer(atacadoUbicado.getUbicacionActual())){
			throw new AtaqueFueraDelRangoDeVisionException();
		}
    	if (!this.puedeAtacar){
    		throw new YaSeSeleccionoUnAtaqueException();
    	}
    	this.puedeAtacar = false;
    	atacado.recibirDanio(this.danio);
		
    }
    
    protected boolean puedoAtacar(){
    	
    	return this.puedeAtacar;
		
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

    	setChanged();
		notifyObservers();
    	
	}
    
    public void setUbicacionInicial(Terreno terreno){
    	
    	this.terrenoActual = terreno;
		this.estaUbicada = true;
		
    }
    
    public void actualizarUbicacion(Terreno terreno) {
    	
    	if( (!this.puedoVer(terreno.getCoordenada())) || terreno.estaOcupado() ){
    		throw new UbicacionNoValidaException();
    	}
    	if (!this.puedeMoverse){
    		throw new YaSeSeleccionoUnMovimientoException();
    	}
    	
    	this.puedeMoverse = false;
    	this.terrenoActual.desocupar();
    	terreno.ubicar(this);    	
    	this.terrenoActual = terreno;
		
	}
    
	public void subirANaveDeTransporte(NaveTransporte unaNave) {
		
		if(!this.puedoVer(unaNave.getUbicacionActual())){
			throw new NaveFueraDelRangoDeVisionUnidadException();
		}
		unaNave.transportarNuevaUnidad(this);
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
    
    public void pasarTiempo(){
    	
    	if(this.estaVivo()){
    		if(this.estaEnConstruccion()){
    			this.tiempoConstruccionActual += 1;
    		}
    		else{
    			if(this.esRadioactivo()){
    	    		this.radiacion.emitirRadiacion(this);
    	    	}
    			else if (this.puedeAtacar){
    				this.vitalidad.curarPorTurno(1);
    				if(this.puedeMoverse && this.puedeAtacar){
        				this.vitalidad.curarPorTurno(1);
        			}
    			}
    		}
    		this.puedeAtacar = true;
			this.puedeMoverse = true;
			setChanged();
    		notifyObservers();
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
    
	
    public void recibirDanio(Ataque ataque){
    	
    	if(this.estaEnConstruccion()){
    		throw new UnidadEnConstruccionException();
    	}
    	
    	ataque.afectar(this);

        if(this.vitalidad.getValor() == 0){
        	this.estaViva = false;
        }
        
        setChanged();
		notifyObservers();
		
    }

    public void infectarCon(Radiacion radiacion){
    	
    	this.radiacion = radiacion;
    	
    }
    
    
	public void recibirAtaqueFisico(int danio){
		
		this.vitalidad.recibirAtaque(danio);
		
	}
    
    public boolean esRadioactivo() {
		
		return (this.radiacion != null);
		
	}

	public boolean puedeMoverse() {
		return this.puedeMoverse;
	}

	public boolean puedeAtacar() {
		return this.puedeAtacar;
	}
	
	public Jugador getJugador(){
		return this.jugador;
	}
   
}
