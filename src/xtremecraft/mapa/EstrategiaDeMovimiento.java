package xtremecraft.mapa;


public abstract class EstrategiaDeMovimiento {
	
	protected int avanceFila;
	protected int avanceColumna;
	
	public Coordenada avanzar(Coordenada coordenada){
		
		return new Coordenada(coordenada.fila()+this.avanceFila,coordenada.columna()+this.avanceColumna);
		
	}
	
}
