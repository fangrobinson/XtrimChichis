package xtremecraft.unidades;

public interface Atacable {
	
//	void recibirDanio(int danio);
	 
	int getVida();
	
	boolean estaVivo();

//	boolean recibirDanioMisilEMP();
//
//	boolean recibirDanio(Radiacion radiacion);
//	
	void recibirDanio(Ataque ataque);

	void recibirAtaqueFisico(int danio);

}
