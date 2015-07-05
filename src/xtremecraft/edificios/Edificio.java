package xtremecraft.edificios;

import java.util.Observable;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Actualizable;
import xtremecraft.partida.Jugador;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.Ataque;
import xtremecraft.unidades.BarraDeVitalidad;
import xtremecraft.unidades.Cobrable;
import xtremecraft.unidades.IdentificableUbicable;

public abstract class Edificio extends Observable implements IdentificableUbicable,Atacable,Actualizable,Construible,Cobrable{
	
	protected Terreno terrenoActual;
	protected static int vidaInicial;
	protected BarraDeVitalidad vitalidad;
	protected int tiempoConstruccion;
	protected int turnosConstruccionPasados;
	protected boolean estaVivo;
	protected boolean estaEnConstruccion;
	protected Jugador jugador;
	
	public Edificio(Jugador unJugador,Terreno unTerreno,int vida){
		
		if (unTerreno.getClass() != Tierra.class){
			throw new UnEdificioSoloSePuedeUbicarEnTierraException();
		}
		
		this.terrenoActual = unTerreno;
		vidaInicial = vida;
		this.vitalidad = new BarraDeVitalidad(vida);
		this.vitalidad.recibirAtaque(vida);
		this.turnosConstruccionPasados = 0;
		this.estaEnConstruccion = true;
		this.estaVivo = true;
		this.jugador = unJugador;
	
	}
	
	public static int getVidaInicial(){
		
		return vidaInicial;
		
	}
	
	public static String getEstadoInicial(){
		
		return "En Construccion";
		
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.terrenoActual.getUbicacionActual();
		
	}
	
	public int getVida(){
		
		return this.vitalidad.getValor();
		
	}
	
	public boolean estaEnConstruccion(){
		
		return this.estaEnConstruccion;
		
	}
	
	public int getNumeroJugador(){
		
		return this.jugador.getNumeroDeJugador();
		
	}
	
	private String generarEstadoImprimible(){
		
		return "vida: "+Integer.toString(this.vitalidad.getValor());
		
	}

    public String getEstadoImprimible(){
    	
    	if(this.estaEnConstruccion()) return getEstadoInicial();
    	if(!this.estaVivo()) return "Edificio muerto";
    	return this.generarEstadoImprimible();
    	
    }

	public void setUbicacionInicial(Terreno unTerreno){
		
		this.terrenoActual = unTerreno;
		terrenoActual.ubicar(this);
		
	}
	
	public void actualizarUbicacion(Terreno unTerreno){
		
		this.terrenoActual.desocupar();
		this.setUbicacionInicial(unTerreno);
		setChanged();
		notifyObservers();
		
	}	
	
	public int tiempoConstruccion(){
		
		return this.tiempoConstruccion;
		
	}

	public int vidaMax(){
		
		return this.vitalidad.vidaMax();
		
	}
	
	public boolean estaElevado(){
		
		return false;
		
	}
	
	public boolean elevar(){
		
		return false;
		
	}
	
	public void pasarTiempo(){
		
		if (this.estaEnConstruccion()){
			this.turnosConstruccionPasados += 1;
			if (this.turnosConstruccionPasados >= this.tiempoConstruccion){
				this.estaEnConstruccion = false;
				this.vitalidad.curarPorTurno(100);
			}
		}
		else{
			this.vitalidad.curarPorTurno(1);
		}
		setChanged();
		notifyObservers();
		
	}
	
    public boolean pertenezcoAEstaRaza(Terran terran){
    	
    	return terran.posee(this);
    	
    }
    
    public void recibirDanio(Ataque ataque){
    	
    	ataque.afectar(this);

        if(this.vitalidad.getValor() == 0){
        	this.estaVivo = false;
        }
        
        setChanged();
		notifyObservers();
		
    }
    
	public void recibirAtaqueFisico(int danio){
		
		this.vitalidad.recibirAtaque(danio);
		
	}
	
	public boolean estaVivo(){
		
		return this.estaVivo;
		
	}
	
	public Jugador getJugador(){
		return this.jugador;
	}

	public void restarTurnosConstruccion(int turnosARestar) {

		this.turnosConstruccionPasados -= turnosARestar;
	
	}
	
}
