package xtremecraft.edificios;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.BarraDeVitalidad;
import xtremecraft.unidades.Ubicable;


abstract class Edificio implements Ubicable,Atacable {
	
	protected BarraDeVitalidad vida;
	protected Coordenada coordenadas;
	protected int tiempoConstruccion;
	protected boolean estaElevado;

	public Edificio(Terreno unTerreno,int vida){
		
		this.coordenadas = new Coordenada(unTerreno.fila(),unTerreno.columna());
		this.estaElevado = unTerreno.estaElevado();
		this.vida = new BarraDeVitalidad(vida);
	
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.coordenadas;
		
	}
	
	public int getVida(){
		
		return this.vida.devolverValor();
		
	}
	
	public void recibirDanio(int valorDanio){
		
		this.vida.recibirAtaque(valorDanio);
		
	}

	public void actualizarUbicacion(Terreno terreno) {
		
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
	
}
