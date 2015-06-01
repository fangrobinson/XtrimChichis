package xtremecraft.mapa;

import java.util.TreeMap;

public class Mapa {
	
	private TreeMap<Integer, TreeMap<Integer, Celda>> mapaAlto;
	private int alto;
	private int ancho;

	public Mapa(int cant_jugadores) {
		
		if (cant_jugadores <= 1){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		this.alto = this.decidirAlto(cant_jugadores);
		this.ancho = this.decidirAncho(cant_jugadores);
		
		rellenarMapa(alto, ancho);	
	}

	private void rellenarMapa(int alto, int ancho) {
		this.mapaAlto = new TreeMap<Integer, TreeMap<Integer, Celda>>();
		for(int i =0; i < this.alto ;i = i + 1) {
			this.mapaAlto.put(i, new TreeMap<Integer, Celda>());
			for(int j = 0; j < this.ancho; j = j + 1) {
				this.mapaAlto.get(i).put(j, new Celda(j, i));
			}
		}	
	}
	
	public boolean tieneAire() {
		return true;
	}

	public boolean tieneTierra() {
		return true;
	}
	
	private int decidirAlto(int cant_jugadores) {
		return cant_jugadores * 25;
	}

	private int decidirAncho(int cant_jugadores) {
		return cant_jugadores * 100;
	}
	
}