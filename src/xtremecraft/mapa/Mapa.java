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
		
		rellenarMapa(alto, ancho, cant_jugadores);	
	}

	private void rellenarMapa(int alto, int ancho, int cant_jugadores) {
		this.mapaAlto = new TreeMap<Integer, TreeMap<Integer, Celda>>();
		
		for(int i =0; i < this.alto ;i = i + 1) {
			this.mapaAlto.put(i, new TreeMap<Integer, Celda>());
			for(int j = 0; j < this.ancho; j = j + 1) {
				this.mapaAlto.get(i).put(j, obtenerCeldaAdecuada(i, j, cant_jugadores));
			}
		}	
	}
	
	private int decidirAlto(int cant_jugadores) {
		return cant_jugadores * 50;
	}

	private int decidirAncho(int cant_jugadores) {
		return cant_jugadores * 50;
	}
	
	private Celda obtenerCeldaAdecuada(int i, int j, int cant_jugadores) {
		if (j < (3*cant_jugadores/8) || j > (5*cant_jugadores/8)){
			if (i < (3*cant_jugadores/8) || i > (5*cant_jugadores/8)) {
				return new Tierra(j, i);
			}
		}
		return new Aire(j,i);
	}
	
	public boolean tieneAire() {
		return true;
	}

	public boolean tieneTierra() {
		return true;
	}
}