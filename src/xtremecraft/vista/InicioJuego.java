package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import com.sun.xml.internal.ws.api.server.Container;

@SuppressWarnings("serial")
public class InicioJuego extends JFrame{
	
	public static ArrayList<String> nombresJugadores = new ArrayList<String>();
	JPanel panel = new JPanel();
	JLabel mensaje = new JLabel("Ingrese cantidad de jugadores:");
	Integer numeroDeJugadores[] = {2,3,4};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox menu = new JComboBox(numeroDeJugadores);
	
	
	public InicioJuego(){
		
		setTitle("Cantidad de jugadores");
		setSize(250,100);
		setResizable(false);
		java.awt.Container contentPane = getContentPane();
		new BoxLayout(contentPane,BoxLayout.PAGE_AXIS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		panel.add(mensaje);		
			
		panel.add(menu);
	
		menu.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent seleccionUsuario){
				
				int elementoSeleccionado = menu.getSelectedIndex();
				int cantidadDeJugadores = numeroDeJugadores[elementoSeleccionado];
				setVisible(false);
				new IngresoNombres(nombresJugadores,cantidadDeJugadores);
				
			}
			
		});
		
		add(panel);
		
	}
	
	public static void main(String args[]){
				
		new InicioJuego();
		
	}

}
