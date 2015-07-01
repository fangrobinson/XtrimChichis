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
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Partida;

public class MapaObservable extends JPanel{
	
	private static final long serialVersionUID = 7787529771808926374L;
	
	private Mapa modeloReal;
	private HashMap<Class<?>, Class<?>> vistas;
	private TreeMap<Integer, TreeMap<Integer, Vista>> mapaVisible;

	private SectorJuego sector;

	public MapaObservable(){};
	
	public MapaObservable(SectorJuego sectorJuego, Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Mapa mapa = partida.getMapa();
		this.sector = sectorJuego;
		this.modeloReal = mapa;
		this.vistas = vistas;
		this.mapaVisible = new TreeMap<Integer, TreeMap<Integer, Vista>> ();
		
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
					Vista vistaOcupante = (Vista) identificableVisible;
					
					vistaNueva.agregarOcupante(vistaOcupante);
					
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
		
	}
	
	public void borrarARolete(){
		
		this.remove(0);

		this.remove(0);

		this.remove(0);

		this.remove(0);
		
		revalidate();
		repaint();
		
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
			
			this.sector.agregarObservadoresDeVistas(vistaOcupante);
			
			//agrego
			
			vistaNueva.agregarOcupante(vistaOcupante);
			
			observable = (Observable)terrenoInferior.getUbicableEnTerreno();
			
		}
		
		observable.addObserver(vistaNueva);
		vistaNueva.setCoordenada(terrenoInferior.getCoordenada());		
		vistaNueva.paintComponents(getGraphics());
		vistaNueva.setMaximumSize(new Dimension(25,25));
		
		this.add(vistaNueva, n);
		this.mapaVisible.get(coordenada.fila()).put(coordenada.columna(),vistaNueva);
		
		this.sector.agregarObservadoresDeVistas(vistaNueva);
		
		revalidate();
		repaint();			
		
		
	}
	
	public void agregarObservadorAVistas(Observer observador){
		
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Vista vistaActual = this.mapaVisible.get(i).get(j);
				vistaActual.agregarObservador(observador);
			}
		}
		
	}
	
	public TreeMap<Integer, TreeMap<Integer, Vista>> getVistas() {
		
		return this.mapaVisible;
	}

}
	

