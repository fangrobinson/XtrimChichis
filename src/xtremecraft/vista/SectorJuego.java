package xtremecraft.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
//import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorJuego extends JPanel implements MouseMotionListener{
	
	private MapaObservableTerrestre mapaTerrestre;
	private MapaObservableAereo mapaAereo;
	private static final long serialVersionUID = 1L;

	public SectorJuego(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException {
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		
		addMouseMotionListener(this);
		
		//Info de Unidades Edificios Etc
		SectorSeleccionInformacion info = new SectorSeleccionInformacion();
		info.setPreferredSize(new Dimension(350, 600));
		info.setVisible(true);
		this.add(info);
		//this.add(Box.createRigidArea(getPreferredSize()));
		//Mapa de Juego
		this.mapaTerrestre = new MapaObservableTerrestre(partida, vistas);
		this.mapaTerrestre.setPreferredSize(new Dimension(600,600));
		this.mapaTerrestre.setVisible(true);
		
		this.mapaAereo = new MapaObservableAereo(partida, vistas);
		this.mapaAereo.setPreferredSize(new Dimension(600,600));
		this.mapaAereo.setVisible(false);
		
		
		this.setBorder(BorderFactory.createLineBorder(mapaTerrestre.getBackground(), 5));
		this.add(this.mapaTerrestre);
		this.add(this.mapaAereo);
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		 Component componente = this.findComponentAt(e.getX(), e.getY());
		 if (componente == this.mapaAereo){
			 this.mapaAereo.setVisible(false);
			 this.mapaTerrestre.setVisible(true);
		 }
		 if (componente == this.mapaTerrestre){
			 this.mapaTerrestre.setVisible(false);
			 this.mapaAereo.setVisible(true);
		 }
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouseDragged(e);
	}
	
}
