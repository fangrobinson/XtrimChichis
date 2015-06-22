package xtremecraft.edificios;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.sistema.Actualizable;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.BarraDeVitalidad;
import xtremecraft.unidades.Cobrable;
import xtremecraft.unidades.Radiacion;
import xtremecraft.unidades.Ubicable;


public abstract class Edificio implements Ubicable,Atacable,Actualizable,Construible,Cobrable{
	
	protected Terreno terrenoActual;
	protected BarraDeVitalidad vida;
	protected int tiempoConstruccion;
	protected int turnosConstruccionPasados;
	protected boolean estaVivo;
	protected boolean estaEnConstruccion;
	
	public Edificio(Terreno unTerreno,int vida){
		
		this.terrenoActual = unTerreno;
		this.vida = new BarraDeVitalidad(vida);
		this.vida.recibirAtaque(vida);
		this.turnosConstruccionPasados = 0;
		this.estaEnConstruccion = true;
		this.estaVivo = true;
	
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.terrenoActual.getUbicacionActual();
		
	}
	
	public int getVida(){
		
		return this.vida.getValor();
		
	}
	
	public boolean estaEnConstruccion(){
		
		return this.estaEnConstruccion;
		
	}
	
	public void recibirDanio(int valorDanio){
		//TODO: Implementar logica de danio recibido que resta turnos.
		if(this.estaEnConstruccion){
			int turnosARestar = valorDanio % (this.vidaMax()/this.tiempoConstruccion);
			this.turnosConstruccionPasados -= turnosARestar;
		}
		else{
			this.vida.recibirAtaque(valorDanio);	
		}
		if(this.vida.getValor() == 0){
			this.estaVivo = false;
		}
	}

	public void actualizarUbicacion(Terreno unTerreno){
		
		this.terrenoActual.desocupar();
		this.terrenoActual = unTerreno;
		terrenoActual.ubicar(this);
		
	}
	
	public int tiempoConstruccion(){
		
		return this.tiempoConstruccion;
		
	}

	public int vidaMax(){
		
		return this.vida.vidaMax();
		
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
				this.vida.curarPorTurno(100);
			}
		}
		else{
			this.vida.curarPorTurno(1);
		}
	}
	
    public boolean pertenezcoAEstaRaza(Terran terran){
    	
    	return terran.posee(this);
    	
    }

	public boolean recibirDanioMisilEMP(){
		
		return false;
		
	}
	
	public boolean recibirDanio(Radiacion radiacion){
		
		return false;
		
	}
	
	public boolean estaVivo(){
		
		return this.estaVivo;
		
	}
	
}
