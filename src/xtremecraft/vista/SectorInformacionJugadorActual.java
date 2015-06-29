package xtremecraft.vista;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;

public class SectorInformacionJugadorActual extends JPanel implements Observer, MouseListener{

	private static final long serialVersionUID = 1L;
	
	Partida partida;
	JLabel tituloPanel;
	JLabel cantidadMinerales;
	JLabel cantidadGasVespeno;
	JLabel cantidadPoblacion;
	JButton btnPasarTurno = new JButton("PASAR TURNO");
	
	public SectorInformacionJugadorActual(Partida partida){
		
		partida.agregarObservadorDeJugadores(this,partida.quienJuega());
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20,20,10,10));
		
		this.partida = partida;
		
		this.tituloPanel = new JLabel ("SUMINISTROS Y RECURSOS " + this.partida.quienJuega().nombre());
		
		this.cantidadMinerales = new JLabel ("Minerales:"+this.cantidadDeMinerales(this.partida.quienJuega()));
		this.cantidadGasVespeno = new JLabel ("Gas vespeno:"+this.cantidadDeGasVespeno(this.partida.quienJuega()));
		this.cantidadPoblacion = new JLabel ("Poblacion disponible:"+this.cantidadDePoblacion(this.partida.quienJuega()));
	
		btnPasarTurno.addMouseListener(this);
		this.add(btnPasarTurno);
		
		this.add(this.tituloPanel);
		this.add(this.cantidadMinerales);
		this.add(this.cantidadGasVespeno);
		this.add(this.cantidadPoblacion);
		
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
				
		this.tituloPanel.setText("SUMINISTROS Y RECURSOS " + this.partida.quienJuega().nombre());
		this.cantidadMinerales.setText("Minerales:"+this.cantidadDeMinerales(jugador));
		this.cantidadGasVespeno.setText("Gas vespeno:"+this.cantidadDeGasVespeno(jugador));
		this.cantidadPoblacion.setText("Poblacion disponible:"+this.cantidadDePoblacion(jugador));
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		partida.quienJuega().pasarTurno();
		
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
