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
	protected int tiempoDeConstruccionActual;
	protected boolean estaElevado;
	private boolean estaVivo;

	public Edificio(Terreno unTerreno,int vida){
		
		this.coordenadas = new Coordenada(unTerreno.fila(),unTerreno.columna());
		this.estaElevado = unTerreno.estaElevado();
		this.vida = new BarraDeVitalidad(vida);
		this.tiempoDeConstruccionActual = 1;
		this.estaVivo = true;
	
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.coordenadas;
		
	}
	
	public int getVida(){
		
		return this.vida.devolverValor();
		
	}
	
	public boolean estaEnConstruccion(){
		
		return this.tiempoDeConstruccionActual < this.tiempoConstruccion;
		
	}
	
	public void recibirDanio(int valorDanio){
		//TODO: corregir el modelado del tiempo.
		if(this.estaEnConstruccion()){
			this.tiempoDeConstruccionActual -= 1;
		}else{
			this.vida.recibirAtaque(valorDanio);
		}
		if(this.vida.devolverValor() == 0) this.estaVivo =false;
		
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
		//revisar modelado de paso del tiempo
		if(this.estaEnConstruccion()) this.tiempoDeConstruccionActual += 2;
		
	}
	
    public boolean pertenezcoAEstaRaza(Terran terran){
    	
    	return terran.posee(this);
    	
    }
	
	public int tiempoContruccion(){
		
		return this.tiempoConstruccion;
		
	}
	
	public boolean recibirDanioMisilEMP(){
		
		return false;
		
	}
	
	public boolean estaVivo(){
		
		return this.estaVivo;
		
	}
	
}
