package xtremecraft.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.recursos.MinaDeMinerales;

public class MapaObservable extends JPanel implements Observer{
	
	private static final long serialVersionUID = 7787529771808926374L;
	
	private Mapa modeloReal;
	private HashMap<Class<?>, Class<?>> vistas;
	private TreeMap<Integer, TreeMap<Integer, Vista>> mapaVisible;

	private ObservableSeleccionado seleccionado1;

	private ObservableSeleccionado seleccionado2;
	
	public MapaObservable(){};
	
	//public MapaObservable(Mapa mapa, int x, int y){
	public MapaObservable(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Mapa mapa = partida.getMapa();
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
				//TODO: refactor considerar cambios a identificable.
				if(terrenoInferior.estaOcupado()){
					Identificable identificable = (Identificable)terrenoInferior.getUbicableEnTerreno();
					int numero = identificable.getJugador();
					vistaClase = this.vistas.get(terrenoInferior.getUbicableEnTerreno().getClass());
					IdentificableVisible identificableVisible = (IdentificableVisible) vistaClase.newInstance();
					identificableVisible.setJugador(numero);
					vistaNueva = (Vista) identificableVisible;
					
					Observable observable = (Observable)terrenoInferior.getUbicableEnTerreno();
					observable.addObserver(vistaNueva);
				}else{
					if (!terrenoInferior.tieneRecursos()){
						vistaClase = this.vistas.get(terrenoInferior.getClass());
						vistaNueva = (Vista) vistaClase.newInstance();
						Observable observable = (Observable)terrenoInferior;
						observable.addObserver(vistaNueva);
					}else{
						vistaClase = this.vistas.get(terrenoInferior.getRecurso().getClass());
						vistaNueva = (Vista) vistaClase.newInstance();
						Observable observable = (Observable)terrenoInferior.getRecurso();
						observable.addObserver(vistaNueva);
					}
			
					
					
				}
				
				vistaNueva.setCoordenada(terrenoInferior.getCoordenada());
				
				this.mapaVisible.get(i).put(j, vistaNueva);
				
				vistaNueva.paintComponents(getGraphics());
				vistaNueva.agregarObservador(this);
				add(vistaNueva);
				
			}
		}
		
	}
	
	public void agregarObservadorAVistas(Observer observador){
		
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Vista vistaActual = this.mapaVisible.get(i).get(j);
				vistaActual.agregarObservador(observador);
			}
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (this.seleccionado1 == null){
			this.seleccionado1 = (ObservableSeleccionado) o;
		}else{
			this.seleccionado2 = (ObservableSeleccionado) o;
		}
	}
	
	//public void agregarAccionConstruirMineral(JLabel label){
			
		
		
	}//
	

	class AccionCrearRecolectorDeMineral implements ActionListener{
		
		private Tierra tierraParaConstruccion;
		private Jugador jugador;
		private MapaObservable mapa;
		
		public AccionCrearRecolectorDeMineral(MapaObservable mapa,Jugador jugador,Identificable seleccionado){
			
			//try{
				this.jugador = jugador;
				this.mapa = mapa;
				this.tierraParaConstruccion = (Tierra) seleccionado;
				
			//catch (ClassCastException){
				
				//throw new esteElementoNoPuedeRealizarLaAccionPedidaException();
			//}
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
				Coordenada coordenadaSeleccionado = this.tierraParaConstruccion.getCoordenada();
				this.jugador.crearRecolectorDeMineral(this.tierraParaConstruccion);
				//this.mapa.actualizarConRecolector(coordenadaSeleccionado);
			}

}
	

