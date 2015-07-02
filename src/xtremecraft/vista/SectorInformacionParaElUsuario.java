package xtremecraft.vista;

import java.awt.Dimension;
import java.util.HashMap;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorInformacionParaElUsuario extends JPanel{

	private static final long serialVersionUID = 1L;
	
	

	public SectorInformacionParaElUsuario(Partida partida, HashMap<Class<?>, Class<?>> vistas, MapaObservable mapaVistas) throws InstantiationException, IllegalAccessException{
		
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(1000, 200));

		JPanel informacionJugador = new SectorInformacionJugadorActual(partida, mapaVistas);
		informacionJugador.setPreferredSize(new Dimension(400, 100));
		this.add(informacionJugador);
		setVisible(true);
		
		
		SectorReferenciasDelMapa referencias = new SectorReferenciasDelMapa(vistas);
		referencias.setPreferredSize(new Dimension(600, 100));
		this.add(referencias);
		referencias.setVisible(true);

	}

}
