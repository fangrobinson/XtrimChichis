package xtremecraft.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Partida;

@SuppressWarnings("serial")
public class SectorAccionesDisponibles extends JPanel implements Observer{
	
	private MapaObservable mapaObservable;
	private Partida partida;
	
	public SectorAccionesDisponibles(MapaObservable mapaVisible, Partida partida){
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		this.mapaObservable = mapaVisible;
		this.partida = partida;
		mapaVisible.agregarObservadorAVistas(this);
		setVisible(true);
					
	}

	
	public void actualizarPanel(ArrayList<AbstractAction> accionesSeleccionado) {
		
		this.removeAll();
		
		for(int i=0; i<accionesSeleccionado.size(); i++){
			
			JButton botonAccion = new JButton(accionesSeleccionado.get(i)); 
			this.add(botonAccion);
			
		}
		
	}
	
	@Override
	public void update(Observable obs, Object arg1) {
		
		ObservableSeleccionado observable = (ObservableSeleccionado) obs;
		Coordenada coordenadasSeleccionado = observable.getCoordenadaActualSeleccionado();
		Class<? extends Vista> claseSeleccionado = observable.getClaseVista();
		HashMap<Class<?>, ArrayList<AbstractAction>> acciones = GeneradorDeAccionesDisponibles.generarAcciones(this.partida,this.mapaObservable,coordenadasSeleccionado, this);
		ArrayList<AbstractAction> accionesSeleccionado = acciones.get(claseSeleccionado);
		this.actualizarPanel(accionesSeleccionado);
		
	}
	
}
