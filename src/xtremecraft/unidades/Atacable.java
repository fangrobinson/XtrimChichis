package xtremecraft.unidades;

public interface Atacable {
	
	void recibirDanio(int danio);
	 
	int getVida();
	
	boolean estaVivo();

	boolean recibirDanioMisilEMP();

	boolean recibirDanio(Radiacion radiacion);

}
