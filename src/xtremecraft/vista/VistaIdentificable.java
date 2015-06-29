package xtremecraft.vista;

import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public abstract class VistaIdentificable extends Vista implements IdentificableVisible{
	
	@Override
	public void paintComponent(Graphics grafico) {

		super.paintComponent(grafico);
        JLabel numeroIdentificador = new JLabel();
        numeroIdentificador.setText(Integer.toString(this.numeroJugador));
        this.add(numeroIdentificador);
       
    }
	
	public void setJugador(int jugador){
		this.numeroJugador = jugador;
	}

}
