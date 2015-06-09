package xtremecraft.edificios;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Coordenada;
import xtremecraft.unidades.BarraDeVitalidad;


abstract class Edificio implements Ubicable,Atacable {
	
	protected BarraDeVitalidad vida;
	protected Coordenada coordenadas;

	public Edificio(int fila, int columna,int vida){
		
		this.coordenadas = new Coordenada(fila,columna);
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

	
	
	
}
