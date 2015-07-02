package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import javax.swing.JPanel;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.NoSePudoOcuparElTerrenoException;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Partida;
import xtremecraft.unidades.UbicacionNoValidaException;
import xtremecraft.unidades.Unidad;

public class MapaObservable extends JPanel implements Observer{
	
	private static final long serialVersionUID = 7787529771808926374L;
	
	private Mapa modeloReal;
	private SectorJuego sector;
	private HashMap<Class<?>, Class<?>> vistas;
	private TreeMap<Integer, TreeMap<Integer, Vista>> mapaVisible;
	private Coordenada coordenadaUltimoClickeado;
	private Coordenada coordenadaOrigenMovimiento;
	private boolean estrategiaDeMovimientoIniciada;


	public MapaObservable(){};
	
	public MapaObservable(SectorJuego sectorJuego, Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Mapa mapa = partida.getMapa();
		this.sector = sectorJuego;
		this.modeloReal = mapa;
		this.vistas = vistas;
		this.mapaVisible = new TreeMap<Integer, TreeMap<Integer, Vista>> ();
		this.estrategiaDeMovimientoIniciada = false;
		
		setBounds(mapa.ancho(), mapa.alto(), 800, 800);
		this.setLayout(new GridLayout(mapa.ancho(), mapa.alto()));
		
				
		TreeMap<Integer, TreeMap<Integer, Celda>> mapaIterable = this.modeloReal.devolverMapaEstatico();
		
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			this.mapaVisible.put(i,new TreeMap<Integer,Vista>());
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Celda celda = mapaIterable.get(i).get(j);
				Terreno terrenoInferior = celda.getCapaInferior();
				Class<?> vistaClase;
				Vista vistaNueva = null;
				Observable observable = null;
				
				//TODO: refactor considerar cambios a identificable.
				if (!terrenoInferior.tieneRecursos()){
					vistaClase = this.vistas.get(terrenoInferior.getClass());
					vistaNueva = (Vista) vistaClase.newInstance();
					observable = (Observable)terrenoInferior;
				}else{
					vistaClase = this.vistas.get(terrenoInferior.getRecurso().getClass());
					vistaNueva = (Vista) vistaClase.newInstance();
					observable = (Observable)terrenoInferior.getRecurso();
				}if(terrenoInferior.estaOcupado()){
					Identificable identificable = (Identificable)terrenoInferior.getUbicableEnTerreno();
					int numero = identificable.getJugador();
					vistaClase = this.vistas.get(terrenoInferior.getUbicableEnTerreno().getClass());
					IdentificableVisible identificableVisible = (IdentificableVisible) vistaClase.newInstance();
					identificableVisible.setJugador(numero);
					vistaNueva = (Vista) identificableVisible;
					observable = (Observable)terrenoInferior.getUbicableEnTerreno();
					
				}	
				
				observable.addObserver(vistaNueva);
				vistaNueva.setCoordenada(terrenoInferior.getCoordenada());
				
				this.mapaVisible.get(i).put(j, vistaNueva);
				
				vistaNueva.paintComponents(getGraphics());
				vistaNueva.setMaximumSize(new Dimension(25,25));
				add(vistaNueva);
				
			}
		}
		this.agregarObservadorAVistas(this);
		
	}
	
	public void actualizarVistaEnCoordenada(Coordenada coordenada) throws InstantiationException, IllegalAccessException{
		
		Vista vistaCelda = this.mapaVisible.get(coordenada.fila()).get(coordenada.columna());
		this.mapaVisible.get(coordenada.fila()).remove(coordenada.columna());
		int n = this.getComponentZOrder(vistaCelda);
		this.remove(n);
		
		Celda celdaReal = this.modeloReal.getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna());
		Terreno terrenoInferior = celdaReal.getCapaInferior();
		Class<?> vistaClase;
		Vista vistaNueva = null;
		Observable observable = null;
		
		//TODO: refactor considerar cambios a identificable.
		if (!terrenoInferior.tieneRecursos()){
			
			vistaClase = this.vistas.get(terrenoInferior.getClass());
			vistaNueva = (Vista) vistaClase.newInstance();
			observable = (Observable)terrenoInferior;
			
		}else{
			
			vistaClase = this.vistas.get(terrenoInferior.getRecurso().getClass());
			vistaNueva = (Vista) vistaClase.newInstance();
			observable = (Observable)terrenoInferior.getRecurso();
			
		}if(terrenoInferior.estaOcupado()){
			
			Identificable identificable = (Identificable)terrenoInferior.getUbicableEnTerreno();
			int numero = identificable.getJugador();
			vistaClase = this.vistas.get(terrenoInferior.getUbicableEnTerreno().getClass());
			IdentificableVisible identificableVisible = (IdentificableVisible) vistaClase.newInstance();
			identificableVisible.setJugador(numero);
			Vista vistaOcupante = (Vista) identificableVisible;
			vistaNueva = vistaOcupante;
			observable = (Observable)terrenoInferior.getUbicableEnTerreno();
			vistaNueva.setMaximumSize(new Dimension(10,10));
			
		}
		
		observable.addObserver(vistaNueva);
		
		vistaNueva.setCoordenada(terrenoInferior.getCoordenada());
		vistaNueva.paintComponents(getGraphics());
		vistaNueva.setMaximumSize(new Dimension(25,25));
		
		this.add(vistaNueva, n);
		this.mapaVisible.get(coordenada.fila()).put(coordenada.columna(),vistaNueva);
		this.sector.agregarObservadoresDeVistas(vistaNueva);
		this.agregarObservadorAVista(vistaNueva, this);
		
		revalidate();
		repaint();			
		
		
	}
	
	public void agregarObservadorAVistas(Observer observador){
		
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Vista vistaActual = this.mapaVisible.get(i).get(j);
				this.agregarObservadorAVista(vistaActual, observador);
			}
		}
		
	}
	
	public void agregarObservadorAVista(Vista nuevaVistaObservada, Observer nuevoObservador){
		
		nuevaVistaObservada.agregarObservador(nuevoObservador);
		
	}
	
	public TreeMap<Integer, TreeMap<Integer, Vista>> getVistas() {
		
		return this.mapaVisible;
		
	}

	@Override
	public void update(Observable obs, Object arg1) {
		
		ObservableSeleccionado observable = (ObservableSeleccionado) obs;
		this.coordenadaUltimoClickeado = observable.getCoordenadaActualSeleccionado();
		if(this.estrategiaDeMovimientoIniciada){
			
			Celda celdaOrigen = this.modeloReal.getCeldaEnFilaColumna(this.coordenadaOrigenMovimiento.fila(), this.coordenadaOrigenMovimiento.columna());
			Celda celdaDestino = this.modeloReal.getCeldaEnFilaColumna(this.coordenadaUltimoClickeado.fila(), this.coordenadaUltimoClickeado.columna());
			Terreno terrenoDestino = celdaDestino.getCapaInferior();
			Unidad unidadAMover = (Unidad) celdaOrigen.getUbicableEnInferior();

			try{
				unidadAMover.actualizarUbicacion(terrenoDestino);
			}catch(UbicacionNoValidaException | NoSePudoOcuparElTerrenoException destinoInvalido){
				new MensajeDeError("No se puede mover a la locacion seleccionada");
			}
			try {
				this.actualizarVistaEnCoordenada(this.coordenadaOrigenMovimiento);
				this.actualizarVistaEnCoordenada(this.coordenadaUltimoClickeado);
				this.estrategiaDeMovimientoIniciada = false;
				
			} catch (InstantiationException | IllegalAccessException e) {
				new MensajeDeError("Error interno del sistema");
			}
		}
		
	}

	public void comenzarMovimiento(Coordenada coordenadaInicioMovimiento) {
		//TODO: ver que el jugador que inicio el movimiento es el mismo que la termina.
		//tal vez hacer reset de estrategiaDeMovimientoIniciada al pasar el turno.
		this.estrategiaDeMovimientoIniciada = true;
		this.coordenadaOrigenMovimiento = coordenadaInicioMovimiento;
		
	}		

}
	

