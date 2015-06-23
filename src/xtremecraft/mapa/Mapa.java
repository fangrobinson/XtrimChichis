package xtremecraft.mapa;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import xtremecraft.mapa.Celda;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.Recurso;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Ubicable;


public class Mapa {
	
	private TreeMap<Integer, TreeMap<Integer, Celda>> mapaAlto;
	private int alto;
	private int ancho;
	private static int maximoRecursoPorUnidadTerreno = 1000;
	public ArrayList<Tierra> terrenosBasesJugadores;
	public Mapa(int cant_jugadores) {
		
		if (cant_jugadores <= 1){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		this.alto = this.decidirAlto(cant_jugadores);
		this.ancho = this.decidirAncho(cant_jugadores);
		this.terrenosBasesJugadores = new ArrayList<Tierra>();
		rellenarMapa(alto, ancho, cant_jugadores);	
		//TODO: armar bien este algoritmo. Lo armo para que ubique las bases aleatoriamente,
		//para los tests, pero hay que armarlo bien.
		ubicarBases(cant_jugadores);
		ubicarRecursosMinerales();
		
	}

	private void rellenarMapa(int alto, int ancho, int cant_jugadores) {
		
		this.mapaAlto = new TreeMap<Integer, TreeMap<Integer, Celda>>();
		
		for(int fila = 0; fila < this.alto ;fila = fila + 1) {
			this.mapaAlto.put(fila, new TreeMap<Integer, Celda>());
			for(int columna = 0; columna < this.ancho; columna = columna + 1) {
				this.mapaAlto.get(fila).put(columna, obtenerCeldaAdecuada(fila, columna));
			}
		}
				
	}
	
	private int decidirAlto(int cant_jugadores) {
		
		return cant_jugadores * 50;
		
	}

	private int decidirAncho(int cant_jugadores) {
		
		return cant_jugadores * 50;
		
	}
	
	private Celda obtenerCeldaAdecuada(int fila, int columna){
		
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
			if(!this.ubicarCapaSuperior(ubicable, celda )){
				return false;
			}
			else ubicable.actualizarUbicacion(celda.getCapaSuperior());
		}
		else ubicable.actualizarUbicacion(celda.getCapaInferior());
		return true;
		
	}
	
	public ArrayList<Celda> obtenerCeldasAdyacentesAlUbicable(Ubicable unUbicable){
		
		Coordenada coordenadaActual = unUbicable.getUbicacionActual();
		ArrayList<Celda> listaDeCeldasAdyacentes = new ArrayList<Celda>();
		ArrayList<Coordenada> coordenadasAdyacentes = coordenadaActual.getCoordenadasAdyacentes();
		
		for(int i=0;i<coordenadasAdyacentes.size();i++){
			Coordenada unaCoordenada = coordenadasAdyacentes.get(i);
			if(this.coordenadaEstaDentroDelMapa(unaCoordenada)){
				listaDeCeldasAdyacentes.add(this.getCeldaEnFilaColumna(unaCoordenada.fila(),unaCoordenada.columna()));
			}
		}	
		return listaDeCeldasAdyacentes;
		
	}

	private boolean coordenadaEstaDentroDelMapa(Coordenada unaCoordenada) {
		
		boolean filaEstaDentroDelMapa = (unaCoordenada.fila()>=0) && (unaCoordenada.fila()<this.alto);
		boolean columnaEstaDentroDelMapa = (unaCoordenada.columna()>=0) && (unaCoordenada.columna()<this.ancho);
		return filaEstaDentroDelMapa && columnaEstaDentroDelMapa;
		
	}
	
	public ArrayList<Celda> obtenerCeldasEnRadio(Ubicable unUbicable, int radio) {
		
		Coordenada coordenadaActual = unUbicable.getUbicacionActual();
		ArrayList<Celda> listaDeCeldasEnRadio = new ArrayList<Celda>();
		ArrayList<Coordenada> coordenadasEnRadio = coordenadaActual.getCoordenadasEnRadio(radio);
		
		for(int i=0;i<coordenadasEnRadio.size();i++){
			Coordenada unaCoordenada = coordenadasEnRadio.get(i);
			if(this.coordenadaEstaDentroDelMapa(unaCoordenada)){
				listaDeCeldasEnRadio.add(this.getCeldaEnFilaColumna(unaCoordenada.fila(),unaCoordenada.columna()));
			}
		}	
		listaDeCeldasEnRadio.add(this.getCeldaEnFilaColumna(coordenadaActual.fila(),coordenadaActual.columna()));
		return listaDeCeldasEnRadio;
		
	}

	public void liberarEspacioCorrespondienteA(Ubicable ubicable) {
		
		int fila = ubicable.getUbicacionActual().fila();
		int columna = ubicable.getUbicacionActual().columna();
		Celda celda = this.getCeldaEnFilaColumna(fila,columna);
		if(ubicable.estaElevado()) celda.liberarCapaSuperior();
		else celda.liberarCapaInferior();
		
	}
	
	private int numeroAleatorioEntreMinimoYMaximo(int min,int max){
		
		Random aleatorio = new Random();
		int cantidadAleatoria = aleatorio.nextInt(max-min) + min;
		return cantidadAleatoria;
		
	}
	
	public Tierra obtenerTerrenoJugador(int numeroJugador) {
		
		return this.terrenosBasesJugadores.get(numeroJugador);
		
	}
	

	private void ubicarRecursosMinerales() {
		
		for(int i=0;i<this.terrenosBasesJugadores.size();i++){
			Tierra terrenoActual = this.terrenosBasesJugadores.get(i);
			//obtengo un listado de coordenadas en un radio aleatorio de la base actual:
			int radioAleatorioAlrededorDeLaBase = this.numeroAleatorioEntreMinimoYMaximo(2,3);
			Coordenada coordenadaBase = terrenoActual.getCoordenada();
			ArrayList<Coordenada> celdasAlrededorDeEstaBase = coordenadaBase.getCoordenadasEnRadio(radioAleatorioAlrededorDeLaBase);
			//selecciono (aleatoriamente) los terrenos en los que quiero ubicar un recurso alrededor de la base:
			for(int j=0;j<celdasAlrededorDeEstaBase.size();j++){
				Coordenada coordenadaActual = celdasAlrededorDeEstaBase.get(j); 
				if(!this.coordenadaEstaDentroDelMapa(coordenadaActual)) continue;
				Tierra posibleTerrenoParaRecurso = (Tierra)this.getCeldaEnFilaColumna(coordenadaActual.fila(),coordenadaActual.columna()).getCapaInferior();
				
				int aleatorioEleccion = new Random().nextInt(2);
				boolean aleatorioEleccionEsMineral = (aleatorioEleccion == 0);
				boolean aleatorioEleccionEsGasVespeno = (aleatorioEleccion == 1);
				boolean terrenoDisponible = ((!posibleTerrenoParaRecurso.tieneRecursos()) && (!this.terrenosBasesJugadores.contains(posibleTerrenoParaRecurso)));
				
				if(terrenoDisponible && aleatorioEleccionEsMineral){
					this.agregarNodoMineral(posibleTerrenoParaRecurso.fila(), posibleTerrenoParaRecurso.columna());
				}
				else if(terrenoDisponible && aleatorioEleccionEsGasVespeno){
					this.agregarVolcanGasVespeno(posibleTerrenoParaRecurso.fila(), posibleTerrenoParaRecurso.columna());
				}
			}
		}	
		
	}

	private void agregarNodoMineral(int fila, int columna) {
		
		int cantidadAleatoriaMineral = this.numeroAleatorioEntreMinimoYMaximo(1,Mapa.maximoRecursoPorUnidadTerreno);
		this.getCeldaEnFilaColumna(fila, columna).getCapaInferior().agregarRecursoNatural(new MinaDeMinerales(cantidadAleatoriaMineral));
		
	}
	
	private void agregarVolcanGasVespeno(int fila, int columna) {
		
		int cantidadAleatoriaMineral = this.numeroAleatorioEntreMinimoYMaximo(1,Mapa.maximoRecursoPorUnidadTerreno);
		this.getCeldaEnFilaColumna(fila, columna).getCapaInferior().agregarRecursoNatural(new VolcanGasVespeno(cantidadAleatoriaMineral));
		
	}

	//TODO:ARREGLAR!!!!!!!!!!!!!!
	public void ubicarBases(int cantidadJugadores) {
		
		Tierra posibleTerreno;
		int filaAleatoria;
		int columnaAleatoria;
		while(this.terrenosBasesJugadores.size()<cantidadJugadores){
			columnaAleatoria = new Random().nextInt(this.ancho);
			filaAleatoria = new Random().nextInt(this.alto);
			posibleTerreno = (Tierra)this.getCeldaEnFilaColumna(filaAleatoria,columnaAleatoria).getCapaInferior();
			if(!this.terrenosBasesJugadores.contains(posibleTerreno)){
				this.terrenosBasesJugadores.add(posibleTerreno);
			}
				
		}
		
	}
	
	public void pasarTiempo() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Terreno> obtenerTerrenosConRecursos(){
		
		ArrayList<Terreno> terrenos = new ArrayList<Terreno>(); 
		for(Entry<Integer, TreeMap<Integer, Celda>> filas : this.mapaAlto.entrySet()) { 
			for(Entry<Integer, Celda> columnas : filas.getValue().entrySet()) {
				Celda celda = columnas.getValue();
				if(celda.getCapaInferior().tieneRecursos()){
					terrenos.add(celda.getCapaInferior());
				}
			}
		}
		return terrenos;
	}
	
	public Terreno obtenerTerrenoConMinaDeMinerales(){
		
		ArrayList<Terreno> listaTerrenos = this.obtenerTerrenosConRecursos();
		for (int i = 0; i < listaTerrenos.size(); i++){
			Recurso recursoActual = listaTerrenos.get(i).getRecurso(); 
			if (recursoActual.getClass() == MinaDeMinerales.class){
				return listaTerrenos.get(i);
			}
		}
		return null;
		
	}
	
	public Terreno obtenerTerrenoConVolcanGasVespeno(){
		
		ArrayList<Terreno> listaTerrenos = this.obtenerTerrenosConRecursos();
		for (int i = 0; i < listaTerrenos.size(); i++){
			Recurso recursoActual = listaTerrenos.get(i).getRecurso(); 
			if (recursoActual.getClass() == VolcanGasVespeno.class){
				return listaTerrenos.get(i);
			}
		}
		return null;
		
	}
	

}