package xtremecraft.vista;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorInformacionParaElUsuario extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	public SectorInformacionParaElUsuario(Partida partida){
		
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(1000, 150));
		
		JPanel informacionJugador = new SectorInfoJugadorActual(partida.quienJuega());
		
		add(informacionJugador);
		
		setVisible(true);
		
	}

	@Override
	public void update(Observable partida, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
