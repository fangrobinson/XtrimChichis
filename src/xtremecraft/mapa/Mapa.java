package xtremecraft.mapa;

import java.util.TreeMap;

import xtremecraft.mapa.EstrategiaUbicacion;
import xtremecraft.recursos.NodoMineral;
import xtremecraft.unidades.Unidad;

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
		ubicarBases(cant_jugadores);
	}

	private void rellenarMapa(int alto, int ancho, int cant_jugadores) {
		this.mapaAlto = new TreeMap<Integer, TreeMap<Integer, Celda>>();
		
		for(int fila =0; fila < this.alto ;fila = fila + 1) {
			this.mapaAlto.put(fila, new TreeMap<Integer, Celda>());
			for(int columna = 0; columna < this.ancho; columna = columna + 1) {
				this.mapaAlto.get(fila).put(columna, obtenerCeldaAdecuada(fila, columna, cant_jugadores));
			}
		}	
	}
	
	private int decidirAlto(int cant_jugadores) {
		return cant_jugadores * 50;
	}

	private int decidirAncho(int cant_jugadores) {
		return cant_jugadores * 50;
	}
	
	
	private Celda obtenerCeldaAdecuada(int fila, int columna, int cant_jugadores) {
		int primer_corte_ancho = 3 *decidirAncho(cant_jugadores)/8;
		int primer_corte_alto = 3 *decidirAlto(cant_jugadores)/8;
		int segundo_corte_ancho = 5 *decidirAncho(cant_jugadores)/8;
		int segundo_corte_alto = 5 *decidirAlto(cant_jugadores)/8;
		if (fila < (primer_corte_alto) || fila > (segundo_corte_alto)){
			if (columna < (primer_corte_ancho) || columna > (segundo_corte_ancho)) {
				return new Tierra(fila,columna);
			}
		}
		return new Aire(fila,columna);
	}
	
	private void ubicarBases(int cant_jugadores) {
		EstrategiaUbicacion estrategia = new EstrategiaUbicacion();
		EstrategiaCuadrante estrategiaParticular;
		int jugadores_ubicados = 0;
		int ancho;
		int alto;
		int posicionRandom = 0;
		int cant_recursos = 5000;
	
		while (jugadores_ubicados < cant_jugadores) {
			if (jugadores_ubicados % 4 == 0) {
				posicionRandom = estrategia.posicionRandom(jugadores_ubicados);
			}
			
			estrategiaParticular = estrategia.conseguirEstrategiaParaCuadrante(jugadores_ubicados, this.alto, this.ancho);
			
			// Repetir esto para agregar mas recursos
			ancho = estrategiaParticular.getAncho(posicionRandom);
			alto = estrategiaParticular.getAlto(posicionRandom);
			this.mapaAlto.get(alto).get(ancho).ocuparConRecursoNatural(new NodoMineral(cant_recursos));
			// Falta agregar Bases todavia no implementadas
			jugadores_ubicados = jugadores_ubicados + 1;
		}

	}
	
	public boolean tieneAire() {
		return true;
	}

	public boolean tieneTierra() {
		return true;
	}
	
	//NOTA: tal vez este metodo deberia ser privado. De esa forma el mapa se encarga de ocupar la celda.
	public Celda getCeldaEnFilaColumna(int fila, int columna){
		return this.mapaAlto.get(fila).get(columna);
	}
	
	
	public boolean colocarUnidad(Unidad unaUnidad,int fila, int columna){
		
		Celda celda= this.getCeldaEnFilaColumna(columna, fila);
		return celda.ocuparConUnidad(unaUnidad);
		
	}
	

	
	
	
}