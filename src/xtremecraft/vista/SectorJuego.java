package xtremecraft.vista;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BorderFactory;
//import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorJuego extends JPanel{

	private static final long serialVersionUID = 1L;

	public SectorJuego(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException {
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		
		//Info de Unidades Edificios Etc
		SectorSeleccionInformacion info = new SectorSeleccionInformacion();
		info.setPreferredSize(new Dimension(350, 600));
		info.setVisible(true);
		this.add(info);
		//this.add(Box.createRigidArea(getPreferredSize()));
		//Mapa de Juego
		MapaObservable mapa = new MapaObservable(partida, vistas);
		mapa.setPreferredSize(new Dimension(600,600));
		mapa.setVisible(true);
		this.setBorder(BorderFactory.createLineBorder(mapa.getBackground(), 5));
		this.add(mapa);
	}
	
}
