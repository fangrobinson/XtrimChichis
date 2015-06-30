package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.mapa.Mapa;

public class SectorSeleccionInformacion  extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	public SectorSeleccionInformacion(MapaObservable mapaObservable,Mapa mapa){
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setBackground(new Color(255,255,255));
		
		JPanel panelSeleccionObjeto = new SectorEstadoObjetoSeleccionado(mapaObservable,mapa);
		
		this.add(panelSeleccionObjeto);
		
		
		ArrayList<String> accionesIniciales = new ArrayList<String>();
		AccionesDisponibles panelAccionesDisponibles = new AccionesDisponibles(accionesIniciales);
		panelAccionesDisponibles.setVisible(true);
		
		this.add(panelAccionesDisponibles);
		
		setVisible(true);

	}
	
	public void actualizarAccionesDisponibles(ArrayList<String> accionesNuevas){
		AccionesDisponibles panelAccionesDisponibles = new AccionesDisponibles(accionesNuevas);
		panelAccionesDisponibles.setVisible(true);
		
		this.add(panelAccionesDisponibles);
	}

}
