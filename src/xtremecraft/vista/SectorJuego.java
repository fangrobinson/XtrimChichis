package xtremecraft.vista;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorJuego extends JPanel{
	
	private MapaObservable mapaTerrestre;
	private SectorSeleccionInformacion sectorSeleccionInformacion;
	
	private static final long serialVersionUID = 1L;

	public SectorJuego(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		

		this.mapaTerrestre = new MapaObservable(this, partida, vistas);
		this.mapaTerrestre.setMaximumSize(new Dimension (500, 500));
		this.mapaTerrestre.setPreferredSize(new Dimension(500, 400));
		this.mapaTerrestre.setVisible(true);
		

		this.sectorSeleccionInformacion = new SectorSeleccionInformacion(this.mapaTerrestre,partida);
		this.sectorSeleccionInformacion.setPreferredSize(new Dimension(400, 600));
		this.sectorSeleccionInformacion.setVisible(true);
		this.add(this.sectorSeleccionInformacion);
		
		this.setBorder(BorderFactory.createLineBorder(mapaTerrestre.getBackground(), 5));
		this.add(this.mapaTerrestre);
		
	}
	
	public void agregarObservadoresDeVistas(Vista vista){
		
		this.sectorSeleccionInformacion.agregarObservadoresAVista(vista);
		
	}

	public MapaObservable getMapaVisible() {
		return this.mapaTerrestre;
	}

}
