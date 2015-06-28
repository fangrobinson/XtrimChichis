package xtremecraft.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class VistaDeposito extends Vista implements IdentificableVisible{
	
	public VistaDeposito(){
		
		this.color = new Color(128,128,128);
	
	}
	
	@Override
	public void paintComponent(Graphics grafico) {

        super.paintComponent(grafico);
        grafico.setColor(color);
        JLabel numero = new JLabel(Integer.toString(this.numeroJugador));
        this.add(numero);
        Dimension dimension = getSize();

        grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);
    }
	
	public void setJugador(int jugador){
		this.numeroJugador = jugador;
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		return null;
		// TODO Auto-generated method stub
		
	}
	
}
