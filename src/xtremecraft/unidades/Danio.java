package xtremecraft.unidades;

public class Danio {
	int aire;
	int tierra;

	public Danio(int danioAire, int danioTierra){
		this.aire = danioAire;
		this.tierra = danioTierra;
	}
	
	public int devolverDanio(boolean estaElevado){
		if(estaElevado){
			return this.aire;
		}
		return this.tierra;
	}
}
