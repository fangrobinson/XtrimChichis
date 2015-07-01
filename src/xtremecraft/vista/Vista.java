package xtremecraft.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Identificable;

public abstract class Vista extends JPanel implements MouseListener,Observer{
	
	private static final long serialVersionUID = -973667959098244571L;
	
	Color color;
	Coordenada ubicacion;
	int numeroJugador;
	protected String nombre;
	
	private ObservableSeleccionado observableSeleccionado = new ObservableSeleccionado();
//	private Vista ocupante;

	
	public Vista(String nombreVista, String estadoInicial){
		
		this.addMouseListener(this);
		this.nombre = nombreVista;
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.observableSeleccionado.setNombre(this.nombre);
		this.observableSeleccionado.setEstado(estadoInicial);
		this.observableSeleccionado.setAcciones(this.devolverAcciones());
		this.observableSeleccionado.setClaseVista(this.getClass());
		
	}
	
	public void paintComponent(Graphics grafico) {

        super.paintComponent(grafico);
        grafico.setColor(color);
        Dimension dimension = getSize();
        grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);
        
    }

	public void setCoordenada(Coordenada coordenada) {
		
		this.ubicacion = coordenada;
		this.observableSeleccionado.setCoordenada(this.ubicacion);
		
	}
	
	public Coordenada getCoordenada(){
		
		return this.ubicacion;
		
	}
	
	public Color getColor(){
		
		return this.color;
		
	}
	
	public abstract ArrayList<String> devolverAcciones();
	
	
	@Override
	public void update(Observable observado, Object arg) {
		
		Identificable identificableVisibleEnMapa = (Identificable)observado;
		//this.observableSeleccionado.setNombre(this.nombre);
		this.observableSeleccionado.setEstado(identificableVisibleEnMapa.getEstadoImprimible());
		
	}
	
	public void agregarObservador(Observer observador) {
		
		//observador avisa cuando el mouse hace click sobre esta celda visible:
		this.observableSeleccionado.addObserver(observador);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent click) {
		//Cuando hago click en el panel, se notifica la ventana de informacion dinamica:
		//Coordenada ubicacionActual = new Coordenada(click.getX(),click.getY());
		this.observableSeleccionado.notificarObservado();
		
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

	public void setNombre() {
		// TODO Auto-generated method stub
		
	}

	public void borrarObservador(Observer observador) {
		
		this.observableSeleccionado.deleteObserver(observador);
		
	}

	/*
	public void agregarOcupante(Vista vistaOcupante) {
		
		this.ocupante = vistaOcupante;
		this.add(vistaOcupante);
		this.revalidate();
		
	}
	
	public void cambiarOcupante (Vista nuevoOcupante){
		
		this.desocuparVista();
		this.ocupante = nuevoOcupante;
		this.add(nuevoOcupante);
		this.revalidate();
		
	}
	
	public void desocuparVista(){
		
		this.ocupante.setVisible(false);
		this.ocupante = null;
		this.revalidate();
		
	}*/

}
