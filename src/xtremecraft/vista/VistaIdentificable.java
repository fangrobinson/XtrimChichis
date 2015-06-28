package xtremecraft.vista;

import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public abstract class VistaIdentificable extends Vista implements IdentificableVisible{
	
	@Override
	public void paintComponent(Graphics grafico) {

		super.paintComponent(grafico);
        JLabel numero = new JLabel(Integer.toString(this.numeroJugador));
        this.add(numero);
       
    }
	
	public void setJugador(int jugador){
		this.numeroJugador = jugador;
	}

}
