package xtremecraft.vista;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xtremecraft.mapa.Mapa;

public class SectorSeleccionObjeto extends JPanel implements MouseListener,Observer{

	private static final long serialVersionUID = 1L;
	private MapaObservable mapaObservable;
	JLabel nombreCapaSuperior = new JLabel("AIRE");
	JLabel estadoSuperior = new JLabel("");
	JLabel nombreCapaInferior = new JLabel("TIERRA");
	JLabel estadoInferior = new JLabel("");
	
	public SectorSeleccionObjeto(MapaObservable mapaObservable, Mapa mapa) {
		
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20,20,10,10));
		setBackground(new Color(255,255,255));
		this.mapaObservable = mapaObservable;
		this.mapaObservable.agregarObservadorAVistas(this);
		
		estadoSuperior.addMouseListener(this);
		estadoInferior.addMouseListener(this);
		
		this.add(nombreCapaSuperior);
		this.add(estadoSuperior);
		this.add(nombreCapaInferior);
		this.add(estadoInferior);
		
		setVisible(true);
		
	}
	
	@Override
	public void update(Observable vistaObservada, Object arg1) {
		
		ObservablePosicionMouse vista = (ObservablePosicionMouse) vistaObservada;
		int x = vista.getCoordenadaActualMouse().fila();
		int y = vista.getCoordenadaActualMouse().columna();
		this.estadoSuperior.setText("Coordenada: "+ Integer.toString(x)+","+ Integer.toString(y));
		this.estadoInferior.setText("Coordenada: "+ Integer.toString(x)+","+ Integer.toString(y));
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
