package xtremecraft.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xtremecraft.partida.Partida;

@SuppressWarnings("serial")
public class IngresoNombres extends JFrame {
	
	JTextField cajaTexto = new JTextField(30);
	JPanel panel = new JPanel();
	JLabel mensaje = new JLabel("Nombre jugador:");
	JButton btnEnter = new JButton("Ingresar Jugador");

	public IngresoNombres(final ArrayList<String> nombresJugadores, final int cantidadDeJugadores){
		
		setTitle("JUGADORES");
		setVisible(true);
		setResizable(false);
		java.awt.Container contentPane = getContentPane();
		new BoxLayout(contentPane,BoxLayout.PAGE_AXIS);
		setSize(400,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.add(Box.createRigidArea(getPreferredSize()));
		
		panel.add(mensaje);
		
		panel.add(Box.createRigidArea(getPreferredSize()));
		
		int numeroJugador = nombresJugadores.size()+1;
		cajaTexto.setText("Jugador"+" "+ Integer.toString(numeroJugador));
		
		cajaTexto.addMouseListener(new MouseListener(){
		
			@Override
			public void mouseClicked(MouseEvent click) {
				cajaTexto.setText("");
				
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
						
		});
		
		
		panel.add(cajaTexto);
						
		panel.add(Box.createRigidArea(getPreferredSize()));
		
		panel.add(btnEnter);
			
		btnEnter.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent click){
				
				String nombreNuevoJugador = cajaTexto.getText();
				
				if(nombresJugadores.contains(nombreNuevoJugador)){
					new MensajeDeError("Este nombre ya fue ingresado por otro jugador. Elige otro nombre.");
				}else{		
					nombresJugadores.add(nombreNuevoJugador);
				}				
				if(nombresJugadores.size() == cantidadDeJugadores){
					setVisible(false);
					try {
						Partida partida = new Partida(nombresJugadores);
						//new VentanaDeJuego(partida);
						new VentanaDeAlgoCraft(partida);
					} catch (InstantiationException | IllegalAccessException e) {
						new MensajeDeError("Error no se pudo generar el mapa");
					}
					//System.exit(0);
				}
				else{
					int numeroJugador = nombresJugadores.size()+1;
					cajaTexto.setText("Jugador"+" "+ Integer.toString(numeroJugador));
				}
			}

		});		
		
		add(panel);
		
	}

}
