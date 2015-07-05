package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public abstract class VistaIdentificable extends Vista implements IdentificableVisible{
	
	public VistaIdentificable(String nombre, ArrayList<Vista> vistasInferiores){
		
		super(nombre, vistasInferiores);
		
	}
	
	@Override
	public void paintComponent(Graphics grafico) {

		super.paintComponent(grafico);
		setMaximumSize(new Dimension (10,10));
               
    }
	
	public void setJugador(int jugador){
		
		this.numeroJugador = jugador;
		JLabel numeroIdentificador = new JLabel();
        numeroIdentificador.setText(Integer.toString(this.numeroJugador));
        numeroIdentificador.setMaximumSize(new Dimension (7,7));
        this.add(numeroIdentificador);
        this.revalidate();

	}

}
