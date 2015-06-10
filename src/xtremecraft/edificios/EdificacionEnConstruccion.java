package xtremecraft.edificios;

import xtremecraft.unidades.Atacable;
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
	public void recibirDanio(int danio) {
		this.vida.recibirAtaque(danio);
		
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
	
	public Edificio terminarConstruccion() throws NoEstaListoException{
		if(!this.listo){
			throw new NoEstaListoException("No ha pasado el tiempo suficiente para terminar la construccion.");
		}
		return this.edificio;
	}
	
}