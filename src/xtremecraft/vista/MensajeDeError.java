package xtremecraft.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MensajeDeError extends JFrame{
	
	JLabel mensajeDeError;
	JPanel panel = new JPanel();
	JButton btnAceptar = new JButton("Aceptar");
	
	public MensajeDeError(String mensaje){
		
		setTitle("ERROR");
		setVisible(true);
		//TODO: hacer que el texto se ajuste dinamicamente a el frame.
		setSize(500,150);
		
		mensajeDeError = new JLabel(mensaje);
			
		panel.add(mensajeDeError);
		
		btnAceptar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent click){
				
				setVisible(false);
				
			}
			
		});
		
		panel.add(btnAceptar);
		
		add(panel);
		
		
	}

}
