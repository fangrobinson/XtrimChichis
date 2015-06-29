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
	
	JLabel titulo = new JLabel("ESTADO ACTUAL SELECCIONADO"); 
	JLabel nombreObservable = new JLabel("");
	JLabel estadoObservable = new JLabel("");
	JLabel coordenadaObservable = new JLabel("");
	
	public SectorSeleccionObjeto(MapaObservable mapaObservable, Mapa mapa) {
		
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20,20,10,10));
		setBackground(new Color(255,255,255));
		this.mapaObservable = mapaObservable;
		this.mapaObservable.agregarObservadorAVistas(this);
		
		//TODO:para seleccionar en teoria... despues lo implementamos bien
		estadoObservable.addMouseListener(this);
		
		this.add(titulo);
		this.add(nombreObservable);
		this.add(coordenadaObservable);
		this.add(estadoObservable);
		
		
		setVisible(true);
		
	}
	
	@Override
	public void update(Observable observableSeleccionado, Object arg1) {
		
		ObservableSeleccionado vista = (ObservableSeleccionado) observableSeleccionado;
		nombreObservable.setText(vista.getNombre());
		coordenadaObservable.setText(vista.getCoordenadaImprimible());
		estadoObservable.setText(vista.getEstado());
		
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
