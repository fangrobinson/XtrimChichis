package xtremecraft.vista;

import java.awt.Dimension;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorSeleccionInformacion extends JPanel{

	private static final long serialVersionUID = 1L;
	private SectorAccionesDisponibles panelAccionesDisponibles;
	private SectorEstadoObjetoSeleccionado panelSeleccionObjeto;
	public SectorSeleccionInformacion( MapaObservable mapaObservable, Partida partida){
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension (400, 800));
				
		this.panelSeleccionObjeto = new SectorEstadoObjetoSeleccionado(mapaObservable,partida.getMapa());
		this.panelSeleccionObjeto.setPreferredSize(new Dimension (400, 400));
		this.add(panelSeleccionObjeto);
		
		this.panelAccionesDisponibles = new SectorAccionesDisponibles(mapaObservable,partida);
		this.panelAccionesDisponibles.setMaximumSize(new Dimension (400, 400));
		panelAccionesDisponibles.setVisible(true);
		
		this.add(panelAccionesDisponibles);
		
		setVisible(true);
		
	}

	public void agregarObservadoresAVista(Vista vista) {
		
		vista.agregarObservador(this.panelSeleccionObjeto);
		vista.agregarObservador(this.panelAccionesDisponibles);
		
	}

}
