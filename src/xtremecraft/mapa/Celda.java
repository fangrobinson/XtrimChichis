package xtremecraft.mapa;

public class Celda {
	
	private int coordenadaX;
	private int coordenadaY;
	private boolean ocupada;
	
	public Celda(int X,int Y){
		
		this.coordenadaX=X;
		this.coordenadaY=Y;
		this.ocupada=false;
		
	}
	
	public boolean estaOcupada(){
		
		return this.ocupada;
		
	}
	
	public int getAbcisa(){
		
		return this.coordenadaX;
		
	}

	public int getOrdenada() {
		
		return this.coordenadaY;
		
	}

	public void Ocupar() {
		
		this.ocupada=true;
		
	}
	

}
