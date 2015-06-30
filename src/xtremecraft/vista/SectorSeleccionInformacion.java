package xtremecraft.vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorSeleccionInformacion extends JPanel{

	private static final long serialVersionUID = 1L;
	private AccionesDisponibles panelAccionesDisponibles;
	//private Partida partida;
	//private MapaObservable mapa;
	
	public SectorSeleccionInformacion(MapaObservable mapaObservable, Partida partida){
		
		//this.mapa = mapaObservable;
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setBackground(new Color(255,255,255));
				
		JPanel panelSeleccionObjeto = new SectorEstadoObjetoSeleccionado(mapaObservable,partida.getMapa());
		
		this.add(panelSeleccionObjeto);
		
		this.panelAccionesDisponibles = new AccionesDisponibles();
		setBackground(new Color(255,255,255));
		panelAccionesDisponibles.setVisible(true);
		
		this.add(panelAccionesDisponibles);
		
		setVisible(true);
		

	}
	
}
