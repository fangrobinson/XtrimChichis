package xtremecraft.mapa;

public class EstrategiaCuartoCuadrante extends EstrategiaCuadrante {
	
	private int ancho;
	private int alto;
	
	public EstrategiaCuartoCuadrante(int alto, int ancho){
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public int getAlto(int posicion_base){
		return this.alto - posicion_base;
	}
	public int getAncho(int posicion_base){
		return this.ancho - posicion_base;
	}
}
