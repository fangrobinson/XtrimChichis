package xtremecraft.vista;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorInformacionParaElUsuario extends JPanel{

	private static final long serialVersionUID = 1L;

	public SectorInformacionParaElUsuario(Partida partida){
		
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(1000, 150));
		
		JPanel informacionJugador = new SectorInfoJugadorActual(partida.quienJuega());
		
		add(informacionJugador);
		
		setVisible(true);
		
	}
	
}
