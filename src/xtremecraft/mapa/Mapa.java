package xtremecraft.mapa;

import java.util.TreeMap;
import java.util.Random;

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
	/*
	private void ubicarBases(int cant_jugadores, Ubicable elemento) {
		Random randomGen = new Random();
		int jugadores_ubicados = 0;
		int randomInt;
		int randomCorrimiento;

		while (jugadores_ubicados < cant_jugadores) {
			if (jugadores_ubicados % 4 = 0) {
				randomInt = randomGen.nextInt(25) + (jugadores_ubicados/4 * 50);
			}
			// Aca genero las posiciones en las que ubicar
			// Tienen que llamar a this.mapaAlto.get(X).get(Y).ubicar(new GasVepeno); GasVespeno, Material, etc
			// Si quieren ubicar otra cosa tiran un + randomGen.nextInt(5); para que lo tire en un lugar rangom digamos.

			// Lo cambie para que ubice las cosas por defecto, pero tiene el problema de que no puede ubicar las bases porque 
			// serian de distinta raza. Recomiendo que pueda llegar a recibir una posible lista de Array con clases
			// y le tira sarasa.ubicar(array.at(i).crearBase); Por ejemplo... pero bueno. Mañana lo discutimos.

			// Quiero cambiarlo para que esto se haga de una sola pasada sin la If Wall con complejos se que se puede
			// pero ni idea si les jode o no dejarlo asi. Queria que se entienda la idea primero.g

			// Agrego documentacion:

			// Deberia recibir por parametro un array de elementos y un array de jugadores
			// Entonces cada vez que itera clava arrayDeJugadores.at(i).crearBase();
			// y alrededor con random le ubicamos lo que haya en el array de elementos,
			// por ejemplo Gas Vepeno, minerales etc.
			// Sino la llamas para ubicar gas vespeno, despues la llamas para minerales y te los clava en la loma del ojete.

			if (jugadores_ubicados % 4 = 0) {
				this.mapaAlto.get(randomInt).get(randomInt).ubicar(elemento);
			}
			if (jugadores_ubicados % 4 = 1) {
				this.mapaAlto.get(this.alto - randomInt).get(this.ancho - randomInt).ubicar(elemento);
			}
			if (jugadores_ubicados % 4 = 2) {
				this.mapaAlto.get(randomInt).get(this.ancho - randomInt).ubicar(elemento);
			}
			if (jugadores_ubicados % 4 = 3) {
				this.mapaAlto.get(this.alto - randomInt).get(randomInt).ubicar(elemento);
			}
			jugadores_ubicados = jugadores_ubicados + 1;
		}

	}
	*/
	public boolean tieneAire() {
		return true;
	}

	public boolean tieneTierra() {
		return true;
	}
}