package xtremecraft.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;

public class SectorAccionesDisponibles extends JPanel implements Observer{
	
	private static final long serialVersionUID = -973667959098244571L;
	Coordenada coordenadasSeleccionado;
	
	public SectorAccionesDisponibles(HashMap<Class<?>, ArrayList<AbstractAction>> accionesDisponibles, MapaObservable mapaVisible){
		
		mapaVisible.agregarObservadorAVistas(this);
		setVisible(true);
		
		/*
		DefaultListModel<String> accionesProcesadas = new DefaultListModel<String>();
		
		this.list = new JList<String>(accionesProcesadas);
		this.list.setBackground(getBackground());
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setLayoutOrientation(JList.VERTICAL);
		*/
		

		
	}

	
	public void actualizarPanel(JComboBox<String> accionesDisponiblesJugador) {
		
		accionesDisponiblesJugador.setVisible(true);
		this.add(accionesDisponiblesJugador);
		this.setVisible(true);
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		ObservableSeleccionado observable = (ObservableSeleccionado) arg0;
		this.coordenadasSeleccionado = observable.getCoordenadaActualSeleccionado();
		
	}
	
}
