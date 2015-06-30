package xtremecraft.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PedirJugadores extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static ArrayList<String> nombresJugadores = new ArrayList<String>();
	private static int cantidadJugadores;
	private static Integer numeroDeJugadores[] = {2,3,4};
	
	public PedirJugadores(VentanaDeAlgoCraft  ventanaJuego){
		
		setTitle("Cantidad de jugadores");
		setSize(250,100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Ingrese cantidad de jugadores:"));	
		JComboBox<Integer> menuIngresoJugadores = new JComboBox<Integer>(numeroDeJugadores);
		panel.add(menuIngresoJugadores);
		menuIngresoJugadores.addActionListener(new MenuJugadoresListener(this, ventanaJuego));
		add(panel);
		setVisible(true);
		
	}
	
	static class MenuJugadoresListener implements ActionListener{
		
		JFrame frame;
		VentanaDeAlgoCraft ventanaJuego;
		
		public MenuJugadoresListener(JFrame frame, VentanaDeAlgoCraft  ventanaJuego){
			this.frame = frame;
			this.ventanaJuego = ventanaJuego;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<Integer> menuIngresoJugadores = (JComboBox<Integer>) e.getSource();
			int elementoSeleccionado = menuIngresoJugadores.getSelectedIndex();
			int cantidadDeJugadores = numeroDeJugadores[elementoSeleccionado];
			frame.setVisible(false);
			cantidadJugadores = cantidadDeJugadores;
			pedirNombres(nombresJugadores, cantidadDeJugadores, ventanaJuego).setVisible(true);
		
		}
		
	}
	
	static JFrame pedirNombres(ArrayList<String> nombresJugadores, int cantidadDeJugadores, VentanaDeAlgoCraft ventanaJuego){
		JFrame pantallaNombres = new JFrame();
		pantallaNombres.setTitle("Jugadores");
		pantallaNombres.setVisible(true);
		pantallaNombres.setResizable(false);
		
		pantallaNombres.setSize(400,150);
		pantallaNombres.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		
		panel.add(Box.createRigidArea(pantallaNombres.getPreferredSize()));
		
		panel.add(new JLabel("Nombre jugador:"));
		
		panel.add(Box.createRigidArea(pantallaNombres.getPreferredSize()));
		
		int numeroJugador = nombresJugadores.size()+1;
		
		JTextField cajaTexto = new JTextField(30);
		cajaTexto.setText("Jugador"+" "+ Integer.toString(numeroJugador));
		
		cajaTexto.addMouseListener(new IngresoNombreJugadorListener());
		
		panel.add(cajaTexto);
		
		panel.add(Box.createRigidArea(pantallaNombres.getPreferredSize()));
		
		JButton botonIngreso = new JButton("Ingresar Jugador");
		
		
		botonIngreso.addActionListener(new BotonNombreJugadorListener(cajaTexto, pantallaNombres, ventanaJuego));
		
		panel.add(botonIngreso);
		
		pantallaNombres.add(panel);
		
		pantallaNombres.setVisible(true);
		
		return pantallaNombres;
		
	}
	
	static class IngresoNombreJugadorListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent click) {
			
			JTextField cajaTexto = (JTextField) click.getSource();
			cajaTexto.setText("");
			
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
	
	static class BotonNombreJugadorListener implements ActionListener{
		
		private JTextField cajaTexto;
		private JFrame frame;
		private VentanaDeAlgoCraft  ventanaJuego;
		
		public BotonNombreJugadorListener(JTextField cajaTexto, JFrame frame, VentanaDeAlgoCraft  ventanaJuego){
			this.cajaTexto = cajaTexto;
			this.frame = frame;
			this.ventanaJuego = ventanaJuego;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String nombreNuevoJugador = cajaTexto.getText();
			if(nombresJugadores.contains(nombreNuevoJugador)){
				new MensajeDeError("Este nombre ya fue ingresado por otro jugador. Elige otro nombre.");
			}else{		
				nombresJugadores.add(nombreNuevoJugador);
			}				
			if(nombresJugadores.size() == cantidadJugadores){
				frame.setVisible(false);
				this.ventanaJuego.agregarPartida(nombresJugadores);

			}
			else{
				int numeroJugador = nombresJugadores.size()+1;
				cajaTexto.setText("Jugador"+" "+ Integer.toString(numeroJugador));
			}
			
		}

		
		
		
	}
	
}
