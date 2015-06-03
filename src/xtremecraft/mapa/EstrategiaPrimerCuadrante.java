package xtremecraft.mapa;

public class EstrategiaPrimerCuadrante extends EstrategiaCuadrante {
	
	private int ancho;
	
	public EstrategiaPrimerCuadrante(int ancho){
		this.ancho = ancho;
	}
	
	public int getAlto(int posicion_base){
		return posicion_base + this.corrimiento();
	}
	public int getAncho(int posicion_base){
		return this.ancho - posicion_base + this.corrimiento();
	}
}
