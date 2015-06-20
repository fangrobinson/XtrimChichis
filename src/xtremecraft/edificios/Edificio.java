package xtremecraft.edificios;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.sistema.Actualizable;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.BarraDeVitalidad;
import xtremecraft.unidades.Ubicable;


public abstract class Edificio implements Ubicable,Atacable,Actualizable,Construible{
	
	protected BarraDeVitalidad vida;
	protected Coordenada coordenadas;
	protected int tiempoConstruccion;
	protected int turnosConstruccionPasados;
	protected boolean estaElevado;
	protected boolean estaVivo;
	protected boolean estaEnConstruccion;

	public Edificio(Terreno unTerreno,int vida){
		
		this.coordenadas = new Coordenada(unTerreno.fila(),unTerreno.columna());
		this.estaElevado = unTerreno.estaElevado();
		this.vida = new BarraDeVitalidad(vida);
		this.vida.recibirAtaque(vida);
		this.turnosConstruccionPasados = 0;
		this.estaEnConstruccion = true;
		this.estaVivo = true;
	
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.coordenadas;
		
	}
	
	public int getVida(){
		
		return this.vida.devolverValor();
		
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
		if(this.vida.devolverValor() == 0){
			this.estaVivo = false;
		}
	}

	public void actualizarUbicacion(Terreno terreno){
		
		this.coordenadas = new Coordenada(terreno.fila(),terreno.columna());
		
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
			/*
			double porcentaje = 100 / this.tiempoConstruccion;
			this.vida.curarPorTurno(porcentaje);
			
			if (this.vida.devolverValor() >= this.vidaMax()){
				this.estaEnConstruccion = false;
			}*/
		}
		else{
			this.vida.curarPorTurno(1);
		}
	}
	
    public boolean pertenezcoAEstaRaza(Terran terran){
    	
    	return terran.posee(this);
    	
    }
	//Esta repetido o_O
    /*
	public int tiempoContruccion(){
		
		return this.tiempoConstruccion;
		
	}
	*/
    
	public boolean recibirDanioMisilEMP(){
		
		return false;
		
	}
	
	public boolean estaVivo(){
		
		return this.estaVivo;
		
	}
	
}
