package xtremecraft.mapa;

public class Mapa {
	
	public Mapa(int cant_jugadores) {
		
		if (cant_jugadores <= 0){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		
	}
	
}