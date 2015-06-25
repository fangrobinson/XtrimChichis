package xtremecraft.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		
		mensaje.setHorizontalTextPosition(JLabel.LEFT);
		mensaje.setVerticalTextPosition(JLabel.TOP);
		
		panel.add(Box.createRigidArea(getPreferredSize()));
		
		panel.add(mensaje);
		
		panel.add(Box.createRigidArea(getPreferredSize()));
		
		panel.add(cajaTexto);
		
		panel.add(Box.createRigidArea(getPreferredSize()));
		
		panel.add(btnEnter);
			
		btnEnter.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent click){
				
				String input = cajaTexto.getText();
				nombresJugadores.add(input);
				
				if(nombresJugadores.size() == cantidadDeJugadores){
					System.exit(0);
				}
				else{
					cajaTexto.setText("");
				}
			}

		});		
		//panel.add(label);
		add(panel);
		
	}

}
