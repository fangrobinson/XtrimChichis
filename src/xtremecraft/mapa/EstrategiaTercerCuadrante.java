package xtremecraft.mapa;

public class EstrategiaTercerCuadrante extends EstrategiaCuadrante {
	
	private int alto;
	
	public EstrategiaTercerCuadrante(int alto){
		this.alto = alto;
	}
	
	public int getAlto(int posicion_base){
		return this.alto - posicion_base + this.corrimiento();
	}
	public int getAncho(int posicion_base){
		return posicion_base + this.corrimiento();
	}
}
