package xtremecraft.edificios;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.sistema.Actualizable;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.BarraDeVitalidad;
import xtremecraft.unidades.Ubicable;


public abstract class Edificio implements Ubicable,Atacable,Actualizable {
	
	protected BarraDeVitalidad vida;
	protected Coordenada coordenadas;
	protected int tiempoConstruccion;
	protected int tiempoDeConstruccionActual;
	protected boolean estaElevado;

	public Edificio(Terreno unTerreno,int vida){
		
		this.coordenadas = new Coordenada(unTerreno.fila(),unTerreno.columna());
		this.estaElevado = unTerreno.estaElevado();
		this.vida = new BarraDeVitalidad(vida);
		this.tiempoDeConstruccionActual = 0;
	
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.coordenadas;
		
	}
	
	public int getVida(){
		
		return this.vida.devolverValor();
		
	}
	
	public void recibirDanio(int valorDanio){
		
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion){
			//revisar modelado de paso del tiempo
			this.tiempoDeConstruccionActual -= 1;
		}else{
			this.vida.recibirAtaque(valorDanio);
		}
	}

	public void actualizarUbicacion(Terreno terreno){
		
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion){
			throw new IllegalArgumentException("Este edificio se encuentra en contruccion");
		}
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
	
	public void pasarTiempo(){
		//revisar modelado de paso del tiempo
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion) this.tiempoDeConstruccionActual += 1;
		
	}
	
}
