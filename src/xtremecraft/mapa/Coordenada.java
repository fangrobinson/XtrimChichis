package xtremecraft.mapa;

import java.util.ArrayList;

public class Coordenada {
	
	private int columna;
	private int fila;
	
	
	public Coordenada(int fila, int columna) {

		this.columna = columna;
		this.fila = fila;
		
	}
	
	public int columna(){
		
		return this.columna;
		
	}

	public int fila() {
		
		return this.fila;
		
	}

	public double distancia(Coordenada coordenadaCelda2) {
		
		double cuadradoDeltaX = Math.pow((this.columna() - coordenadaCelda2.columna()), 2);
		double cuadradoDeltaY = Math.pow((this.fila() - coordenadaCelda2.fila()),2);
		double sumaCuadradosCatetos= (cuadradoDeltaX + cuadradoDeltaY);
		
		return Math.sqrt(sumaCuadradosCatetos);

	}

	public void nuevaFila(int y) {
		
		this.fila = y;
		
	}

	public void nuevaColumna(int x) {
		
		this.columna = x;
				
	}

	public ArrayList<Coordenada> getCoordenadasAdyacentes(Coordenada coordenadaActual) {
		
		ArrayList<Coordenada> coordenadasAdyacentes = new ArrayList<Coordenada>();
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila(),coordenadaActual.columna() - 1));
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila(),coordenadaActual.columna() + 1));
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila() + 1,coordenadaActual.columna()));
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila() - 1,coordenadaActual.columna()));
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila() + 1,coordenadaActual.columna() + 1));
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila() + 1,coordenadaActual.columna() - 1));
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila() - 1,coordenadaActual.columna() + 1));
		coordenadasAdyacentes.add(new Coordenada(coordenadaActual.fila() - 1,coordenadaActual.columna() - 1));
		return coordenadasAdyacentes;
	}

	
}
