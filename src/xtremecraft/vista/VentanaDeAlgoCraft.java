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
		setPreferredSize(new Dimension(1000, 750));
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		//Agregar aca los componentes de este Frame
		JPanel sectorJuego = new SectorJuego(partida);
		sectorJuego.setPreferredSize(new Dimension(1000, 600));
		setVisible(true);
		JPanel panelInformacion = new SectorInformacionParaElUsuario(partida);
		panelInformacion.setPreferredSize(new Dimension(1000, 150));
		
		//Ponerlos visibles
		sectorJuego.setVisible(true);
		panelInformacion.setVisible(true);
		this.add(sectorJuego);
		this.add(panelInformacion);
		pack();
		setResizable(false);
		setVisible(true);
		
	}

}