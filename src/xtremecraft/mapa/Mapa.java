package xtremecraft.mapa;

import java.util.TreeMap;

public class Mapa {
	
	private TreeMap<Integer, Celda> mapa;

	public Mapa(int cant_jugadores) {
		
		if (cant_jugadores <= 1){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		
		this.mapa = new TreeMap<Integer, Celda>();
		
	}
	
}