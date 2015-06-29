package xtremecraft.vista;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import javax.swing.JPanel;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Partida;

public class MapaObservable extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 7787529771808926374L;
	
	private Mapa modeloReal;
	private HashMap<Class<?>, Class<?>> vistas;
	private TreeMap<Integer, TreeMap<Integer, Vista>> mapaVisible;
	
	public MapaObservable(){};
	
	//public MapaObservable(Mapa mapa, int x, int y){
	public MapaObservable(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		this.addMouseListener(this);
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
	public void mouseClicked(MouseEvent e) {
				
		//TODO: revisar esto considerando la implementacion de la ventana de estados:
		
		//Vista vistaSeleccionada = (Vista) this.findComponentAt(x,y);
		
		/*Coordenada ubicacion = vistaSeleccionada.getCoordenada();
		this.modeloReal.getCeldaEnFilaColumna(ubicacion.fila(), ubicacion.columna());*/
		
		/*ArrayList<String> opciones = vistaSelecciona
		 * da.mostrarOpcionesAccion();
		
		JPanel panel = new JPanel();
		JLabel mensaje = new JLabel("Elija la opcion que desee:");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox menuOpciones = new JComboBox((ComboBoxModel) opciones);
		
		panel.add(mensaje);		
		
		panel.add(menuOpciones);
		
		add(panel);
		panel.setVisible(true);
		
		setVisible(true);
		*/
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseClicked(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
