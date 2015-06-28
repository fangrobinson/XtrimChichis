package xtremecraft.vista;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class VentanaDeAlgoCraft  extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public VentanaDeAlgoCraft(Partida partida) throws InstantiationException, IllegalAccessException{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Algo Craft Game");
		setPreferredSize(new Dimension(1200,800));
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		//Agregar aca los componentes de este Frame
		JPanel panelInformacion = new SectorInformacionParaElUsuario(partida);
		this.add(panelInformacion);
		
		//Ponerlos visibles
		panelInformacion.setVisible(true);
		pack();
		
		setVisible(true);
		
	}

}