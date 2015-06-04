package xtremecraft.mapa;

public class Coordenada {
	
	private int coordenadaX;
	private int coordenadaY;
	
	
	public Coordenada(int fila, int columna) {

		this.coordenadaX = columna;
		this.coordenadaY = fila;
		
	}
	
	public int getX(){
		
		return this.coordenadaX;
		
	}

	public int getY() {
		
		return this.coordenadaY;
		
	}

	public double distancia(Coordenada coordenadaCelda2) {
		
		double cuadradoDeltaX = Math.pow((this.getX() - coordenadaCelda2.getX()), 2);
		double cuadradoDeltaY = Math.pow((this.getY() - coordenadaCelda2.getY()),2);
		double sumaCuadradosCatetos= (cuadradoDeltaX + cuadradoDeltaY);
		
		return Math.sqrt(sumaCuadradosCatetos);

	}
	
	
}
