package xtremecraft.unidades;

public interface Atacable {
	 
	int getVida();
	
	boolean estaVivo();

	void recibirDanio(Ataque ataque);

	void recibirAtaqueFisico(int danio);

}
