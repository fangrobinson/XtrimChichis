package xtremecraft.vista;

import java.awt.Color;

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
		
		setVisible(true);

	}

}
