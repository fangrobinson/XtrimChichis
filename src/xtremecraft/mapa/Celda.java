package xtremecraft.mapa;

public abstract class Celda {
	
	int coordenadaX;
	int coordenadaY;
	boolean ocupada;
	String tipo;
	
	
	public boolean estaOcupada(){
		
		return this.ocupada;
		
	}
	
	public int getX(){
		
		return this.coordenadaX;
		
	}

	public int getY() {
		
		return this.coordenadaY;
		
	}

	public void Ocupar() {
		
		this.ocupada=true;
		
	}
	

}
