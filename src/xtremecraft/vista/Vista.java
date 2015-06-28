package xtremecraft.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

//import javax.swing.JLabel;
import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;

public abstract class Vista extends JPanel{
	
	private static final long serialVersionUID = -973667959098244571L;
	
	Color color;
	Coordenada ubicacion;
	int numeroJugador;
	
	public void paintComponent(Graphics grafico) {

        super.paintComponent(grafico);
        grafico.setColor(color);
        Dimension dimension = getSize();

        grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);
    }

	public void setCoordenada(Coordenada coordenada) {
		this.ubicacion = coordenada;
	}

}
