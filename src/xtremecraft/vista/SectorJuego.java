package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class SectorJuego extends JPanel implements MouseListener{
	
	private MapaObservable mapaTerrestre;
	private SectorSeleccionInformacion info;
	private static final long serialVersionUID = 1L;

	public SectorJuego(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		
		//Mapa de Juego
		this.mapaTerrestre = new MapaObservable(partida, vistas);
		this.mapaTerrestre.setPreferredSize(new Dimension(600,600));
		this.mapaTerrestre.setVisible(true);
		
		//Info de Unidades Edificios Etc
		this.info = new SectorSeleccionInformacion(this.mapaTerrestre,partida.getMapa());
		this.info.setPreferredSize(new Dimension(350, 600));
		this.info.setVisible(true);
		this.add(this.info);
		
		this.setBorder(BorderFactory.createLineBorder(mapaTerrestre.getBackground(), 5));
		this.add(this.mapaTerrestre);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.getComponentAt(e.getX(), e.getY()) == this.mapaTerrestre){
			Vista vistaClikeada = (Vista) this.mapaTerrestre.getComponentAt(e.getX(), e.getY());
			ArrayList<String> accionesVista = vistaClikeada.devolverAcciones();
			this.info.actualizarAccionesDisponibles(accionesVista);
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
	
}
