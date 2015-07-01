package xtremecraft.vista;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorJuego extends JPanel{
	
	private MapaObservable mapaTerrestre;
	private SectorSeleccionInformacion info;
	private HashMap<Class<?>, ArrayList<AbstractAction>> accionesDisponibles;
	
	private static final long serialVersionUID = 1L;

	public SectorJuego(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		

		this.mapaTerrestre = new MapaObservable(partida, vistas);
		this.mapaTerrestre.setMaximumSize(new Dimension (500, 500));
		this.mapaTerrestre.setPreferredSize(new Dimension(500,500));
		this.mapaTerrestre.setVisible(true);
		
		this.accionesDisponibles = AccionesDisponibles.generarAcciones(partida,this.mapaTerrestre);

		this.info = new SectorSeleccionInformacion(this.accionesDisponibles,this.mapaTerrestre,partida);
		this.info.setPreferredSize(new Dimension(400, 600));
		this.info.setVisible(true);
		this.add(this.info);
		
		this.setBorder(BorderFactory.createLineBorder(mapaTerrestre.getBackground(), 5));
		this.add(this.mapaTerrestre);
		
		
	}

}
