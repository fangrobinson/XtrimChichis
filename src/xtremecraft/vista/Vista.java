package xtremecraft.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;

public abstract class Vista extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = -973667959098244571L;
	
	Color color;
	Coordenada ubicacion;
	int numeroJugador;
	private ObservablePosicionMouse mouseObservable = new ObservablePosicionMouse();
	
	
	public Vista(){
		
		this.addMouseListener(this);
		
	}
	
	public void paintComponent(Graphics grafico) {

        super.paintComponent(grafico);
        grafico.setColor(color);
        Dimension dimension = getSize();
        grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);
        
    }

	public void setCoordenada(Coordenada coordenada) {
		
		this.ubicacion = coordenada;
		
	}
	
	public Coordenada getCoordenada(){
		
		return this.ubicacion;
		
	}
	
	public Color getColor(){
		return this.color;
	}

	public void agregarObservador(Observer observador) {
		
		this.mouseObservable.addObserver(observador);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent click) {
		
		this.mouseObservable.setCoordenada(this.ubicacion);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
