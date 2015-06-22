package xtremecraft.unidades;

public interface Atacable {
	
	public void recibirDanio(int danio);
	 
	public int getVida();
	
	public boolean estaVivo();

	public boolean recibirDanioMisilEMP();

	public boolean recibirDanio(Radiacion radiacion);

}
