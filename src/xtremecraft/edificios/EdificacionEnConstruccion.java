package xtremecraft.edificios;

import xtremecraft.interfaces.Atacable;
import xtremecraft.unidades.BarraDeVitalidad;

public class EdificacionEnConstruccion implements Atacable {
	
	private Edificio edificio;
	private BarraDeVitalidad vida;
	private boolean listo;
	
	public EdificacionEnConstruccion(Edificio edificio){
		this.edificio = edificio;
		this.listo = false;
		
		int vidaMaxima = edificio.getVida();
		vida = new BarraDeVitalidad(vidaMaxima);
		vida.recibirAtaque(vidaMaxima);
	}
	
	@Override
	public void recibirDanio(int daño) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getVida() {
		return this.vida.devolverValor();
	}
	
	public void pasarTiempo(){
		int vidaAumentar = this.edificio.vidaMax() / this.edificio.tiempoConstruccion();
		this.vida.regenerar(vidaAumentar);
		int vida = this.getVida();
		int vidaMax = this.vida.vidaMax();
		if (vida == vidaMax){
			listo = true;
		}
	}
	
	public boolean estaListo(){
		return this.listo;
	}
	
	public Edificio terminarConstruccion(){
		if(!this.listo){
			
		}
		return this.edificio;
	}
	
}
