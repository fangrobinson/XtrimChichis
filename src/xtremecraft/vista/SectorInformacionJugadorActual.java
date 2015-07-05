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
	
	private Partida partida;
	private JLabel tituloPanel;
	private JLabel cantidadMinerales;
	private JLabel cantidadGasVespeno;
	private JLabel cantidadPoblacion;
	private JButton btnPasarTurno = new JButton("PASAR TURNO");

	private JLabel nombreJugador;

	private MapaObservable mapaVistas;
	
	public SectorInformacionJugadorActual(Partida partida, MapaObservable mapaVistas){
		
		partida.agregarObservadorDeJugadores(this,partida.quienJuega());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		this.partida = partida;
		this.mapaVistas = mapaVistas;
		this.tituloPanel = new JLabel ( "SUMINISTROS Y RECURSOS" );
		this.nombreJugador = new JLabel(this.partida.quienJuega().nombre());
		
		this.cantidadMinerales = new JLabel ("Minerales:"+this.getStringCantidadDeMinerales(this.partida.quienJuega()));
		this.cantidadGasVespeno = new JLabel ("Gas vespeno:"+this.getStringCantidadDeGasVespeno(this.partida.quienJuega()));
		this.cantidadPoblacion = new JLabel ("Poblacion disponible:"+this.getStringCantidadDePoblacion(this.partida.quienJuega()));
	
		btnPasarTurno.addMouseListener(this);
		this.add(btnPasarTurno);
		
		this.add(this.tituloPanel);
		this.add(this.nombreJugador);
		this.add(this.cantidadMinerales);
		this.add(this.cantidadGasVespeno);
		this.add(this.cantidadPoblacion);
		
		setVisible(true);
		
	}
	
	private String getStringCantidadDePoblacion(Jugador jugador) {
		
		return Integer.toString(jugador.getPoblacionDisponible());
		
	}

	private String getStringCantidadDeGasVespeno(Jugador jugador) {
		
		return Integer.toString(jugador.getCantidadDeGasVespeno());
		
	}

	private String getStringCantidadDeMinerales(Jugador jugador) {
		
		return Integer.toString(jugador.getCantidadDeMinerales());
		
	}

	@Override
	public void update(Observable jugador, Object arg1) {
		
		this.generarVistaPanel(jugador);
	
	}

	private void generarVistaPanel(Observable jugadorActual) {
			
		Jugador jugador = (Jugador) jugadorActual;
				
		this.nombreJugador.setText(this.partida.quienJuega().nombre());
		this.cantidadMinerales.setText("Minerales:"+this.getStringCantidadDeMinerales(jugador));
		this.cantidadGasVespeno.setText("Gas vespeno:"+this.getStringCantidadDeGasVespeno(jugador));
		this.cantidadPoblacion.setText("Poblacion disponible:"+this.getStringCantidadDePoblacion(jugador));
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		partida.quienJuega().pasarTurno();
		mapaVistas.pasarTurno();
		this.generarVistaPanel(partida.quienJuega());
		
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
