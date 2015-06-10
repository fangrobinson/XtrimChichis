package xtremecraft.edificios;

public class DepositoDeSuministros extends Edificio{

	private static int vida = 100;
	private static int tiempoDeConstruccion = 6;
	
	public DepositoDeSuministros(int fila, int columna) {
		
		super(fila, columna, vida);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}

	
	public boolean puedeUbicarseEnTierra() {
		
		return false;
		
	}

	
	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}

}
