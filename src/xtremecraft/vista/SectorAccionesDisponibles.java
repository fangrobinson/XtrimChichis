package xtremecraft.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Partida;

public class SectorAccionesDisponibles extends JPanel implements Observer{
	
	private static final long serialVersionUID = -973667959098244571L;
	private MapaObservable mapaObservable;
	private Partida partida;
	
	public SectorAccionesDisponibles(MapaObservable mapaVisible, Partida partida){
		
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
		HashMap<Class<?>, ArrayList<AbstractAction>> acciones = GeneradorDeAccionesDisponibles.generarAcciones(this.partida,this.mapaObservable,coordenadasSeleccionado);
		ArrayList<AbstractAction> accionesSeleccionado = acciones.get(claseSeleccionado);
		this.actualizarPanel(accionesSeleccionado);
		
	}
	
}