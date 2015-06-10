package xtremecraft.unidades;

public class Danio {
	int aire;
	int tierra;

	public Danio(int dañoAire, int dañoTierra){
		this.aire = dañoAire;
		this.tierra = dañoTierra;
	}
	
	public int devolverDanio(boolean estaElevado){
		if(estaElevado){
			return this.aire;
		}
		return this.tierra;
	}
}
