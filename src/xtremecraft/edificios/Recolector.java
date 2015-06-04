package xtremecraft.edificios;


public abstract class Recolector extends Edificio {
	
	protected int reservas;
	protected int aumentoDeReservaEnTurno;

	public Recolector(int fila, int columna){
		super(fila,columna);
		this.reservas=0;
		this.aumentoDeReservaEnTurno=10;
	
	}

	public void aumentarReservasEnTurno(){
		
		this.reservas+=this.aumentoDeReservaEnTurno;
		
	}
	 
	

}
