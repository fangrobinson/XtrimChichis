package xtremecraft.vista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xtremecraft.partida.Jugador;

public class SectorInfoJugadorActual extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	Observable miJugador;
	JLabel tituloPanel;
	JLabel cantidadDeMinerales;
	JLabel cantidadDeGasVespeno;
	JLabel cantidadDePoblacion;
	
	public SectorInfoJugadorActual(Jugador jugador){
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		
		this.miJugador = jugador;
		this.tituloPanel = new JLabel ("SUMINISTROS Y RECURSOS");
		
		this.add(this.tituloPanel);
		
		this.cantidadDeMinerales = new JLabel ("Minerales:"+this.cantidadDeMinerales(jugador));
		this.cantidadDeGasVespeno = new JLabel ("Gas vespeno:"+this.cantidadDeGasVespeno(jugador));
		this.cantidadDePoblacion = new JLabel ("Poblacion disponible:"+this.cantidadDePoblacion(jugador));
		
		this.add(this.cantidadDeMinerales);
		this.add(this.cantidadDeGasVespeno);
		this.add(this.cantidadDePoblacion);
		
		setVisible(true);
		
	}
	
	private String cantidadDePoblacion(Jugador jugador) {
		
		return Integer.toString(jugador.getPoblacionDisponible());
		
	}

	private String cantidadDeGasVespeno(Jugador jugador) {
		
		return Integer.toString(jugador.getCantidadDeGasVespeno());
		
	}

	private String cantidadDeMinerales(Jugador jugador) {
		
		return Integer.toString(jugador.getCantidadDeMinerales());
		
	}

	@Override
	public void update(Observable jugador, Object arg1) {
		
		this.generarVistaPanel(jugador);
	
	}

	private void generarVistaPanel(Observable jugadorActual) {
		
		Jugador jugador = (Jugador) jugadorActual;
		this.cantidadDeMinerales = new JLabel ("Minerales:"+this.cantidadDeMinerales(jugador));
		this.cantidadDeGasVespeno = new JLabel ("Gas vespeno:"+this.cantidadDeGasVespeno(jugador));
		this.cantidadDePoblacion = new JLabel ("Poblacion disponible:"+this.cantidadDePoblacion(jugador));
		
		this.cantidadDeMinerales.revalidate();
		this.cantidadDeGasVespeno.revalidate();
		this.cantidadDePoblacion.revalidate();
		
		this.repaint();
		
	}
	
}
