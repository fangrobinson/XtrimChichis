package xtremecraft.mapa;

import java.util.ArrayList;
import java.util.TreeMap;

import xtremecraft.unidades.Ubicable;


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
		//ubicarBases(cant_jugadores);
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
	
	
	private Celda obtenerCeldaAdecuada(int fila, int columna, int cant_jugadores){
		return new Celda(new Tierra(fila, columna), new Aire(fila, columna));
	}
	
	//Ya no hay mas islas voladoras
	
	public boolean tieneAire() {
		return true;
	}

	public boolean tieneTierra() {
		return true;
	}
	
	public Celda getCeldaEnFilaColumna(int fila, int columna){
		
		return this.mapaAlto.get(fila).get(columna);
		
	}
	
	private boolean ubicarCapaInferior(Ubicable ubicable, Celda celda ){
		
		return celda.ubicarCapaInferior(ubicable);
		
	}
	
	private boolean ubicarCapaSuperior(Ubicable ubicable, Celda celda ){
		
		return celda.ubicarCapaSuperior(ubicable);
		
	}
	
	public boolean ubicar(Ubicable ubicable, Celda celda ){
		
		if(!this.ubicarCapaInferior(ubicable, celda )){
			if(!this.ubicarCapaSuperior(ubicable, celda )) return false;
			else ubicable.actualizarUbicacion(celda.getCapaSuperior());
		}
		else ubicable.actualizarUbicacion(celda.getCapaInferior());
		return true;
		
	}
	
	public ArrayList<Celda> obtenerCeldasAdyacentesAlUbicable(Ubicable unUbicable){
		
		Coordenada coordenadaActual = unUbicable.getUbicacionActual();
		ArrayList<Celda> listaDeCeldasAdyacentes = new ArrayList<Celda>();
		ArrayList<Coordenada> coordenadasAdyacentes = coordenadaActual.getCoordenadasAdyacentes(coordenadaActual);
		
		for(int i=0;i<coordenadasAdyacentes.size();i++){
			Coordenada unaCoordenada = coordenadasAdyacentes.get(i);
			if(this.coordenadaEstaDentroDelMapa(unaCoordenada)){
				listaDeCeldasAdyacentes.add(this.getCeldaEnFilaColumna(unaCoordenada.fila(),unaCoordenada.columna()));
			}
		}	
		return listaDeCeldasAdyacentes;
		
	}

	private boolean coordenadaEstaDentroDelMapa(Coordenada unaCoordenada) {
		
		boolean filaEstaDentroDelMapa = unaCoordenada.fila()>0 && unaCoordenada.fila()<this.ancho;
		boolean columnaEstaDentroDelMapa = unaCoordenada.columna()>0 && unaCoordenada.columna()<this.alto;
		return filaEstaDentroDelMapa && columnaEstaDentroDelMapa;
	}
}