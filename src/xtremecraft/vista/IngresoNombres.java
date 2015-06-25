package xtremecraft.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class IngresoNombres extends JFrame {
	
	JTextField cajaTexto = new JTextField(30);
	JPanel panel = new JPanel();
	//JLabel label = new JLabel("Nombre jugador:");
	JButton btnEnter = new JButton("Ingresar Jugador");

	public IngresoNombres(final ArrayList<String> nombresJugadores, final int cantidadDeJugadores){
		
		setTitle("BIENVENIDO");
		setVisible(true);
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//label.setHorizontalTextPosition(JLabel.LEFT);
		//label.setVerticalTextPosition(JLabel.TOP);
		
		//panel.add(label);
		panel.add(cajaTexto);
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
