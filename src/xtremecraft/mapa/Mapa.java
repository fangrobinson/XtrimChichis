package xtremecraft.mapa;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.Recurso;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Ubicable;


public class Mapa {
	
	private TreeMap<Integer, TreeMap<Integer, Celda>> mapaAlto;
	private int alto;
	private int ancho;
	private static int cantidadInicialDeRecursoPorTerreno = 1000;
	private ArrayList<Tierra> terrenosBasesJugadores;
	private ArrayList<EstrategiaUbicacion> estrategiasUbicacion;
	
	public Mapa(int cantidadJugadores) {
		
		if (cantidadJugadores <= 1){
		
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		
		}
		
		this.alto = this.decidirAlto();
		this.ancho = this.decidirAncho();
		this.terrenosBasesJugadores = new ArrayList<Tierra>();
		this.estrategiasUbicacion = new ArrayList<EstrategiaUbicacion>();
		this.estrategiasUbicacion.add(new EstrategiaUbicacionMinerales());
		this.estrategiasUbicacion.add(new EstrategiaUbicacionGasVespeno());
		this.estrategiasUbicacion.add(new EstrategiaUbicacionDosJugadores());
		this.estrategiasUbicacion.add(new EstrategiaUbicacionTresJugadores());
		this.estrategiasUbicacion.add(new EstrategiaUbicacionCuatroJugadores());
		
		rellenarMapa();
		
		ubicarBases(cantidadJugadores);
		
		ubicarRecursos();	
		
	}
	
	public static int getCantidadDeRecursoInicialPorTerreno(){
		
		return cantidadInicialDeRecursoPorTerreno;
		
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
	
	private int decidirAlto() {
		
		return 25;
		
	}

	private int decidirAncho() {
		
		return 25;
		
	}
	
	private Celda obtenerCeldaAdecuada(int fila, int columna){
		
		return new Celda(new Tierra(fila, columna), new Aire(fila, columna));
		
	}
	
	//Ya no hay mas islas voladoras. Dejamos este comentario porque es gracioso.
	
	public boolean tieneAire() {
		
		return true;
		
	}

	public boolean tieneTierra() {
		
		return true;
		
	}
	
	public Celda getCeldaEnFilaColumna(int fila, int columna){
		
		return this.mapaAlto.get(fila).get(columna);
		
	}
	
	public Terreno ubicarCapaInferior(Ubicable ubicable, Celda celda ){
		
		return celda.ubicarCapaInferior(ubicable);
		
	}
	
	private Terreno ubicarCapaSuperior(Ubicable ubicable, Celda celda ){
		
		return celda.ubicarCapaSuperior(ubicable);
		
	}
	
	public void ubicar(Ubicable ubicable, Celda celda){
		
		Terreno terreno = null;
		try{
			terreno = this.ubicarCapaInferior(ubicable, celda);
		}
		catch(RuntimeException NoSePudoOcuparElTerrenoException){
			terreno = this.ubicarCapaSuperior(ubicable, celda);
		}
		ubicable.actualizarUbicacion(terreno);
		
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
		
	public Tierra obtenerTerrenoJugador(int numeroJugador) {
		
		int posicion = numeroJugador - 1;
		return this.terrenosBasesJugadores.get(posicion);
		
	}
	
	public void ubicarRecursos(){
		
		int estrategiaMinerales = 0;
		int estrategiaGasVespeno = 1;
		EstrategiaUbicacion estrategiaUbicacion = this.estrategiasUbicacion.get(estrategiaMinerales);
		ArrayList<Coordenada> coordenadasUbicacionRecursos = estrategiaUbicacion.getCoordenadasDeUbicacion();
		Coordenada coordenadaActual;
		for(int posicion=0;posicion<coordenadasUbicacionRecursos.size();posicion++){
		
			coordenadaActual = coordenadasUbicacionRecursos.get(posicion);
			this.agregarNodoMineral(coordenadaActual);

		}
		estrategiaUbicacion = this.estrategiasUbicacion.get(estrategiaGasVespeno);
		coordenadasUbicacionRecursos = estrategiaUbicacion.getCoordenadasDeUbicacion();
		for(int posicion=0;posicion<coordenadasUbicacionRecursos.size();posicion++){
		
			coordenadaActual = coordenadasUbicacionRecursos.get(posicion);
			this.agregarVolcanGasVespeno(coordenadaActual);
		
		}
		
	}
	
	private void agregarNodoMineral(Coordenada coordenada) {
		
		int cantidadMineral = cantidadInicialDeRecursoPorTerreno;
		this.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getCapaInferior().agregarRecursoNatural(new MinaDeMinerales(cantidadMineral));
		
	}
	
	private void agregarVolcanGasVespeno(Coordenada coordenada) {
		
		int cantidadGasVespeno = cantidadInicialDeRecursoPorTerreno;
		this.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getCapaInferior().agregarRecursoNatural(new VolcanGasVespeno(cantidadGasVespeno));
		
	}

	public void ubicarBases(int cantidadJugadores) {
		
		EstrategiaUbicacion estrategiaUbicacion = this.estrategiasUbicacion.get(cantidadJugadores);
		ArrayList<Coordenada> coordenadasUbicacion = estrategiaUbicacion.getCoordenadasDeUbicacion();
		for(int posicion=0;posicion<coordenadasUbicacion.size();posicion++){
		
			Coordenada estaCoordenada = coordenadasUbicacion.get(posicion);
			Tierra terreno =  (Tierra) this.getCeldaEnFilaColumna(estaCoordenada.fila(),estaCoordenada.columna()).getCapaInferior();
			this.terrenosBasesJugadores.add(terreno);
		
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
			if (recursoActual.getClass() == MinaDeMinerales.class && !recursoActual.estaSiendoExplotado()){
			
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