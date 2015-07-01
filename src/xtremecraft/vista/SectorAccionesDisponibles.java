package xtremecraft.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Partida;

public class SectorAccionesDisponibles extends JPanel implements Observer{
	
	private static final long serialVersionUID = -973667959098244571L;
	Coordenada coordenadasSeleccionado;
	private Class<?> claseSeleccionado;
	private MapaObservable mapaObservable;
	private Partida partida;
	
	public SectorAccionesDisponibles(HashMap<Class<?>, ArrayList<AbstractAction>> accionesDisponibles, MapaObservable mapaVisible, Partida partida){
		
		this.mapaObservable = mapaVisible;
		this.partida = partida;
		mapaVisible.agregarObservadorAVistas(this);
		setVisible(true);
					
	}

	
	public void actualizarPanel(JComboBox<String> accionesDisponiblesJugador) {
		
		accionesDisponiblesJugador.setVisible(true);
		this.add(accionesDisponiblesJugador);
		this.setVisible(true);
		
	}


	@Override
	public void update(Observable obs, Object arg1) {
		
		ObservableSeleccionado observable = (ObservableSeleccionado) obs;
		this.coordenadasSeleccionado = observable.getCoordenadaActualSeleccionado();
		this.claseSeleccionado = observable.getClass();
		ArrayList<AbstractAction> acciones = new ArrayList<AbstractAction>();
		for(int i=0; i<acciones.size(); i++){
						
		}
		
	}
	
}
