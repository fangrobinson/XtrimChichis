package xtremecraft.mapa;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

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
	
	public Mapa(int cantidadJugadores) {
		
		if (cantidadJugadores <= 1){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		this.alto = this.decidirAlto(cantidadJugadores);
		this.ancho = this.decidirAncho(cantidadJugadores);
		this.terrenosBasesJugadores = new ArrayList<Tierra>();
		rellenarMapa();	
		//TODO: armar bien este algoritmo. Lo armo para que ubique las bases aleatoriamente,
		//para los tests, pero hay que armarlo bien.
		ubicarBases(cantidadJugadores);
		ubicarRecursosMinerales();
		
	}
	
	public TreeMap<Integer, TreeMap<Integer, Celda>> devolverMapaEstatico(){
		return this.mapaAlto;
	}
	
	public int alto(){
		return this.alto;
	}
	
	public int ancho(){
		return this.ancho;
	}

	private void rellenarMapa() {
		
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
		
		ArrayList<Coordenada>coordenadasParaUbicacionDeRecurso = obtenerCoordenadasParaUbicacionDeMinerales();
		
		for(int posicion=0;posicion<coordenadasParaUbicacionDeRecurso.size();posicion++){
			
			Coordenada coordenadaActual = coordenadasParaUbicacionDeRecurso.get(posicion); 
			Tierra posibleTerrenoParaRecurso = (Tierra)this.getCeldaEnFilaColumna(coordenadaActual.fila(),coordenadaActual.columna()).getCapaInferior();
				
			int aleatorioEleccion = new Random().nextInt(2);
			boolean aleatorioEleccionEsMineral = (aleatorioEleccion == 0);
			boolean aleatorioEleccionEsGasVespeno = (aleatorioEleccion == 1);
			boolean terrenoEstaDisponible = ((!posibleTerrenoParaRecurso.tieneRecursos()) && (!this.terrenosBasesJugadores.contains(posibleTerrenoParaRecurso)));
			
			if(terrenoEstaDisponible && aleatorioEleccionEsMineral){
				this.agregarNodoMineral(posibleTerrenoParaRecurso.fila(), posibleTerrenoParaRecurso.columna());
			}
			if(terrenoEstaDisponible && aleatorioEleccionEsGasVespeno){
				this.agregarVolcanGasVespeno(posibleTerrenoParaRecurso.fila(), posibleTerrenoParaRecurso.columna());
			}
				
		}
	
	}
	
	private ArrayList<Coordenada> obtenerCoordenadasParaUbicacionDeMinerales(){
		
		ArrayList<Coordenada> coordenadasParaUbicacionDeRecurso = new ArrayList<Coordenada>();
		ArrayList<Coordenada> celdasAlrededorDeEstaBase = new ArrayList<Coordenada>();
		Coordenada coordenadaBaseActual;
		
		for(int i=0;i<this.terrenosBasesJugadores.size();i++){
			Tierra terrenoActual = this.terrenosBasesJugadores.get(i);
			//obtengo un listado de coordenadas en un radio aleatorio de la base actual:
			int radioAleatorioAlrededorDeLaBase = this.numeroAleatorioEntreMinimoYMaximo(2,3);
			coordenadaBaseActual = terrenoActual.getCoordenada();
			celdasAlrededorDeEstaBase = coordenadaBaseActual.getCoordenadasEnRadio(radioAleatorioAlrededorDeLaBase);
			//De las coordenadas obtenidas selecciono solo aquellas que estan dentro de las dimensiones del mapa:
			for(int j=0;j<celdasAlrededorDeEstaBase.size();j++){
				Coordenada unaCoordenada = celdasAlrededorDeEstaBase.get(j); 
				if(this.coordenadaEstaDentroDelMapa(unaCoordenada)){
					coordenadasParaUbicacionDeRecurso.add(unaCoordenada);
				}
			}
		}
		return coordenadasParaUbicacionDeRecurso;
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