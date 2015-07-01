package xtremecraft.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorSeleccionInformacion extends JPanel{

	private static final long serialVersionUID = 1L;
	private SectorAccionesDisponibles panelAccionesDisponibles;
	
	public SectorSeleccionInformacion(HashMap<Class<?>, ArrayList<AbstractAction>> accionesDisponibles, MapaObservable mapaObservable, Partida partida){
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension (350, 600));

		setBackground(new Color(255,255,255));
				
		JPanel panelSeleccionObjeto = new SectorEstadoObjetoSeleccionado(mapaObservable,partida.getMapa());
		panelSeleccionObjeto.setMaximumSize(new Dimension (200, 600));
		this.add(panelSeleccionObjeto);
		
		this.panelAccionesDisponibles = new SectorAccionesDisponibles(accionesDisponibles,mapaObservable,partida);
		this.panelAccionesDisponibles.setMaximumSize(new Dimension (150, 600));
		setBackground(new Color(255,255,255));
		panelAccionesDisponibles.setVisible(true);
		
		this.add(panelAccionesDisponibles);
		
		setVisible(true);
		

	}
	
}
