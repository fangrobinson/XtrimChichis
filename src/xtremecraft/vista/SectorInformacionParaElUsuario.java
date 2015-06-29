package xtremecraft.vista;

import java.awt.Dimension;
import java.util.HashMap;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorInformacionParaElUsuario extends JPanel{

	private static final long serialVersionUID = 1L;
	
	

	public SectorInformacionParaElUsuario(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException{
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(1000, 150));
		
		
		//TODO: Agregar Informacion del Jugador.
		JPanel informacionJugador = new SectorInformacionJugadorActual(partida);
		this.add(informacionJugador);
		setVisible(true);
		
		
		SectorReferenciasDelMapa referencias = new SectorReferenciasDelMapa(vistas);
		referencias.setVisible(true);
		this.add(referencias);
		//TODO: Poner visible las cosas.
		
	}

}
