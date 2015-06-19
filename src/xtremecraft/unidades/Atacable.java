package xtremecraft.unidades;

public interface Atacable {
	
	public void recibirDanio(int daño);
	 
	public int getVida();

	public boolean recibirDanioMisilEMP();
	
	public boolean estaVivo();

}
